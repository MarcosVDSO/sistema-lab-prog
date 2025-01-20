package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.entities.Employees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AddressesDTO {

    private UUID addressId;


    private String country;


    private String state;


    private String landmark;


    private String city;


    private String cep;


    private LocalDateTime createdAt;


    private Admins admin;


    private Customers customer;

    private Employees employee;

}
