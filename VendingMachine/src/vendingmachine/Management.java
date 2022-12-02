package vendingmachine; 

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Management {
	static Scanner scan = new Scanner(System.in);
	private static ArrayList<Items> popular = new ArrayList<Items>();
	private static ArrayList<Items> remove = new ArrayList<Items>();
	private static ArrayList<Items> add = new ArrayList<Items>();
	static ArrayList<Items> availableStock = new ArrayList<Items>();

	public static void setStock() throws FileNotFoundException {
		File file = new File("Inventory.txt");
		Scanner items = new Scanner(file);

		String line;

		while( items.hasNextLine()){
			line = items.nextLine();
			String arr[] = line.split(",");
			Items item = new Items();

			// Instantiate item object
			item.itemName = arr[0];
			item.itemPrice = arr[1];
			item.itemCount = Integer.valueOf(arr[2]);
			item.expDate = arr[3];
			item.itemStatus = arr[4];
			item.present = false;

			// Insert data into array
			availableStock.add(item);
		}

		items.close();
	}
	
	public static String displayInventory(ArrayList<Items> inventory) {
		String str = "";

		for (int i = 0; i < inventory.size(); i++) {
			str = str + String.format("%-9s %-15s %-10s %-13s", inventory.get(i).itemLocation,
					inventory.get(i).itemName, "$" + inventory.get(i).itemPrice, inventory.get(i).itemCount) + "\n";
		}

		return str;
	}

	static String displayMachineStatus(VendingMachine v) {

		String machineStatus = v.getStatus();

		if (machineStatus.equals("Online")) {
			return "Vending Machine is Online";
		} else if (machineStatus.equals("Restocking")) {
			return "Vending Machine is currently being Restocked";
		}

		return "Vending Machine is Offline";
	}

	static String displayHistory(ArrayList<String> history) {

		String message = "";

		for (int i = 0; i < history.size(); i++)
			message = message + history.get(i) + "\n";

		return message;
	}

	static void populatePopular(ArrayList<Items> inventory) {
		for (int i = 0; i < inventory.size(); i++)
			popular.add(inventory.get(i));
	}

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

	static String displayPopular(VendingMachine v) {
		String str = "";

		popularItems(v.inventory);

		for (int i = 0; i < popular.size(); i++) {
			str = str
					+ String.format("%-5s %-10s %-8s %-5s %-10s", i + 1, popular.get(i).itemName,
							"$" + popular.get(i).itemPrice, popular.get(i).itemCount, popular.get(i).purchaseCount)
					+ "\n";
		}

		return str;
	}

	static String displayExpired(VendingMachine v) throws ParseException {
		String str = "";

		SimpleDateFormat mdy = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();

		for (int i = 0; i < v.inventory.size(); i++) {
			String exp_date = v.inventory.get(i).expDate;
			Date exp = mdy.parse(exp_date);

			if (today.compareTo(exp) > 0) {
				v.inventory.get(i).itemStatus = "Expired";
				
				str = str
						+ String.format("%-10s %-8s %-5s %-10s", v.inventory.get(i).itemName,
								"$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount, v.inventory.get(i).purchaseCount)
						+ "\n";
				
			}

		}
		return str;
	}

	static String displayRecalled(VendingMachine v) {
		String str = "";

		for (int i = 0; i < v.inventory.size(); i++) {
			String status = v.inventory.get(i).itemStatus;

			if (status.equals("Recall")) {
				str = str
						+ String.format(" %-10s %-8s %-5s %-10s",v.inventory.get(i).itemName,
								"$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount, v.inventory.get(i).purchaseCount)
						+ "\n";
			}

		}
		return str;
	}
	
	static void addToRemove(Items item){
		remove.add(item);
	}
	
	static void delToRemove(Items item) {
		remove.remove(item);
	}
	
	static String setRemoveInstruction(int index) {
		return "Done!";
	}
	
	static void addToAdd(Items item) {
		add.add(item);
	}
	
	static void delToAdd(Items item) {
		add.remove(item);
	}
	
	static String setAddInstruction(int index) {
		return "Done!";
	}

	public static void startManagment(ArrayList<VendingMachine> machines) {

	}
}
