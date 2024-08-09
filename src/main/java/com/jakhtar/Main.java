package com.jakhtar;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cart shoppingCart = new Cart(new ArrayList<>());
        Checkout checkout = new Checkout(new ArrayList<>());
        Scanner scan = new Scanner(System.in);
        UserInterface ui = new UserInterface(scan, shoppingCart, checkout);
        ui.startScanning();
    }
}
    