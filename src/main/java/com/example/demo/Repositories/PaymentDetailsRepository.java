package com.example.demo.Repositories;

import com.example.demo.Entities.PaymentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "paymentdetails", path = "paymentdetails")
public interface PaymentDetailsRepository extends CrudRepository<PaymentDetails, Integer> {
}
