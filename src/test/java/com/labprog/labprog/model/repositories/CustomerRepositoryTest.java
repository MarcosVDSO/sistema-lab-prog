package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Customers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testCreateCustomer() {
        Customers customer = Customers.builder().addresses(new ArrayList<Addresses>())
                .firstname("Kleiton")
                .lastname("Arruda")
                .username("sdadasdasda")
                .password("123")
                .profilePhoto("6131")
                .email("masadasdas").build();

        Customers save = repository.save(customer);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(customer.getAddresses(), save.getAddresses());
        Assertions.assertEquals(customer.getFirstname(), save.getFirstname());
        Assertions.assertEquals(customer.getLastname(), save.getLastname());
        Assertions.assertEquals(customer.getUsername(), save.getUsername());
        Assertions.assertEquals(customer.getPassword(), save.getPassword());
        Assertions.assertEquals(customer.getProfilePhoto(), save.getProfilePhoto());
        Assertions.assertEquals(customer.getEmail(), save.getEmail());
    }
}
