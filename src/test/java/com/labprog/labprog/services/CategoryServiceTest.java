package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.repositories.CategoriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Test
    public void shouldCreateCategorySuccessfully() {
        Categories category = Categories.builder()
                .categoryName("Eletrônicos")
                .categoryDescription("Produtos eletrônicos diversos")
                .build();

        Categories createdCategory = categoryService.createCategory(category);

        Assertions.assertEquals("Eletrônicos", createdCategory.getCategoryName());
        Assertions.assertEquals("Produtos eletrônicos diversos", createdCategory.getCategoryDescription());
    }

    @Test
    public void shouldntCreateCategoryIfNameNotExists() {
        Categories category = Categories.builder()
                .categoryDescription("Produtos diversos")
                .build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(category);
        });

        Assertions.assertEquals("Category name is required", exception.getMessage());
    }

    @Test
    public void shouldReturnCategorySuccessfully() {
        Categories category = Categories.builder()
                .categoryName("Móveis")
                .categoryDescription("Produtos para casa")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Categories savedCategory = categoriesRepository.save(category);

        Categories foundCategory = categoryService.getCategoryById(savedCategory.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Assertions.assertEquals("Móveis", foundCategory.getCategoryName());
        Assertions.assertEquals("Produtos para casa", foundCategory.getCategoryDescription());
    }

    @Test
    void shouldReturnNothingIfCategoryDoesNotExist() {
        Optional<Categories> category = categoryService.getCategoryById(UUID.randomUUID()); // Categoria não existe

        Assertions.assertEquals(Optional.empty(), category);
    }

    @Test
    public void shouldUpdateCategorySuccessfully() {
        Categories category = Categories.builder()
                .categoryName("Roupas")
                .categoryDescription("Vestimentas e acessórios")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Categories savedCategory = categoriesRepository.save(category);

        Categories updatedCategory = Categories.builder()
                .categoryName("Roupas e acessórios")
                .categoryDescription("Vestimentas e acessórios para todos os públicos")
                .updatedAt(LocalDateTime.now())
                .build();

        Categories result = categoryService.udpateCategory(savedCategory.getCategoryId(), updatedCategory);

        Assertions.assertEquals("Roupas e acessórios", result.getCategoryName());
        Assertions.assertEquals("Vestimentas e acessórios para todos os públicos", result.getCategoryDescription());
    }

    @Test
    public void shouldntUpdateCategoryIfCategoryNotExists() {
        UUID categoryId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.udpateCategory(categoryId, new Categories());
        });

        Assertions.assertEquals("Category not found", exception.getMessage());
    }

    @Test
    public void shouldDeleteCategorySuccessfully() {
        Categories category = Categories.builder()
                .categoryName("Alimentos")
                .categoryDescription("Comidas e bebidas")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Categories savedCategory = categoriesRepository.save(category);

        categoryService.deleteCategory(savedCategory.getCategoryId());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(savedCategory.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        });

        Assertions.assertEquals("Category not found", exception.getMessage());
    }

    @Test
    public void shouldntDeleteCategoryIfCategoryDoesNotExist() {
        UUID categoryId = UUID.randomUUID();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        Assertions.assertEquals("Category not found", exception.getMessage());
    }
}
