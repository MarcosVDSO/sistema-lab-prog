package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.*;
import com.labprog.labprog.model.entities.Categories;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.entities.Products;
import com.labprog.labprog.model.entities.Review;
import com.labprog.labprog.services.ProductService;
import com.labprog.labprog.services.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productServices;
    @Autowired
    private UploadImageService uploadImageService;

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
    public ResponseEntity<Products> addProductSkuToProduct(
            @PathVariable UUID id, @ModelAttribute ProductSkuForm form
            ) throws IOException {
        try {

            String imageUrl = uploadImageService.saveImage(form);

            ProductSkus productSku = ProductSkus.builder()
                    .sku(form.getSku())
                    .price(form.getPrice())
                    .stockQuantity(form.getStockQuantity())
                    .productImage(imageUrl)
                    .build();

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

    @PostMapping("/addComment/{productId}/{userId}")
    public ResponseEntity<Products> addComment(@PathVariable UUID productId, @PathVariable UUID userId, @RequestBody ReviewDTO reviewDTO) {
        try {
            Review review = new Review(reviewDTO);
            Products product = productServices.addComment(productId, userId, review);
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
