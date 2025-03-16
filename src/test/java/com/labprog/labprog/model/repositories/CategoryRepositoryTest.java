package com.labprog.labprog.model.repositories;

import com.labprog.labprog.model.entities.Categories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired
    CategoriesRepository repository;

    @Test
    public void testCreateCategory() {
        Categories category = Categories.builder()
                .categoryName("Fones")
                .categoryDescription("Fones de ouvido")
                .build();

        Categories save = repository.save(category);

        Assertions.assertNotNull(save);
        Assertions.assertEquals(category.getCategoryName(), save.getCategoryName());
        Assertions.assertEquals(category.getCategoryDescription(), save.getCategoryDescription());
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
