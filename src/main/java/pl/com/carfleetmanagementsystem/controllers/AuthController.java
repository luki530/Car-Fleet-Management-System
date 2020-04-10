package pl.com.carfleetmanagementsystem.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import pl.com.carfleetmanagementsystem.http.request.ChangePasswordRequest;
import pl.com.carfleetmanagementsystem.http.request.ResetPasswordRequest;
import pl.com.carfleetmanagementsystem.models.*;
import pl.com.carfleetmanagementsystem.http.request.LoginRequest;
import pl.com.carfleetmanagementsystem.http.request.SignupRequest;
import pl.com.carfleetmanagementsystem.http.response.JwtResponse;
import pl.com.carfleetmanagementsystem.http.response.MessageResponse;
import pl.com.carfleetmanagementsystem.repository.ConfirmationTokenRepository;
import pl.com.carfleetmanagementsystem.repository.PasswordResetTokenRepository;
import pl.com.carfleetmanagementsystem.repository.RoleRepository;
import pl.com.carfleetmanagementsystem.repository.UserRepository;
import pl.com.carfleetmanagementsystem.security.jwt.JwtUtils;
import pl.com.carfleetmanagementsystem.security.services.EmailSenderService;
import pl.com.carfleetmanagementsystem.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getPhoneNumber(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String regexp = "(\\+48|0)[0-9]{9}";

        if(!signUpRequest.getPhoneNumber().matches(regexp)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Wrong phone number!"));
        }

        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords are not the same!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()),
                false);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_NEW)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "employee":
                        Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(employeeRole);

                        break;
                    case "boss":
                        Role bossRole = roleRepository.findByName(ERole.ROLE_BOSS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(bossRole);

                        break;
                    case "driver":
                        Role driverRole = roleRepository.findByName(ERole.ROLE_DRIVER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(driverRole);

                        break;
                    default:
                        Role newRole = roleRepository.findByName(ERole.ROLE_NEW)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(newRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("carfleetmanagementsystem@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail()).get();
            user.setEnabled(true);
            userRepository.save(user);
            confirmationTokenRepository.deleteByConfirmationToken(confirmationToken);
            return ResponseEntity.ok(new MessageResponse("Account verified!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
        }

    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        if (userRepository.existsByUsername(resetPasswordRequest.getUsername())) {

            User user = userRepository.findByUsername(resetPasswordRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found !"));

            PasswordResetToken passwordResetToken = new PasswordResetToken(user);

            passwordResetTokenRepository.save(passwordResetToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Reset your password!");
            mailMessage.setFrom("carfleetmanagementsystem@gmail.com");
            mailMessage.setText("To reset your password, please click here : "
                    + "http://localhost:8080/auth/change-password?token=" + passwordResetToken.getPasswordResetToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new MessageResponse("Email for password reset has been sent to your email! "));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        PasswordResetToken token = passwordResetTokenRepository.findByPasswordResetToken(changePasswordRequest.getPasswordResetToken());

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail()).get();
            user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
            passwordResetTokenRepository.delete(token);
            return ResponseEntity.ok(new MessageResponse("Password changed!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
        }
    }
}
