package vendingmachine;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class ManagementTest {

	@Test
	public void testGetInventory() throws FileNotFoundException {
		VendingMachine v = new VendingMachine();
		
		ArrayList<Items> expected = v.inventory;
		
		assertEquals(expected, Management.getInventory(v));
	}

	@Test
	public void testGetMachineStatus() throws FileNotFoundException {
		VendingMachine v = new VendingMachine();
		
		String expected = v.machineStatus;
		
		assertEquals(expected, Management.getMachineStatus(v));
	}

}
