package com.labprog.labprog.model.repositories;

import java.util.UUID;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    
}
