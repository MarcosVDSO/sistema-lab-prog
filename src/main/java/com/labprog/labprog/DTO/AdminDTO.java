package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Addresses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class AdminDTO {
    private UUID adminId;

    private List<Addresses> addresses;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String profilePhoto;

    private String email;
}
