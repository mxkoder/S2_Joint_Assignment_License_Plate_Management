package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String title;
    @NotEmpty(message = "Vehicle owner first name cannot be empty")
    @Size(min = 1, max = 1000, message
            = "First name must be between 1 and 1000 characters")
    private String firstName;

    @Size(min = 1, max = 1000, message
            = "Surname must be between 1 and 1000 characters")
    private String surname;

    @NotNull(message = "Vehicle owner date of birth cannot be null")
    @Past
    private LocalDate dob;
    private String address;

    private String postCode;

    @Size(min = 1, max = 15, message
            = "Phone number must be between 1 and 15 digits")
    private String phoneNumber;

    @Email
    private String email;

    @Column(unique = true)
    @NotEmpty(message = "Vehicle owner driving licence cannot be empty")
    @Size(min=16, max=16, message = "UK driving licence must 16 characters long.")
    private String drivingLicenceNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Owner")
    private List<LicensePlate> licensePlatesOwned;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Owner")
    private List<Purchase> purchases;

    public Customer() {

    }

    public Customer(String firstName, String surname, LocalDate dob, String address, String postCode, String phoneNumber, String email, String drivingLicenceNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public List<LicensePlate> getLicensePlatesOwned() {
        return licensePlatesOwned;
    }

    public void setLicensePlatesOwned(List<LicensePlate> licensePlatesOwned) {
        this.licensePlatesOwned = licensePlatesOwned;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
