package vendingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
	
	private String machineStatus;
	private double credit;
	String itemSelection;
	private String machineLocation;

	// List of Items in inventory
	ArrayList<Items> inventory = new ArrayList<Items>();

	// Contains all products purchased
	ArrayList<String> history = new ArrayList<String>();
	
	//Contains items to be removed
	ArrayList<Items> remove = new ArrayList<Items>();
	
	//Contains items to be added
	ArrayList<Items> add = new ArrayList<Items>();
	
	//status mutators
	String getStatus() {
		return machineStatus;
	}
	
	void setStatus(String status) {
		machineStatus = status;
	}
	
	//credit mutators
	double getCredit() {
		return credit;
	}
	
	void setCredit(double num) {
		credit = num;
	}
	
	//location mutators
	String getLocation() {
		return machineLocation;
	}
	
	void setLocation(String location) {
		machineLocation = location;
	}

	
	
	// Loads Vending Machine
	void setInventory(ArrayList<Items> availableStock){

		String[] slots = {"A1","A2","A3","A4","B1","B2","B3","B4"};

		for( int i = 0; i < 8; i++){
			Items temp = availableStock.get(i);
			Items item = new Items();
			item.copy(temp);
			
			item.itemLocation = slots[i];
			inventory.add(item);
		}
	}

	public VendingMachine() throws FileNotFoundException, ParseException {
		machineStatus = "Online";
		setInventory(Management.availableStock);
		credit = 0.0;
		itemSelection = "";
	}

}
