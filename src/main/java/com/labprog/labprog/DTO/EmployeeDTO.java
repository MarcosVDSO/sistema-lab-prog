package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Addresses;

import java.util.List;
import java.util.UUID;

public class EmployeeDTO {
    private UUID employeeId;
    private List<Addresses> addresses;
    private String cpf;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
}
