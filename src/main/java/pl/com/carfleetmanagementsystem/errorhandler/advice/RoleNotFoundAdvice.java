package pl.com.carfleetmanagementsystem.errorhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.carfleetmanagementsystem.errorhandler.exception.RoleNotFoundException;
import pl.com.carfleetmanagementsystem.errorhandler.exception.UserNotFoundException;

@ControllerAdvice
public class RoleNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String RoleNotFoundHandler(RoleNotFoundException e) {
        return e.getMessage();
    }
}
