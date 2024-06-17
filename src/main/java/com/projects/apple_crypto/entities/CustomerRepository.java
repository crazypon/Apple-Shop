package com.projects.apple_crypto.entities;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    Customer findByFirstName(String username);

}
