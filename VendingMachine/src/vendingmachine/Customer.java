package vendingmachine;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.*;

public class Customer {
	static Scanner scan = new Scanner(System.in);

	// Prints each line of items in vending machine
	static void printLine(ArrayList<Items> inventory, int start, int end) {
		while (start < end) {
			String content = "[" + inventory.get(start).itemLocation + " " + inventory.get(start).itemName + " $" 
								+ inventory.get(start).itemPrice+"]";

			System.out.print(String.format("%-25s", content));
			start++;
		}

		System.out.println();
	}

	// Prints Inventory
	static void printInventory(ArrayList<Items> inventory) {
		printLine(inventory, 0, inventory.size() / 2);
		printLine(inventory, inventory.size() / 2, inventory.size());

	}
	
	//Gets Vending Machine Status
	private static String getMachineStatus(VendingMachine v) {
		return v.machineStatus;
	}
	
	// Finds Item Location
	static Items getItem(ArrayList<Items> inventory, String itemLocation) {

		Items item = new Items();
		boolean found = false;

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).itemLocation.equals(itemLocation)) {
				item = inventory.get(i);
				found = true;
			}
		}

		if (!found)
			item.itemLocation = "Not Found!";

		return item;
	}

	static void purchaseItem(VendingMachine v) throws IOException {
		printInventory(v.inventory);
		
		//Insert Money
		double credit = 0.0;
		credit = payment(credit);

		System.out.print("Please Select Item or type c to cancel> ");
		String itemLocation;
		itemLocation = scan.next();

		// keeps looping until user is done "d"
		while (!(itemLocation.equals("c"))) {

			Items item = getItem(v.inventory, itemLocation);

			// Checks if the item location is valid
			if (!(item.itemLocation.equals("Not Found!"))) {

				// Check if item is In Stock
				if (item.itemCount != 0) {

					//Prints item name
					System.out.println("You have chosen: " + item.itemName);

					//Prints item price
					System.out.println("Price of " + item.itemName + ": " + item.itemPrice);
					
					//Converts itemPrice from string to double
					double price = Double.parseDouble(item.itemPrice);
					
					// Check if enough credit
					if (credit < price) {
						System.out.println("Insufficient Funds. Please Add More Money.");
					} else {
						// end of transaction
						credit = credit - price;
						System.out.println("Thank You! Please Take Item.");
						updateInventory(v.inventory, item.itemName);
						updatePurchaseHistory(v.history, item.itemName);
					}
				} else {
					System.out.println("Sorry Item Out of Stock. Please Choose Another Item.");
				}
			} else {
				System.out.println("Item Not Found. Please try again.");
			}
			System.out.println();
			printInventory(v.inventory);
			credit = payment(credit);
			System.out.print("Please Select Item or type c to cancel>");
			itemLocation = scan.next();
		}
		
		int coins[] = giveChangeBack(credit);
		
		printChange(credit,coins);

		System.out.println();
	}

	static double payment(double credit) {
		boolean done = false;

		while (!done) {
			System.out.println("Credit: " + credit);
			System.out.print("Insert Money or press d if done>");
			String input = scan.next();

			if (input.equals("d")) {
				done = true;
			} else if (paymentValidity(input)) {
				credit = credit + Double.parseDouble(input);
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}
		return credit;
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
			return true;
		case "0.1":
			return true;
		case "0.05":
			return true;
		case "0.01":
			return true;
		default:
			return false;
		}
	}

	// Prints coin given in change back
	static void printChange(double change, int coins[]) {
		System.out.println("Change Back: " + change);
		//System.out.println(
				//"Quarters: " + coins[0] + ", Dimes: " + coins[1] + ", Nickles: " + coins[2] + ", Pennies: " + coins[3]);
		System.out.println("Quarters: " + coins[0]);
	}

	// Figures out what coins to give in change back
	static int[] giveChangeBack(double num) {
		double change = Math.abs(num);
		int coins[] = new int[4];

		while (change > 0.00) {
			if (change >= 0.25) {
				coins[0]++;
				change = change - 0.25;
			} else if (change >= 0.10) {
				coins[1]++;
				change = change - 0.10;
			} else if (change >= 0.05) {
				coins[2]++;
				change = change - 0.05;
			} else {
				coins[3]++;
				change = change - 0.01;
			}
		}

		return coins;
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
	static void updatePurchaseHistory(ArrayList<String> history, String itemName) throws IOException {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
		LocalDateTime current = LocalDateTime.now();

		String addPurchase = date.format(current) + " " + itemName;

		history.add(addPurchase);
	}
	
	public static void startCustomer(ArrayList<VendingMachine> machines) throws IOException {
		Main.printMachineSelection();
		String input;
		input = scan.next();
		
		//Prompts user to choose vending machine
		while (!(input.equals("exit"))) {
			boolean valid = false;
			
			//Checks input is within range of machines available
			if (Integer.valueOf(input) <= machines.size())
				valid = true;

			if (valid) {
				System.out.println("\n-Vending Machine #" + (Integer.valueOf(input)));
				
				//Retrieves info of selected vending machine
				VendingMachine v = machines.get(Integer.valueOf(input) - 1);
				
				//Checks the status of the machine
				if(getMachineStatus(v).equals("Online")) {
					purchaseItem(v);
				}else {
					System.out.println("This Vending Machine is Currently " + getMachineStatus(v) + ". Select Another.\n");					
				}
			} else {
				System.out.println("Invalid Input. Try Again");
			}

			Main.printMachineSelection();
			input = scan.next();
		}

		System.out.println();
	}

}
