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
@Table(name = "ProductSkus")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productSkuId")
    private UUID productSkuId;

    @OneToMany(mappedBy = "productSku")
    private List<CartItems> cartItem;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @Column(name = "sku", nullable = false)
    private String uuid;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

}
