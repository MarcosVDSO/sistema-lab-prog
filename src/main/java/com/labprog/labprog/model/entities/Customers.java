package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labprog.labprog.DTO.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customers implements User, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID customerId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    public Customers(CustomerDTO customerDTO) {
        this.cart = customerDTO.getCart();
        this.firstname = customerDTO.getFirstname();
        this.lastname = customerDTO.getLastname();
        this.username = customerDTO.getUsername();
        this.password = customerDTO.getPassword();
        this.email = customerDTO.getEmail();
        this.cpf = customerDTO.getCpf();
    }

    public Customers(String firstname, String lastname, String username, String password, String cpf, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_CUSTOMER")
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
