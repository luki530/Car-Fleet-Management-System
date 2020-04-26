package pl.com.carfleetmanagementsystem.http.request;

import javax.validation.constraints.NotBlank;

public class ConfirmEmailRequest {

    @NotBlank
    private String confirmationToken;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
