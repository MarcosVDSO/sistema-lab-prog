package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.AdminDTO;
import com.labprog.labprog.DTO.EmployeesDTO;
import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminServices adminService;

    @PostMapping
    public ResponseEntity<Admins> addAdmin(@RequestBody AdminDTO adminDTO) {
        try {
            Admins admin = new Admins(adminDTO);
            Admins savedAdmin = adminService.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Admins> deleteAdmin(@PathVariable UUID uid) {
        try {
            adminService.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Admins>> getAllAdmin() {
        try {
            List<Admins> admin = adminService.findAll();
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Admins> getAdmin(@PathVariable UUID uid) {
        try {
            Optional<Admins> admin = adminService.findById(uid);
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{uid}")
    public ResponseEntity<Admins> updateAdmin(@PathVariable UUID uid, @RequestBody AdminDTO adminDTO) {
        try {
            Admins admin = new Admins(adminDTO);
            Admins updatedAdmin = adminService.update(uid, admin);

            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
