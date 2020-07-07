package pl.com.carfleetmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 12)
    private String phoneNumber;

    @NotBlank
    @Size(max = 20)
    private String name;

    private boolean emailConfirmed = false;

    private boolean phoneNumberConfirmed = false;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private PasswordResetToken passwordResetToken;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private EmailConfirmationToken emailConfirmationToken;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private PhoneNumberConfirmationCode phoneNumberConfirmationCode;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id", referencedColumnName = "id")
    private Card card;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "driver")
    private Set<CarLog> carLog;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "driver")
    private Set<Notification> notification;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, String username, String email, String phoneNumber, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public EmailConfirmationToken getEmailConfirmationToken() {
        return emailConfirmationToken;
    }

    public void setEmailConfirmationToken(EmailConfirmationToken emailConfirmationToken) {
        this.emailConfirmationToken = emailConfirmationToken;
    }

    public PhoneNumberConfirmationCode getPhoneNumberConfirmationCode() {
        return phoneNumberConfirmationCode;
    }

    public void setPhoneNumberConfirmationCode(PhoneNumberConfirmationCode phoneNumberConfirmationCode) {
        this.phoneNumberConfirmationCode = phoneNumberConfirmationCode;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Set<CarLog> getCarLog() {
        return carLog;
    }

    public void setCarLog(Set<CarLog> carLog) {
        this.carLog = carLog;
    }

    public Set<Notification> getNotification() {
        return notification;
    }

    public void setNotification(Set<Notification> notification) {
        this.notification = notification;
    }
}
