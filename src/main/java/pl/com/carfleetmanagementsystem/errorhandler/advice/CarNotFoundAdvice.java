package pl.com.carfleetmanagementsystem.errorhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.carfleetmanagementsystem.errorhandler.exception.CarNotFoundException;

@ControllerAdvice
public class CarNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CarNotFoundHandler(CarNotFoundException e) {
        return e.getMessage();
    }
}
