package com.example.demo.Repositories;

import com.example.demo.Entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "vehicle", path = "vehicle")
@CrossOrigin(allowedHeaders="*")
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}
