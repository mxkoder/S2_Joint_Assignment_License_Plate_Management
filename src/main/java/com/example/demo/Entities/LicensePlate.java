package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LicensePlate implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer licensePlateId;

    @NotNull(message = "License plate number cannot be null")
    @Column(unique = true)
    @Size(min = 1, max = 8, message = "License plates must be between 1 and 8 characters long.")
    private String licensePlateNumber;

    /**
     * The price of a licence fee includes a DVLA assignment fee of £80
     * <p>As shown in https://dvlaregistrations.dvla.gov.uk/ , the £80 DVLA assignment fee is applied at the point of purchase of the license plate and
     * is included in the license plate price</p>
     */
    @NotNull(message = "License plate price needs to be included.")
    @Min(value = 0, message = "License plate price should not be less than 0.")
    @Max(value = 5000000, message = "License plate price should not be less than £5,000,000.")
    private Double priceIncludingVatAndDvlaAssignmentFee;

    @NotNull(message = "License plate availability cannot be null.")
    private Boolean available;

    @PastOrPresent
    private LocalDate earliestPossibleFirstRegistrationOfVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner")
    private Customer owner;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Purchase.class)
    private Purchase purchaseDetails;

    public LicensePlate() {

    }

    public LicensePlate(String licensePlateNumber, Double priceIncludingVatAndDvlaAssignmentFee, Boolean available, LocalDate earliestPossibleFirstRegistrationOfVehicle, Customer owner, Purchase purchaseDetails) {
        this.licensePlateNumber = licensePlateNumber;
        this.priceIncludingVatAndDvlaAssignmentFee = priceIncludingVatAndDvlaAssignmentFee;
        this.available = available;
        this.earliestPossibleFirstRegistrationOfVehicle = earliestPossibleFirstRegistrationOfVehicle;
        this.owner = owner;
        this.purchaseDetails = purchaseDetails;
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

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Purchase getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Purchase purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
