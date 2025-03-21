package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Carts;
import com.labprog.labprog.model.entities.enums.ROLES;
import com.labprog.labprog.model.entities.enums.USER_STATUS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private UUID customerId;

    private List<Addresses> addresses;
    private String firstname;
    private String lastname;
    private String username;
    private String cpf;
    private Carts cart;
    private String password;
    private String profilePhoto;
    private String email;
    private String role;
    private String status;

}
