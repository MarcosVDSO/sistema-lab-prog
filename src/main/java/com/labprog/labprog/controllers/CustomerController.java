package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.CustomerDTO;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.services.CustomersService;
//import com.labprog.labprog.services.utils.PasswordEncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomersService customersService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping
    public ResponseEntity<Customers> addCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customers customer = new Customers(customerDTO);
            Customers savedCustomer = customersService.save(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Customers> deleteCustomer(@PathVariable UUID uid) {
        try {
            customersService.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        try {
            List<Customers> customers = customersService.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Customers> getCustomer(@PathVariable UUID uid) {
        try {
            Optional<Customers> customer = customersService.findById(uid);
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{uid}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable UUID uid, @RequestBody CustomerDTO customerDTO) {
        try {
            Customers customer = new Customers(customerDTO);
            Customers updatedCustomer = customersService.update(uid, customer);
            logger.info("passou pelo servico: ");
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
