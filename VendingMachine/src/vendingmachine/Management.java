package vendingmachine;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JComboBox;

public class Management {
	static ArrayList<Items> popular = new ArrayList<Items>();
	static ArrayList<Items> availableStock = new ArrayList<Items>();
	static ArrayList<VendingMachine> machines = new ArrayList<VendingMachine>();

	// Uses Inventory text file to populate availabeStock
	public static void setStock() throws FileNotFoundException, ParseException {
		File file = new File("Inventory.txt");
		Scanner items = new Scanner(file);

		String line;

		while (items.hasNextLine()) {
			line = items.nextLine();
			String arr[] = line.split(",");
			Items item = new Items();

			// Instantiate item object
			item.itemName = arr[0];
			item.itemPrice = arr[1];
			item.itemCount = Integer.valueOf(arr[2]);
			item.expDate = arr[3];
			item.itemStatus = checkItemStatus(arr[3], arr[4]);

			// Insert data into array
			availableStock.add(item);
		}

		items.close();
	}

	// Checks the status an item
	static String checkItemStatus(String exp_date, String status) throws ParseException {
		SimpleDateFormat mdy = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();

		Date exp = mdy.parse(exp_date);

		if (today.compareTo(exp) > 0)
			status = "Expired";

		return status;
	}

	// Returns the current inventory
	public static String displayInventory(ArrayList<Items> inventory) {
		String str = "";

		for (int i = 0; i < inventory.size(); i++) {
			str = str + String.format("%-9s %-15s %-13s %-5s", inventory.get(i).itemLocation, inventory.get(i).itemName,
					"$" + inventory.get(i).itemPrice, inventory.get(i).itemCount) + "\n";
		}

		return str;
	}

	// Returns the status of Machine
	static String displayMachineStatus(VendingMachine v) {

		String machineStatus = v.getStatus();

		if (machineStatus.equals("Online")) {
			return "Vending Machine is Online";
		} else if (machineStatus.equals("Restocking")) {
			return "Vending Machine is currently being Restocked";
		}

		return "Vending Machine is Offline";
	}

	// Returns the purchase history
	static String displayHistory(ArrayList<String> history) {

		String message = "";

		for (int i = 0; i < history.size(); i++)
			message = message + history.get(i) + "\n";

		return message;
	}

	// Fills popular list with items from inventory
	static void populatePopular(ArrayList<Items> inventory) {
		for (int i = 0; i < inventory.size(); i++)
			popular.add(inventory.get(i));
	}

	// Ranks items in popular list from most popular to least popular
	static void popularItems(ArrayList<Items> inventory) {

		popular.clear();

		populatePopular(inventory);

		int size = popular.size();

		for (int i = 0; i < size; i++) {
			int max_index = i;
			int max = popular.get(i).purchaseCount;

			for (int j = i; j < size; j++) {

				if (max < popular.get(j).purchaseCount) {
					max_index = j;
					max = popular.get(j).purchaseCount;
				}
			}

			Items temp = popular.get(i);
			popular.set(i, popular.get(max_index));
			popular.set(max_index, temp);

		}
	}

	// Returns popular list
	static String displayPopular(VendingMachine v) {
		String str = "";

		popularItems(v.inventory);

		str = str + String.format("%-8s %-12s %-8s %-10s %-9s", "Rank", "Name", "Price", "Quantity", "Purchased")
				+ "\n";

		for (int i = 0; i < popular.size(); i++) {
			str = str
					+ String.format("%-8s %-12s %-11s %-11s %-5s", i + 1, popular.get(i).itemName,
							"$" + popular.get(i).itemPrice, popular.get(i).itemCount, popular.get(i).purchaseCount)
					+ "\n";
		}

		return str;
	}

	// Returns list of expired items in inventory
	static String displayExpired(VendingMachine v) throws ParseException {
		String str = "",
				header = String.format("%-13s %-13s %-13s %-9s", "Name", "Price", "Quantity", "Purchased") + "\n";

		str = str + header;

		for (int i = 0; i < v.inventory.size(); i++) {
			String item_status = checkItemStatus(v.inventory.get(i).expDate, v.inventory.get(i).itemStatus);
			v.inventory.get(i).itemStatus = item_status;

			if (item_status.equals("Expired")) {
				str = str + String.format("%-13s %-16s %-14s %-5s", v.inventory.get(i).itemName,
						"$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount,
						v.inventory.get(i).purchaseCount) + "\n";
			}

		}

		if (str.equals(header)) {
			str = "Currently No Expired Items";
		}

		return str;
	}

	// Returns list of recalled items in inventory
	static String displayRecalled(VendingMachine v) {
		String str = "",
		header = String.format("%-13s %-13s %-13s %-9s", "Name", "Price", "Quantity", "Purchased") + "\n";

		str = str + header;

		for (int i = 0; i < v.inventory.size(); i++) {
			String status = v.inventory.get(i).itemStatus;

			if (status.equals("Recall")) {
				str = str + String.format("%-13s %-16s %-14s %-5s", v.inventory.get(i).itemName,
						"$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount,
						v.inventory.get(i).purchaseCount) + "\n";
			}

		}

		if (str.equals(header)) {
			str = "Currently No Recalled Items";
		}
		return str;
	}

	// Adds item to remove list
	static void addToRemove(VendingMachine v, int index) {
		Items item = v.inventory.get(index);
		v.remove.add(item);
	}

	// Removes item from remove list
	static void delToRemove(VendingMachine v, int index) {
		Items item = v.inventory.get(index);
		v.remove.remove(item);
	}

	// Adds items to add list
	static void addToAdd(ArrayList<JComboBox<String>> list, VendingMachine v) {
		for (int j = 0; j < list.size(); j++) {
			String str = list.get(j).getItemAt(list.get(j).getSelectedIndex());

			String[] arr = str.split("\\s+");
			DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate current = LocalDate.now();
			String today = date.format(current);

			for (int i = 0; i < availableStock.size(); i++) {
				if (availableStock.get(i).itemName.equals(arr[1])) {
					Items item = new Items();
					item.copy(availableStock.get(i));
					item.itemLocation = arr[0];
					item.itemStatus = "OK";
					item.expDate = today;
					int quantity = 10;

					for (int h = 0; h < v.inventory.size(); h++) {
						if (availableStock.get(i).itemName.equals(v.inventory.get(h).itemName)
								&& (availableStock.get(i).itemStatus.equals("OK"))) {
							quantity = quantity - v.inventory.get(h).itemCount;
						}
					}

					item.itemCount = quantity;

					availableStock.get(i).itemStatus = "OK";

					v.add.add(item);
				}
			}
		}
	}

	// Returns available stock
	static ArrayList<Items> getValidItems() {
		return availableStock;
	}
	
	public static void main(String[] args) throws FileNotFoundException, ParseException {

		String[] locations = { "Moraga Way, Sacramento, CA",
				"6000 Jed Smith Dr, Sacramento, CA", "6002 J St, Sacramento, CA" };
		
		setStock();
		
		// Create Multiple Vending Machines 3 for now
		for (int i = 0; i < 3; i++) {
			VendingMachine v = new VendingMachine();
			v.setLocation(locations[i]);
			machines.add(v);
		}
		

		//Intializes interfaces
		CustomerGUI frame = new CustomerGUI();
		ManagementGUI frame2 = new ManagementGUI();
		RestockerGUI frame3 = new RestockerGUI();

	}

}
