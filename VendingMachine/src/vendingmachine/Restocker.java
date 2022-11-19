package vendingmachine;

import java.util.ArrayList;
import java.util.Scanner;

public class Restocker {
	static Scanner scan = new Scanner(System.in);

	static void printCommands() {
		System.out.println("Choose Commnand:");
		System.out.println("View Vending Machine Status press 0");
		System.out.println("Set Vending Machine Status press 1");
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
				getMachineStatus(v.machineStatus);
				break;
			case "1":
				setMachineStatus(v);
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

	static void setMachineStatus(VendingMachine v) {
		String setStatus, status = v.machineStatus;
		
		System.out.println("\n-Set Vending Machine Status");
		do {
			System.out.println("To set status to Online type 0");
			System.out.println("To set status to Restocking type 1");
			System.out.println("To exit type 2");
			System.out.print("User Input>");

			setStatus = scan.next();

			switch (setStatus) {
			case ("0"):
				if (status.equals("Online")) {
					System.out.println("Vending machine status is already set to Online");
				} else {
					v.machineStatus = "Online";
					System.out.println("Vending machine status set to Online");
				}
				break;
			case ("1"):
				if (status.equals("Restocking")) {
					System.out.println("Vending machine status is already set to Restocking");
				} else {
					status = "Restocking";
					v.machineStatus = status;
					System.out.println("Vending machine status set to Restocking");
				}
				break;
			default:
				System.out.println("Invalid Input. Try Again.");
				break;
			}

			System.out.println();

		} while (!(setStatus.equals("2")));

	}

	static void getMachineStatus(String machineStatus) {
		System.out.println("\n-View Machine Status");

		if (machineStatus.equals("Online")) {
			System.out.println("Vending Machine is Online");
		} else if (machineStatus.equals("Restocking")) {
			System.out.println("Vending Machine is currently being Restocked");
		} else {
			System.out.println("Vending Machine is Offline");
		}
		System.out.println();
	}

	static void startRestocker(ArrayList<VendingMachine> machines) {
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

