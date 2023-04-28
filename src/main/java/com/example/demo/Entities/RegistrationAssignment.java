package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegistrationAssignment implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registrationAssignmentId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = LicensePlate.class, mappedBy = "vehicleAssignment")
    @NotNull(message="License Plate cannot be left empty")
    private LicensePlate licensePlate;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class, mappedBy = "vehicleAssignment")
    @NotNull(message="Vehicle the licence plate is to be assigned to cannot be left empty")
    private Vehicle vehicle;

    /**
     * V5C registration certificate (logbook) document reference number
     * <p>As explained in https://www.personalisedvehicleregistration.service.gov.uk/assign/vehicle-lookup , an 11 digit V5C registration certificate refrence is needed
     * assigning a registration number to a vehicle</p>
     */
    @NotNull(message = "A V5C logbook document reference number must be supplied to assign a registration number to a vehicle")
    @Size(min=11, max=11, message = "The V5C logbook document reference number must be 11 digits long.")
    private String v5cLogbookReferenceNumber;

    private LocalDate dateLicenseAssignedToVehicle;

    public RegistrationAssignment() {

    }

    public RegistrationAssignment(Integer registrationAssignmentId, LicensePlate licensePlate, Vehicle vehicle, String v5cLogbookReferenceNumber, LocalDate dateLicenseAssignedToVehicle) {
        this.registrationAssignmentId = registrationAssignmentId;
        this.licensePlate = licensePlate;
        this.vehicle = vehicle;
        this.v5cLogbookReferenceNumber = v5cLogbookReferenceNumber;
        this.dateLicenseAssignedToVehicle = dateLicenseAssignedToVehicle;
    }

    public Integer getRegistrationAssignmentId() {
        return registrationAssignmentId;
    }

    public void setRegistrationAssignmentId(Integer registrationAssignmentId) {
        this.registrationAssignmentId = registrationAssignmentId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getV5cLogbookReferenceNumber() {
        return v5cLogbookReferenceNumber;
    }

    public void setV5cLogbookReferenceNumber(String v5cLogbookReferenceNumber) {
        this.v5cLogbookReferenceNumber = v5cLogbookReferenceNumber;
    }

    public LocalDate getDateLicenseAssignedToVehicle() {
        return dateLicenseAssignedToVehicle;
    }

    public void setDateLicenseAssignedToVehicle(LocalDate dateLicenseAssignedToVehicle) {
        this.dateLicenseAssignedToVehicle = dateLicenseAssignedToVehicle;
    }
}
