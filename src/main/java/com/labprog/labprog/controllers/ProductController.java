package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.CategoryDTO;
import com.labprog.labprog.DTO.ProductDTO;
import com.labprog.labprog.DTO.ProductSkuDTO;
import com.labprog.labprog.DTO.ReviewDTO;
import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.entities.Review;
import com.labprog.labprog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Products> createProduct(@RequestBody ProductDTO productDTO) {

        try {
            Products products = new Products(productDTO);
            Products savedProducts = productServices.createProduct(products);
            return new ResponseEntity<>(savedProducts,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/productSku/{id}")
    public ResponseEntity<Products> addProductSkuToProduct(@PathVariable UUID id, @RequestBody ProductSkuDTO productSkuDTO) {
        try {
            ProductSkus productSku = new ProductSkus(productSkuDTO);
            Products product = productServices.addProductSku(id, productSku);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/newCategory/{id}")
    public ResponseEntity<Products> addCategoryToProduct(@PathVariable UUID id, @RequestBody CategoryDTO categoryDTO) {
        try {
            Categories categories = new Categories(categoryDTO);
            Products product = productServices.addNewCategory(id, categories);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/category/{productId}/{categoryId}")
    public ResponseEntity<Products> addCategoryToProduct(@PathVariable UUID productId, @PathVariable UUID categoryId) {
        try {
            Products product = productServices.addCategory(productId, categoryId);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/addComment/{productId}")
    public ResponseEntity<Products> addComment(@PathVariable UUID productId, @RequestBody ReviewDTO reviewDTO) {
        try {
            Review review = new Review(reviewDTO);
            Products product = productServices.addComment(productId, review);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = productServices.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable UUID id) {
        try {
            Products product = productServices.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {

        try {
            Products product = new Products(productDTO);
            Products updatedProduct = productServices.updateProduct(id, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Products> deleteProduct(@PathVariable UUID id) {

        try {
            productServices.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
