package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.CustomerDTO;
import com.labprog.labprog.DTO.EmployeesDTO;
import com.labprog.labprog.model.entities.Customers;

import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.services.CustomersService;
import com.labprog.labprog.services.EmployeesService;
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
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @PostMapping
    public ResponseEntity<Employees> addEmployee(@RequestBody EmployeesDTO employeesDTO) {
        try {
            Employees employee = new Employees(employeesDTO);
            Employees savedEmployee = employeesService.save(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Employees> deleteEmployee(@PathVariable UUID uid) {
        try {
            employeesService.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        try {
            List<Employees> employee = employeesService.findAll();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Employees> getEmployee(@PathVariable UUID uid) {
        try {
            Optional<Employees> employee = employeesService.findById(uid);
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{uid}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable UUID uid, @RequestBody EmployeesDTO employeeDTO) {
        try {
            Employees employee = new Employees(employeeDTO);
            Employees updatedEmployee = employeesService.update(uid, employee);

            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
