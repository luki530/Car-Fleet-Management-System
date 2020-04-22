package pl.com.carfleetmanagementsystem.models;

import javax.validation.constraints.NotBlank;

public class ConfirmPhoneNumberRequest {

    @NotBlank
    private String confirmationCode;

    @NotBlank
    private String username;

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
