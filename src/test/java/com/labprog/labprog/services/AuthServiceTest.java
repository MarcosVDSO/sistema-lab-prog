package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Addresses;
import com.labprog.labprog.model.entities.Users;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AuthServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Test
    public void testIfUsernameExists() {

        Users user = Users.builder()
                .firstname("marcos")
                .lastname("estrela")
                .username("marcos.estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .status("ENABLED")
                .password("12345678")
                .role("ADMIN")
                .build();

        Users savedUser = userService.save(user);
        UserDetails foundedUser = authService.loadUserByUsername(user.getUsername());

        Assertions.assertEquals(user.getUsername(), foundedUser.getUsername());

    }

    @Test
    public void testIfUsernameNotExists() {
        String username = "123123";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            authService.loadUserByUsername(username);
        });

        Assertions.assertEquals("User not found with username: " + username, exception.getMessage());
    }

}
