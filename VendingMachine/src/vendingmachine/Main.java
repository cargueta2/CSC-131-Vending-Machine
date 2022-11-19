package vendingmachine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<VendingMachine> machines = new ArrayList<VendingMachine>();
	
	static Scanner scan = new Scanner(System.in);                                                                                                              
	
	// Prints out interfaces to select from
	static void printInterfaceSelection() {
		System.out.println("Choose Interface:");
		System.out.println("Customer UI type 'c'");
		System.out.println("Restocker UI type 'r'");
		System.out.println("Management UI type 'm'");
		System.out.println("To exit type 'exit'");
		System.out.print("User Input> ");
	}
	
	static void printMachineSelection() {
		System.out.println("Choose a Vending Machine:");
		//Prints out the each vending machine to select from
		for(int i = 0; i < machines.size(); i++) {
			System.out.println("For Vending Machine #" + (i+1) + " press " + (i+1));
		}
		System.out.println("To Exit type exit");
		System.out.print("User Input> ");
	}
	
	public static void main(String[] args) throws IOException {

		//Create Multiple Vending Machines 3 for now
		for(int i = 0; i < 3; i++) {
			VendingMachine v = new VendingMachine();
			machines.add(v);
		}
		
		// Display interface menu
		String banner = "===========INTERFACE SELCTION MENU============";
		System.out.println(banner);
		printInterfaceSelection();

		String choose = scan.next();

		while (!(choose.equals("exit"))) {

			switch (choose) {
			// Customer
			case ("c"):
				System.out.println("\n==============CUSTOMER INTERFACE==============");
				Customer.startCustomer(machines);
				System.out.println(banner);
				break;
//			// Restocker
			case ("r"):
				System.out.println("\n==============RESTOCKER INTERFACE=============");
				Restocker.startRestocker(machines);
				System.out.println(banner);
				break;
			// Management
			case ("m"):
				System.out.println("\n============MANAGEMENT INTERFACE==============");
				Management.startManagment(machines);
				System.out.println(banner);
				break;
			default:
				System.out.println("Invalid Input. Try Again.\n");
			}
			printInterfaceSelection();
			choose = scan.next();
		}

	}
}
