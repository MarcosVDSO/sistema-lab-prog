package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.repositories.ProductsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public Products createProduct(Products product) {

        this.validateProduct(product);

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
