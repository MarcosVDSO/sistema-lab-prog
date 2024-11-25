package com.labprog.labprog.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.labprog.labprog.model.entities.Addresses;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AddressRepositoryTest {
    @Autowired
    AddressRepository repository;

    @Test
    public void testCreateAddress() {
    
    }

    @Test
    public void testFindOneAddress() {

    }

    @Test
    public void testFindAllAddresses() {

    }

    @Test
    public void testUpdateAddress() {

    }

    @Test
    public void testDeleteAddress() {

    }

    @Test
    public void testDeleteManyAddresses() {

    }
}
