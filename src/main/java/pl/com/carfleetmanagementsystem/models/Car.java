package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cl_id", referencedColumnName = "carLogsId")
    private CarLog carLog;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ld_id", referencedColumnName = "loggerDeviceId")
    private LoggerDevice loggerDevice;

    private String model;

    private String plateNumber;

    private boolean isBlocked;

    public Car(Long carId, String model, String plateNumber, boolean isBlocked) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.isBlocked = isBlocked;
        this.carId = carId;
    }

    public Car(Long carId){
        this.carId = carId;
    }

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public CarLog getCarLog() {
        return carLog;
    }

    public void setCarLog(CarLog carLog) {
        this.carLog = carLog;
    }

    public LoggerDevice getLoggerDevice() {
        return loggerDevice;
    }

    public void setLoggerDevice(LoggerDevice loggerDevice) {
        this.loggerDevice = loggerDevice;
    }
}
