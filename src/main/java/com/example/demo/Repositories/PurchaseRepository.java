package com.example.demo.Repositories;

import com.example.demo.Entities.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "purchase", path = "purchase")
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
