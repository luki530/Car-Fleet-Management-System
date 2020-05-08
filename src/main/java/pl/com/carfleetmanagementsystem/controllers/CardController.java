package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.carfleetmanagementsystem.models.Card;
import pl.com.carfleetmanagementsystem.repository.CardRepository;
import pl.com.carfleetmanagementsystem.repository.UserRepository;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addcard")
    public ResponseEntity<Card> createCar(@Valid @RequestBody Card card) {
        return ResponseEntity.ok().body(cardRepository.save(card));
    }
}
