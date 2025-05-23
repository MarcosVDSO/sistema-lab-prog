package com.labprog.labprog.model.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.labprog.labprog.model.entities.Addresses;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressRepositoryTest {
    @Autowired
    AddressRepository repository;

    @Test
    public void testCreateAddress() {

        Addresses address = Addresses.builder()
                .country("Brazil")
                .state("Maranhão")
                .landmark("Perto da praça")
                .city("São Luis")
                .cep("123")
                .neighborhood("Lira")
                .street("Pedro Lessa")
                .number("10")
                .build();

        Addresses save = repository.save(address);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(address.getCountry(), save.getCountry());
        Assertions.assertEquals(address.getState(), save.getState());
        Assertions.assertEquals(address.getLandmark(), save.getLandmark());
        Assertions.assertEquals(address.getCity(), save.getCity());
        Assertions.assertEquals(address.getCep(), save.getCep());
        Assertions.assertEquals(address.getNeighborhood(), save.getNeighborhood());
        Assertions.assertEquals(address.getStreet(), save.getStreet());

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
