package com.example.product_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.strategy.DiscountStrategy;
import com.example.product_service.entity.Product;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired 
    private RestTemplate restTemplate;
    private DiscountStrategy discountStrategy;

    @HystrixCommand(fallbackMethod = "fallbackGetProducts")

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> fallbackGetProducts() { 
        return Collections.emptyList(); // Return an empty list
    }
    public void setDiscountStrategy(DiscountStrategy discountStrategy) { 
        this.discountStrategy = discountStrategy; 
    } 
    public double calculatePrice(double price) { 
        return discountStrategy.applyDiscount(price); 
    }

    public String getOtherServiceData() { 
        String url = "http://other-service/api/data"; 
        return restTemplate.getForObject(url, String.class); 
    }
}
