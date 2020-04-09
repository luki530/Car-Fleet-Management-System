package pl.com.carfleetmanagementsystem.security.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {

    void sendEmail(SimpleMailMessage email);

}
