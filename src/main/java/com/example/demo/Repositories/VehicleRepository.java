package com.example.demo.Repositories;

import com.example.demo.Entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vehicle", path = "vehicle")
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}
