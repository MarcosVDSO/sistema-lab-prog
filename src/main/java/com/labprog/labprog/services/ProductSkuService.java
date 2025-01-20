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

    public void deleteProductSkuById(UUID id) {
        ProductSkus productSku = getProductSkuById(id);
        productSkusRepository.delete(productSku);
    }

}
