package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

@Entity
public class LoggerDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    private String simCardNumber;

    @OneToOne(mappedBy = "loggerDevice")
    private Car car;

    public LoggerDevice(Long id, String serialNumber, String simCardNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.simCardNumber = simCardNumber;
    }

    public LoggerDevice(){
    }

    public LoggerDevice(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
