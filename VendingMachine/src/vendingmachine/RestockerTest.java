package vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

class RestockerTest {

	@Test
	void testDisplayRemoveInstruction() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		Items item1 = Customer.getItem(v.inventory, "A2");
		v.remove.add(item1);

		String expected = String.format("%-9s %-15s %-5s", "Slot", "Name", "Quantity") + "\n"
				+ String.format("%-9s %-18s %-5s", item1.itemLocation, item1.itemName, item1.itemCount) + "\n";

		assertEquals(expected, Restocker.displayRemoveInstruction(v));
	}

	@Test
	void testDisplayAddInstruction() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		Items item1 = Customer.getItem(v.inventory, "A2");
		v.add.add(item1);

		String expected = String.format("%-9s %-15s %-5s", "Slot", "Name", "Quantity") + "\n"
				+ String.format("%-9s %-18s %-5s", item1.itemLocation, item1.itemName, item1.itemCount) + "\n";

		assertEquals(expected, Restocker.displayAddInstruction(v));
	}

	@Test
	void testSetMachineStatus1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		
		String expected = "Restocking";
		
		Restocker.setMachineStatus("Restocking", v);
		
		assertEquals(expected, v.getStatus());
	}
	
	@Test
	void testSetMachineStatus2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		
		String expected = "Online";
		
		Restocker.setMachineStatus("Online", v);
		
		assertEquals(expected, v.getStatus());
	}
	
	@Test
	void testSetMachineStatus3() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		
		String expected = "Offline";
		
		Restocker.setMachineStatus("Offline", v);
		
		assertEquals(expected, v.getStatus());
	}

}
