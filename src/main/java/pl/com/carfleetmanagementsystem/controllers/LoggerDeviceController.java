package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.errorhandler.exception.LoggerDeviceNotFoundException;
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

    @DeleteMapping("/listofloggerdevices/{id}")
    public HttpStatus deleteLoggerDeviceById(@PathVariable("id") Long id) {
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(id).orElseThrow(() -> new LoggerDeviceNotFoundException(id));
        loggerDeviceRepository.delete(loggerDevice);
        return HttpStatus.FORBIDDEN;
    }

    @GetMapping("/listofloggerdevices")
    public ResponseEntity<List<LoggerDevice>> findAllLoggerDevices(){
        List<LoggerDevice> loggerDevices = loggerDeviceRepository.findAll();
        return ResponseEntity.ok().body(loggerDevices);
    }

    @GetMapping("/listofloggerdevices/{id}")
    public ResponseEntity<LoggerDevice> findById(@PathVariable("id") Long id){
        LoggerDevice loggerDevice = loggerDeviceRepository.findById(id).orElseThrow(() -> new LoggerDeviceNotFoundException(id));
        return ResponseEntity.ok().body(loggerDevice);

    }
    @PostMapping("/listofloggerdevices")
    public ResponseEntity<LoggerDevice> createLoggerDevice(@Valid @RequestBody LoggerDevice loggerDevice){
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDevice));
    }

    @PutMapping("/listofloggerdevices/{id}")
    public ResponseEntity<LoggerDevice> updateLoggerDevice(@PathVariable("id") Long id, @Valid @RequestBody LoggerDevice loggerDevice){
        loggerDevice.setId(id);
        Optional<LoggerDevice> loggerDeviceDb = this.loggerDeviceRepository.findById(loggerDevice.getId());
        LoggerDevice loggerDeviceUpdate = loggerDeviceDb.get();
        loggerDeviceUpdate.setSerialNumber(loggerDevice.getSerialNumber());
        loggerDeviceUpdate.setSimCardNumber(loggerDevice.getSimCardNumber());
        return ResponseEntity.ok().body(loggerDeviceRepository.save(loggerDeviceUpdate));
    }

}
