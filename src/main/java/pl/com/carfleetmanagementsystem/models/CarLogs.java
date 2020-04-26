package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;
import com.google.api.gbase.client.*;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class CarLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carLogsId;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User driver;

    @OneToOne(targetEntity = Car.class, fetch = FetchType.EAGER)
    private Car car;

    @OneToOne(targetEntity = LoggerDevice.class, fetch = FetchType.EAGER)
    private LoggerDevice loggerDevice;

    private long time;

    private Double speed;

    private Double acceleration;

//    private Location gpsLocation;

    public CarLogs(User driver, Car car, LoggerDevice loggerDevice, long time, Double speed, Double acceleration) {
        this.driver = driver;
        this.car = car;
        this.loggerDevice = loggerDevice;
        this.time = time;
        this.speed = speed;
        this.acceleration = acceleration;
//        this.gpsLocation = gpsLocation;
    }

    public CarLogs() {

    }

    public Long getCarLogsId() {
        return carLogsId;
    }

    public void setCarLogsId(Long carLogsId) {
        this.carLogsId = carLogsId;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LoggerDevice getLoggerDevice() {
        return loggerDevice;
    }

    public void setLoggerDevice(LoggerDevice loggerDevice) {
        this.loggerDevice = loggerDevice;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }
//
//    public Location getGpsLocation() {
//        return gpsLocation;
//    }
//
//    public void setGpsLocation(Location gpsLocation) {
//        this.gpsLocation = gpsLocation;
//    }
}
