package com.labprog.labprog.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID adminId;

    @OneToMany(mappedBy = "addressId")
    private List<Addresses> addresses;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Carts cart;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_photo", nullable = false)
    private String profilePhoto;

    @Column(name = "email", nullable = false)
    private String email;
}
