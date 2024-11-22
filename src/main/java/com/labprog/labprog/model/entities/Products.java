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
@Table(name = "Products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productId")
    private UUID productId;

    @OneToMany(mappedBy = "product")
    private List<ProductSkus> productSkus;

    @OneToMany(mappedBy = "product")
    private List<Categories> categories;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

}
