package com.example.product_service.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.product_service.ProductServiceApplication;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSteps {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<Product[]> responseEntity;

    @Given("the following products exist")
    public void the_following_products_exist(List<Map<String, String>> products) {
        products.forEach(productMap -> {
            Product product = new Product();
            product.setName(productMap.get("name"));
            product.setPrice(Double.parseDouble(productMap.get("price")));
            product.setQuantity(Integer.parseInt(productMap.get("quantity")));
            productRepository.save(product);
        });
    }

    @When("I request all products")
    public void i_request_all_products() {
        responseEntity = restTemplate.getForEntity("/api/products", Product[].class);
    }

    @Then("I should receive a list of products")
    public void i_should_receive_a_list_of_products() {
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotEmpty();
    }
}

