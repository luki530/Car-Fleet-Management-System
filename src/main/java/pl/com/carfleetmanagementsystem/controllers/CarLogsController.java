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

    @Autowired
    CarLogsRepository carLogsRepository;

    @Autowired
    CarAnalysersManager carAnalysersManager;

    @DeleteMapping("/{carLogsId}")
    public HttpStatus deleteCarLogsById(@PathVariable("carLogsId") Long carLogsId) {
        CarLog carLog = carLogsRepository.findById(carLogsId).orElseThrow(() -> new CarNotFoundException(carLogsId));
        carLogsRepository.delete(carLog);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CarLog>> findAllCarLogs(){
        List<CarLog> carLogs = carLogsRepository.findAll();
        return ResponseEntity.ok().body(carLogs);
    }

    @GetMapping("/{carLogsId}")
    public ResponseEntity<CarLog> findById(@PathVariable("carLogsId") Long carLogsId){
        CarLog carLog = carLogsRepository.findById(carLogsId).orElseThrow(() -> new CarNotFoundException(carLogsId));
        return ResponseEntity.ok().body(carLog);

    }
    @PostMapping("/create")
    public ResponseEntity<CarLog> createCarLogs(@Valid  @RequestBody CarLog carLog){
        carAnalysersManager.analyseCarLog(carLog);
        return ResponseEntity.ok().body(carLogsRepository.save(carLog));
    }

}
