package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.labprog.labprog.DTO.ProductSkuDTO;
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
    @Column(name = "product_sku_id")
    private UUID productSkuId;

    @OneToMany(mappedBy = "productSku")
    @JsonBackReference
    private List<CartItems> cartItem;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProductSkus(ProductSkuDTO productSkuDTO) {
        this.cartItem = productSkuDTO.getCartItem();
        this.quantity = productSkuDTO.getQuantity();
        this.price = productSkuDTO.getPrice();
        this.sku = productSkuDTO.getSku();
    }
}
