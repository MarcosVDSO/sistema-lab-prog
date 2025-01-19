package com.labprog.labprog.controllers;

import com.labprog.labprog.model.entities.SubCategories;
import com.labprog.labprog.services.ProductService;
import com.labprog.labprog.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SubCategories createSubCategory(@RequestBody SubCategories subCategory) {
        return subCategoryService.createSubCategory(subCategory);
    }

    @GetMapping
    public List<SubCategories> getAllSubCategory() {
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{id}")
    public SubCategories getSubCategoryById(@PathVariable UUID id) {
        return subCategoryService.getSubCategoryById(id);
    }

    @PutMapping("/{id}")
    public SubCategories updateSubCategory(@PathVariable UUID id, @RequestBody SubCategories subCategory) {
        return subCategoryService.updateSubCategory(id, subCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoryById(@PathVariable UUID id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }

}
