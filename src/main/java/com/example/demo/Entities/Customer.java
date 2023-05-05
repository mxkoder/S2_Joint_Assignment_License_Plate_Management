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

    @Size(max = 30, message = "Number of characters for 'title' exceeded.")
    private String title;
    @NotEmpty(message = "Vehicle owner first name cannot be empty")
    @Size(max = 1000, message = "First name cannot exceed 1000 characters")
    private String firstName;

    @Size(max = 1000, message = "Surname cannot exceed 1000 characters")
    private String surname;

    @Past
    private LocalDate dob;

    @Size(max = 200, message = "Number of characters for 'address' exceeded.")
    private String address;

    @Size(min = 1, max = 200, message = "Number of characters for 'postcode' exceeded.")
    @NotNull(message = "Post code cannot be left blank.")
    private String postCode;

    @Size(max = 15, message
            = "Phone number must be minimum 15 digits")
    private String phoneNumber;

    @Email
    private String email;

    //todo - dln - write exception?
    @Column(unique = true)
    @NotEmpty(message = "Vehicle owner driving licence cannot be empty")
//    @Size(min=16, max=16, message = "UK driving licence must 16 characters long.")
    private String drivingLicenseNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<LicensePlate> licensePlatesOwned;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<PaymentDetails> paymentDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Purchase> purchases;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<RegistrationAssignment> registrationAssignments;

    public Customer() {

    }

    public Customer(String title, String firstName, String surname, LocalDate dob, String address, String postCode, String phoneNumber, String email, String drivingLicenseNumber) {
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
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

    public List<RegistrationAssignment> getRegistrationAssignments() {
        return registrationAssignments;
    }

    public void setRegistrationAssignments(List<RegistrationAssignment> registrationAssignments) {
        this.registrationAssignments = registrationAssignments;
    }
}
