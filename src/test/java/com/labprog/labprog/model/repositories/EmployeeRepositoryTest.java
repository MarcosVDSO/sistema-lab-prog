package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Employees;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    EmployeesRepository repository;

    @Test
    public void testCreateEmployee() {
        Employees employee = Employees.builder().addresses(new ArrayList<Addresses>())
                .firstname("Kleiton")
                .lastname("Arruda")
                .username("sdadasdasda")
                .password("123")
                .email("masadasdas").build();

        Employees save = repository.save(employee);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(employee.getAddresses(), save.getAddresses());
        Assertions.assertEquals(employee.getFirstname(), save.getFirstname());
        Assertions.assertEquals(employee.getLastname(), save.getLastname());
        Assertions.assertEquals(employee.getUsername(), save.getUsername());
        Assertions.assertEquals(employee.getPassword(), save.getPassword());
        Assertions.assertEquals(employee.getEmail(), save.getEmail());
        Assertions.assertEquals(employee.getCpf(), save.getCpf());

    }
}
