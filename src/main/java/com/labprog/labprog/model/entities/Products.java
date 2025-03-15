package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labprog.labprog.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductSkus> productSkus;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    public Products(ProductDTO productDTO) {
        this.productName = productDTO.getProductName();
        this.productDescription = productDTO.getProductDescription();
        this.summary = productDTO.getSummary();
        this.productSkus = productDTO.getProductSkus();
        this.category = productDTO.getCategory();
        this.brandName = productDTO.getBrandName();
        this.manufacturer = productDTO.getManufacturer();
    }
}
