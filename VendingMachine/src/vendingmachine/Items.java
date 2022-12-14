package vendingmachine;

public class Items {
	//Stores where item is located in machine
	String itemLocation = "";
	
	//stores the name of the product
	String itemName;
	
	//stores the price of the product
	String itemPrice;
	
	//stores quantity of items
	int itemCount = 0;
	
	//stores the expiration item of product
	String expDate;
	
	//stores item status
	String itemStatus;
	
	//times purchased
	int purchaseCount = 0;
	
	
	public void copy(Items copy) {
		itemLocation = copy.itemLocation;
		itemName = copy.itemName;
		itemPrice = copy.itemPrice;
		itemCount = 10;
		expDate = copy.expDate;
		itemStatus = copy.itemStatus;
	}
}
