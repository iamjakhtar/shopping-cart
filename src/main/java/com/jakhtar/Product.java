package com.jakhtar;


public class Product {
    private String productName;
    private double unitPrice;

    public Product(String productName, double unitPrice) {
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return this.productName;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    @Override
    public String toString() {
        return productName + "\t\t\t" + unitPrice;
    }

}
