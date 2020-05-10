package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.analyse.CarAnalysersManager;
import pl.com.carfleetmanagementsystem.errorhandler.exception.CarNotFoundException;
import pl.com.carfleetmanagementsystem.models.CarLog;
import pl.com.carfleetmanagementsystem.repository.CarLogsRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/carlog")
public class CarLogsController {
//hagiographa
    @Autowired
    CarLogsRepository carLogsRepository;

    @Autowired
    CarAnalysersManager carAnalysersManager;

    @DeleteMapping("/{id}")
    public HttpStatus deleteCarLogsById(@PathVariable("id") Long id) {
        CarLog carLog = carLogsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        carLogsRepository.delete(carLog);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/find/{carId}/{start}/{end}")
    public ResponseEntity<List<CarLog>> find(@PathVariable("carId") Long id, @PathVariable("start") Long start, @PathVariable("end") Long end) {
        return ResponseEntity.ok().body(carLogsRepository.findByCar_IdAndTimeBetween(id, start, end));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CarLog>> findAllCarLogs() {
        List<CarLog> carLogs = carLogsRepository.findAll();
        return ResponseEntity.ok().body(carLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarLog> findById(@PathVariable("id") Long id) {
        CarLog carLog = carLogsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return ResponseEntity.ok().body(carLog);

    }

    @PostMapping("/create")
    public ResponseEntity<CarLog> createCarLogs(@Valid @RequestBody CarLog carLog) {
        carAnalysersManager.analyseCarLog(carLog);
        return ResponseEntity.ok().body(carLogsRepository.save(carLog));
    }

}
