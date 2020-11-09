package com.bigcart.paymentservice.bigcartpaymentservice.model;

import com.bigcart.paymentservice.bigcartpaymentservice.config.CreditCardNumberConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class VisaCard {
    @Id
    @GeneratedValue
    long id;
    @Convert(converter = CreditCardNumberConverter.class)
    private String cardNumber;
    private String cvs;
    private LocalDate expirationDate;
    private String nameOnCard;
    private long userId;
    private double balance;

    public VisaCard() {
    }
    public VisaCard(String cardNumber, String cvs, LocalDate expirationDate, String nameOnCard, long userId) {
        this.cardNumber = cardNumber;
        this.cvs = cvs;
        this.expirationDate = expirationDate;
        this.nameOnCard = nameOnCard;
        this.userId=userId;
        this.balance = 100000;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
