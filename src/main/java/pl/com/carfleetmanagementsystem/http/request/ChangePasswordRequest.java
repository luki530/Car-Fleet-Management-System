package pl.com.carfleetmanagementsystem.http.request;

import pl.com.carfleetmanagementsystem.models.PasswordResetToken;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {

    @NotBlank
    private String passwordResetToken;

    @NotBlank
    private String newPassword;

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
