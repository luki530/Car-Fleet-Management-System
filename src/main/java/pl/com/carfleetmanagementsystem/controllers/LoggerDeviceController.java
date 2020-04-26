package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.models.LoggerDevice;
import pl.com.carfleetmanagementsystem.repository.LoggerDeviceRepository;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoggerDeviceController {

    @Autowired
    LoggerDeviceRepository loggerDeviceRepository;

    @DeleteMapping("/{loggerDeviceId}")
    public ResponseEntity<?> deleteLoggerDeviceById(@PathVariable("loggerDeviceId") Long loggerDeviceId) {
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(loggerDeviceId).orElseThrow(() -> new IllegalArgumentException());
        loggerDeviceRepository.delete(loggerDevice);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping("/listofloggerdevices")
    public ResponseEntity<List<LoggerDevice>> findAllLoggerDevices(){
        List<LoggerDevice> loggerDevices = loggerDeviceRepository.findAll();
        return ResponseEntity.ok().body(loggerDevices);
    }

    @GetMapping("/loggerdevice/{loggerDeviceId}")
    public ResponseEntity<LoggerDevice> findById(@PathVariable("loggerDeviceId") Long loggerDeviceId){
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(loggerDeviceId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(loggerDevice);

    }
    @PostMapping("/listofloggerdevices")
    public ResponseEntity<LoggerDevice> createLoggerDevice(@RequestBody LoggerDevice loggerDevice){
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDevice));
    }

    @PutMapping("/listofloggerdevices")
    public ResponseEntity<LoggerDevice> updateLoggerDevice(@RequestBody LoggerDevice loggerDevice){
        Optional<LoggerDevice> loggerDeviceId = this.loggerDeviceRepository.findById(loggerDevice.getLoggerDeviceId());
        LoggerDevice loggerDeviceUpdate = loggerDeviceId.get();
        loggerDeviceUpdate.setCar(loggerDevice.getCar());
        loggerDeviceUpdate.setLoggerDeviceId(loggerDevice.getLoggerDeviceId());
        loggerDevice.setSerialNumber(loggerDevice.getSerialNumber());
        loggerDevice.setSimCardNumber(loggerDevice.getSimCardNumber());
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDeviceUpdate));
    }



}
