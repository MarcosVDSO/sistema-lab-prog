package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
    private String neighborhood;
    private String street;
    private String number;
    private Users user;

}
