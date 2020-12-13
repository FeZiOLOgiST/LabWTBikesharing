package by.epam.bikesharing.entity;

import by.epam.bikesharing.service.PasswordHash;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class User implements BaseEntity {

    private long id;
    private long bikeId;
    private String email;
    private String login;
    private String role;
    private BigDecimal balance;
    private PasswordHash passwordHash;
    private String image;
    private String locale;

    public User(){}

    public User(User copy) {
        this.id = copy.id;
        this.bikeId = copy.bikeId;
        this.email = copy.email;
        this.login = copy.login;
        this.role = copy.role;
        this.balance = copy.balance;
        this.passwordHash = copy.passwordHash;
        this.image = copy.image;
        this.locale = copy.locale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getBikeId() {
        return bikeId;
    }

    public void setBikeId(long bikeId) {
        this.bikeId = bikeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("bikeId=" + bikeId)
                .add("email='" + email + "'")
                .add("login='" + login + "'")
                .add("role='" + role + "'")
                .add("balance=" + balance)
                .add("locale=" + locale)
                .toString();
    }
}