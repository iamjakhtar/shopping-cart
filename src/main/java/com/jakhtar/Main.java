package com.jakhtar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(new ArrayList<>());
        List<Discount> discounts = Arrays.asList(new Discount("A", 3, 1.3), new Discount("B", 2, 0.45));
        Checkout checkout = new Checkout(discounts, cart);
        Scanner scan = new Scanner(System.in);
        UserInterface ui = new UserInterface(scan, cart, checkout);
        ui.startScanning();
    }
}
    