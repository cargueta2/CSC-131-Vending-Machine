package vendingmachine;

import java.util.ArrayList;
import java.util.Scanner;

public class Management {
	static Scanner scan = new Scanner(System.in);

	static void printCommands() {
		System.out.println("Choose Command:");
		System.out.println("View Inventory press 0");
		System.out.println("View Purchase History press 1");
		System.out.println("View Machine Status press 2");
		System.out.println("To Exit type exit");
		System.out.print("User Input>");
	}

	static void chooseCommand(VendingMachine v) {

		printCommands();

		String command;
		command = scan.next();

		while (!(command.equals("exit"))) {
			switch (command) {
			case "0":
				viewInventory(v);
				break;
			case "1":
				getAnalytics(v.history);
				break;
			case "2":
				viewMachineStatus(v);
				break;
			default:
				System.out.println("Input Invalid. Try Again.");
				break;
			}
			printCommands();
			command = scan.next();
		}

		System.out.println();
	}

	static void printInventory(ArrayList<Items> inventory) {
		System.out.println("\n-Inventory");

		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(String.format("%-5s %-10s %-8s %-5s %-10s", inventory.get(i).itemLocation,
					inventory.get(i).itemName, "$"+ inventory.get(i).itemPrice, inventory.get(i).itemCount,
					inventory.get(i).expDate));
		}

		System.out.println();
	}
	
	static ArrayList<Items> getInventory(VendingMachine v){
		return v.inventory;
	}

	static void viewInventory(VendingMachine v) {
		
		printInventory(getInventory(v));
	}

	static String getMachineStatus(VendingMachine v) {
		return v.machineStatus;
	}
	
	static void viewMachineStatus(VendingMachine v) {
		System.out.println("\n-Machine Status");
		
		String machineStatus = getMachineStatus(v);
		
		if (machineStatus.equals("Online")) {
			System.out.println("Vending Machine is Online");
		} else if (machineStatus.equals("Restocking")) {
			System.out.println("Vending Machine is currently being Restocked");
		} else {
			System.out.println("Vending Machine is Offline");
		}

		System.out.println();
	}

	static void getAnalytics(ArrayList<String> history) {

		System.out.println("\n-Purchase History");

		if (history.size() != 0) {
			for (int i = 0; i < history.size(); i++)
				System.out.println(history.get(i));
		} else {
			System.out.println("No Current Purchase History");
		}

		System.out.println();
	}

	public static void startManagment(ArrayList<VendingMachine> machines) {
		Main.printMachineSelection();
		String input;
		input = scan.next();

		while (!(input.equals("exit"))) {
			boolean valid = false;

			if (Integer.valueOf(input) <= machines.size())
				valid = true;

			if (valid) {
				System.out.println("\n-Vending Machine #" + (Integer.valueOf(input)));
				VendingMachine v = machines.get(Integer.valueOf(input) - 1);
				chooseCommand(v);
			} else {
				System.out.println("Invalid Input. Try Again");
			}

			Main.printMachineSelection();
			input = scan.next();
		}

		System.out.println();
	}
}
