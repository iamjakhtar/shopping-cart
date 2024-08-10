package com.jakhtar;

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

        while (true) {
            System.out.println("Type 'exit' to end the program.");
            System.out.println("Add item to cart. Enter a product Name: ");
            String validProductName = getValidProduct();

            if (validProductName != null) {
                SKU sku = SKU.valueOf(validProductName);
                this.cart.addProductToCart(new Product(sku.getProductName(), sku.getPrice()));
                double total = this.cart.getCartTotal();
                double totalDiscount = this.checkout.getTotalDiscount();
                this.checkout.printCheckoutSummary(total, totalDiscount);

            } else {
                this.checkout.printCheckoutMessage();
                break;
            }

        }
    }

    private String getValidProduct() {

        while (true) {
            String input = this.scanner.nextLine().trim().toUpperCase();

            if (input.toUpperCase().equals("EXIT")) {
                this.checkout.printCheckoutMessage();
                break;
            }
            
            try {
                SKU.valueOf(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product name! Please try 'A', 'B' 'C' or 'D'");

            } 
        }
        return null;
    }

}
