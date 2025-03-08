package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labprog.labprog.DTO.ProductSkuDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToOne(mappedBy = "productSku")
    @JsonIgnore
    private CartItems cartItem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @OneToOne(mappedBy = "productSku")
    private OrderItems orderItem;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "stock_quantity", nullable = false)
    private Long stockQuantity;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "product_image", nullable = false)
    private String productImage;
//
//    @Type(JsonBinaryType.class)
//    @Column(name = "product_attributes", columnDefinition = "jsonb")
//    private Map<String, Object> product_attributes;

    public ProductSkus(ProductSkuDTO productSkuDTO) {
        this.cartItem = productSkuDTO.getCartItem();
        this.stockQuantity = productSkuDTO.getStockQuantity();
        this.price = productSkuDTO.getPrice();
        this.sku = productSkuDTO.getSku();
        this.productImage = productSkuDTO.getProductImage();
    }
}
