package pl.com.carfleetmanagementsystem.errorhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.carfleetmanagementsystem.errorhandler.exception.LocationNotFound;

@ControllerAdvice
public class LocationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LocationNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String LocationNotFoundHandler(LocationNotFound e) {
        return e.getMessage();
    }

}
