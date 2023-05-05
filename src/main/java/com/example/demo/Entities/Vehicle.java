package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    @Column(unique = true)
    private String licensePlateNumber;

    // Vehicle Identification Number - a 17 character DVLA unique identifier for each vehicle
    @Column(unique = true)
    @Size(min=17, max=17, message = "The Vehicle Identification Number (VIN) must be 17 characters long.")
    private String VIN;

    @Size(min=1, max=30, message = "Please enter the make of the vehicle.")
    private String make;

    @Size(min=1, max=30, message = "Please enter the colour of the vehicle.")
    private String colour;

    @PastOrPresent
    private LocalDate dateOfFirstRegistration;

    public Vehicle () {

    }

    public Vehicle(String licensePlateNumber, String VIN, String make, String colour, LocalDate dateOfFirstRegistration) {
        this.licensePlateNumber = licensePlateNumber;
        this.VIN = VIN;
        this.make = make;
        this.colour = colour;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN.toUpperCase();
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public LocalDate getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(LocalDate dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }
}
