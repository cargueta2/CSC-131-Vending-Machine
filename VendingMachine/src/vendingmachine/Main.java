package vendingmachine;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	static ArrayList<VendingMachine> machines = new ArrayList<VendingMachine>();
	
	public static void printInventory(VendingMachine v) {
		for(int i = 0; i < v.inventory.size(); i ++) {
			System.out.println(String.format("%-9s %-15s %-10s %-13s", v.inventory.get(i).itemLocation,
					v.inventory.get(i).itemName, "$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount) + "\n");
		}
	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {

		String[] locations = { "Moraga Way, Sacramento, CA 95826",
				"Hornet Bookstore, 6000 Jed Smith Dr, Sacramento, CA 95819", "6002 J St, Sacramento, CA 95819" };
		
		Management.setStock();
		
		// Create Multiple Vending Machines 3 for now
		for (int i = 0; i < 3; i++) {
			VendingMachine v = new VendingMachine();
			v.setLocation(locations[i]);
			machines.add(v);
		}
		
		Management.setStock();

		CustomerGUI frame = new CustomerGUI();
		ManagementGUI frame2 = new ManagementGUI();

	}

}
