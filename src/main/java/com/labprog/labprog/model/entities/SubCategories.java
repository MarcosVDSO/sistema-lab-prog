package com.labprog.labprog.model.entities;

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
    @Column(name = "subCategoryId")
    private UUID subCategoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories category;

    @Column(name = "subCategoryName", nullable = false)
    private String subCategoryName;

    @Column(name = "subCategoryDescription", nullable = false)
    private String subCategoryDescription;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}
