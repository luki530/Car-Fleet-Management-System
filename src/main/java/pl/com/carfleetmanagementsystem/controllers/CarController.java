package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.errorhandler.exception.CarNotFoundException;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.repository.CarRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @DeleteMapping("/listofcars/{carId}")
    public HttpStatus deleteCarById(@PathVariable("carId") Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        carRepository.delete(car);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/listofcars")
    public ResponseEntity<List<Car>> findAllCars(){
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/listofcars/{carId}")
    public ResponseEntity<Car> findById(@PathVariable("carId") Long carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        return ResponseEntity.ok().body(car);

    }
    @PostMapping("/listofcars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car){
        return ResponseEntity.ok().body(carRepository.save(car));
    }

    @PutMapping("/listofcars/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable("carId") Long carId, @Valid @RequestBody Car car){
        car.setCarId(carId);
        Optional<Car> carDb = this.carRepository.findById(car.getCarId());
        Car carUpdate = carDb.get();
        carUpdate.setModel(car.getModel());
        carUpdate.setPlateNumber(car.getPlateNumber());
        carUpdate.setBlocked(car.isBlocked());
        return ResponseEntity.ok().body(carRepository.save(carUpdate));
    }
}
