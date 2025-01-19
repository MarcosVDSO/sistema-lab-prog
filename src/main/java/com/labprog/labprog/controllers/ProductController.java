package com.labprog.labprog.controllers;

import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productServices;

    @PostMapping
    public Products createProduct(@RequestBody Products products) {
        return productServices.createProduct(products);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productServices.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable UUID id) {
        return productServices.getProductById(id);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable UUID id, @RequestBody Products products) {
        return productServices.updateProduct(id, products);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
