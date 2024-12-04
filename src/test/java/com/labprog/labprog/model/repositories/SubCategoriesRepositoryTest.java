package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.entities.SubCategories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class SubCategoriesRepositoryTest {
    @Autowired
    SubCategoriesRepository repository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Test
    public void testCreateSubCategories() {

        Categories category = Categories.builder()
                .subCategories(new ArrayList<SubCategories>())
                .categoryName("Fones")
                .categoryDescription("Fones de ouvido")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        Categories categorySave = categoriesRepository.save(category);

        SubCategories subCategory = SubCategories.builder()
                .category(categorySave)
                .subCategoryName("Nome sub Categoria")
                .subCategoryDescription("descricao")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        SubCategories save = repository.save(subCategory);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(subCategory.getCategory(), save.getCategory());
        Assertions.assertEquals(subCategory.getSubCategoryName(), save.getSubCategoryName());
        Assertions.assertEquals(subCategory.getSubCategoryDescription(), save.getSubCategoryDescription());
        Assertions.assertEquals(subCategory.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(subCategory.getUpdatedAt(), save.getUpdatedAt());

    }
}
