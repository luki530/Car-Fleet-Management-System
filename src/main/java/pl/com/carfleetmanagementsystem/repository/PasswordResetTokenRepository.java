package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.carfleetmanagementsystem.models.ConfirmationToken;
import pl.com.carfleetmanagementsystem.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByPasswordResetToken(String passwordResetToken);
}
