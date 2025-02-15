package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.SubCategories;
import com.labprog.labprog.model.repositories.ProductsRepository;
import com.labprog.labprog.model.repositories.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoriesRepository subCategoriesRepository;

    public SubCategories createSubCategory(SubCategories subCategory) {

        this.validateSubCategory(subCategory);

        subCategory.setCreatedAt(LocalDateTime.now());
        subCategory.setUpdatedAt(LocalDateTime.now());

        return subCategoriesRepository.save(subCategory);
    }

    public List<SubCategories> getAllSubCategories() {
        return subCategoriesRepository.findAll();
    }

    public SubCategories getSubCategoryById(UUID id) {
        return subCategoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sub category not found with id" + id));
    }

    public SubCategories updateSubCategory(UUID id, SubCategories subCategoryData) {

        SubCategories subCategory = getSubCategoryById(id);

        subCategory.setCategory(subCategoryData.getCategory());
        subCategory.setSubCategoryName(subCategoryData.getSubCategoryName());
        subCategory.setSubCategoryDescription(subCategoryData.getSubCategoryDescription());
        subCategory.setUpdatedAt(LocalDateTime.now());

        return subCategoriesRepository.save(subCategory);
    }

    public void deleteSubCategory(UUID id) {
        SubCategories subCategory = getSubCategoryById(id);
        subCategoriesRepository.delete(subCategory);
    }

    private void validateSubCategory(SubCategories subCategory) {
        if (subCategory.getSubCategoryName() == null) {
            throw new RuntimeException("Sub category name is required");
        }

        if (subCategory.getSubCategoryDescription() == null) {
            throw new RuntimeException("Sub category description is required");
        }
    }

}
