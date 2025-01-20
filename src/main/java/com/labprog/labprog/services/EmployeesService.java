package com.labprog.labprog.services;

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

    public Employees update(UUID customerId, Employees updatedCustomer) {

        Employees updatedEmployee = employeesRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        verifyCustomer(updatedEmployee, false);
        // Atualizar apenas os campos que realmente mudaram
        if (!updatedCustomer.getEmail().equals(updatedEmployee.getEmail())
                && employeesRepository.existsByEmail(updatedCustomer.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        if (!updatedCustomer.getUsername().equals(updatedEmployee.getUsername())
                && employeesRepository.existsByUsername(updatedCustomer.getUsername())) {
            throw new IllegalArgumentException("Nome de usuário já está em uso");
        }

        updatedEmployee.setFirstname(updatedCustomer.getFirstname());
        updatedEmployee.setLastname(updatedCustomer.getLastname());
        updatedEmployee.setEmail(updatedCustomer.getEmail());
        updatedEmployee.setPassword(updatedCustomer.getPassword());
        updatedEmployee.setProfilePhoto(updatedCustomer.getProfilePhoto());
        return employeesRepository.save(updatedEmployee);
        //coment

    }


    public void deleteById(UUID employeerId) {
        Optional<Employees> employee = employeesRepository.findById(employeerId);
        if (employee.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        employeesRepository.deleteById(employeerId);
    }

    private void verifyCustomer(Employees employee, boolean isNewEmployee) {
        if (employee == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        if (employee.getFirstname() == null || employee.getLastname() == null) {
            throw new IllegalArgumentException("Nome e sobrenome são obrigatórios");
        }
        if (employee.getEmail() == null) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (employee.getPassword() == null) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
//        if (customer.getAddresses() == null || customer.getAddresses().isEmpty()) {
//            throw new IllegalArgumentException("Pelo menos um endereço é obrigatório");
//        }
        if (employee.getUsername() == null) {
            throw new IllegalArgumentException("Nome de usuário é obrigatório");
        }

        if (isNewEmployee) {
            if (employeesRepository.existsByEmail(employee.getEmail())) {
                throw new IllegalArgumentException("O email já está cadastrado");
            }
            if (employeesRepository.existsByUsername(employee.getUsername())) {
                throw new IllegalArgumentException("O nome de usuário já está cadastrado");
            }
        }
    }

}
