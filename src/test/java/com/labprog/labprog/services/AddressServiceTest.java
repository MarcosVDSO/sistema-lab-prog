package com.labprog.labprog.services;


import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Products;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
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
                .build();

        Addresses createdAddress = addressesService.save(address);

        Assertions.assertEquals("Brazil", createdAddress.getCountry());

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
                    .build()
            );
        });

        Assertions.assertEquals("Street cannot be null or empty", exception.getMessage());
    }

}
