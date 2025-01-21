package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class EmployeesServiceTest {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesRepository employeesRepository;

    private Employees employee;

    @BeforeEach
    void setup() {
        employeesRepository.deleteAll();

        employee = Employees.builder()
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@example.com")
                .username("johndoe")
                .password("password123")
                .profilePhoto("photo.png")
                .build();

        employeesRepository.save(employee);
    }

    @Test
    void testFindAll() {
        var employees = employeesService.findAll();
        assertThat(employees).isNotEmpty();
        assertThat(employees.get(0).getEmail()).isEqualTo("johndoe@example.com");
    }

    @Test
    void testFindById() {
        var found = employeesService.findById(employee.getEmployeeId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("johndoe@example.com");
    }

    @Test
    void testSave() {
        Employees newEmployee = Employees.builder()
                .firstname("Jane")
                .lastname("Smith")
                .email("janesmith@example.com")
                .username("janesmith")
                .password("password123")
                .profilePhoto("photo2.png")
                .build();

        Employees savedEmployee = employeesService.save(newEmployee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo("janesmith@example.com");
    }

//    @Test
//    void testSaveDuplicateEmailThrowsException() {
//        Employees duplicateEmailEmployee = Employees.builder()
//                .firstname("Jane")
//                .lastname("Smith")
//                .email("johndoe@example.com") // Email duplicado
//                .username("janesmith")
//                .password("password123")
//                .profilePhoto("photo2.png")
//                .build();
//
//        Assertions.assertThrows(DuplicateEmailException.class, () -> {
//            employeesService.save(duplicateEmailEmployee);
//        });
//    }

    @Test
    void testUpdate() {
        Employees updatedEmployee = Employees.builder()
                .firstname("John")
                .lastname("Doe Updated")
                .email("newemail@example.com")
                .username("johndoe_updated")
                .password("newpassword123")
                .profilePhoto("newphoto.png")
                .build();

        Employees result = employeesService.update(employee.getEmployeeId(), updatedEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getLastname()).isEqualTo("Doe Updated");
        assertThat(result.getEmail()).isEqualTo("newemail@example.com");
    }

    @Test
    void testDeleteById() {
        employeesService.deleteById(employee.getEmployeeId());
        Optional<Employees> deletedEmployee = employeesRepository.findById(employee.getEmployeeId());
        assertThat(deletedEmployee).isEmpty();
    }

//    @Test
//    void testDeleteNonExistentEmployeeThrowsException() {
//        UUID randomId = UUID.randomUUID();
//        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
//            employeesService.deleteById(randomId);
//        });
//    }
}
