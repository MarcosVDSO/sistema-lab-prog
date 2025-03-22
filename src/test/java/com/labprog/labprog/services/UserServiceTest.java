package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Users;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void shouldCreateAnUserSuccessfully() {

        Users user = Users.builder()
                .username("marcos.estrela")
                .firstname("marcos")
                .lastname("estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .password("12345678")
                .role("ADMIN")
                .status("ENABLED")
                .build();

        Users createdUser = userService.save(user);

        Assertions.assertEquals(user.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(user.getFirstname(), createdUser.getFirstname());
        Assertions.assertEquals(user.getLastname(), createdUser.getLastname());
        Assertions.assertEquals(user.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(user.getCpf(), createdUser.getCpf());
        Assertions.assertEquals(user.getPassword(), createdUser.getPassword());
        Assertions.assertEquals(user.getRole(), createdUser.getRole());
        Assertions.assertEquals(user.getStatus(), createdUser.getStatus());

    }

    @Test
    public void shouldntCreateAnUserIfUsernameIsNull() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("   ")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Username cannot be null or empty", exception.getMessage());

    }

    @Test
    public void shouldntCreateAnUserIfFirstnameIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("   ")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Invalid Firstname or Lastname", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfLastnameIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("   ")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Invalid Firstname or Lastname", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfEmailIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("   ")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfCpfIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("   ")
                    .password("12345678")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Cpf cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfPasswordIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("   ")
                    .role("ADMIN")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfRoleIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("   ")
                    .status("ENABLED")
                    .build()
            );
        });

        Assertions.assertEquals("Role cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateAnUserIfStatusIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(Users.builder()
                    .username("marcos.estrela")
                    .firstname("marcos")
                    .lastname("estrela")
                    .email("exemplo@email.com")
                    .cpf("00000000000")
                    .password("12345678")
                    .role("ADMIN")
                    .status("   ")
                    .build()
            );
        });

        Assertions.assertEquals("Status cannot be null or empty", exception.getMessage());
    }


    @Test
    public void shouldFindOneUserSuccessfully() {
        Users user = Users.builder()
                .username("marcos.estrela")
                .firstname("marcos")
                .lastname("estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .password("12345678")
                .role("ADMIN")
                .status("ENABLED")
                .build();

        Users createdUser = userService.save(user);

        Users foundedUser = userService.findById(createdUser.getUserId());

        Assertions.assertEquals(foundedUser.getUserId(), createdUser.getUserId());

    }

    @Test
    public void shouldReturnNothingIfUserNotExists() {
        UUID userId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.findById(userId);
        });

        Assertions.assertEquals("User not found!", exception.getMessage());
    }

    @Test
    public void shouldUpdateUserSuccessfully() {
        Users user = Users.builder()
                .username("marcos.estrela")
                .firstname("marcos")
                .lastname("estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .password("12345678")
                .role("ADMIN")
                .status("ENABLED")
                .build();

        Users createdUser = userService.save(user);

        Users newUserData = Users.builder()
                .firstname("agenor")
                .build();

        Users updatedUser = userService.update(createdUser.getUserId(), newUserData);

        Assertions.assertEquals(createdUser.getFirstname(), updatedUser.getFirstname());
    }

    @Test
    public void shouldntUpdateIfUserNotExists() {
        UUID userId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.update(userId, Users.builder().build());
        });

        Assertions.assertEquals("User not found!", exception.getMessage());

    }

    @Test
    public void shouldDeleteUserSuccessfully() {
        Users user = Users.builder()
                .username("marcos.estrela")
                .firstname("marcos")
                .lastname("estrela")
                .email("exemplo@email.com")
                .cpf("00000000000")
                .password("12345678")
                .role("ADMIN")
                .status("ENABLED")
                .build();

        Users createdUser = userService.save(user);
        
        userService.deleteById(createdUser.getUserId());
    }

    @Test
    public void shouldntDeleteIfUserNotExists() {
        UUID userId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.deleteById(userId);
        });

        Assertions.assertEquals("User not found!", exception.getMessage());
    }

}
