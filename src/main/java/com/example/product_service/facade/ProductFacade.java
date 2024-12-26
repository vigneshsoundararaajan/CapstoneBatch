package com.example.product_service.facade;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacade {

    @Autowired
    private ProductService productService;

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    public Product createProduct(Product product) {
        return productService.saveProduct(product);
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}

