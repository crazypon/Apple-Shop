package com.projects.apple_crypto.entities;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<AppleCryptoOrder, Long> {
    
}
