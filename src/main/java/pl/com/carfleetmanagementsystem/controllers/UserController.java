package pl.com.carfleetmanagementsystem.controllers;

import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.errorhandler.exception.CarNotFoundException;
import pl.com.carfleetmanagementsystem.errorhandler.exception.UserNotFoundException;
import pl.com.carfleetmanagementsystem.http.response.MessageResponse;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.User;
import pl.com.carfleetmanagementsystem.repository.UserRepository;
import pl.com.carfleetmanagementsystem.security.jwt.JwtUtils;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/listofusers")
    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('BOSS') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> users(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/myprofile")
    public ResponseEntity<User> user(@RequestHeader(name="Authorization") String token){
        token=token.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/userprofile")
    public ResponseEntity<User> id(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/userprofile/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return HttpStatus.FORBIDDEN;
    }

}
