package pl.com.carfleetmanagementsystem.http.request;

public class AssignLoggerDeviceRequest {

    private Long carId;

    private String serialNumber;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
