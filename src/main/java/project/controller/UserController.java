package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dao.UserDao;
import project.user.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userDao.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer userId){

        User user = userDao.findOne(userId);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer userId,@Valid @RequestBody User userDetails){

        User user = userDao.findOne(userId);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone_number(userDetails.getPhone_number());
        user.setUsername(userDetails.getUsername());

        User updateUser = userDao.save(user);

        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Integer userId){
        User user = userDao.findOne(userId);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userDao.delete(user);

        return ResponseEntity.ok().build();
    }

}
