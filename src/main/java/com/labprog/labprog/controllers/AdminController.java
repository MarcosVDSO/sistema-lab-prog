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
            Admins admin = new Admins(adminDTO);
            Admins savedAdmin = adminService.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Admins> deleteAdmin(@PathVariable UUID uid) {
        adminService.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Admins>> getAllAdmin() {
        List<Admins> admin = adminService.findAll();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Admins> getAdmin(@PathVariable UUID uid) {
        Optional<Admins> admin = adminService.findById(uid);
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);

    }
    @PutMapping("/{uid}")
    public ResponseEntity<Admins> updateAdmin(@PathVariable UUID uid, @RequestBody AdminDTO adminDTO) {
        Admins admin = new Admins(adminDTO);
        Admins updatedAdmin = adminService.update(uid, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

    }
    
}
