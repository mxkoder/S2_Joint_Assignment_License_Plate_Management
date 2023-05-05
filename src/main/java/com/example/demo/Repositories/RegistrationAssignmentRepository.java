package com.example.demo.Repositories;

import com.example.demo.Entities.RegistrationAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "registrationassignment", path = "registrationassignment")
@CrossOrigin(allowedHeaders="*")
public interface RegistrationAssignmentRepository extends CrudRepository<RegistrationAssignment, Integer> {
}
