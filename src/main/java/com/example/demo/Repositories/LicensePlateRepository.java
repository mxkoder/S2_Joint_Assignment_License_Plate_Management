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

    //todo add search for partial license plate number
    //todo - need to get partial parameter from UI, user input
//    @Query("select l from LicensePlate l where l.licensePlateNumber like '%[user input]%'")
//    List<LicensePlate> findLicensePlatePartialMatch();
}
