package com.jakhtar;

import java.util.List;

public class Checkout {
    private List<Discount> discounts;

    public Checkout(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addDiscount(String productName, int quantity, double discountedPrice) {
        this.discounts.add(new Discount(productName, quantity, discountedPrice));
    }

    public List<Discount> getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public void displayDiscounts() {
        this.discounts.stream().forEach(d -> System.out.println(d));
    }
    
}
