package com.labprog.labprog.model.repositoriesTests;

import com.labprog.labprog.model.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testCreateCustomer() {

    }
}
