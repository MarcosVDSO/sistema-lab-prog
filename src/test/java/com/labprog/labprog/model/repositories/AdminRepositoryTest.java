package com.labprog.labprog.model.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;

import com.labprog.labprog.model.entities.Admins;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AdminRepositoryTest {
    @Autowired
    AdminRepository repository;

    @Test
    public void testCreateAdmin() {

        Admins admin = Admins.builder()
                    .firstname("marcos")
                    .lastname("oliveira")
                    .password("password")
                    .username("usernameteste")
                    .profilePhoto("profilePhoto")
                    .email("email").build();

        Admins save = repository.save(admin);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(admin.getFirstname(), save.getFirstname());
        Assertions.assertEquals(admin.getLastname(), save.getLastname());
        Assertions.assertEquals(admin.getPassword(), save.getPassword());
        Assertions.assertEquals(admin.getUsername(), save.getUsername());
        Assertions.assertEquals(admin.getProfilePhoto(), save.getProfilePhoto());
        Assertions.assertEquals(admin.getEmail(), save.getEmail());
    
    }

    @Test
    public void testFindOneAdmin() {

    }

    @Test
    public void testFindAllAdmins() {

    }

    @Test
    public void testUpdateAdmin() {

    }

    @Test
    public void testDeleteAdmin() {

    }

    @Test
    public void testDeleteManyAdmins() {

    }

}
