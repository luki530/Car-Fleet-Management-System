package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

@Entity
public class CarError {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Car car;

    private String errorCode;

    private String errorMessage;

    public CarError() {
    }

    public CarError(Long id, String errorCode, String errorMessage){
        this.id = id;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
