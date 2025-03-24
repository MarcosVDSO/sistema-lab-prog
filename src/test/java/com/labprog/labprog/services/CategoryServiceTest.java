package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldCreateACategorySuccessfully() {

        Categories category = Categories.builder()
                .categoryName("name")
                .categoryDescription("description")
                .build();

        Categories createdCategory = categoryService.createCategory(category);

        Assertions.assertEquals("name", createdCategory.getCategoryName());
        Assertions.assertEquals("description", createdCategory.getCategoryDescription());
    }

    @Test
    public void shouldntCreateACategoryIfNameIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(Categories.builder()
                    .categoryName("   ")
                    .categoryDescription("description")
                    .build()
            );
        });

        Assertions.assertEquals("Category name cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldntCreateACategoryIfDescriptionIsNull() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(Categories.builder()
                    .categoryName("name")
                    .categoryDescription("   ")
                    .build()
            );
        });

        Assertions.assertEquals("Category description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void shouldReturnOneCategory() {
        Categories category = Categories.builder()
                .categoryName("name")
                .categoryDescription("description")
                .build();

        Categories createdCategory = categoryService.createCategory(category);

        Categories foundedCategory = categoryService.getCategoryById(createdCategory.getCategoryId());

        Assertions.assertEquals(createdCategory.getCategoryId(), foundedCategory.getCategoryId());
    }

    @Test
    public void shouldReturnNothingIfCategoryNotExists() {

        UUID categoryId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });

        Assertions.assertEquals("Category not found!", exception.getMessage());
    }

    @Test
    public void shouldUpdateCategory() {
        Categories category = Categories.builder()
                .categoryName("name")
                .categoryDescription("description")
                .build();

        Categories createdCategory = categoryService.createCategory(category);

        Categories categoryData = Categories.builder()
                .categoryName("new name")
                .build();

        categoryService.updateCategory(createdCategory.getCategoryId(), categoryData);

        Assertions.assertEquals("new name", categoryData.getCategoryName());
    }

    @Test
    public void shouldntUpdateCategoryIfNotExists() {

        UUID categoryId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(categoryId, Categories.builder().build());
        });

        Assertions.assertEquals("Category not found!", exception.getMessage());
    }

    @Test
    public void shouldDeleteCategory() {
        Categories category = Categories.builder()
                .categoryName("name")
                .categoryDescription("description")
                .build();

        Categories createdCategory = categoryService.createCategory(category);

        categoryService.deleteCategory(createdCategory.getCategoryId());
    }

    @Test
    public void shouldnDeleteCategoryIfNotExists() {

        UUID categoryId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        Assertions.assertEquals("Category not found!", exception.getMessage());
    }

}
