package vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import org.junit.jupiter.api.Test;

class ManagementTest {
	@Test
	void testDisplayInventory() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		String expected = "";

		for (int i = 0; i < v.inventory.size(); i++) {
			expected = expected + String.format("%-9s %-15s %-13s %-5s", v.inventory.get(i).itemLocation,
					v.inventory.get(i).itemName, "$" + v.inventory.get(i).itemPrice, v.inventory.get(i).itemCount)
					+ "\n";
		}

		assertEquals(expected, Management.displayInventory(v.inventory));
	}

	@Test
	void testDisplayMachineStatus1() throws FileNotFoundException, ParseException {
		VendingMachine v = new VendingMachine();
		v.setStatus("Online");

		String expected = "Vending Machine is Online";

		assertEquals(expected, Management.displayMachineStatus(v));
	}

	@Test
	void testDisplayMachineStatus2() throws FileNotFoundException, ParseException {
		VendingMachine v = new VendingMachine();
		v.setStatus("Restocking");

		String expected = "Vending Machine is currently being Restocked";

		assertEquals(expected, Management.displayMachineStatus(v));
	}

	@Test
	void testDisplayMachineStatus3() throws FileNotFoundException, ParseException {
		VendingMachine v = new VendingMachine();
		v.setStatus("Offline");

		String expected = "Vending Machine is Offline";

		assertEquals(expected, Management.displayMachineStatus(v));
	}

	@Test
	void testDisplayHistory() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		v.setCredit(20.0);

		String[] inputs = { "A1", "B2", "B3", "A2" };

		for (int i = 0; i < 4; i++) {
			Customer.purchaseItem(v, inputs[i]);
		}

		String expected = "";

		for (int i = 0; i < v.history.size(); i++)
			expected = expected + v.history.get(i) + "\n";

		assertEquals(expected, Management.displayHistory(v.history));
	}

	@Test
	void testPopularItems() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		v.setCredit(20.0);

		String[] inputs = { "A1", "A2", "A2", "A2", "B3", "B3", "A2" };

		for (int i = 0; i < inputs.length; i++) {
			Customer.purchaseItem(v, inputs[i]);
		}

		ArrayList<Items> expected = new ArrayList<Items>();
		expected.add(Customer.getItem(v.inventory, "A2"));
		expected.add(Customer.getItem(v.inventory, "B3"));
		expected.add(Customer.getItem(v.inventory, "A1"));

		Management.popularItems(v.inventory);

		Boolean inOrder = true;

		for (int i = 0; i < 3; i++) {
			if (!(expected.get(i).equals(Management.popular.get(i))))
				inOrder = false;
		}

		assertEquals(true, inOrder);
	}

	@Test
	void testCheckItemsStaus1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		String expected = "Expired";
		Items item = Customer.getItem(v.inventory, "A3");

		assertEquals(expected, Management.checkItemStatus(item.expDate, item.itemStatus));
	}

	void testCheckItemsStaus2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		String expected = "OK";
		Items item = Customer.getItem(v.inventory, "A1");

		assertEquals(expected, Management.checkItemStatus(item.expDate, item.itemStatus));
	}

	@Test
	void testDisplayRecalled() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		String expected = String.format("%-13s %-13s %-13s %-9s", "Name", "Price", "Quantity", "Purchased") + "\n";

		Items item = Customer.getItem(v.inventory, "B3");

		expected = expected + String.format("%-13s %-16s %-14s %-5s", item.itemName, "$" + item.itemPrice,
				item.itemCount, item.purchaseCount) + "\n";
		
		assertEquals(expected, Management.displayRecalled(v));
	}
	
	@Test 
	void testRemoveItems() throws FileNotFoundException, ParseException{
		Management.setStock();
		VendingMachine v = new VendingMachine();
		Items item1 = Customer.getItem(v.inventory, "A2");
		v.remove.add(item1);
		
		String err = "";
		
		if (!(item1.itemName.equals(v.remove.get(0).itemName))) {
			err = err + "Item Name: Not Equal, ";
		} else if (!(item1.itemPrice.equals(v.remove.get(0).itemPrice))) {
			err = err + "Item Price: Not Equal, ";
		} else if (item1.itemCount != v.remove.get(0).itemCount) {
			err = err + "Item Count: Not Equal, ";
		} else if (!(item1.expDate.equals(v.remove.get(0).expDate))) {
			err = err + "Item Exp Date: Not Equal, ";
		} else if (!(item1.itemStatus.equals(v.remove.get(0).itemStatus)))
			;

		assertEquals("", err);
		
	}

}
