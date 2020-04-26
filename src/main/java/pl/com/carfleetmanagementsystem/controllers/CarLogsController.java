package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLogs;
import pl.com.carfleetmanagementsystem.repository.CarLogsRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarLogsController {

    @Autowired
    CarLogsRepository carLogsRepository;

    @DeleteMapping("/cars/{carId}/carLogs/{carLogsId}")
    public ResponseEntity<?> deleteCarLogsById(@PathVariable("carLogsId") Long carLogsId) {
        CarLogs carLogs = carLogsRepository.findById(carLogsId).orElseThrow(() -> new IllegalArgumentException());
        carLogsRepository.delete(carLogs);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}/carLogs")
    public ResponseEntity<List<CarLogs>> findAllCarLogs(){
        List<CarLogs> carLogs = carLogsRepository.findAll();
        return ResponseEntity.ok().body(carLogs);
    }

    @GetMapping("/cars/{carId}/carLogs/{carLogsId}")
    public ResponseEntity<CarLogs> findById(@PathVariable("carLogsId") Long carLogsId){
        CarLogs carLogs = carLogsRepository.findById(carLogsId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(carLogs);

    }
    @PostMapping("/cars/{carId}/carLogs")
    public ResponseEntity<CarLogs> createCarLogs(@RequestBody CarLogs carLogs){
        return ResponseEntity.ok().body(carLogsRepository.save(carLogs));
    }

    @PutMapping("/cars")
    public ResponseEntity<CarLogs> updateCarLogs(@RequestBody CarLogs carLogs){
        Optional<CarLogs> carLogsId = this.carLogsRepository.findById(carLogs.getCarLogsId());
        CarLogs carLogsUpdate = carLogsId.get();
        carLogsUpdate.setAcceleration(carLogs.getAcceleration());
        carLogsUpdate.setCar(carLogs.getCar());
        carLogsUpdate.setDriver(carLogs.getDriver());
        carLogsUpdate.setLoggerDevice(carLogs.getLoggerDevice());
        carLogsUpdate.setTime(carLogs.getTime());
        carLogsUpdate.setSpeed(carLogs.getSpeed());
        carLogsUpdate.setCarLogsId(carLogs.getCarLogsId());
        return ResponseEntity.ok().body(carLogsRepository.save(carLogsUpdate));
    }

}
