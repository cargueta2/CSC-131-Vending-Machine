package vendingmachine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restocker {
	//Returns the items in the remove list
	public static String displayRemoveInstruction(VendingMachine v) {
		String str = "";

		if (v.remove.size() == 0) {
			return "No Current Instructions";
		}
		
		str = str + String.format("%-9s %-15s %-5s", "Slot", "Name", "Quantity") + "\n";

		for (int i = 0; i < v.remove.size(); i++) {
			str = str + String.format("%-9s %-18s %-5s", v.remove.get(i).itemLocation, v.remove.get(i).itemName,
					v.remove.get(i).itemCount) + "\n";

		}

		return str;
	}
	
	//Returns the items in the add list
	public static String displayAddInstruction(VendingMachine v) {
		String str = "";

		if (v.add.size() == 0) {
			return "No Current Instructions";
		}
		
		str = str + String.format("%-9s %-15s %-5s", "Slot", "Name", "Quantity") + "\n";

		for (int i = 0; i < v.add.size(); i++) {
			str = str + String.format("%-9s %-18s %-5s", v.add.get(i).itemLocation, v.add.get(i).itemName,
					v.add.get(i).itemCount) + "\n";
		}

		return str;
	}
	
	//Sets the machine status
	public static void setMachineStatus(String status, VendingMachine v) {
		v.setStatus(status);
	}
	
	//updates the inventory of machine
	public static void updateItems(VendingMachine v) {
		int purchaseCount = 0;
		
		//inserts items in add list into inventory
		for (int i = 0; i < v.add.size(); i++) {
			
			for(int j = 0; j < v.inventory.size(); j++) {
				if(v.inventory.get(j).itemName.equals(v.add.get(i).itemName))
					purchaseCount = v.inventory.get(j).purchaseCount;
			}
			
			v.inventory.get(i).copy(v.add.get(i));
			
			
			v.inventory.get(i).purchaseCount = purchaseCount;
			
			if(!(v.add.get(i).itemStatus.equals("OK")))
				v.inventory.get(i).expDate = "2/23/23";
			
			v.inventory.get(i).itemCount = 10;
		}
		
		//clears remove and add list
		v.remove.clear();
		v.add.clear();
	}

}
