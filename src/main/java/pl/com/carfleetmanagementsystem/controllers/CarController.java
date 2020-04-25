package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("carId") Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException());
        carRepository.delete(car);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping("/listofcars")
    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('BOSS') or hasRole('ADMIN')")
    public ResponseEntity<List<Car>> findAllCars(){
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> findById(Long carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(car);

    }
    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return ResponseEntity.ok().body(carRepository.save(car));
    }

    @PutMapping("/cars")
    public ResponseEntity<Car> updateCar(Car car){
        Optional<Car> carId = this.carRepository.findById(car.getCarId());
        Car carUpdate = carId.get();
        carUpdate.setCarId(car.getCarId());
        carUpdate.setModel(car.getModel());
        carUpdate.setPlateNumber(car.getPlateNumber());
        carUpdate.setBlocked(car.isBlocked());
        carRepository.save(carUpdate);
        return ResponseEntity.ok().body(carUpdate);
    }


}
