package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.errorhandler.exception.CarNotFoundException;
import pl.com.carfleetmanagementsystem.errorhandler.exception.LoggerDeviceNotFoundException;
import pl.com.carfleetmanagementsystem.errorhandler.exception.UserNotFoundException;
import pl.com.carfleetmanagementsystem.http.request.AddCardRequest;
import pl.com.carfleetmanagementsystem.http.request.AssignLoggerDeviceRequest;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.Card;
import pl.com.carfleetmanagementsystem.models.LoggerDevice;
import pl.com.carfleetmanagementsystem.models.User;
import pl.com.carfleetmanagementsystem.repository.CarRepository;
import pl.com.carfleetmanagementsystem.repository.LoggerDeviceRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LoggerDeviceRepository loggerDeviceRepository;

    @DeleteMapping("/listofcars/{id}")
    public HttpStatus deleteCarById(@PathVariable("id") Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/listofcars")
    public ResponseEntity<List<Car>> findAllCars(){
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/listofcars/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") Long id){
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return ResponseEntity.ok().body(car);

    }

    @PostMapping("/listofcars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car){
        return ResponseEntity.ok().body(carRepository.save(car));
    }

    @PutMapping("/listofcars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @Valid @RequestBody Car car){
        car.setId(id);
        Optional<Car> carDb = this.carRepository.findById(car.getId());
        Car carUpdate = carDb.get();
        carUpdate.setModel(car.getModel());
        carUpdate.setPlateNumber(car.getPlateNumber());
        carUpdate.setBlocked(car.isBlocked());
        return ResponseEntity.ok().body(carRepository.save(carUpdate));
    }

    @PutMapping("/assignloggerdevice")
    public ResponseEntity<?> assignLoggerDevice(@Valid @RequestBody AssignLoggerDeviceRequest assignLoggerDeviceRequest) {
        Car car = carRepository.findById(assignLoggerDeviceRequest.getCarId()).orElseThrow(() -> new CarNotFoundException(assignLoggerDeviceRequest.getCarId()));
        LoggerDevice loggerDevice = loggerDeviceRepository.findBySerialNumber(assignLoggerDeviceRequest.getSerialNumber()).orElseThrow(() -> new LoggerDeviceNotFoundException(assignLoggerDeviceRequest.getSerialNumber()));
        loggerDevice.setCar(car);
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDevice));
    }

}
