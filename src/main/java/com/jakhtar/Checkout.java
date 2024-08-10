package com.jakhtar;

import java.text.DecimalFormat;
import java.util.List;

public class Checkout {
    private List<Discount> discounts;
    private Cart cart;

    public Checkout(List<Discount> discounts, Cart cart) {
        this.discounts = discounts;
        this.cart = cart;
    }

    public void addDiscount(String productName, int quantity, double discountedPrice) {
        Discount discount = new Discount(productName, quantity, discountedPrice);
        this.discounts.add(discount);
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

    public boolean checkForDiscount() {
        for (Discount d: this.discounts) {
             List<Product> list = this.cart.getAllCartItems().stream()
                .filter(p -> p.getProductName().equals(d.getProductName())).toList();
            if (list.size() >= d.getQuantity()) {
                return true;
            }
        }
        return false;
    }

    public double getTotalDiscount() {
        if (this.checkForDiscount()) {
            return applyDiscount(discounts);
        }
        return 0.0;
    }

    private double applyDiscount(List<Discount> discounts) {
        if (discounts.isEmpty()) {
            return 0.0;
        }
        double discountedPrice = 0.0;
        double netTotal = 0.0;
        for (Discount d : discounts) {
            List<Product> p = this.cart.getAllByName(d.getProductName());
            Product product;
            if (p.isEmpty()) {
                continue;
            } else {
                product = p.get(0);
            }
            
            if (this.isEligibleForDiscount(product)) {
                discountedPrice += (p.size() / d.getQuantity()) * d.getDiscountPrice();
                discountedPrice += (p.size() % d.getQuantity()) * product.getUnitPrice();
                discountedPrice = this.getProductTotal(cart, product) - discountedPrice;
                netTotal += this.getProductTotal(cart, product) - discountedPrice;
            } else {
                netTotal += this.getProductTotal(cart, product);
            }
            discountedPrice = 0.0;
        }

        return this.cart.getCartTotal() - netTotal;
    }

    private double getProductTotal(Cart cart, Product product) {
        return cart.getAllCartItems().stream()
            .filter(p -> p.getProductName().equals(product.getProductName()))
            .mapToDouble(p -> p.getUnitPrice()).sum();
    }

    public void printCheckoutSummary(double total, double totalDiscount) {
        this.cart.displayCart();
        System.out.println("Discount\t\t" + new DecimalFormat("0.##").format(totalDiscount));
        System.out.println("Net Total\t\t" + new DecimalFormat("0.##").format(total - totalDiscount));
        System.out.println("-".repeat(28));
    }
    
    public void printCheckoutMessage() {
        System.out.println("Thanks for shopping!");
        System.exit(0);
    }

    public boolean isEligibleForDiscount(Product product) {
        return this.discounts.stream()
            .anyMatch(p -> p.getProductName()
            .equalsIgnoreCase(product.getProductName()));
    }
}
