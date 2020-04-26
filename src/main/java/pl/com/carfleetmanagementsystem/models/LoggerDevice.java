package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LoggerDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loggerDeviceId;

    private String serialNumber;

    private String simCardNumber;

    @OneToMany(targetEntity = Car.class, fetch = FetchType.EAGER)
    private Set<Car> car;

    public LoggerDevice(Long loggerDeviceId, String serialNumber, String simCardNumber) {
        this.loggerDeviceId = loggerDeviceId;
        this.serialNumber = serialNumber;
        this.simCardNumber = simCardNumber;
    }

    public LoggerDevice(){
    }

    public LoggerDevice(Long loggerDeviceId){
        this.loggerDeviceId = loggerDeviceId;
    }

    public Long getLoggerDeviceId() {
        return loggerDeviceId;
    }

    public void setLoggerDeviceId(Long loggerDeviceId) {
        this.loggerDeviceId = loggerDeviceId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }

}
