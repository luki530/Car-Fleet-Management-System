package pl.com.carfleetmanagementsystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String model;

    private String plateNumber;

    private boolean isBlocked;

    public Car(long carId, String model, String plateNumber, boolean isBlocked) {
        this.carId = carId;
        this.model = model;
        this.plateNumber = plateNumber;
        this.isBlocked = isBlocked;
    }
    public Car() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        carId = carId;
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
}
