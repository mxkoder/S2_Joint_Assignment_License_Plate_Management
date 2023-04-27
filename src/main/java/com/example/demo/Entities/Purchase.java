package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Purchase implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = LicensePlate.class, mappedBy = "purchaseDetails")
    private LicensePlate licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

    //todo - or - attach payment option to customer?
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = LicensePlate.class)
    @JoinColumn(name = "paymentDetails")
    //todo - may be not null?
    private PaymentDetails paymentDetails;

    private Boolean purchaseIsCompleted;

    private LocalDate purchaseDate;

    private Double amountPaid;

    public Purchase() {

    }


    public Purchase(LicensePlate licensePlate, Customer customer, PaymentDetails paymentDetails, Boolean purchaseIsCompleted, LocalDate purchaseDate, Double amountPaid) {
        this.licensePlate = licensePlate;
        this.customer = customer;
        this.paymentDetails = paymentDetails;
        this.purchaseIsCompleted = purchaseIsCompleted;
        this.purchaseDate = purchaseDate;
        this.amountPaid = amountPaid;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Boolean getPurchaseIsCompleted() {
        return purchaseIsCompleted;
    }

    public void setPurchaseIsCompleted(Boolean purchaseIsCompleted) {
        this.purchaseIsCompleted = purchaseIsCompleted;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
