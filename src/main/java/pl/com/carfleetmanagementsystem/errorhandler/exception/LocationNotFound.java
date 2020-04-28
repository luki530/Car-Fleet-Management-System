package pl.com.carfleetmanagementsystem.errorhandler.exception;

public class LocationNotFound extends RuntimeException {

    public LocationNotFound (Long id){
        super("Could not find location: " + id);
    }
}
