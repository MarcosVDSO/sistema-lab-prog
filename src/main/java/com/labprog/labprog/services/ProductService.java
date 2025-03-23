package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.*;
import com.labprog.labprog.model.repositories.ProductSkusRepository;
import com.labprog.labprog.model.repositories.ProductsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    public Products createProduct(Products product) {

        this.validateProduct(product);
        if (product.getProductSkus() == null) {
            product.setProductSkus(new ArrayList<>());
        }

        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }

        return productsRepository.save(product);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(UUID id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public Products updateProduct(UUID id, Products productData) {

        Products product = getProductById(id);

        if (productData.getProductName() != null) {
            product.setProductName(productData.getProductName());
        }

        if (productData.getProductDescription() != null) {
            product.setProductDescription(productData.getProductDescription());
        }
        if (productData.getSummary() != null) {
            product.setSummary(productData.getSummary());
        }
        if (productData.getManufacturer() != null) {
            product.setManufacturer(productData.getManufacturer());
        }
        if (productData.getBrandName() != null) {
            product.setBrandName(productData.getBrandName());
        }

        return productsRepository.save(product);
    }

    public Products deleteProduct(UUID id) {
        Products product = getProductById(id);

        if (product == null) {
            throw new RuntimeException("Product not found!");
        }

        productsRepository.delete(product);
        return product;
    }

    public Products addProductSku(UUID productId, ProductSkus productSkuData) {
        Products product = getProductById(productId);

        ProductSkus productSku = productSkuService.createProductSku(productSkuData);
        productSku.setProduct(product);

        product.getProductSkus().add(productSku);

        return productsRepository.save(product);
    }

    public Products addNewCategory(UUID productId, Categories categoryData) {
        Products product = getProductById(productId);

        Categories category = categoryService.createCategory(categoryData);
        category.getProducts().add(product);

        product.setCategory(category);

        return productsRepository.save(product);
    }

    public Products addCategory(UUID productId, UUID categoryId) {
        Products product = getProductById(productId);

        Categories category = categoryService.getCategoryById(categoryId).orElse(null);

        if (product != null && category != null) {
            product.setCategory(category);
            category.getProducts().add(product);
            return productsRepository.save(product);
        }

        return null;
    }

    public Products addComment(UUID productId, UUID userId,Review categorieData) {
        Products product = getProductById(productId);
        Users user = userService.findById(userId);

        Review review = reviewService.create(categorieData);
        product.getReviews().add(review);
        review.setProduct(product);
        review.setUser(user);
        user.getReviews().add(review);

        return productsRepository.save(product);
    }

    private void validateProduct(Products product) {
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            throw new RuntimeException("Product name cannot be null or empty");
        }

        if (product.getProductDescription() == null || product.getProductDescription().isBlank()) {
            throw new RuntimeException("Product description cannot be null or empty");
        }

        if (product.getSummary() == null || product.getSummary().isBlank()) {
            throw new RuntimeException("Summary cannot be null or empty");
        }

        if (product.getManufacturer() == null || product.getManufacturer().isBlank()) {
            throw new RuntimeException("Manufacturer cannot be null or empty");
        }

        if (product.getBrandName() == null || product.getBrandName().isBlank()) {
            throw new RuntimeException("Brand name cannot be null or empty");
        }
    }

}
