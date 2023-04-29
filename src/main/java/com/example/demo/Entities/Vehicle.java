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

    //todo reduce fields if too many

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    // todo get via assign reg
//    @OneToOne(fetch = FetchType.LAZY, targetEntity = LicensePlate.class, mappedBy = "vehicleDetails")
    //private LicensePlate ;

    @Column(unique = true)
    private String licensePlateNumber;

    // Vehicle Identification Number - a 17 character DVLA unique identifier for each vehicle
    @Column(unique = true)
    @Size(min=17, max=17, message = "The Vehicle Identification Number (VIN) must be 17 characters long.")
    private String VIN;

    private String make;

    private String colour;

    @PastOrPresent
    private LocalDate dateOfFirstRegistration;

//    @Past
//    private LocalDate yearOfManufacture;

//    private Double cylinderCapacity;
//
//    private Double co2Emissions;
//
//    private String fuelType;

//    private Boolean taxed;

//    private String wheelplan;


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
