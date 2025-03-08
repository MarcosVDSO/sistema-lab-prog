package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class CustomersService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartService cartService;


    public List<Customers> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customers> findById(UUID id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customers save(Customers customer) {
        verifyCustomer(customer, true);

        Carts cart = cartService.createCart();
        customer.setCart(cart);
        cart.setCustomer(customer);

        return customerRepository.save(customer);
    }

    public Customers update(UUID customerId, Customers updatedCustomer) {

        Customers existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() ->  new ObjectNotFoundException("Customer not found"));

//        verifyCustomer(updatedCustomer, false);
        if ((updatedCustomer.getEmail() != null && !updatedCustomer.getEmail().equals(existingCustomer.getEmail())
                && customerRepository.existsByEmail(updatedCustomer.getEmail()))) {
            throw new DuplicateEmailException();
        }
        if ((updatedCustomer.getUsername() != null && !updatedCustomer.getUsername().equals(existingCustomer.getUsername())
                && customerRepository.existsByUsername(updatedCustomer.getUsername()))) {
            throw new DuplicateUserNameException();
        }

        if (updatedCustomer.getFirstname() != null) {
            existingCustomer.setFirstname(updatedCustomer.getFirstname());
        }

        if (updatedCustomer.getLastname() != null) {
            existingCustomer.setLastname(updatedCustomer.getLastname());
        }

        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }

        if (updatedCustomer.getPassword() != null) {
            existingCustomer.setPassword(updatedCustomer.getPassword());
        }

        if (updatedCustomer.getCpf() != null) {
            existingCustomer.setPassword(updatedCustomer.getCpf());
        }

        return customerRepository.save(existingCustomer);


    }


    public void deleteById(UUID customerId) {
            Optional<Customers> customer = customerRepository.findById(customerId);
            if (customer.isEmpty()) {
                throw new ObjectNotFoundException("Customer not found");
            }
            customerRepository.deleteById(customerId);
        }

    private void verifyCustomer(Customers customer, boolean isNewCustomer) {
        if (customer == null) {
            throw new ObjectNotFoundException("Customer not found");
        }
        if (customer.getFirstname() == null || customer.getLastname() == null) {
            throw new InvalidNameException();
        }
        if (!isValidEmail(customer.getEmail())) {
            throw new InvalidEmailException();
        }
        if (customer.getPassword() == null) {
            throw new InvalidPasswordException();
        }
        if (customer.getUsername() == null) {
            throw new InvalidPasswordException();
        }

        if (isNewCustomer) {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new DuplicateEmailException();
            }
            if (customerRepository.existsByUsername(customer.getUsername())) {
                throw new DuplicateUserNameException();
            }
        }
    }

    private boolean isValidEmail( String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(email).matches();
    }



}
