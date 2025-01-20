package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.CategoryDTO;
import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.services.CategoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        try {
            List<Categories> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error finding categories");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable UUID category_id) {
        try {
            Optional<Categories> category = categoryService.getCategoryById(category_id);
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error finding category: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            Categories category = new Categories(categoryDTO);
            Categories savedCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("Error adding category");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable UUID category_id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Categories category = new Categories(categoryDTO);
            Categories updatedCategory = categoryService.udpateCategory(category_id, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error updating category: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Categories> deleteCategory(@PathVariable UUID category_id) {
        try {
            categoryService.deleteCategory(category_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            logger.error("Error deleting category: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
