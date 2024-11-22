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
@Table(name = "Categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "categoryId")
    private UUID categoryId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @OneToMany(mappedBy = "category")
    private List<SubCategories> subCategories;

    @Column(name = "categoryName", nullable = false)
    private String categoryName;

    @Column(name = "categoryDescription", nullable = false)
    private String categoryDescription;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}
