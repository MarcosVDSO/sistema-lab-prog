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
        Customers customer = new Customers(customerDTO);
        Customers savedCustomer = customersService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID uid) {
        customersService.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> customers = customersService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);

    }
    @GetMapping("/{uid}")
    public ResponseEntity<Customers> getCustomer(@PathVariable UUID uid) {
        Optional<Customers> customer = customersService.findById(uid);
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);

    }
    @PutMapping("/{uid}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable UUID uid, @RequestBody CustomerDTO customerDTO) {
        Customers customer = new Customers(customerDTO);
        Customers updatedCustomer = customersService.update(uid, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }

}
