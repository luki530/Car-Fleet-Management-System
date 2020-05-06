package pl.com.carfleetmanagementsystem.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.http.request.*;
import pl.com.carfleetmanagementsystem.http.response.MessageResponse;
import pl.com.carfleetmanagementsystem.security.services.EmailSenderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContactController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Transactional
    @PostMapping("/contactus")
    public ResponseEntity<?> contactUs(@Valid @RequestBody ContactUsRequest contactUsRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("carfleetmanagementsystem@gmail.com");
        mailMessage.setReplyTo(contactUsRequest.getEmail());
        mailMessage.setSubject(contactUsRequest.getSubject());
        mailMessage.setFrom(contactUsRequest.getName());
        mailMessage.setText(contactUsRequest.getMessage());
        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok(new MessageResponse("Message successfully sent!"));
    }


}
