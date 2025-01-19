package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.SubCategoryDTO;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.SubCategories;
import com.labprog.labprog.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<SubCategories> createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) {

        try {
            SubCategories category = new SubCategories(subCategoryDTO);
            SubCategories savedCategory = subCategoryService.createSubCategory(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<SubCategories>> getAllSubCategory() {
        try {
            List<SubCategories> subCategories = subCategoryService.getAllSubCategories();
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategories> getSubCategoryById(@PathVariable UUID id) {
        try {
            SubCategories subCategory = subCategoryService.getSubCategoryById(id);
            return new ResponseEntity<>(subCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategories> updateSubCategory(@PathVariable UUID id, @RequestBody SubCategoryDTO subCategoryDTO) {

        try {
            SubCategories subcategory = new SubCategories(subCategoryDTO);
            SubCategories savedCategory = subCategoryService.updateSubCategory(id, subcategory);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoryById(@PathVariable UUID id) {

        try {
            subCategoryService.deleteSubCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
