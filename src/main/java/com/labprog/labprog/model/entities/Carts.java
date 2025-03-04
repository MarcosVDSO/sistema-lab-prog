package com.labprog.labprog.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id")
    private UUID cartId;

    @OneToOne(mappedBy = "cart")
    private Customers customer;

    @OneToMany(mappedBy = "cart")
    private List<CartItems> cartItems;

    @Column(name = "total", nullable = false)
    private Long total;

}
