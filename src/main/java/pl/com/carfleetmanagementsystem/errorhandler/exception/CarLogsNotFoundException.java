package pl.com.carfleetmanagementsystem.errorhandler.exception;

public class CarLogsNotFoundException extends RuntimeException {

    public CarLogsNotFoundException (Long id) {
        super("Could not find car logs: " + id);
    }

}
