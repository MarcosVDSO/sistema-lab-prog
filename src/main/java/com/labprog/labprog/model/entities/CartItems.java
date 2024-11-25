package com.labprog.labprog.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CartItems")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cartItemId")
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Carts cart;

    @ManyToOne
    @JoinColumn(name = "productSkuId")
    private ProductSkus productSku;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}
