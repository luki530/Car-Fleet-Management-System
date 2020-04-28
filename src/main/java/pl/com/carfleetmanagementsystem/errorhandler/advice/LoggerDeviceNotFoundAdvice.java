package pl.com.carfleetmanagementsystem.errorhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.com.carfleetmanagementsystem.errorhandler.exception.LoggerDeviceNotFoundException;

@ControllerAdvice
public class LoggerDeviceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LoggerDeviceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String LoggerDeviceNotFoundHandler(LoggerDeviceNotFoundException e) {
        return e.getMessage();
    }


}
