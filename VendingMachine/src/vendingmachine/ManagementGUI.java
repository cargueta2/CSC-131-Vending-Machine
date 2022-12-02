package vendingmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ManagementGUI extends JFrame {
	
	
	public ManagementGUI() throws ParseException {
		setTitle("Management UI");

		JTabbedPane machineTabs = new JTabbedPane();

		JPanel machine1, machine2, machine3;

		machine1 = new JPanel();
		machine2 = new JPanel();
		machine3 = new JPanel();

		machineTabs.addTab("Vending Machine #1", machine1);
		makeTabs(machine1, Main.machines.get(0),0);

		machineTabs.addTab("Vending Machine #2", machine2);
		makeTabs(machine2, Main.machines.get(1),1);

		machineTabs.addTab("Vending Machine #3", machine3);
		makeTabs(machine3, Main.machines.get(2),2);

		this.add(machineTabs);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 900);
		this.setVisible(true);
	}

	public static void makeTabs(JPanel panel, VendingMachine v, int machine_index) throws ParseException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Tabs inside machineTab
		JTabbedPane managementTabs = new JTabbedPane();

		JPanel summary, analytics, viewInstructions;

		summary = new JPanel();
		summary.setPreferredSize(new Dimension(1200, 900));

		analytics = new JPanel();
		viewInstructions = new JPanel();

		managementTabs.addTab("Basic Overview", summary);
		addOverviewContent(summary, v);

		managementTabs.addTab("Analytics", analytics);
		addAnalyticsContent(analytics, v);

		managementTabs.addTab("Restocking Instructions", viewInstructions);
		addInstructionContent(viewInstructions, v, machine_index);

		panel.add(managementTabs);
	}

	// Overview Content displays: Machine Location, Machine Status, Inventory and
	// Purchase History
	public static void addOverviewContent(JPanel panel, VendingMachine v) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		int STARTX = 50;
		int STARTY = 50;
		int gap = 50;
		Font displayInfo = new Font(Font.MONOSPACED, Font.PLAIN, 20);
		
		// Machine Location
		JLabel location = new JLabel("Location: " + v.getLocation());
		location.setBounds(STARTX, STARTY, 50, location.getSize().width);
		panel.add(location);

		// Machine Status
		JLabel status = new JLabel("Status: " + Management.displayMachineStatus(v));
		status.setBounds(STARTX, STARTY + gap * 2, 50, status.getSize().width);
		panel.add(status);

		// Inventory
		JLabel inventoryLabel = new JLabel("Inventory");
		panel.add(inventoryLabel);

		JTextArea inventoryContent = new JTextArea();
		inventoryContent.setBounds(0, 0, inventoryContent.getSize().height, inventoryContent.getSize().width);
		panel.add(inventoryContent);
		inventoryContent.setFont(displayInfo);
		setInventoryContent(inventoryContent, v);

		JButton inventoryRefresh = new JButton("Refresh");
		inventoryRefresh.setBounds(250, 30, 50, 100);
		panel.add(inventoryRefresh);
		inventoryRefresh.addActionListener(e -> setInventoryContent(inventoryContent, v));

		// Purchase History
		JLabel purchaseLabel = new JLabel("Purchase History");
		panel.add(purchaseLabel);
		
		JTextArea purchases = new JTextArea();
		purchases.setBounds(30, 30, 200, 500);
		panel.add(purchases);
		purchases.setFont(displayInfo);
		setHistoryContent(purchases, v);

		JButton purchasesRefresh = new JButton("Refresh");
		purchasesRefresh.setBounds(250, 30, 50, 100);
		panel.add(purchasesRefresh);
		purchasesRefresh.addActionListener(e -> setHistoryContent(purchases, v));

	}

	// Displays Inventory
	public static void setInventoryContent(JTextArea inventory, VendingMachine v) {

		String message = String.format("%-9s %-15s %-10s %-13s", "Slot","Name","Price","Quantity") + "\n";
		Main.printInventory(v);
		message = message + Management.displayInventory(v.inventory);

		inventory.setText(message);
	}

	// Displays History
	public static void setHistoryContent(JTextArea purchases, VendingMachine v) {

		if (v.history.size() != 0) {
			purchases.setText("");

			String message = Management.displayHistory(v.history);

			purchases.setText(message);
		} else {
			purchases.setText("No Current History");
		}
	}

	// Analytics display: Item Popularity, Expired Items, and Recalled Items
	public static void addAnalyticsContent(JPanel panel, VendingMachine v) throws ParseException {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Font displayInfo = new Font(Font.MONOSPACED, Font.PLAIN, 20);
		
		// Popular Items
		JLabel popularLabel = new JLabel("Popular Items");
		panel.add(popularLabel);
		
		JTextArea popular = new JTextArea();
		popular.setBounds(30, 30, 200, 500);
		panel.add(popular);
		popular.setFont(displayInfo);
		setPopularContent(popular, v);

		JButton popularRefresh = new JButton("Refresh");
		popularRefresh.setBounds(250, 30, 50, 100);
		panel.add(popularRefresh);
		popularRefresh.addActionListener(e -> setPopularContent(popular, v));
		
		//Expired Items
		JLabel expiredLabel = new JLabel("Expired Items");
		panel.add(expiredLabel);
		
		JTextArea expired = new JTextArea();
		expired.setBounds(30, 30, 200, 500);
		panel.add(expired);
		expired.setFont(displayInfo);
		setExpiredContent(expired, v);

		JButton expiredRefresh = new JButton("Refresh");
		expiredRefresh.setBounds(250, 30, 50, 100);
		panel.add(expiredRefresh);
		expiredRefresh.addActionListener(e -> {
			try {
				setExpiredContent(expired, v);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Recalled Items
		JLabel recalledLabel = new JLabel("Recalled Items");
		panel.add(recalledLabel);
		
		JTextArea recalled = new JTextArea();
		recalled.setBounds(30, 30, 200, 500);
		panel.add(recalled);
		recalled.setFont(displayInfo);
		setRecalledContent(recalled, v);

		JButton recalledRefresh = new JButton("Refresh");
		recalledRefresh.setBounds(250, 30, 50, 100);
		panel.add(recalledRefresh);
		recalledRefresh.addActionListener(e -> setRecalledContent(expired, v));
	}

	public static void setPopularContent(JTextArea popular, VendingMachine v) {

		popular.setText("");

		String message = Management.displayPopular(v);

		popular.setText(message);

	}
	
	public static void setExpiredContent(JTextArea expired, VendingMachine v) throws ParseException {
		expired.setText(Management.displayExpired(v));
	}
	
	public static void setRecalledContent(JTextArea recalled, VendingMachine v) {
		recalled.setText(Management.displayRecalled(v));
	}

	public static void addInstructionContent(JPanel panel, VendingMachine v, int machine_index) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//Remove Items
		JLabel removeLabel = new JLabel("Choose Items To Remove");
		panel.add(removeLabel);
		
		JCheckBox remove_item1 = new JCheckBox(formatRemoveInfo(0,v.inventory));
		panel.add(remove_item1);
		
		JCheckBox remove_item2 = new JCheckBox(formatRemoveInfo(1,v.inventory));
		panel.add(remove_item2);
		
		JCheckBox remove_item3 = new JCheckBox(formatRemoveInfo(2,v.inventory));
		panel.add(remove_item3);
		
		JCheckBox remove_item4 = new JCheckBox(formatRemoveInfo(3,v.inventory));
		panel.add(remove_item4);
		
		JCheckBox remove_item5 = new JCheckBox(formatRemoveInfo(4,v.inventory));
		panel.add(remove_item5);
		
		JCheckBox remove_item6 = new JCheckBox(formatRemoveInfo(5,v.inventory));
		panel.add(remove_item6);
		
		JCheckBox remove_item7 = new JCheckBox(formatRemoveInfo(6,v.inventory));
		panel.add(remove_item7);
		
		JCheckBox remove_item8 = new JCheckBox(formatRemoveInfo(7,v.inventory));
		panel.add(remove_item8);
		
		//Add items
		JLabel addLabel = new JLabel("Choose Items To Add");
		panel.add(addLabel);
		
		JCheckBox add_item1 = new JCheckBox(formatAddInfo(0,Management.availableStock));
		panel.add(add_item1);
		
		JCheckBox add_item2 = new JCheckBox(formatAddInfo(1,Management.availableStock));
		panel.add(add_item2);
		
		JCheckBox add_item3 = new JCheckBox(formatAddInfo(2,Management.availableStock));
		panel.add(add_item3);
		
		JCheckBox add_item4 = new JCheckBox(formatAddInfo(3,Management.availableStock));
		panel.add(add_item4);
		
		JCheckBox add_item5 = new JCheckBox(formatAddInfo(4,Management.availableStock));
		panel.add(add_item5);
		
		JCheckBox add_item6 = new JCheckBox(formatAddInfo(5,Management.availableStock));
		panel.add(add_item6);
		
		JCheckBox add_item7 = new JCheckBox(formatAddInfo(6,Management.availableStock));
		panel.add(add_item7);
		
		JCheckBox add_item8 = new JCheckBox(formatAddInfo(7,Management.availableStock));
		panel.add(add_item8);
		
		JCheckBox add_item9 = new JCheckBox(formatAddInfo(8,Management.availableStock));
		panel.add(add_item9);
		
		JCheckBox add_item10 = new JCheckBox(formatAddInfo(9,Management.availableStock));
		panel.add(add_item10);
		
		JCheckBox add_item11 = new JCheckBox(formatAddInfo(10,Management.availableStock));
		panel.add(add_item11);
		
	}
	
	public static String formatRemoveInfo(int index, ArrayList<Items> inventory) {
		return String.format("%-5s %-10s %-8s %-5s %-10s", inventory.get(index).itemLocation,
				inventory.get(index).itemName, "$" + inventory.get(index).itemPrice, inventory.get(index).itemCount,
				inventory.get(index).itemStatus);
	}
	
	public static String formatAddInfo(int index, ArrayList<Items> inventory) {
		int restock = 10;
		
		if(inventory.get(index).present == true) {
			restock = restock - inventory.get(index).itemCount;
		}
		
		return String.format("%-10s %-8s %-5s %-10s", inventory.get(index).itemName, "$" + inventory.get(index).itemPrice, restock,
				inventory.get(index).itemStatus);
	}

}

