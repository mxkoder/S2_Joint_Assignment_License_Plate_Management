package com.example.demo.Entities;

import com.example.demo.Exceptions.CardNumberLengthNot16Digits;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentDetails implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentDetailsId;

    @Size(min= 0, max= 20, message = "Payment type cannot be longer than 20 characters")
    private String paymentType;

    @Size(min= 0, max= 20, message = "Payment type cannot be longer than 1000 characters")
    @NotEmpty(message = "Cardholder name cannot be left blank.")
    private String cardHolderName;

    //todo -  remove or find out why not working
    @Size(min = 16, max = 16, message = "Card number must be between 12 and 30 digits long, with no spaces.")
    private String cardNumber;

    @Future(message = "The card expiration date must be in the future.")
    private LocalDate expirationDate;

    private Boolean paymentMethodIsValid;

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

        //todo write method / way to set is valid
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

    public void setCardNumber (String cardNumber) throws CardNumberLengthNot16Digits {
        if(cardNumber.length() == 16) {
            String editedCardNumber = cardNumber.substring(12, cardNumber.length());
            this.cardNumber = editedCardNumber;
        }
        else {
            throw new CardNumberLengthNot16Digits("Card number should be 16 digits long");
        }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    @NotNull(message= "A customer must be entered for valid payment details.")
    private Customer customer;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
