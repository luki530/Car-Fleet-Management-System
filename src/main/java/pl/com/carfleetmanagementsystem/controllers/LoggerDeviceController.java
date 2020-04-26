package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.models.LoggerDevice;
import pl.com.carfleetmanagementsystem.repository.LoggerDeviceRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoggerDeviceController {

    @Autowired
    LoggerDeviceRepository loggerDeviceRepository;

    @DeleteMapping("/listofloggerdevices/{loggerDeviceId}")
    public HttpStatus deleteLoggerDeviceById(@PathVariable("loggerDeviceId") Long loggerDeviceId) {
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(loggerDeviceId).orElseThrow(() -> new IllegalArgumentException());
        loggerDeviceRepository.delete(loggerDevice);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/listofloggerdevices")
    public ResponseEntity<List<LoggerDevice>> findAllLoggerDevices(){
        List<LoggerDevice> loggerDevices = loggerDeviceRepository.findAll();
        return ResponseEntity.ok().body(loggerDevices);
    }

    @GetMapping("/listofloggerdevices/{loggerDeviceId}")
    public ResponseEntity<LoggerDevice> findById(@PathVariable("loggerDeviceId") Long loggerDeviceId){
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(loggerDeviceId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(loggerDevice);

    }
    @PostMapping("/listofloggerdevices")
    public ResponseEntity<LoggerDevice> createLoggerDevice(@Valid @RequestBody LoggerDevice loggerDevice){
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDevice));
    }

    @PutMapping("/listofloggerdevices/{loggerDeviceId}")
    public ResponseEntity<LoggerDevice> updateLoggerDevice(@PathVariable("loggerDeviceId") Long loggerDeviceId, @Valid @RequestBody LoggerDevice loggerDevice){
        loggerDevice.setLoggerDeviceId(loggerDeviceId);
        Optional<LoggerDevice> loggerDeviceDb = this.loggerDeviceRepository.findById(loggerDevice.getLoggerDeviceId());
        LoggerDevice loggerDeviceUpdate = loggerDeviceDb.get();
        loggerDeviceUpdate.setCar(loggerDevice.getCar());
        loggerDeviceUpdate.setSerialNumber(loggerDevice.getSerialNumber());
        loggerDeviceUpdate.setSimCardNumber(loggerDevice.getSimCardNumber());
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDeviceUpdate));
    }

}
