package com.labprog.labprog.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Products> products;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_description", nullable = false)
    private String categoryDescription;

    public Categories(CategoryDTO categoryDTO) {

        this.categoryName = categoryDTO.getCategoryName();
        this.categoryDescription = categoryDTO.getCategoryDescription();

    }
}
