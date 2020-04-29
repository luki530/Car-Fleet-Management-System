package pl.com.carfleetmanagementsystem.errorhandler.exception;

import pl.com.carfleetmanagementsystem.models.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username){
        super("Could not find user: " + username);
    }

    public UserNotFoundException(Long id){
        super("Could not find user: " + id);
    }
}
