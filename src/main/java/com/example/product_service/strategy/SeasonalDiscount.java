package com.example.product_service.strategy;

public class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.90; // 10% discount
    }
}

