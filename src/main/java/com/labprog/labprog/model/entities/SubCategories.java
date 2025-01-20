package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.SubCategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SubCategories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "subCategory_id")
    private UUID subCategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @Column(name = "subCategory_name", nullable = false)
    private String subCategoryName;

    @Column(name = "subCategory_description", nullable = false)
    private String subCategoryDescription;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public SubCategories(SubCategoryDTO subCategoryDTO) {
        this.category = subCategoryDTO.getCategory();
        this.subCategoryName = subCategoryDTO.getSubCategoryName();
        this.subCategoryDescription = subCategoryDTO.getSubCategoryDescription();
    }
}
