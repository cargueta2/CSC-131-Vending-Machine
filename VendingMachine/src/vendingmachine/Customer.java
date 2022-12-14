package vendingmachine;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

public class Customer {
	// Get Item Info
	static Items getItem(ArrayList<Items> inventory, String itemLocation) {

		Items item = new Items();

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).itemLocation.equals(itemLocation)) {
				item = inventory.get(i);
			}
		}

		return item;
	}
	
	//Checks if user input is valid
	static boolean validInput(VendingMachine v, String input) {
		boolean valid = false;

		for (int i = 0; i < v.inventory.size(); i++) {
			if (v.inventory.get(i).itemLocation.equals(input)) {
				valid = true;
			}
		}

		return valid;
	}
	
	//Checks if item is in stock
	static boolean checkStock(Items item) {
		boolean inStock = false;

		if (item.itemCount > 0)
			inStock = true;

		return inStock;
	}
	
	//Completes purchase of item
	static String purchaseItem(VendingMachine v, String itemLocation) {
		//check if machine is online
		if (v.getStatus().equals("Online")) {
			double credit = v.getCredit();
			Items item = getItem(v.inventory, itemLocation);

			// Check Stock
			if (checkStock(item) == true) {
				double price = Double.parseDouble(item.itemPrice);
				
				//check funds
				if (credit < price) {
					return "Insufficient Funds.\nPlease Add More Money.";
				}
				
				//updates funds after purchase
				v.setCredit(credit - price);
				
				updateInventory(v, item.itemName);
				updatePurchaseHistory(v.history, item.itemName);
				
				return "Purchased " + item.itemName + "\nThank You!\nPlease Take Item.";
			}

			return "Out of Stock";

		}

		return "Vending Machine is Currently" + v.getStatus();
	}

	//check money validity
	public static boolean paymentValidity(String money) {
		switch (money) {
		case "20.0":
			return true;
		case "10.0":
			return true;
		case "5.0":
			return true;
		case "1.0":
			return true;
		case "0.25":
			return true;
		case "0.0":
			return true;
		default:
			return false;
		}
	}

	// Figures out how many quarters to give in change back
	static String giveChangeBack(VendingMachine v) {
		double change = v.getCredit();
		int quarters = 0;

		while (change > 0.00) {
			if (change >= 0.25) {
				quarters++;
				change = change - 0.25;
			}
		}

		v.setCredit(change);
		return String.valueOf(quarters);
	}

	// Updates inventory and purchase count after purchase
	static void updateInventory(VendingMachine v, String itemName) {

		for (int i = 0; i < v.inventory.size(); i++) {
			if (v.inventory.get(i).itemName.equals(itemName)) {
				v.inventory.get(i).itemCount--;
				v.inventory.get(i).purchaseCount++;
			}
		}
	}

	// Updates purchase history of machine with time date and item name
	static void updatePurchaseHistory(ArrayList<String> history, String itemName) {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
		LocalDateTime current = LocalDateTime.now();

		String addPurchase = date.format(current) + " " + itemName;

		history.add(addPurchase);

	}

}
