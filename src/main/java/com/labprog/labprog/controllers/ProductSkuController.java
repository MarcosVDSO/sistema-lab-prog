package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.ProductSkuDTO;
import com.labprog.labprog.DTO.ProductSkuForm;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.services.ProductSkuService;
import com.labprog.labprog.services.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productSku")
@CrossOrigin(origins = "https://system-lab-prog-front-production.up.railway.app", allowedHeaders = "*")
public class ProductSkuController {
    @Autowired
    private ProductSkuService productSkuServices;
    @Autowired
    private UploadImageService uploadImageService;

    @GetMapping
    public ResponseEntity<Page<ProductSkus>> getAllProductSkus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<ProductSkus> productSkus = productSkuServices.getAllProductSkus(page, size);
            return new ResponseEntity<>(productSkus, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSkus> getProductSkuById(@PathVariable UUID id) {
        try {
            ProductSkus productSkus = productSkuServices.getProductSkuById(id);
            return new ResponseEntity<>(productSkus, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<ProductSkus>> getAllProductSkusByProductId(
            @PathVariable UUID productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<ProductSkus> productSkus = productSkuServices.getProductSkusByProductId(productId, page, size);
            return new ResponseEntity<>(productSkus, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSkus> updateProductSku(@PathVariable UUID id,
                                                        @ModelAttribute ProductSkuForm form) {

        try {

            String imageUrl = uploadImageService.saveImage(form);

            ProductSkus productSkus = ProductSkus.builder()
                    .sku(form.getSku())
                    .price(form.getPrice())
                    .stockQuantity(form.getStockQuantity())
                    .productImage(imageUrl)
                    .build();
            ProductSkus updatedProductSkus = productSkuServices.updateProductSku(id, productSkus);

            return new ResponseEntity<>(updatedProductSkus, HttpStatus.OK);
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSkus> deleteProductSkuById(@PathVariable UUID id) {

        try {
            productSkuServices.deleteProductSkuById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
