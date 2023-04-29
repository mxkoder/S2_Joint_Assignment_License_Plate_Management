package com.example.demo.Repositories;

import com.example.demo.Entities.LicensePlate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "licenseplate", path = "licenseplate")
public interface LicensePlateRepository extends CrudRepository<LicensePlate, Integer> {

    List<LicensePlate> findByLicensePlateNumberIgnoreCase (@Param("lpn") String licensePlateNumber);

    List<LicensePlate> findByLicensePlateNumberContainingIgnoreCase (@Param("lpn") String licensePlateNumberPartial);

    /**
     * MAIN LICENSE PLATE SEARCH - search for license plate numbers and filtering by availability
     * <p>This is the main search to be used in the UI to find partial matches for licence plate number search terms.
     * If the parameter 'av' is set to true it will filter search results showing only license plates which are available. Ignores case in license plate number.</p>
     * @param licensePlateNumberPartial - A string of the license plate number, or partial license plate number search term
     * @param available - Boolean - setting this to true will return only available license plates
     * @return - returns a list of license plates matching the search terms.
     */
    List<LicensePlate> findByLicensePlateNumberContainingIgnoreCaseAndAvailable (@Param("lpn") String licensePlateNumberPartial, @Param("av") Boolean available);

    //todo add search for partial license plate number
    //todo - need to get partial parameter from UI, user input
//    @Query("select l from LicensePlate l where l.licensePlateNumber like '%[user input]%'")
//    List<LicensePlate> findLicensePlatePartialMatch();
}
