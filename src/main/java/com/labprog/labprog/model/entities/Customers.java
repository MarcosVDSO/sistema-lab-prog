package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labprog.labprog.DTO.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private UUID customerId;

    @OneToMany(mappedBy = "addressId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Addresses> addresses;

    @Setter
    @OneToOne
    @JsonIgnore
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

    public Customers(CustomerDTO customerDTO) {
        this.cart = customerDTO.getCart();
        this.firstname = customerDTO.getFirstname();
        this.lastname = customerDTO.getLastname();
        this.username = customerDTO.getUsername();
        this.password = customerDTO.getPassword();
        this.profilePhoto = customerDTO.getProfilePhoto();
        this.email = customerDTO.getEmail();
        this.addresses = customerDTO.getAddresses();
    }
}
