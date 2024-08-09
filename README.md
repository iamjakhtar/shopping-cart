# Shopping Cart
## Overview
A simple command line program that simulates the process of adding items to a shopping cart, applying discounts, and calculating the final total price. 


## Features
#### Add Products to Cart: 
Users can add products to the shopping cart by entering the product name.
#### Set Discount Rules: 
Users can set discount rules, such as "Buy 3 for a discounted price."
#### Calculate Total Price: 
The system calculates the total price of items in the cart, applying discounts where applicable.
#### View Cart Summary: 
The system provides a summary of all items in the cart, including the unit price, discount applied, and total cost.

## Technologies Used
1. Java
2. Collections
3. Streams
4. Maven

##### Requirements
<br>
1.  JDK 8 or higher is installed on your machine.
<br>
2.  IDE: Any IDE of your choice 
<br>
3.  Maven as the build tool.

##### Installation
  Clone the Repository:
<br>
```
git clone https://github.com/yourusername/shopping-cart-checkout.git
```
  Navigate to the Project Directory:
```
cd shopping-cart-checkout
```
  Run the Project:
```
mvn clean install
mvn exec:java -Dexec.mainClass="com.jakhtar.Main"
```
Start Adding Items:
Enter product names (A, B, C, D) to add them to your cart.
Type exit to stop adding items and proceed to checkout.
Set Discount Rules:

After entering all items, you can set discount rules. For example, you might enter "A" as the product name, "3" as the quantity, and "1.30" as the discounted price for three units.

The program will display a summary of all items, discounts applied, and the total cost.
Example Usage

```
Type 'exit' to end the program.
Add item to cart...
Product Name: A
Product Name: B
Product Name: A
Product Name: C
Product Name: exit

Enter product name to set a discount for: A
Enter required quantity to apply discount: 3
Enter discount price: 1.30

****************************
****** Shopping Cart ******
****************************

Product           Unit Price
----------------------------
A                      0.5
B                      0.3
A                      0.5
C                      0.2
----------------------------
Cart total:           1.50
Discount              0.20
Total:                1.30

```


## Project Structure 
<br>
Main.java: The entry point of the application.
<br>
Product.java: Product entity with a name and unit price.
<br>
Cart.java: Entity for holding products added to the shopping cart.
<br>
Discount.java: Discount rule, including the product name, quantity, and discounted price.
<br>
Checkout.java: Contains discounts related data and functionality.
<br>
UserInterface.java: User input and command line functionality.
<br>
SKU.java: Enum for available products and their prices.

## Contributor
1. Jamil Akhtar
