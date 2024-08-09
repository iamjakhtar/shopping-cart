package com.jakhtar;

public class Discount {
    private String productName;
    private int quantity;
    private double discountPrice;

    public Discount(String productName, int quantity, double discountPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.discountPrice = discountPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
    
}
