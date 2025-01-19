package com.labprog.labprog.controllers;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.services.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductSkus createProductSku(@RequestBody ProductSkus productSku) {
        return productSkuServices.createProductSku(productSku);
    }

    @GetMapping
    public List<ProductSkus> getAllProductSkus() {
        return productSkuServices.getAllProductSkus();
    }

    @GetMapping("/{id}")
    public ProductSkus getProductSkuById(@PathVariable UUID id) {
        return productSkuServices.getProductSkuById(id);
    }

    @PutMapping("/{id}")
    public ProductSkus updateProductSku(@PathVariable UUID id, @RequestBody ProductSkus productSku) {
        return productSkuServices.updateProductSku(id, productSku);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductSkuById(@PathVariable UUID id) {
        productSkuServices.deleteProductSkuById(id);
        return ResponseEntity.noContent().build();
    }
}
