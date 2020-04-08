package pl.com.company.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.company.auth.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}