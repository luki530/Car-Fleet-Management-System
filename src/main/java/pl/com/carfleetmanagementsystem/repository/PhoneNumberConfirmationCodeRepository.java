package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.carfleetmanagementsystem.models.PhoneNumberConfirmationCode;

public interface PhoneNumberConfirmationCodeRepository extends JpaRepository<PhoneNumberConfirmationCode, Long> {

    PhoneNumberConfirmationCode findByConfirmationCode(String confirmationCode);

    void deleteByConfirmationCode(String confirmationCode);
}
