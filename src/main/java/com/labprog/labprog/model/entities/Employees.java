package com.labprog.labprog.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employeeId")
    private UUID employeeId;

    @OneToMany(mappedBy = "addressId")
    private List<Addresses> addresses;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profilePhoto", nullable = false)
    private String profilePhoto;

    @Column(name = "email", nullable = false)
    private String email;
}
