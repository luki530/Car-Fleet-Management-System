package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

//google cloud jest glupi
@Entity
@Table(name = "car_logs")
public class CarLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private User driver;
    //google cloud jest glupi
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    private Long time;

    private Double speed;

    private Double acceleration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gl_id", referencedColumnName = "id")
    private Location gpsLocation;

    public CarLog(Long id, long time, Double speed, Double acceleration) {
        this.id = id;
        this.time = time;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    public CarLog() {

    }

    public CarLog(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
