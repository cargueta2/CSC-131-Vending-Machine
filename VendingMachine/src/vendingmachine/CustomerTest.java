package vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testGetItem1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		String location1 = "A1", err = "";

		Items item1 = Customer.getItem(v.inventory, location1);
		Items item2 = new Items();
		item2.copy(item1);

		if (!(item1.itemName.equals(item2.itemName))) {
			err = err + "Item Name: Not Equal, ";
		} else if (!(item1.itemPrice.equals(item2.itemPrice))) {
			err = err + "Item Price: Not Equal, ";
		} else if (item1.itemCount != item2.itemCount) {
			err = err + "Item Count: Not Equal, ";
		} else if (!(item1.expDate.equals(item2.expDate))) {
			err = err + "Item Exp Date: Not Equal, ";
		} else if (!(item1.itemStatus.equals(item2.itemStatus)))
			;

		assertEquals("", err);

	}

	@Test
	void testGetItem2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		String location1 = "B1", err = "";

		Items item1 = Customer.getItem(v.inventory, location1);
		Items item2 = new Items();
		item2.copy(item1);

		if (!(item1.itemName.equals(item2.itemName))) {
			err = err + "Item Name: Not Equal, ";
		} else if (!(item1.itemPrice.equals(item2.itemPrice))) {
			err = err + "Item Price: Not Equal, ";
		} else if (item1.itemCount != item2.itemCount) {
			err = err + "Item Count: Not Equal, ";
		} else if (!(item1.expDate.equals(item2.expDate))) {
			err = err + "Item Exp Date: Not Equal, ";
		} else if (!(item1.itemStatus.equals(item2.itemStatus)))
			;

		assertEquals("", err);

	}

	@Test
	void testValidInput1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		String invalid_input = "C1";
		Boolean valid = Customer.validInput(v, invalid_input);

		assertEquals(false, valid);
	}

	@Test
	void testValidInput2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();

		String valid_input = "B2";
		Boolean valid = Customer.validInput(v, valid_input);

		assertEquals(true, valid);
	}

	@Test
	void testCheckStock1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		Items item = Customer.getItem(v.inventory, "A1");
		item.itemCount = 10;

		Boolean inStock = Customer.checkStock(item);

		assertEquals(true, inStock);
	}

	@Test
	void testCheckStock2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		Items item = Customer.getItem(v.inventory, "A1");
		item.itemCount = 0;

		Boolean inStock = Customer.checkStock(item);

		assertEquals(false, inStock);
	}

	@Test
	void testPaymentValidity1() {
		String input = "20.0";
		Boolean valid = Customer.paymentValidity(input);

		assertEquals(true, valid);
	}

	@Test
	void testPaymentValidity2() {
		String input = "50.0";
		Boolean valid = Customer.paymentValidity(input);

		assertEquals(false, valid);
	}

	@Test
	void testGiveChangeBack1() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		v.setCredit(2.0);

		String change = Customer.giveChangeBack(v);

		assertEquals("8", change);
	}

	@Test
	void testGiveChangeBack2() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		v.setCredit(20.75);

		String change = Customer.giveChangeBack(v);

		assertEquals("83", change);
	}

	@Test
	void testUpdateInventory() throws FileNotFoundException, ParseException {
		Management.setStock();
		VendingMachine v = new VendingMachine();
		
		String itemLocation = "A3";
		Items item = Customer.getItem(v.inventory, itemLocation);
		int count_before = item.itemCount;
		int purchase_count = 4;

		for (int i = 0; i < purchase_count; i++) {
			Customer.updateInventory(v, item.itemName);
		}
		
		assertEquals(count_before - 4, item.itemCount);
	}

	@Test
	void testUpdatePurchaseHistory() throws FileNotFoundException, ParseException {
		ArrayList<String> history = new ArrayList<String>();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
		LocalDateTime current = LocalDateTime.now();
		String itemName = "Coke";
		String addPurchase = date.format(current) + " " + itemName;
		
		for(int i = 0; i < 3; i++) {
			Customer.updatePurchaseHistory(history, itemName);
		}
		
		assertEquals(addPurchase,history.get(0));
	}

}
