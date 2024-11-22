package com.labprog.labprog.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Addresses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "addressId")
    private UUID addressId;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "landmark", nullable = false)
    private String landmark;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admins admin;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employees employee;
}
