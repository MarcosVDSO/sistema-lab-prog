package com.labprog.labprog.model.entities;

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
    private List<ProductSkus> productSkus;

    @OneToMany(mappedBy = "product")
    private List<Categories> categories;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Products(ProductDTO productDTO) {
        this.productName = productDTO.getProductName();
        this.productDescription = productDTO.getProductDescription();
        this.summary = productDTO.getSummary();
        this.productSkus = productDTO.getProductSkus();
        this.categories = productDTO.getCategories();
    }
}
