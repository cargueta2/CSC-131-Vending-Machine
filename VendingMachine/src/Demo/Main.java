package Demo;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {
	static ArrayList<VendingMachine> machines = new ArrayList<VendingMachine>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String[] locations = {"Moraga Way, Sacramento, CA 95826", 
							"Hornet Bookstore, 6000 Jed Smith Dr, Sacramento, CA 95819", 
							"6002 J St, Sacramento, CA 95819"};
		
		//Create Multiple Vending Machines 3 for now
		for(int i = 0; i < 3; i++) {
			VendingMachine v = new VendingMachine();
			v.machineLocation = locations[i];
			machines.add(v);
		}
		
	Demo frame = new Demo();
	ManagementGUI frame2 = new ManagementGUI();

	}

}
