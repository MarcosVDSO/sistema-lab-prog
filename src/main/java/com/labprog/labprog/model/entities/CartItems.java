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
    @Column(name = "cart_item_id")
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Carts cart;

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSkus productSku;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
