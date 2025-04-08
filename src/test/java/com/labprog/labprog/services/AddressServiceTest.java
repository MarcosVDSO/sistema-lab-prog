package com.labprog.labprog.services;


import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Products;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AddressServiceTest {
    @Autowired
    private AddressesService addressesService;

    @Test
    public void shouldCreateAnAddressSuccessfully() {

        Addresses address = Addresses.builder()
                .country("Brazil")
                .state("Maranhão")
                .landmark("Perto do shopping")
                .city("São Luis")
                .cep("00000000")
                .neighborhood("Calhau")
                .street("Holandeses")
                .number("20")
                .build();

        Addresses createdAddress = addressesService.save(address);

        Assertions.assertEquals("Brazil", createdAddress.getCountry());
        Assertions.assertEquals("Maranhão", createdAddress.getState());
        Assertions.assertEquals("Perto do shopping", createdAddress.getLandmark());
        Assertions.assertEquals("São Luis", createdAddress.getCity());
        Assertions.assertEquals("00000000", createdAddress.getCep());
        Assertions.assertEquals("Calhau", createdAddress.getNeighborhood());
        Assertions.assertEquals("Holandeses", createdAddress.getStreet());

    }

    @Test
    public void shouldntCreateAnAddressIfCountryIsNull() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("   ")
                    .state("Maranhão")
                    .landmark("Perto do shopping")
                    .city("São Luis")
                    .cep("00000000")
                    .neighborhood("Calhau")
                    .street("Holandeses")
                    .build()
            );
        });

        Assertions.assertEquals("Country cannot be null or empty", exception.getMessage());

    }

    @Test
    public void shouldntCreateAnAddressIfStateIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("   ")
                    .landmark("Perto do shopping")
                    .city("São Luis")
                    .cep("00000000")
                    .neighborhood("Calhau")
                    .street("Holandeses")
                    .build()
            );
        });

        Assertions.assertEquals("State cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnAddressIfLandmarkIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("Maranhão")
                    .landmark("   ")
                    .city("São Luis")
                    .cep("00000000")
                    .neighborhood("Calhau")
                    .street("Holandeses")
                    .build()
            );
        });

        Assertions.assertEquals("Landmark cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnAddressIfCityIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("Maranhão")
                    .landmark("Perto do shopping")
                    .city("   ")
                    .cep("00000000")
                    .neighborhood("Calhau")
                    .street("Holandeses")
                    .build()
            );
        });

        Assertions.assertEquals("City cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnAddressIfCepIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("Maranhão")
                    .landmark("Perto do shopping")
                    .city("São Luis")
                    .cep("   ")
                    .neighborhood("Calhau")
                    .street("Holandeses")
                    .build()
            );
        });

        Assertions.assertEquals("CEP cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnAddressIfNeighborhoodIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("Maranhão")
                    .landmark("Perto do shopping")
                    .city("São Luis")
                    .cep("00000000")
                    .neighborhood("   ")
                    .street("Holandeses")
                    .number("20")
                    .build()
            );
        });

        Assertions.assertEquals("Neighborhood cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnAddressIfStreetIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.save(Addresses.builder()
                    .country("Brazil")
                    .state("Maranhão")
                    .landmark("Perto do shopping")
                    .city("São Luis")
                    .cep("00000000")
                    .neighborhood("Calhau")
                    .street("   ")
                    .number("20")
                    .build()
            );
        });

        Assertions.assertEquals("Street cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldFindOneAddressSuccessfully() {
        Addresses address = Addresses.builder()
                .country("Brazil")
                .state("Maranhão")
                .landmark("Perto do shopping")
                .city("São Luis")
                .cep("00000000")
                .neighborhood("Calhau")
                .street("Holandeses")
                .number("20")
                .build();

        Addresses createdAddress = addressesService.save(address);

        Addresses foundedAddress = addressesService.findById(createdAddress.getAddressId());

        Assertions.assertEquals(foundedAddress.getCountry(), createdAddress.getCountry());
        Assertions.assertEquals(foundedAddress.getState(), createdAddress.getState());
        Assertions.assertEquals(foundedAddress.getLandmark(), createdAddress.getLandmark());
        Assertions.assertEquals(foundedAddress.getCity(), createdAddress.getCity());
        Assertions.assertEquals(foundedAddress.getCep(), createdAddress.getCep());
        Assertions.assertEquals(foundedAddress.getNeighborhood(), createdAddress.getNeighborhood());
        Assertions.assertEquals(foundedAddress.getStreet(), createdAddress.getStreet());
    }

    @Test
    public void shouldReturnNothingIfAddressNotExists() {
        UUID addressId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.findById(addressId);
        });

        Assertions.assertEquals("Address not found!", exception.getMessage());
    }

    @Test
    public void shouldUpdateAddressSuccessfully() {
        Addresses address = Addresses.builder()
                .country("Brazil")
                .state("Maranhão")
                .landmark("Perto do shopping")
                .city("São Luis")
                .cep("00000000")
                .neighborhood("Calhau")
                .street("Holandeses")
                .number("20")
                .build();

        Addresses createdAddress = addressesService.save(address);

        Addresses newAddressData = Addresses.builder()
                .country("United States")
                .build();

        Addresses updatedAddress = addressesService.update(createdAddress.getAddressId(), newAddressData);

        Assertions.assertEquals("United States", updatedAddress.getCountry());

    }

    @Test
    public void shouldntUpdateIfAddressNotExists() {
        UUID addressId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.update(addressId, new Addresses());
        });

        Assertions.assertEquals("Address not found!", exception.getMessage());
    }

    @Test
    public void shouldDeleteAddressSuccessfully() {
        Addresses address = Addresses.builder()
                .country("Brazil")
                .state("Maranhão")
                .landmark("Perto do shopping")
                .city("São Luis")
                .cep("00000000")
                .neighborhood("Calhau")
                .street("Holandeses")
                .number("20")
                .build();

        Addresses createdAddress = addressesService.save(address);

        addressesService.deleteById(createdAddress.getAddressId());
    }

    @Test
    public void shouldntDeleteIfAddressNotExists() {
        UUID addressId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            addressesService.deleteById(addressId);
        });

        Assertions.assertEquals("Address not found!", exception.getMessage());
    }

}
