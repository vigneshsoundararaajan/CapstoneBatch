package com.example.product_service;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.productservice.steps",
    plugin = {"pretty", "html:target/cucumber.html"}
)
public class CucumberTest {
}

