package com.labprog.labprog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRegisterDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String cpf;
    private String email;
}
