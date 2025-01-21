package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AdminServicesTest {

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private AdminRepository adminRepository;

    private Admins admin;

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();

        admin = Admins.builder()
                .firstname("Alice")
                .lastname("Johnson")
                .email("alice.johnson@example.com")
                .username("alicej")
                .password("securePassword123")
                .profilePhoto("profile1.png")
                .build();

        adminRepository.save(admin);
    }

    @Test
    void testFindAll() {
        var admins = adminServices.findAll();
        assertThat(admins).isNotEmpty();
        assertThat(admins.get(0).getEmail()).isEqualTo("alice.johnson@example.com");
    }

    @Test
    void testFindById() {
        var found = adminServices.findById(admin.getAdminId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("alice.johnson@example.com");
    }

    @Test
    void testSave() {
        Admins newAdmin = Admins.builder()
                .firstname("Bob")
                .lastname("Smith")
                .email("bob.smith@example.com")
                .username("bobsmith")
                .password("password456")
                .profilePhoto("profile2.png")
                .build();

        Admins savedAdmin = adminServices.save(newAdmin);

        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getEmail()).isEqualTo("bob.smith@example.com");
    }

    @Test
    void testSaveDuplicateEmailThrowsException() {
        Admins duplicateEmailAdmin = Admins.builder()
                .firstname("Charlie")
                .lastname("Brown")
                .email("alice.johnson@example.com") // Email duplicado
                .username("charlieb")
                .password("password789")
                .profilePhoto("profile3.png")
                .build();

        Assertions.assertThrows(DuplicateEmailException.class, () -> {
            adminServices.save(duplicateEmailAdmin);
        });
    }

    @Test
    void testSaveDuplicateUsernameThrowsException() {
        Admins duplicateUsernameAdmin = Admins.builder()
                .firstname("David")
                .lastname("Wilson")
                .email("david.wilson@example.com")
                .username("alicej") // Username duplicado
                .password("password000")
                .profilePhoto("profile4.png")
                .build();

        Assertions.assertThrows(DuplicateUserNameException.class, () -> {
            adminServices.save(duplicateUsernameAdmin);
        });
    }

    @Test
    void testUpdate() {
        Admins updatedAdmin = Admins.builder()
                .firstname("Alice")
                .lastname("Johnson Updated")
                .email("alice.updated@example.com")
                .username("alicej_updated")
                .password("newPassword123")
                .profilePhoto("updatedProfile.png")
                .build();

        Admins result = adminServices.update(admin.getAdminId(), updatedAdmin);

        assertThat(result).isNotNull();
        assertThat(result.getLastname()).isEqualTo("Johnson Updated");
        assertThat(result.getEmail()).isEqualTo("alice.updated@example.com");
    }

    @Test
    void testDeleteById() {
        adminServices.deleteById(admin.getAdminId());
        Optional<Admins> deletedAdmin = adminRepository.findById(admin.getAdminId());
        assertThat(deletedAdmin).isEmpty();
    }

    @Test
    void testDeleteNonExistentAdminThrowsException() {
        UUID randomId = UUID.randomUUID();
        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            adminServices.deleteById(randomId);
        });
    }
}
