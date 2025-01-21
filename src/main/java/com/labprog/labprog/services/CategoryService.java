package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.repositories.CategoriesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Categories createCategory(Categories category) {
        logger.info("Adding new category: {}", category.getCategoryName());

        verifyCategory(category);

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return categoriesRepository.save(category);
    }

    public List<Categories> getAllCategories() {
        logger.info("Searching all categories");
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(UUID category_id) {
        logger.info("Searching category with ID: {}", category_id);
        Optional<Categories> category = categoriesRepository.findById(category_id);

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        return category;
    }

    public Categories udpateCategory(UUID category_id, Categories updatedCategory) {
        logger.info("Updating category with ID: {}", category_id);
        Categories existingCategory = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found"));

        verifyCategory(updatedCategory);

        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setCategoryDescription(updatedCategory.getCategoryDescription());
        existingCategory.setUpdatedAt(LocalDateTime.now());

        return categoriesRepository.save(existingCategory);
    }

    public void deleteCategory(UUID category_id) {
        logger.info("Deleting category with ID: {}", category_id);
        Categories category = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoriesRepository.delete(category);
    }

    private void verifyCategory(Categories category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can not be null");
        }
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name is required");
        }
    }

}
