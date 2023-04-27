package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentDetails implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentDetailsId;

    private String paymentType;

    private String cardHolderName;

    private String cardNumber;

    private LocalDate expirationDate;

    private Boolean paymentMethodIsValid;


    //todo - in set card number - chop last 4 digits and only store those!
    // have card number & last 4 dig of card num as sep fields

    // purchase has customer, so don't need to separately add customer
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentDetails")
    private List<Purchase> purchases;

    public PaymentDetails() {

    }

    public PaymentDetails(Integer paymentDetailsId, String paymentType, String cardHolderName, String cardNumber, LocalDate expirationDate, Boolean paymentMethodIsValid) {
        this.paymentDetailsId = paymentDetailsId;
        this.paymentType = paymentType;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.paymentMethodIsValid = paymentMethodIsValid;
    }

    public Integer getPaymentDetailsId() {
        return paymentDetailsId;
    }

    public void setPaymentDetailsId(Integer paymentDetailsId) {
        this.paymentDetailsId = paymentDetailsId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getPaymentMethodIsValid() {
        return paymentMethodIsValid;
    }

    public void setPaymentMethodIsValid(Boolean paymentMethodIsValid) {
        this.paymentMethodIsValid = paymentMethodIsValid;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
