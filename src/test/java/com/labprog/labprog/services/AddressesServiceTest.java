package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.ObjectNotFoundException;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AddressesServiceTest {

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AddressRepository addressRepository;

    private Addresses address;

    @BeforeEach
    void setup() {
        addressRepository.deleteAll();

        address = Addresses.builder()
                .country("Brazil")
                .state("São Paulo")
                .city("São Paulo")
                .landmark("Near the park")
                .cep("12345-678")
                .neighborhood("Downtown")
                .build();

        addressRepository.save(address);
    }

    @Test
    void testFindAll() {
        var addresses = addressesService.findAll(address.getCustomer().getCustomerId());
        assertThat(addresses).isNotEmpty();
        assertThat(addresses.get(0).getCity()).isEqualTo("São Paulo");
    }

    @Test
    void testFindById() {
        var found = addressesService.findById(address.getAddressId());
        assertThat(found).isPresent();
        assertThat(found.get().getCity()).isEqualTo("São Paulo");
    }

    @Test
    void testSave() {
        Addresses newAddress = Addresses.builder()
                .country("USA")
                .state("California")
                .city("Los Angeles")
                .landmark("Near the beach")
                .cep("90001")
                .neighborhood("Hollywood")
                .build();

        Addresses savedAddress = addressesService.save(newAddress);

        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getCity()).isEqualTo("Los Angeles");
    }

    @Test
    void testUpdate() {
        Addresses updatedAddress = Addresses.builder()
                .country("Brazil")
                .state("Rio de Janeiro")
                .city("Rio de Janeiro")
                .landmark("Near the beach")
                .cep("22222-222")
                .neighborhood("Copacabana")
                .build();

        Addresses result = addressesService.update(address.getAddressId(), updatedAddress);

        assertThat(result).isNotNull();
        assertThat(result.getCity()).isEqualTo("Rio de Janeiro");
        assertThat(result.getCep()).isEqualTo("22222-222");
    }

    @Test
    void testDeleteById() {
        addressesService.deleteById(address.getAddressId());
        Optional<Addresses> deletedAddress = addressRepository.findById(address.getAddressId());
        assertThat(deletedAddress).isEmpty();
    }

    @Test
    void testUpdateAddressNotFound() {
        UUID randomId = UUID.randomUUID();
        Addresses updatedAddress = Addresses.builder()
                .country("Brazil")
                .state("Minas Gerais")
                .city("Belo Horizonte")
                .landmark("Near the mountain")
                .cep("31300-000")
                .neighborhood("Centro")
                .build();

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            addressesService.update(randomId, updatedAddress);
        });
    }

//    @Test
//    void testSaveInvalidAddressThrowsException() {
//        Addresses invalidAddress = Addresses.builder()
//                .country(null)
//                .state("São Paulo")
//                .city("São Paulo")
//                .landmark("Near the park")
//                .cep("12345-678")
//                .neighborhood("Downtown")
//                .build();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            addressesService.save(invalidAddress);
//        });
//    }
}
