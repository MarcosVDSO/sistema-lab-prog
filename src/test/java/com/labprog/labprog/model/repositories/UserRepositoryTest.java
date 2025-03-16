package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Users;
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
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    public void testCreateCustomer() {
        Users customer = Users.builder().addresses(new ArrayList<Addresses>())
                .firstname("Kleiton")
                .lastname("Arruda")
                .username("sdadasdasda")
                .password("123")
                .cpf("123")
                .role("ADMIN")
//                .profilePhoto("6131")
                .email("masadasdas").build();

        Users save = repository.save(customer);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(customer.getAddresses(), save.getAddresses());
        Assertions.assertEquals(customer.getFirstname(), save.getFirstname());
        Assertions.assertEquals(customer.getLastname(), save.getLastname());
        Assertions.assertEquals(customer.getUsername(), save.getUsername());
        Assertions.assertEquals(customer.getPassword(), save.getPassword());
//        Assertions.assertEquals(customer.getProfilePhoto(), save.getProfilePhoto());
        Assertions.assertEquals(customer.getEmail(), save.getEmail());
        Assertions.assertEquals(customer.getCpf(), save.getCpf());
        Assertions.assertEquals(customer.getRole(), save.getRole());
    }
}
