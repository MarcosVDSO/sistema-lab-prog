package com.labprog.labprog.controllers;

import com.labprog.labprog.DTO.ProductSkuDTO;
import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.services.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productSku")
public class ProductSkuController {
    @Autowired
    private ProductSkuService productSkuServices;

    @PostMapping
    public ResponseEntity<ProductSkus> createProductSku(@RequestBody ProductSkuDTO productSkuDTO) {

        try {
            ProductSkus productSkus = new ProductSkus(productSkuDTO);
            ProductSkus savedProducts = productSkuServices.createProductSku(productSkus);
            return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<ProductSkus>> getAllProductSkus() {
        try {
            List<ProductSkus> productSkus = productSkuServices.getAllProductSkus();
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

    @PutMapping("/{id}")
    public ResponseEntity<ProductSkus> updateProductSku(@PathVariable UUID id, @RequestBody ProductSkuDTO productSkuDTO) {

        try {
            ProductSkus productSkus = new ProductSkus(productSkuDTO);
            ProductSkus updatedProductSkus = productSkuServices.updateProductSku(id, productSkus);
            return new ResponseEntity<>(updatedProductSkus, HttpStatus.OK);
        } catch (RuntimeException e) {
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
