package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.AddressesDTO;
import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.services.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "https://system-lab-prog-front-production.up.railway.app", allowedHeaders = "*")
public class AddressesController {
    @Autowired
    AddressesService addressesService;

    @GetMapping("/{uid}")
    public ResponseEntity<Addresses> getAddress(@PathVariable UUID uid) {
        try {
            Addresses address = addressesService.findById(uid);
            return new ResponseEntity<>(address, HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Addresses>> getAllAddresses() {
        List<Addresses> admin = addressesService.findAll();
        return new ResponseEntity<>(admin, HttpStatus.OK);
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

    @PutMapping("/{uid}")
    public ResponseEntity<Addresses> updateAddress(@PathVariable UUID uid, @RequestBody AddressesDTO addressesDTO) {
        try {
            Addresses address = new Addresses(addressesDTO);
            Addresses updatedAdress = addressesService.update(uid, address);
            return new ResponseEntity<>(updatedAdress, HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{uid}")
    public ResponseEntity<Addresses> deleteAddress(@PathVariable UUID uid) {
        try {
            addressesService.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
