package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.CategoryDTO;
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
    @Column(name = "category_id")
    private UUID categoryId;

    @OneToOne(mappedBy = "category")
    private Products product;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_description", nullable = false)
    private String categoryDescription;

    public Categories(CategoryDTO categoryDTO) {
        this.categoryId = categoryDTO.getCategory_id();
        this.categoryName = categoryDTO.getCategory_name();
        this.categoryDescription = categoryDTO.getCategory_description();
    }
}
