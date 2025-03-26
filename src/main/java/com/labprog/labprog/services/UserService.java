package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Users;
import com.labprog.labprog.model.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;
    @Autowired
    AddressesService addressesService;


    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public Users save(Users user) {
        verifyCustomer(user, true);

        if (Objects.equals(user.getRole(), "CUSTOMER")) {
            Carts cart = cartService.createCart();
            user.setCart(cart);
            cart.setUser(user);
        }

        if (user.getReviews() == null) {
            user.setReviews(new ArrayList<>());
        }

        return userRepository.save(user);
    }

    public Users update(UUID customerId, Users updatedCustomer) {

        Users existingCustomer = userRepository.findById(customerId)
                .orElseThrow(() ->  new ObjectNotFoundException("User not found!"));

//        verifyCustomer(updatedCustomer, false);
        if ((updatedCustomer.getEmail() != null && !updatedCustomer.getEmail().equals(existingCustomer.getEmail())
                && userRepository.existsByEmail(updatedCustomer.getEmail()))) {
            throw new DuplicateEmailException();
        }
        if ((updatedCustomer.getUsername() != null && !updatedCustomer.getUsername().equals(existingCustomer.getUsername())
                && userRepository.existsByUsername(updatedCustomer.getUsername()))) {
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

        return userRepository.save(existingCustomer);
    }

    public void deleteById(UUID customerId) {
        Optional<Users> customer = userRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ObjectNotFoundException("User not found!");
        }
        userRepository.deleteById(customerId);
    }

    public Users addAddress(UUID userId, Addresses addressData) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));

        Addresses address = addressesService.save(addressData);

        user.getAddresses().add(address);
        address.setUser(user);

        return userRepository.save(user);
    }

    private void verifyCustomer(Users customer, boolean isNewCustomer) {
        if (customer == null) {
            throw new ObjectNotFoundException("User not found!");
        }
        if (customer.getFirstname() == null || customer.getLastname() == null ||
            customer.getFirstname().isBlank() || customer.getLastname().isBlank()) {
            throw new InvalidNameException();
        }
        if (!isValidEmail(customer.getEmail())) {
            throw new InvalidEmailException();
        }
        if (customer.getPassword() == null || customer.getPassword().isBlank()) {
            throw new InvalidPasswordException();
        }
        if (customer.getUsername() == null || customer.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (customer.getCpf() == null || customer.getCpf().isBlank()) {
            throw new IllegalArgumentException("Cpf cannot be null or empty");
        }
        if (customer.getRole() == null || customer.getRole().isBlank()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        if (customer.getStatus() == null || customer.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        if (isNewCustomer) {
            if (userRepository.existsByEmail(customer.getEmail())) {
                throw new DuplicateEmailException();
            }
            if (userRepository.existsByUsername(customer.getUsername())) {
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
