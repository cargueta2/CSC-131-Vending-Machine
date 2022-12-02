package vendingmachine;
/*
 * package vendingmachine;
 * 
 * import static org.junit.Assert.*; import java.io.FileNotFoundException;
 * 
 * import org.junit.Test;
 * 
 * public class CustomerTest {
 * 
 * @Test public void testgetItem() throws FileNotFoundException { VendingMachine
 * v = new VendingMachine();
 * 
 * String error = "";
 * 
 * Items item = Customer.getItem(v.inventory, "A1");
 * 
 * if (!(item.itemLocation.equals("A1"))) { error = error +
 * "Item location: Not equal, "; } else if
 * (!(item.itemName.equals("Dr.Pepper"))) { error = error +
 * "Item name: Not equal, "; } else if (!(item.itemPrice.equals("1.50"))) {
 * error = error + "Item price: Not equal, "; } else if ((item.itemCount != 10))
 * { error = error + "Item count: Not equal, "; } else if
 * (!(item.expDate.equals("10/23/2023"))) { error = error +
 * "Item expDate: Not equal"; }
 * 
 * assertEquals("", error);
 * 
 * }
 * 
 * @Test public void testpaymentValidity1() { String money = "10.0";
 * 
 * boolean valid = Customer.paymentValidity(money);
 * 
 * assertEquals(true,valid); }
 * 
 * @Test public void testpaymentValidity2() { String money = "20.00";
 * 
 * boolean valid = Customer.paymentValidity(money);
 * 
 * assertEquals(false,valid); }
 * 
 * @Test public void testgiveChangeBack() { double change = 2.5;
 * 
 * int coins[] = Customer.giveChangeBack(change);
 * 
 * int expected[] = {10,0,0,0};
 * 
 * assertArrayEquals(expected,coins); }
 * 
 * 
 * }
 */