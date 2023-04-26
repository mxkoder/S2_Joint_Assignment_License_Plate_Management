package com.example.demo.Entitiies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LicensePlate implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer licensePlateId;

    @NotNull(message = "License plate number cannot be null")
    @Column(unique = true)
    private String licensePlateNumber;

    // DVLA assignment fee is 80
    @NotNull(message = "License plate price needs to be included.")
    private Double priceIncludingVatAndDvlaAssignmentFee;

    @NotNull(message = "License plate availability cannot be null.")
    private Boolean available;

    //TODO optional can include date associated with licence plate as a separate field
    @PastOrPresent
    private LocalDate earliestPossibleFirstRegistrationOfVehicle;

    @PastOrPresent
    private LocalDate datePurchased;


    //todo - delete attribute if not work
    // Owner attrubute will be assigned if the license plate is purchase, and will be blank otherwise
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner")
    private Customer Owner;

    public LicensePlate() {

    }

    public LicensePlate(String licensePlateNumber, Double priceIncludingVatAndDvlaAssignmentFee, Boolean available, LocalDate earliestPossibleFirstRegistrationOfVehicle, LocalDate datePurchased, Customer owner) {
        this.licensePlateNumber = licensePlateNumber;
        this.priceIncludingVatAndDvlaAssignmentFee = priceIncludingVatAndDvlaAssignmentFee;
        this.available = available;
        this.earliestPossibleFirstRegistrationOfVehicle = earliestPossibleFirstRegistrationOfVehicle;
        this.datePurchased = datePurchased;
        Owner = owner;
    }

    public Integer getLicensePlateId() {
        return licensePlateId;
    }

    public void setLicensePlateId(Integer licensePlateId) {
        this.licensePlateId = licensePlateId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Double getPriceIncludingVatAndDvlaAssignmentFee() {
        return priceIncludingVatAndDvlaAssignmentFee;
    }

    public void setPriceIncludingVatAndDvlaAssignmentFee(Double priceIncludingVatAndDvlaAssignmentFee) {
        this.priceIncludingVatAndDvlaAssignmentFee = priceIncludingVatAndDvlaAssignmentFee;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDate getEarliestPossibleFirstRegistrationOfVehicle() {
        return earliestPossibleFirstRegistrationOfVehicle;
    }

    public void setEarliestPossibleFirstRegistrationOfVehicle(LocalDate earliestPossibleFirstRegistrationOfVehicle) {
        this.earliestPossibleFirstRegistrationOfVehicle = earliestPossibleFirstRegistrationOfVehicle;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Customer getOwner() {
        return Owner;
    }

    public void setOwner(Customer owner) {
        Owner = owner;
    }
}
