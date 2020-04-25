package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

@Entity
public class LoggerDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loggerDeviceId;

    private String serialNumber;

    private String simCardNumber;

    @OneToOne(targetEntity = Car.class, fetch = FetchType.EAGER)
    private Car car;

    public LoggerDevice(long loggerDeviceId, String serialNumber, String simCardNumber, Car car) {
        this.loggerDeviceId = loggerDeviceId;
        this.serialNumber = serialNumber;
        this.simCardNumber = simCardNumber;
        this.car = car;
    }
    public LoggerDevice(){
    }

    public long getLoggerDeviceId() {
        return loggerDeviceId;
    }

    public void setLoggerDeviceId(long loggerDeviceId) {
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
