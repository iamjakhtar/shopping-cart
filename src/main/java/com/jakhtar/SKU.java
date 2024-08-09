package com.jakhtar;

public enum SKU {
    
    A(0.50),
    B(0.30),
    C(0.20),
    D(0.15);

    private double price;

    private SKU(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProductName() {
        return this.name();
    }

}
