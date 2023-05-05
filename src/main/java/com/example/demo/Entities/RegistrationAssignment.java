package com.example.demo.Entities;

import com.example.demo.Exceptions.LicensePlateNotCompatibleWithVehicleException;
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

    @OneToOne(fetch = FetchType.LAZY, targetEntity = LicensePlate.class)
    @NotNull(message="License Plate cannot be left empty")
    private LicensePlate licensePlate;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Vehicle.class)
    @NotNull(message="Vehicle the licence plate is to be assigned to cannot be left empty")
    private Vehicle vehicle;

    /**
     * V5C registration certificate (logbook) document reference number
     * <p>As explained in https://www.personalisedvehicleregistration.service.gov.uk/assign/vehicle-lookup , an 11 digit V5C registration certificate refrence is needed
     * assigning a registration number to a vehicle</p>
     */
//    @NotNull(message = "A V5C logbook document reference number must be supplied to assign a registration number to a vehicle")
    @Size(min=11, max=11, message = "The V5C logbook document reference number must be 11 digits long.")
    private String v5cLogbookReferenceNumber;

    private LocalDate dateLicenseAssignedToVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    @NotNull(message= "A customer must be entered to assign a registration plate to a vehicle.")
    private Customer customer;

    public RegistrationAssignment() {

    }

    public RegistrationAssignment(Integer registrationAssignmentId, LicensePlate licensePlate, Vehicle vehicle, String v5cLogbookReferenceNumber, LocalDate dateLicenseAssignedToVehicle, Customer customer) {
        this.registrationAssignmentId = registrationAssignmentId;
        this.licensePlate = licensePlate;
        this.vehicle = vehicle;
        this.v5cLogbookReferenceNumber = v5cLogbookReferenceNumber;
        this.dateLicenseAssignedToVehicle = dateLicenseAssignedToVehicle;
        this.customer = customer;
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

    //todo flesh out comment
    /**
     * Setting a Vehicle will assign the registration number of the license plate to the vehicle
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) throws LicensePlateNotCompatibleWithVehicleException {


        //todo add note
        //todo - refactor line to make clearer?
        if(vehicle.getDateOfFirstRegistration().isAfter(this.licensePlate.getEarliestPossibleFirstRegistrationOfVehicle())) {
            if(this.customer.equals(this.licensePlate.getOwner())) {
                this.vehicle = vehicle;
                this.vehicle.setLicensePlateNumber(this.licensePlate.getLicensePlateNumber());
            }
            else {
                throw new LicensePlateNotCompatibleWithVehicleException("Error: The customer needs to own the license plate in order to be able to assign it to a vehicle.");
            }

        }
        else {
            throw new LicensePlateNotCompatibleWithVehicleException("Error: the date of First Registration of the vehicle needs to be after the earliest possible registration date of the License Plate.");
        }

        // todo delete and go with above, just a test
//        if(this.customer.equals(this.licensePlate.getOwner())) {
//            this.vehicle = vehicle;
//            this.vehicle.setLicensePlateNumber(this.licensePlate.getLicensePlateNumber());
//        }
//        else {
//            throw new LicensePlateNotCompatibleWithVehicleException("Error: The customer needs to own the license plate in order to be able to assign it to a vehicle.");
//        }


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
