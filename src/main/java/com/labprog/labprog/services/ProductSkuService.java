package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.ProductSkus;
import com.labprog.labprog.model.repositories.ProductSkusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductSkuService {

    @Autowired
    private ProductSkusRepository productSkusRepository;

    public ProductSkus createProductSku(ProductSkus productSku) {

        this.validateProductSku(productSku);

        productSku.setCreatedAt(LocalDateTime.now());
        productSku.setUpdatedAt(LocalDateTime.now());

        return productSkusRepository.save(productSku);
    }

    public List<ProductSkus> getAllProductSkus() {
        return productSkusRepository.findAll();
    }

    public ProductSkus getProductSkuById(UUID id) {
        return productSkusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product sku not found with id" + id));
    }

    public ProductSkus updateProductSku(UUID id, ProductSkus productSkuData) {

        ProductSkus productSku = getProductSkuById(id);

        productSku.setProduct(productSkuData.getProduct());
        productSku.setPrice(productSkuData.getPrice());
        productSku.setQuantity(productSkuData.getQuantity());
        productSku.setUpdatedAt(LocalDateTime.now());

        return productSkusRepository.save(productSku);
    }

    public ProductSkus deleteProductSkuById(UUID id) {
        ProductSkus productSku = getProductSkuById(id);

        if (productSku == null) {
            throw new RuntimeException("Product sku not found with id" + id);
        }

        productSkusRepository.delete(productSku);
        return productSku;
    }

    private void validateProductSku(ProductSkus productSku) {

        if (productSku.getPrice() == null || productSku.getPrice() <= 0) {
            throw new RuntimeException("Price value is not valid");
        }

        if (productSku.getQuantity() == null || productSku.getQuantity() <= 0) {
            throw new RuntimeException("Quantity value is not valid");
        }

        if (productSku.getSku() == null || productSku.getSku().isEmpty()) {
            throw new RuntimeException("Sku value is not valid");
        }

    }

}
