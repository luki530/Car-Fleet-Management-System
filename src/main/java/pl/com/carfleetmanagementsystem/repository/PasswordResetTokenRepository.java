package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.carfleetmanagementsystem.models.PasswordResetToken;
import pl.com.carfleetmanagementsystem.models.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByPasswordResetToken(String passwordResetToken);

    void deleteByPasswordResetToken(String passwordResetToken);
}
