package pl.com.carfleetmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "car")
    private LoggerDevice loggerDevice;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private Set<CarLog> carLogs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private Set<CarError> carError;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private Set<Notification> notification;

    private String model;

    private String plateNumber;

    private boolean isBlocked;

    public Car(String model, String plateNumber, boolean isBlocked) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.isBlocked = isBlocked;
    }

    public Car(Long id) {
        this.id = id;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateModel) {
        this.plateNumber = plateModel;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Set<CarLog> getCarLogs() {
        return carLogs;
    }

    public void setCarLogs(Set<CarLog> carLogs) {
        this.carLogs = carLogs;
    }

    public void setCarError(Set<CarError> carError) {
        this.carError = carError;
    }

    public Set<CarError> getCarError() {
        return carError;
    }

    public Set<Notification> getNotification() {
        return notification;
    }

    public void setNotification(Set<Notification> notification) {
        this.notification = notification;
    }

    public LoggerDevice getLoggerDevice() {
        return loggerDevice;
    }

    public void setLoggerDevice(LoggerDevice loggerDevice) {
        this.loggerDevice = loggerDevice;
    }
}
