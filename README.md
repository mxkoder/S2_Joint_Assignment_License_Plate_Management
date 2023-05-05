# Web Service for a License Plate Management Application

## About
This is a web service to support a customer facing application to search, buy and assign license plates to vehicles

## Dependencies
The project uses the Spring Boot framework with Data JPA, linking to postgresql database
Hibernate Validator] (https://hibernate.org/validator/documentation/getting-started/) has been added as a dependency to provide constraints and validation for data inputs.

## Running the project
The project runs from:
src/main/java/com/example/demo/DemoApplication.java

You may need to run 'maven clean install' before running the program.

Before running the application, please create a postgress database named 'LicensePlateManagement'
To setup the database connection, please edit line 1 in src/main/resources/application.properties with the link to your postgres database.
Edit the username and password on lines 2 and 3 in application.properties as need to connect to your database.

## Managing the database contents

To run project with a mock license plate data, please run the file named 'license_plate_INSERT_SCRIPT.sql' from the zip file on the LicensePlateManagement database.
In order to keep any data inserted into the database, for subsequent program runs please comment out:

        spring.jpa.hibernate.ddl-auto=create    
On line 17 in application.properties.


## Calling the web service, posting records and search
A Postman collection is provided in the .zip file:

    S2_License_Plate_Management_POSTMAN_COLECTION.postman_collection.json

The collection contains get, post, and search endpoints with sample body data for the key operations of the web service.

To stay within foreign key constraints when posting new sets of records, please add them in the following order:
    1. Post license plate
    2. Post customer
    3. Post payment details
    4. Purchase
    5. Post vehicle
    6. Assign registration

The key search endpoints for license plates is provided in the last set of requests in the Postman collection
The search endpoint used in the client to filter license plates by search term partial match and availability is:

    http://localhost:8080/licenseplate/search/findByLicensePlateNumberContainingIgnoreCaseAndAvailable{?lpn,av}

The logic to implement this is located in the license plate CRUD repository located here:
    
    src/main/java/com/example/demo/Repositories/LicensePlateRepository.java

## Overview of file structure and implementation

The program file structure is as follows:

    src/main/java/com/example/demo/Repositories

The Repositories directory contains the Crud Repositories which act as Rest Resources. 
The license plate repository has search implementation as above.

    src/main/java/com/example/demo/Entities

This repository contains the application entities, which relate postgresql generated tables.
The entity attributes have constraint and validation applied using Hibernate Validator. 
The setter methods in entities have application logic as needed to ensure database integrity and valid transactions.

Core application logic implemented in entities:

### PaymentDetails

In:

    setCardNumber

An exception is thrown if the card number is not 16 digits long, invalidating the post request.

The @Future bean on expirationDate also enforces that expiration dates need to be in the future. 


### Purchase

In:
    
    setPurchaseIsCompleted
An exception will be thrown if the requirements for a purchase of a license plate are not met, invaliding the post request. 
A purhcase can only be made if:
- the payment method is valid
- the license plate being bought is available

If those conditions are met, the following updates will be made:
- the purchase will be marked as completed (purchaseIsCompleted = true)
- The owner attribute in the license plate entity will be set to the customer making the purchase
- The avialability of the license plate will be set to false, so it no longer shows up in searches for other customer.

This attempts to make the transaction atomic and consistent, however an issue with this approach is that the constraints could be violated if there were concurrent transactions.

In:

    setAmountPaid

If a custom price is entered in the 'amountPaid' field, amountPaid will be set to this value.
There is the option to enter '-1' as a value in amountPaid, which will set amountPaid to the price detais in the record of the license plate being bought.

### RegistrationAssignment

In:

    setVehicle

The setVehicle method hs checks in place to ensure that the assignment of the license plate to the vehicle is valid. 

One check is that the customer who is attempting to assign the license plate is also the owner of the license plate 
(this is a moot point in the current UI implementation which has a single flow from search > purchase > assign, however it is more relevant if customers have the option to come back later to assign the license plate after purchase).

Another check is to make sure that the date of first registration of the vehicle is after the earliest possible date of first registration associated with the license plate.
This mimics how the assignment is dealt with on the [dvlaregistrations](https://dvlaregistrations.dvla.gov.uk/buy.html?plate=AH04%20KBC&price=499) website, where they show a warning before assignment.  

If these requirements are met:
- the vehicle will be set in the RegistrationAssignment record
- the license plate number of the vehicle record will be updated to the license plate number being assigned to the vehicle.

As with the 'transaction' in Purchase, there could be issues with this approach if there were concurrent read-writes to the database via the web service.

## Future improvements
There are several areas to improve, including security, testing, OAuth and improved cors handling, dockerising the service, and adding DTOs to provide views to access in the client.
In addition:
- extracting the date of earliest first registration from the license plate number
- add logging so that there is a record of the requests implemented through the web service
- filtering inappropriate license plate numbers 
- incorporating license plate types other than the UK standard implemented in 2001.
- adding checks in the setLicensePlate method to ensure that a license plate meets the format requirements.
- Implement pagination of the license plate search results in the client and web service.
- Improved compatability between direct inserts into the database and post requests via the web service. 
(Currently the auto generated ids in the web service don't take into account any records inserted via insert scripts directly into the database,
which would cause conflicts.)

## Author

Agnes Beviz
