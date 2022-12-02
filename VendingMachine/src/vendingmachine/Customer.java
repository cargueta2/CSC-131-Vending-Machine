package vendingmachine;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

public class Customer {

	// Gets Vending Machine Status
	private static String getMachineStatus(VendingMachine v) {
		return v.getStatus();
	}

	// Gets Item Info
	static Items getItem(ArrayList<Items> inventory, String itemLocation) {

		Items item = new Items();

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).itemLocation.equals(itemLocation)) {
				item = inventory.get(i);
			}
		}

		return item;
	}
	
	static boolean validInput(VendingMachine v, String input) {
		boolean valid = false;
		
		for (int i = 0; i < v.inventory.size(); i++) {
			if (v.inventory.get(i).itemLocation.equals(input)) {
				valid = true;
			}
		}
		
		return valid;
	}

	static boolean checkStock(Items item) {
		boolean inStock = false;

		if (item.itemCount > 0)
			inStock = true;
		
		return inStock;
	}

	static String purchaseItem(VendingMachine v, String itemLocation) {
		if (getMachineStatus(v).equals("Online")) {
			double credit = v.getCredit();
			Items item = getItem(v.inventory, itemLocation);
			
			// Check Stock
			if (checkStock(item) == true) {
				double price = Double.parseDouble(item.itemPrice);

				if (credit < price) {
					return "Insuffiencit Funds.\nPlease Add More Money.";
				}

				v.setCredit(credit - price);
				updateInventory(v.inventory, item.itemName);
				updatePurchaseHistory(v.history, item.itemName);
				updatePurchaseCount(item);
				return "Purchased " + item.itemName + "\nThank You!\nPlease Take Item.";
			}
			
			return "Out of Stock";

		}
		
		return "Vending Machine is Currently" + getMachineStatus(v);
	}

	static String invalidCurreny() {
		return "Invalid Currency. Try Again.";
	}

	static void insertMoney(Double credit, String money) {

		if (paymentValidity(money) == true) {
			credit = credit + Double.parseDouble(money);
		} else {
			invalidCurreny();
		}

	}

	static boolean paymentValidity(String money) {
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

	// Updates inventory after purchase
	static void updateInventory(ArrayList<Items> inventory, String itemName) {

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).itemName.equals(itemName)) {
				inventory.get(i).itemCount--;
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
	
	static void updatePurchaseCount(Items item) {
		item.purchaseCount++;
	}

}
