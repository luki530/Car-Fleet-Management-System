package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.carfleetmanagementsystem.http.response.MessageResponse;
import pl.com.carfleetmanagementsystem.models.User;
import pl.com.carfleetmanagementsystem.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/listofusers")
    public ResponseEntity<List<User>> users(Model model){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

}