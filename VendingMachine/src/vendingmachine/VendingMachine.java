package vendingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
	static Scanner scan = new Scanner(System.in);
	String machineStatus;

	// List of Items in inventory
	ArrayList<Items> inventory = new ArrayList<Items>();

	// Contains all products purchased
	ArrayList<String> history = new ArrayList<String>();

	// Loads Vending Machine
	void setInventory() throws FileNotFoundException {
		File file = new File("Inventory.txt");
		Scanner items = new Scanner(file);

		String line;

		while (items.hasNextLine()) {
			line = items.nextLine();
			String arr[] = line.split(",");
			Items item = new Items();

			// Instantiate item object
			item.itemLocation = arr[0];
			item.itemName = arr[1];
			item.itemPrice = arr[2];
			item.itemCount = Integer.valueOf(arr[3]);
			item.expDate = arr[4];
			item.itemStatus = arr[5];

			// Insert data into array
			inventory.add(item);
		}

		items.close();
	}

	public VendingMachine() throws FileNotFoundException {
		machineStatus = "Online";
		setInventory();
	}

}
