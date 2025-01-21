package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CustomerServiceTest {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    public void shouldFindAllCustomersSuccessfully() {
        // Preparando dados para persistir no banco
        Addresses address = Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the park")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build();

        Customers customer1 = Customers.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .password("password123")
                .profilePhoto("profile1.jpg")
                .addresses(List.of(address))
                .build();

        Customers customer2 = Customers.builder()
                .firstname("Jane")
                .lastname("Smith")
                .email("jane.smith@example.com")
                .username("janesmith")
                .password("password123")
                .profilePhoto("profile2.jpg")
                .addresses(List.of(address))
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // Testando
        List<Customers> customers = customersService.findAll();

        assertEquals(2, customers.size());
    }

    @Test
    @Transactional
    public void shouldFindCustomerByIdSuccessfully() {
        // Preparando dados para persistir no banco
        Addresses address = Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the central park")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build();

        Customers customer = Customers.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .password("password123")
                .profilePhoto("profile.jpg")
                .addresses(List.of(address))
                .build();

        customerRepository.save(customer);

        // Testando
        Optional<Customers> foundCustomer = customersService.findById(customer.getCustomerId());

        assertEquals(true, foundCustomer.isPresent());
        assertEquals("john.doe@example.com", foundCustomer.get().getEmail());
    }

    @Test
    @Transactional
    public void shouldSaveCustomerSuccessfully() {
        // Preparando dados para persistir no banco
        Addresses address = Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the park")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build();

        Customers customerToSave = Customers.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .password("password123")
                .profilePhoto("profile.jpg")
                .addresses(List.of(address))
                .build();

        // Testando
        Customers savedCustomer = customersService.save(customerToSave);

        assertNotNull(savedCustomer);
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
        assertEquals("johndoe", savedCustomer.getUsername());
    }

    @Test
    @Transactional
    public void shouldUpdateCustomerSuccessfully() {
        // Preparando dados para persistir no banco
        List<Addresses> addressList = new ArrayList<>();
        addressList.add(Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the central park")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build());

        Customers existingCustomer = Customers.builder()
                .firstname("Existing")
                .lastname("User")
                .email("existing@example.com")
                .username("existinguser")
                .password("password123")
                .profilePhoto("existing.jpg")
                .addresses(addressList)
                .build();

        customerRepository.save(existingCustomer);

        // Criando dados para atualizar
        List<Addresses> updatedAddressList = new ArrayList<>();
        updatedAddressList.add(Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the central park updated")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build());

        Customers updatedCustomer = Customers.builder()
                .firstname("Updated")
                .lastname("User")
                .email("updated@example.com")
                .username("updateduser")
                .password("newpassword123")
                .profilePhoto("updated.jpg")
                .addresses(updatedAddressList)
                .build();

        // Testando
        Customers updatedResult = customersService.update(existingCustomer.getCustomerId(), updatedCustomer);

        assertEquals("updated@example.com", updatedResult.getEmail());
        assertEquals("updateduser", updatedResult.getUsername());
    }

    @Test
    @Transactional
    public void shouldDeleteCustomerByIdSuccessfully() {
        // Preparando dados para persistir no banco
        Addresses address = Addresses.builder()
                .country("USA")
                .state("California")
                .landmark("Near the park")
                .city("Los Angeles")
                .cep("90001")
                .neighborhood("Downtown")
                .build();

        Customers customer = Customers.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .username("johndoe")
                .password("password123")
                .profilePhoto("profile.jpg")
                .addresses(List.of(address))
                .build();

        customerRepository.save(customer);

        // Testando
        customersService.deleteById(customer.getCustomerId());

        Optional<Customers> deletedCustomer = customerRepository.findById(customer.getCustomerId());
        assertEquals(false, deletedCustomer.isPresent());
    }


}
