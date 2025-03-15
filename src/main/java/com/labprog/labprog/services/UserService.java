package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.Users;
import com.labprog.labprog.model.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;


    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findById(UUID id) {
        return userRepository.findById(id);
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

        return userRepository.save(user);
    }

    public Users update(UUID customerId, Users updatedCustomer) {

        Users existingCustomer = userRepository.findById(customerId)
                .orElseThrow(() ->  new ObjectNotFoundException("Customer not found"));

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
                throw new ObjectNotFoundException("Customer not found");
            }
            userRepository.deleteById(customerId);
        }

    private void verifyCustomer(Users customer, boolean isNewCustomer) {
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
