package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.SubCategories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired
    CategoriesRepository repository;

    @Test
    public void testCreateCategory() {
        Categories category = Categories.builder()
                .subCategories(new ArrayList<SubCategories>())
                .categoryName("Fones")
                .categoryDescription("Fones de ouvido")
                .createdAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .updatedAt(LocalDateTime.of(2024, 11, 27, 10, 30, 0))
                .build();

        Categories save = repository.save(category);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(category.getSubCategories(), save.getSubCategories());
        Assertions.assertEquals(category.getCategoryName(), save.getCategoryName());
        Assertions.assertEquals(category.getCategoryDescription(), save.getCategoryDescription());
        Assertions.assertEquals(category.getCreatedAt(), save.getCreatedAt());
        Assertions.assertEquals(category.getUpdatedAt(), save.getUpdatedAt());
    }

    @Test
    public void testFindOneCategory() {

    }

    @Test
    public void testFindAllCategories() {

    }

    @Test
    public void testUpdateCategory() {

    }

    @Test
    public void testDeleteCategory() {

    }

    @Test
    public void testDeleteManyCategories() {

    }
}
