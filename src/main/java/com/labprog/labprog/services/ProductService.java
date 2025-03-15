package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.entities.Review;
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

    public Products createProduct(Products product) {

        this.validateProduct(product);
        if (product.getProductSkus() == null) {
            product.setProductSkus(new ArrayList<>());
        }
        return productsRepository.save(product);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(UUID id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id" + id));
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
            throw new RuntimeException("Product not found with id" + id);
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

    public Products addComment(UUID productId, Review categorieData) {
        Products product = getProductById(productId);

        Review review = reviewService.create(categorieData);
        product.getReviews().add(review);
        review.setProduct(product);

        return productsRepository.save(product);
    }

    private void validateProduct(Products product) {
        if (product.getProductName() == null) {
            throw new RuntimeException("Product name is required");
        }

        if (product.getProductDescription() == null) {
            throw new RuntimeException("Product description is empty");
        }

        if (product.getSummary() == null) {
            throw new RuntimeException("Product summary is empty");
        }

        if (product.getManufacturer() == null) {
            throw new RuntimeException("Product manufacturer is empty");
        }

        if (product.getBrandName() == null) {
            throw new RuntimeException("Product brand name is empty");
        }
    }

}
