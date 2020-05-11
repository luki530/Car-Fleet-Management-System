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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import pl.com.carfleetmanagementsystem.errorhandler.exception.RoleNotFoundException;
import pl.com.carfleetmanagementsystem.http.request.*;
import pl.com.carfleetmanagementsystem.models.*;
import pl.com.carfleetmanagementsystem.http.response.JwtResponse;
import pl.com.carfleetmanagementsystem.http.response.MessageResponse;
import pl.com.carfleetmanagementsystem.repository.*;
import pl.com.carfleetmanagementsystem.security.jwt.JwtUtils;
import pl.com.carfleetmanagementsystem.security.services.EmailSenderService;
import pl.com.carfleetmanagementsystem.security.services.UserDetailsImpl;
import pl.digitalvirgo.justsend.api.client.services.impl.Constants;
import pl.digitalvirgo.justsend.api.client.services.impl.MessageServiceImpl;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;
import pl.digitalvirgo.justsend.api.client.services.impl.services.MessageService;


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
    CardRepository cardRepository;

    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PhoneNumberConfirmationCodeRepository phoneNumberConfirmationCodeRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private MessageService messageService = new MessageServiceImpl("JDJhJDEyJGRxWGlveW1Rd05xMzB0YVJhLjBpVHV6aFQ4a21JY3l6SEF4M0ZxTnZjLmFRcVVKVi9PbFhh");

    @Transactional
    @PostMapping("/cardlogin")
    public ResponseEntity<?> authenticateUsersCardId(@Valid @RequestBody CardLoginRequest cardLoginRequest) {

        Card card = cardRepository.findByCardId(cardLoginRequest.getCardId()).get();
        User user = card.getUser();

        Authentication authentication = new UsernamePasswordAuthenticationToken(UserDetailsImpl.build(user), null);

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

    @Transactional
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
                userDetails.getPhoneNumber(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            User user = userRepository.findByUsername(signUpRequest.getUsername()).get();
            if (!(user.isEmailConfirmed() && user.isPhoneNumberConfirmed())) {
                userRepository.delete(user);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            User user = userRepository.findByEmailIgnoreCase(signUpRequest.getEmail()).get();
            if (!(user.isEmailConfirmed() && user.isPhoneNumberConfirmed())) {
                userRepository.delete(user);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }
        }


        String regexp = "(\\+48|0)[0-9]{9}";

        if (!signUpRequest.getPhoneNumber().matches(regexp)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Wrong phone number!"));
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords are not the same!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_NEW)
                .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_NEW.toString()));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
        emailConfirmationTokenRepository.save(emailConfirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("carfleetmanagementsystem@gmail.com");
        mailMessage.setText("To confirm your email, please click here : "
                + "https://www.carfleetmanagementsystem.pl/confirmemail?token=" + emailConfirmationToken.getConfirmationToken());
        emailSenderService.sendEmail(mailMessage);
        user.setEmailConfirmationToken(emailConfirmationToken);

        Constants.JUSTSEND_API_URL = "https://justsend.pl/api/rest";
        PhoneNumberConfirmationCode phoneNumberConfirmationCode = new PhoneNumberConfirmationCode();
        
        phoneNumberConfirmationCodeRepository.save(phoneNumberConfirmationCode);
        messageService.sendMessage(user.getPhoneNumber(), "CFMS", "Your phone confirmation code: " + phoneNumberConfirmationCode.getConfirmationCode(), BulkVariant.PRO);
        user.setPhoneNumberConfirmationCode(phoneNumberConfirmationCode);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Transactional
    @RequestMapping(value = "/confirm-email", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserEmail(@Valid @RequestBody ConfirmEmailRequest confirmEmailRequest) {
        String confirmationToken = confirmEmailRequest.getConfirmationToken();
        EmailConfirmationToken token = emailConfirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail()).get();
            user.setEmailConfirmed(true);
            userRepository.save(user);
            emailConfirmationTokenRepository.deleteByConfirmationToken(confirmationToken);
            return ResponseEntity.ok(new MessageResponse("Account verified!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
        }

    }

    @Transactional
    @RequestMapping(value = "/confirm-phone-number", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserPhoneNumber(@Valid @RequestBody ConfirmPhoneNumberRequest confirmPhoneNumberRequest) {

        String confirmationCode = confirmPhoneNumberRequest.getConfirmationCode();

        PhoneNumberConfirmationCode code = phoneNumberConfirmationCodeRepository.findByConfirmationCode(confirmationCode);

        if (code != null && code.getUser().getUsername().equals(confirmPhoneNumberRequest.getUsername())) {
            User user = userRepository.findByEmailIgnoreCase(code.getUser().getEmail()).get();
            user.setPhoneNumberConfirmed(true);
            userRepository.save(user);
            phoneNumberConfirmationCodeRepository.deleteByConfirmationCode(confirmationCode);
            return ResponseEntity.ok(new MessageResponse("Phone number verified!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
        }
    }

    @Transactional
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        if (userRepository.existsByUsername(resetPasswordRequest.getUsername())) {

            User user = userRepository.findByUsername(resetPasswordRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException(resetPasswordRequest.getUsername()));

            PasswordResetToken passwordResetToken = new PasswordResetToken(user);

            passwordResetTokenRepository.save(passwordResetToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Reset your password!");
            mailMessage.setFrom("carfleetmanagementsystem@gmail.com");
            mailMessage.setText("To reset your password, please click here : "
                    + "https://www.carfleetmanagementsystem.pl/changepassword?token=" + passwordResetToken.getPasswordResetToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new MessageResponse("Email for password reset has been sent to your email! "));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
    }

    @Transactional
    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        PasswordResetToken token = passwordResetTokenRepository.findByPasswordResetToken(changePasswordRequest.getToken());

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords are not the same!"));
        }

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
