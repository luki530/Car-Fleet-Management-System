package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Set;

@Entity
@Table(name = "car_logs")
public class CarLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carLogsId;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private Set<User> driver;

    @OneToMany(targetEntity = Car.class, fetch = FetchType.EAGER)
    private Set<Car> car;

    @OneToMany(targetEntity = LoggerDevice.class, fetch = FetchType.EAGER)
    private Set<LoggerDevice> loggerDevice;

    private long time;

    private Double speed;

    private Double acceleration;

    @OneToMany(targetEntity = Location.class, fetch = FetchType.EAGER)
    private Set<Location> gpsLocation;

    public CarLogs(Long carLogsId, long time, Double speed, Double acceleration) {
        this.carLogsId = carLogsId;
        this.time = time;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    public CarLogs() {

    }

    public CarLogs(Long carLogsId) {
        this.carLogsId = carLogsId;
    }

    public Long getCarLogsId() {
        return carLogsId;
    }

    public void setCarLogsId(Long carLogsId) {
        this.carLogsId = carLogsId;
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
    public Set<User> getDriver() {
        return driver;
    }

    public void setDriver(Set<User> driver) {
        this.driver = driver;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }

    public Set<LoggerDevice> getLoggerDevice() {
        return loggerDevice;
    }

    public void setLoggerDevice(Set<LoggerDevice> loggerDevice) {
        this.loggerDevice = loggerDevice;
    }

    public Set<Location> getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(Set<Location> gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
}
