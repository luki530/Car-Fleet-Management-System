package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLogs;
import pl.com.carfleetmanagementsystem.repository.CarLogsRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarLogsController {

    @Autowired
    CarLogsRepository carLogsRepository;

    @DeleteMapping("/carlogs/{carLogsId}")
    public HttpStatus deleteCarLogsById(@PathVariable("carLogsId") Long carLogsId) {
        CarLogs carLogs = carLogsRepository.findById(carLogsId).orElseThrow(() -> new IllegalArgumentException());
        carLogsRepository.delete(carLogs);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/carlogs")
    public ResponseEntity<List<CarLogs>> findAllCarLogs(){
        List<CarLogs> carLogs = carLogsRepository.findAll();
        return ResponseEntity.ok().body(carLogs);
    }

    @GetMapping("/carlogs/{carLogsId}")
    public ResponseEntity<CarLogs> findById(@PathVariable("carLogsId") Long carLogsId){
        CarLogs carLogs = carLogsRepository.findById(carLogsId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(carLogs);

    }
    @PostMapping("/carlogs")
    public ResponseEntity<CarLogs> createCarLogs(@Valid  @RequestBody CarLogs carLogs){
        return ResponseEntity.ok().body(carLogsRepository.save(carLogs));
    }

}
