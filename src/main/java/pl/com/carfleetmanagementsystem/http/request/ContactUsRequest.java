package pl.com.carfleetmanagementsystem.http.request;

import javax.validation.constraints.NotBlank;

public class ContactUsRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

    private boolean copy;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean getCopy(){
        return copy;
    }
}
