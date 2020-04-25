package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.com.carfleetmanagementsystem.models.CarLogs;
import pl.com.carfleetmanagementsystem.repository.CarLogsRepository;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarLogsController {

    @Autowired
    CarLogsRepository carLogsRepository;

    @DeleteMapping("/cars/{carId}/carLogs")
    public ResponseEntity<?> deleteCarLogsById(@PathVariable("carId") Long carId) {
        CarLogs carLogs = carLogsRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException());
        carLogsRepository.delete(carLogs);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

}
