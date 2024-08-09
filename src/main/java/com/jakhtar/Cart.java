package com.jakhtar;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public Cart(List<Product> products) {
        this.products = products;
    }

    public void addProductToCart(Product product) {
        this.products.add(product);
    }

    public List<Product> getAllCartItems() {
        return this.products;
    }

    public List<Product> getAllByName(String productName) {
        return this.products.stream().filter(p -> p.getProductName().equals(productName)).toList();
    }

    public List<Product> getFullPriceProducts(Discount d, List<Product> productList) {
        return productList.stream().filter(p -> !p.getProductName().equals(d.getProductName())).toList();
    }

    public double getCartTotal() {
        return this.getAllCartItems().stream().mapToDouble(p -> p.getUnitPrice()).sum();
    }

    public void displayCart() {
        System.out.println("*".repeat(28));
        System.out.println("*".repeat(6) + " Shopping Cart " + "*".repeat(6));
        System.out.println("*".repeat(28));
        System.out.println();
        System.out.println("Product\t\t Unit Price");
        System.out.println("-".repeat(28));
        this.getAllCartItems().stream().forEach(p -> System.out.println(p));
        System.out.println("*".repeat(28));
        System.out.println("Cart total: \t\t" + this.getCartTotal());
    }
}
    