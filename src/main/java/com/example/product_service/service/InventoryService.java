package com.example.product_service.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InventoryService {

    public Flux<String> getInventoryUpdates() {
        return Flux.just("Item1", "Item2", "Item3")
                .delayElements(Duration.ofSeconds(1));
    }
}
