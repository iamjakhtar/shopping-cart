package com.jakhtar;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Cart cart;
    private Checkout checkout;
    private boolean stopScanning = false;
    private boolean performCheckout = true;

    public UserInterface(Scanner scanner, Cart cart, Checkout checkout) {
        this.scanner = scanner;
        this.cart = cart;
        this.checkout = checkout;
    }

    public void startScanning() {

        while (true) {

            if (stopScanning) {
                break;
            }

            this.exitPrompt();
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
        this.printCheckoutSummary(totalDiscount, totalDiscount);
    }

    public double applyDiscount(List<Discount> discounts) {
        double total = 0.0;
        for (Discount d : discounts) {
            List<Product> p = this.cart.getAllByName(d.getProductName());
            total += (p.size() / d.getQuantity()) * d.getDiscountPrice();
            total += (p.size() % d.getQuantity()) * p.get(0).getUnitPrice();
        }

        //adding price of products not eligible for discount
        total += this.cart.getAllCartItems().stream()
                .filter(cartItem -> discounts.stream()
                        .noneMatch(discount -> discount.getProductName().equals(cartItem.getProductName())))
                .mapToDouble(p -> p.getUnitPrice())
                .sum();
        return total;
    }

    private void setDiscountRules() {
        while (true) {
            this.exitPrompt();
            System.out.println("Enter product name to to set discount for: ");
            String productName = this.getValidProduct();

            if (productName != null) {
                System.out.println("Enter required quantity to apply discount: ");
                int qty = this.getValidQuantity();
                System.out.println("Enter discount price: ");
                double discountedPrice = this.getValidPrice(productName, qty);
                this.checkout.addDiscount(productName, qty, discountedPrice);
            } else {
                break;
            }
        }
    }

    private String getValidProduct() {

        while (true) {
            String input = this.scanner.nextLine().trim().toUpperCase();
            this.checkExitCommand(input); 
            
            if (stopScanning) {
                return null;
            }
            try {
                SKU.valueOf(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product name! Please try 'A', 'B' 'C' or 'D'");

            }
        }
    }

    private int getValidQuantity() {
        while (true) {
            this.exitPrompt();
            try {
                String input = this.scanner.nextLine().trim();
                this.checkExitCommand(input);
                
                if (stopScanning) return 0;

                int qty = Integer.parseInt(input);

                if (qty > 0) {
                    return qty;
                } else {
                    System.out.println("Please enter a positive whole number e.g. 1, 2, 3");

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity! Please enter a whole number e.g. 1, 2, 3");

            }
        }
    }

    private double getValidPrice(String productName, int qty) {
        SKU sku = SKU.valueOf(productName);
        double unitPrice = sku.getPrice();
        double maxPrice = unitPrice * qty;
        while (true) {
            this.exitPrompt();
            try {
                String input = this.scanner.nextLine().trim();
                // this.checkExitCommand(input);

                if (stopScanning) return 0.0;

                double price = Double.parseDouble(input);

                if (price > unitPrice && price < maxPrice) {
                    return price;
                } else {
                    System.out.println("Discount must be greater than " + unitPrice + " and less than " + maxPrice);

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a decimal number e.g 1.5, 1.2");
            }
        }
    }

    private void exitPrompt() {
        System.out.println("Type 'exit' to end the program.");
    }

    private void checkExitCommand(String userInput) {
        if (userInput.toUpperCase().equals("END")) {
            System.out.println("Thanks for shopping.");
            System.exit(0);
        }
    }

    private void printCheckoutSummary(double totalDiscount, double total) {
        System.out.println("Discount\t\t" + new DecimalFormat("0.##").format(totalDiscount));
        System.out.println("Total: \t\t\t" + new DecimalFormat("0.##").format(total));
    }
}
