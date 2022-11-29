package Demo;
import java.util.ArrayList;

public class Management {
	private static ArrayList<Items> popular = new ArrayList<Items>();

	public static String displayInventory(ArrayList<Items> inventory) {
		String str = "";

		for (int i = 0; i < inventory.size(); i++) {
			str = str + String.format("%-5s %-10s %-8s %-5s %-10s", inventory.get(i).itemLocation,
					inventory.get(i).itemName, "$" + inventory.get(i).itemPrice, inventory.get(i).itemCount,
					inventory.get(i).expDate) + "\n";
		}

		return str;
	}

	static String getMachineStatus(VendingMachine v) {
		return v.machineStatus;
	}

	static String displayMachineStatus(VendingMachine v) {

		String machineStatus = getMachineStatus(v);

		if (machineStatus.equals("Online")) {
			return "Vending Machine is Online";
		} else if (machineStatus.equals("Restocking")) {
			return "Vending Machine is currently being Restocked";
		}

		return "Vending Machine is Offline";
	}

	static String displayHistory(ArrayList<String> history) {

		String message = "";

		for (int i = 0; i < history.size(); i++)
			message = message + history.get(i) + "\n";

		return message;
	}

	static void populatePopular(ArrayList<Items> inventory) {
		for(int i = 0; i < inventory.size(); i++)
			popular.add(inventory.get(i));
	}
	
	static void popularItems(ArrayList<Items> inventory) {
		
		popular.clear();
		
		populatePopular(inventory);
		
		int size = popular.size();
		
		for (int i = 0; i < size; i++) {
			int max_index = i;
			int max = popular.get(i).purchaseCount;
			
			for (int j = i; j < size; j++) {
				
				if( max < popular.get(j).purchaseCount) {
					max_index = j;
					max = popular.get(j).purchaseCount;
				}
			}
			
			Items temp = popular.get(i);
			popular.set(i,popular.get(max_index));
			popular.set(max_index, temp);
			
		}
	}

	static String displayPopular(VendingMachine v) {
		String str = "";

		popularItems(v.inventory);
		
		str = "Popualar Size: " + popular.size() + " \n";

		for (int i = 0; i < popular.size(); i++) {
			str = str
					+ String.format("%-5s %-10s %-8s %-5s %-10s", i + 1, popular.get(i).itemName,
							"$" + popular.get(i).itemPrice, popular.get(i).itemCount, popular.get(i).purchaseCount)
					+ "\n";
		}

		return str;
	}

	public static void startManagment(ArrayList<VendingMachine> machines) {

	}
}
