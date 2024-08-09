package com.jakhtar;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Cart cart;
    private Checkout checkout;

    public UserInterface(Scanner scanner, Cart cart, Checkout checkout) {
        this.scanner = scanner;
        this.cart = cart;
        this.checkout = checkout;
    }

    public void startScanning() {
        
        while(true) {
            System.out.println("Type 'exit' to end the program.");
            System.out.println("Add item to cart...");
            System.out.println("Product Name: ");
            String validProductName = getValidProduct();
            
            if (validProductName != null) {
                SKU sku = SKU.valueOf(validProductName);
                this.cart.addProductToCart(new Product(sku.getProductName(), sku.getPrice()));
                this.cart.displayCart();
            } else {
                break;
            }
            
        }

        if (!this.cart.getAllCartItems().isEmpty()) {
            this.doCheckout();
        }
    }

    public void doCheckout() {
        this.setDiscountRules();
        this.cart.displayCart();
        double total = this.applyDiscount(this.checkout.getDiscounts());
        double totalDiscount = (this.cart.getCartTotal() - total);
        System.out.println("Discount\t\t" + new DecimalFormat("0.##").format(totalDiscount));
        System.out.println("Total: \t\t\t" + new DecimalFormat("0.##").format(total));
    }

    public double applyDiscount(List<Discount> discounts) {
        double total = 0.0;
        for (Discount d : discounts) {
            List<Product> p = this.cart.getAllByName(d.getProductName());
            total += (p.size() / d.getQuantity()) * d.getDiscountPrice();
            total += (p.size() % d.getQuantity()) * p.get(0).getUnitPrice();
        }

        total += this.cart.getAllCartItems().stream()
            .filter(cartItem -> discounts.stream()
                .noneMatch(discount -> discount.getProductName().equals(cartItem.getProductName())))
            .mapToDouble(p -> p.getUnitPrice())
            .sum();
        return total;
    }

    private void setDiscountRules() {
        while (true) {
            System.out.println("Enter product name to to set discount for: ");
            String productName = getValidProduct();

            if (productName != null) {
                System.out.println("Enter required quantity to apply discount: ");
                int qty = Integer.valueOf(this.scanner.nextLine());
                System.out.println("Enter discount price: ");
                double discountedPrice = Double.valueOf(this.scanner.nextLine());
                this.checkout.addDiscount(productName, qty, discountedPrice);
            } else {
                break;
            }
        }
    }

    private String getValidProduct() {
       
        while (true) {
            String userInput = this.scanner.nextLine().trim().toUpperCase();

            if (userInput.equals("EXIT")) {
                break;
            }

            try {
                SKU.valueOf(userInput);
                return userInput;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product name! Please try 'A', 'B' 'C' or 'D'");
            }
        }
        return null;
    }
}
