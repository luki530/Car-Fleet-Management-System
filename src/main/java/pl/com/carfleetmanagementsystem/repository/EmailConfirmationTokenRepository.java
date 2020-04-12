package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.carfleetmanagementsystem.models.EmailConfirmationToken;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken, Long> {
    EmailConfirmationToken findByConfirmationToken(String confirmationToken);

    void deleteByConfirmationToken(String confirmationToken);
}
