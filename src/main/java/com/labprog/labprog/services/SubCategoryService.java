package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.SubCategories;
import com.labprog.labprog.model.repositories.ProductsRepository;
import com.labprog.labprog.model.repositories.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoriesRepository subCategoriesRepository;

    public SubCategories createSubCategory(SubCategories subCategory) {
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

        return subCategoriesRepository.save(subCategory);
    }

    public void deleteSubCategory(UUID id) {
        SubCategories subCategory = getSubCategoryById(id);
        subCategoriesRepository.delete(subCategory);
    }

}
