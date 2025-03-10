package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class EmployeesService {
    @Autowired
    EmployeesRepository employeesRepository;


    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    public Optional<Employees> findById(UUID id) {
        return employeesRepository.findById(id);
    }

    @Transactional
    public Employees save(Employees employee) {
        verifyCustomer(employee, true);
        return employeesRepository.save(employee);
    }

    public Employees update(UUID customerId, Employees employeeData) {

        Employees updatedEmployee = employeesRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException());

//        verifyCustomer(updatedEmployee, false);

        if (employeeData.getEmail() != null && !employeeData.getEmail().equals(updatedEmployee.getEmail())
                && employeesRepository.existsByEmail(employeeData.getEmail())) {
            throw new DuplicateEmailException();
        }

        if (employeeData.getUsername() != null && !employeeData.getUsername().equals(updatedEmployee.getUsername())
                && employeesRepository.existsByUsername(employeeData.getUsername())) {
            throw new DuplicateUserNameException();
        }

        if (employeeData.getFirstname() != null) {
            updatedEmployee.setFirstname(employeeData.getFirstname());
        }

        if (employeeData.getLastname() != null) {
            updatedEmployee.setLastname(employeeData.getLastname());
        }

        if (employeeData.getEmail() != null) {
            updatedEmployee.setEmail(employeeData.getEmail());
        }

        if (employeeData.getPassword() != null) {
            updatedEmployee.setPassword(employeeData.getPassword());
        }

        if (employeeData.getCpf() != null) {
            updatedEmployee.setCpf(employeeData.getCpf());
        }

        return employeesRepository.save(updatedEmployee);

    }


    public void deleteById(UUID employeerId) {
        Optional<Employees> employee = employeesRepository.findById(employeerId);
        if (employee.isEmpty()) {
            throw new ObjectNotFoundException();
        }
        employeesRepository.deleteById(employeerId);
    }

    private void verifyCustomer(Employees employee, boolean isNewEmployee) {
        if (employee == null) {
            throw new ObjectNotFoundException();
        }
        if (employee.getFirstname() == null || employee.getLastname() == null) {
            throw new InvalidAddressException();
        }
        if (employee.getEmail() == null) {
            throw new InvalidEmailException();
        }
        if (employee.getPassword() == null) {
            throw new InvalidPasswordException();
        }

        if (employee.getUsername() == null) {
            throw new InvalidUserNameException();
        }

        if (isNewEmployee) {
            if (employeesRepository.existsByEmail(employee.getEmail())) {
                throw new DuplicateEmailException();
            }
            if (employeesRepository.existsByUsername(employee.getUsername())) {
                throw new DuplicateUserNameException();
            }
        }


    }

}
