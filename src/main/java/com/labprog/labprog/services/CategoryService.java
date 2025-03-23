package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.repositories.CategoriesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Categories createCategory(Categories category) {

        validate(category);

        if (category.getProducts() == null) {
            category.setProducts(new ArrayList<>());
        }

        return categoriesRepository.save(category);
    }

    public List<Categories> getAllCategories() {

        return categoriesRepository.findAll();
    }

    public Categories getCategoryById(UUID category_id) {

        return categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found!"));
    }

    public Categories updateCategory(UUID category_id, Categories updatedCategory) {

        Categories existingCategory = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found!"));

        if (updatedCategory.getCategoryName() != null) {
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
        }
        if (updatedCategory.getCategoryDescription() != null) {
            existingCategory.setCategoryDescription(updatedCategory.getCategoryDescription());
        }

        return categoriesRepository.save(existingCategory);
    }

    public void deleteCategory(UUID category_id) {

        Categories category = categoriesRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found!"));
        categoriesRepository.delete(category);
    }

    private void validate(Categories category) {
        if (category.getCategoryName() == null || category.getCategoryName().isBlank()) {
            throw new RuntimeException("Category name cannot be null or empty");
        }

        if (category.getCategoryDescription() == null || category.getCategoryDescription().isBlank()) {
            throw new RuntimeException("Category description cannot be null or empty");
        }
    }

}
