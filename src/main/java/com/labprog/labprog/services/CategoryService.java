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

        return categoriesRepository.save(category);
    }

    public List<Categories> getAllCategories() {
        logger.info("Searching all categories");
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(UUID category_id) {
        logger.info("Searching category with ID: {}", category_id);
        return categoriesRepository.findById(category_id);
    }

    public Categories udpateCategory(UUID category_id, Categories updatedCategory) {
        logger.info("Updating category with ID: {}", category_id);
        Categories existingCategory = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        existingCategory.setCategoryDescription(updatedCategory.getCategoryDescription());

        return categoriesRepository.save(existingCategory);
    }

    public void deleteCategory(UUID category_id) {
        logger.info("Deleting category with ID: {}", category_id);
        Categories category = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoriesRepository.delete(category);
    }

}
