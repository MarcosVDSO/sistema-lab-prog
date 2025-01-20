package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.AddressesDTO;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.services.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AdressesController {
    @Autowired
    AddressesService addressesService;

//    @GetMapping("/{uid}")
//    public ResponseEntity<List<Addresses>> get(@PathVariable UUID uid) {
//        try {
//            List<Addresses> Addresses = addressesService.getAdressesByCustomerId(uid);
//            return new ResponseEntity<>(Addresses, HttpStatus.OK);
//        }
//        catch (RuntimeException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
    @GetMapping("/{uid}")
    public ResponseEntity<Addresses> getAdress(@PathVariable UUID uid) {
        try {
            Optional<Addresses> address = addressesService.findById(uid);
            return new ResponseEntity<>(address.get(), HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Addresses> addAddress(@RequestBody AddressesDTO addressDTO) {
        try {
            Addresses address = new Addresses(addressDTO);
            Addresses savedAddress = addressesService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
