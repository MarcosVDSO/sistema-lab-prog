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
        Employees employee = new Employees(employeesDTO);
        Employees savedEmployee = employeesService.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Employees> deleteEmployee(@PathVariable UUID uid) {
        employeesService.deleteById(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employee = employeesService.findAll();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/{uid}")
    public ResponseEntity<Employees> getEmployee(@PathVariable UUID uid) {
        Optional<Employees> employee = employeesService.findById(uid);
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);

    }
    @PutMapping("/{uid}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable UUID uid, @RequestBody EmployeesDTO employeeDTO) {
        Employees employee = new Employees(employeeDTO);
        Employees updatedEmployee = employeesService.update(uid, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    }
}
