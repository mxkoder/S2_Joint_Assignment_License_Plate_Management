package com.example.demo.Repositories;

import com.example.demo.Entities.RegistrationAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "registrationassignment", path = "registrationassignment")
public interface RegistrationAssignmentRepository extends CrudRepository<RegistrationAssignment, Integer> {
}
