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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cl_id", referencedColumnName = "id")
    private User driver;

    @OneToOne(mappedBy = "carLogs")
    private Car car;

    private long time;

    private Double speed;

    private Double acceleration;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "gl_id", referencedColumnName = "locationId")
    private Location gpsLocation;

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
    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Location getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(Location gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
}
