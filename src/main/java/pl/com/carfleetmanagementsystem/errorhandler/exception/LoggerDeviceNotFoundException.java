package pl.com.carfleetmanagementsystem.errorhandler.exception;

public class LoggerDeviceNotFoundException extends RuntimeException {

    public LoggerDeviceNotFoundException (Long id) {
        super("Could not find logger device: " + id);
    }
}
