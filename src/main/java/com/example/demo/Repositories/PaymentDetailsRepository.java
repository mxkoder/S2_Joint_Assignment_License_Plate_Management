package com.example.demo.Repositories;

import com.example.demo.Entities.PaymentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "paymentdetails", path = "paymentdetails")
@CrossOrigin(allowedHeaders="*")
public interface PaymentDetailsRepository extends CrudRepository<PaymentDetails, Integer> {
}
