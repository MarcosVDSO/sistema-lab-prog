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

        if (productSkuData.getProduct() != null) {
            productSku.setProduct(productSkuData.getProduct());
        }

        if (productSkuData.getSku() != null) {
            productSku.setSku(productSkuData.getSku());
        }

        if (productSkuData.getPrice() != null) {
            productSku.setPrice(productSkuData.getPrice());
        }

        if (productSkuData.getStockQuantity() != null) {
            productSku.setStockQuantity(productSkuData.getStockQuantity());
        }

        if (productSkuData.getProductImage() != null) {
            productSku.setProductImage(productSkuData.getProductImage());
        }

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

        if (productSku.getStockQuantity() == null || productSku.getStockQuantity() <= 0) {
            throw new RuntimeException("Stock quantity value is not valid");
        }

        if (productSku.getSku() == null || productSku.getSku().isEmpty()) {
            throw new RuntimeException("Sku value is not valid");
        }

    }

}
