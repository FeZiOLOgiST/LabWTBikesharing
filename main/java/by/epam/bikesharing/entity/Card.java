package by.epam.bikesharing.entity;

import by.epam.bikesharing.service.PasswordHash;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Card implements BaseEntity {

    private long id;
    private long userId;
    private String number;
    private String firstName;
    private String LastName;
    private int month;
    private int year;
    private PasswordHash cvv;
    private BigDecimal balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PasswordHash getCvv() {
        return cvv;
    }

    public void setCvv(PasswordHash cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("number='" + number + "'")
                .add("firstName='" + firstName + "'")
                .add("LastName='" + LastName + "'")
                .add("month=" + month)
                .add("year=" + year)
                .add("balance=" + balance)
                .toString();
    }
}