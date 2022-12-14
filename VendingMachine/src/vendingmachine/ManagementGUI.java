package vendingmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ManagementGUI extends JFrame {
	static JPanel machine1, machine2, machine3;
	static JTabbedPane machineTabs;

	public ManagementGUI() throws ParseException {
		setTitle("Management UI");

		machineTabs = new JTabbedPane();

		machine1 = new JPanel();
		machine2 = new JPanel();
		machine3 = new JPanel();

		machineTabs.addTab("Vending Machine #1", machine1);
		makeTabs(machine1, Management.machines.get(0), 0);

		machineTabs.addTab("Vending Machine #2", machine2);
		makeTabs(machine2, Management.machines.get(1), 1);

		machineTabs.addTab("Vending Machine #3", machine3);
		makeTabs(machine3, Management.machines.get(2), 2);

		this.setResizable(false);
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
		addOverviewContent(summary, v, machine_index);

		managementTabs.addTab("Analytics", analytics);
		addAnalyticsContent(analytics, v, machine_index);

		managementTabs.addTab("Restocking Instructions", viewInstructions);
		addInstructionContent(viewInstructions, v, machine_index);

		panel.add(managementTabs);
	}

	// Overview Content displays: Machine Location, Machine Status, Inventory and
	// Purchase History
	public static void addOverviewContent(JPanel panel, VendingMachine v, int machine_index) {
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(null);
		panel.setSize(1200, 900);
		Font labelFont = new Font("Consolas", Font.BOLD, 20);
		Font displayInfo = new Font("Consolas", Font.PLAIN, 20);

		// Machine Location
		JLabel location = new JLabel("Location: " + v.getLocation());
		location.setMaximumSize(location.getPreferredSize());
		panel.add(location);
		location.setFont(labelFont);

		// Machine Status
		JLabel status = new JLabel("Status: " + Management.displayMachineStatus(v));
		panel.add(status);
		status.setFont(labelFont);

		// Inventory
		JLabel inventoryLabel = new JLabel("Inventory");
		panel.add(inventoryLabel);
		inventoryLabel.setFont(labelFont);

		JTextArea inventoryContent = new JTextArea();
		panel.add(inventoryContent);
		inventoryContent.setFont(displayInfo);
		setInventoryContent(inventoryContent, v);
		inventoryContent.setEditable(false);

		// Purchase History
		JLabel purchaseLabel = new JLabel("Purchase History");
		panel.add(purchaseLabel);
		purchaseLabel.setFont(labelFont);

		JTextArea purchases = new JTextArea();
		panel.add(purchases);
		purchases.setFont(displayInfo);
		setHistoryContent(purchases, v);
		purchases.setEditable(false);
		JScrollPane scroll = new JScrollPane(purchases);
		panel.add(scroll);

		// Refresh Button
		JButton refreshOverviewButton = new JButton("Refresh");
		panel.add(refreshOverviewButton);
		refreshOverviewButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		refreshOverviewButton.setBounds(panel.getWidth() - (refreshOverviewButton.getPreferredSize().width + 50), 25,
		refreshOverviewButton.getPreferredSize().width, refreshOverviewButton.getPreferredSize().height);
		refreshOverviewButton.addActionListener(e -> refreshOverview(panel, v, machine_index));

		// Layout
		int center = panel.getWidth() / 2;
		int gap = 25;
		int start_Y = 25;

		// Location
		location.setBounds(panel.getWidth() / 2 - location.getPreferredSize().width / 2, start_Y,
				location.getPreferredSize().width, location.getPreferredSize().height);

		// Status
		status.setBounds(panel.getWidth() / 2 - status.getPreferredSize().width / 2,
				start_Y + location.getPreferredSize().height, status.getPreferredSize().width,
				status.getPreferredSize().height);

		// Inventory
		inventoryLabel.setBounds(center - inventoryLabel.getPreferredSize().width / 2, status.getY() + gap * 2,
				inventoryLabel.getPreferredSize().width, inventoryLabel.getPreferredSize().height);
		inventoryContent.setBounds(center - inventoryContent.getPreferredSize().width / 2, inventoryLabel.getY() + gap,
				inventoryContent.getPreferredSize().width, inventoryContent.getPreferredSize().height);

		// Purchase History
		purchaseLabel.setBounds(center - purchaseLabel.getPreferredSize().width / 2,
				inventoryContent.getY() + inventoryContent.getPreferredSize().height + gap * 2,
				purchaseLabel.getPreferredSize().width, purchaseLabel.getPreferredSize().height);
		scroll.setBounds(center - inventoryContent.getPreferredSize().width / 2, purchaseLabel.getY() + gap,
				inventoryContent.getPreferredSize().width, inventoryContent.getPreferredSize().height);

	}

	public static void refreshOverview(JPanel panel, VendingMachine v, int machine_index) {
		panel.removeAll();
		addOverviewContent(panel, v, machine_index);
	}

	// Displays Inventory
	public static void setInventoryContent(JTextArea inventory, VendingMachine v) {

		String message = String.format("%-9s %-15s %-10s %-8s", "Slot", "Name", "Price", "Quantity") + "\n";
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
	public static void addAnalyticsContent(JPanel panel, VendingMachine v, int machine_index) throws ParseException {
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(null);
		panel.setSize(1200, 900);
		Font labelFont = new Font("Consolas", Font.BOLD, 20);
		Font displayInfo = new Font("Consolas", Font.PLAIN, 20);

		// Popular Items
		JLabel popularLabel = new JLabel("Popular Items");
		panel.add(popularLabel);
		popularLabel.setFont(labelFont);

		JTextArea popular = new JTextArea();
		panel.add(popular);
		popular.setFont(displayInfo);
		setPopularContent(popular, v);
		popular.setEditable(false);

		// Expired Items
		JLabel expiredLabel = new JLabel("Expired Items");
		panel.add(expiredLabel);
		expiredLabel.setFont(labelFont);

		JTextArea expired = new JTextArea();
		panel.add(expired);
		expired.setFont(displayInfo);
		setExpiredContent(expired, v);
		expired.setEditable(false);

		// Recalled Items
		JLabel recalledLabel = new JLabel("Recalled Items");
		panel.add(recalledLabel);
		recalledLabel.setFont(labelFont);

		JTextArea recalled = new JTextArea();
		panel.add(recalled);
		recalled.setFont(displayInfo);
		setRecalledContent(recalled, v);
		recalled.setEditable(false);

		// Refresh Button
		JButton refreshAnalyticsButton = new JButton("Refresh");
		panel.add(refreshAnalyticsButton);
		refreshAnalyticsButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		refreshAnalyticsButton.setBounds(panel.getWidth() - (refreshAnalyticsButton.getPreferredSize().width + 50), 25,
		refreshAnalyticsButton.getPreferredSize().width, refreshAnalyticsButton.getPreferredSize().height);
		refreshAnalyticsButton.addActionListener(e -> {
			try {
				refreshAnalytics(panel, v, machine_index);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		// Layout
		int gap = 25;
		int start_Y = 25;
		int center = panel.getWidth() / 2;

		// Popular
		popularLabel.setBounds(center - popularLabel.getPreferredSize().width / 2, start_Y,
				popularLabel.getPreferredSize().width, popularLabel.getPreferredSize().height);
		popular.setBounds(center - popular.getPreferredSize().width / 2, popularLabel.getY() + gap,
				popular.getPreferredSize().width, popular.getPreferredSize().height);

		// Expired
		expiredLabel.setBounds(center - expiredLabel.getPreferredSize().width / 2,
				popular.getY() + popular.getPreferredSize().height + gap * 3, expiredLabel.getPreferredSize().width,
				expiredLabel.getPreferredSize().height);
		expired.setBounds(center - popular.getPreferredSize().width / 2, expiredLabel.getY() + gap,
				popular.getPreferredSize().width, expired.getPreferredSize().height);

		// Recalled
		recalledLabel.setBounds(center - recalledLabel.getPreferredSize().width / 2,
				expired.getY() + expired.getPreferredSize().height + gap * 3, recalledLabel.getPreferredSize().width,
				recalledLabel.getPreferredSize().height);
		recalled.setBounds(center - popular.getPreferredSize().width / 2, recalledLabel.getY() + gap,
				popular.getPreferredSize().width, recalled.getPreferredSize().height);

	}
	
	public static void refreshAnalytics(JPanel panel, VendingMachine v, int machine_index) throws ParseException {
		panel.removeAll();
		addAnalyticsContent(panel, v, machine_index);
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
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(null);
		panel.setSize(1200, 900);
		Font labelFont = new Font("Consolas", Font.BOLD, 20);
		Font displayInfo = new Font("Consolas", Font.PLAIN, 20);

		// Remove Items
		JLabel removeLabel = new JLabel("Choose Items To Remove");
		panel.add(removeLabel);
		removeLabel.setFont(labelFont);

		JCheckBox remove_item1 = new JCheckBox(formatRemoveInfo(0, v.inventory));
		remove_item1.setFont(displayInfo);
		panel.add(remove_item1);

		JCheckBox remove_item2 = new JCheckBox(formatRemoveInfo(1, v.inventory));
		remove_item2.setFont(displayInfo);
		panel.add(remove_item2);

		JCheckBox remove_item3 = new JCheckBox(formatRemoveInfo(2, v.inventory));
		remove_item3.setFont(displayInfo);
		panel.add(remove_item3);

		JCheckBox remove_item4 = new JCheckBox(formatRemoveInfo(3, v.inventory));
		remove_item4.setFont(displayInfo);
		panel.add(remove_item4);

		JCheckBox remove_item5 = new JCheckBox(formatRemoveInfo(4, v.inventory));
		remove_item5.setFont(displayInfo);
		panel.add(remove_item5);

		JCheckBox remove_item6 = new JCheckBox(formatRemoveInfo(5, v.inventory));
		remove_item6.setFont(displayInfo);
		panel.add(remove_item6);

		JCheckBox remove_item7 = new JCheckBox(formatRemoveInfo(6, v.inventory));
		remove_item7.setFont(displayInfo);
		panel.add(remove_item7);

		JCheckBox remove_item8 = new JCheckBox(formatRemoveInfo(7, v.inventory));
		remove_item8.setFont(displayInfo);
		panel.add(remove_item8);

		// Add items
		ArrayList<Items> validList = Management.getValidItems();
		ArrayList<JComboBox<String>> list = new ArrayList<JComboBox<String>>();

		JLabel addLabel = new JLabel("Choose Items to Add");
		panel.add(addLabel);
		addLabel.setFont(labelFont);

		JComboBox<String> A1_item = new JComboBox<String>(formatArray("A1", validList, v));
		A1_item.setFont(displayInfo);
		panel.add(A1_item);
		list.add(A1_item);
		A1_item.setEnabled(false);

		JComboBox<String> A2_item = new JComboBox<String>(formatArray("A2", validList, v));
		A2_item.setFont(displayInfo);
		panel.add(A2_item);
		list.add(A2_item);
		A2_item.setEnabled(false);

		JComboBox<String> A3_item = new JComboBox<String>(formatArray("A3", validList, v));
		A3_item.setFont(displayInfo);
		panel.add(A3_item);
		list.add(A3_item);
		A3_item.setEnabled(false);

		JComboBox<String> A4_item = new JComboBox<String>(formatArray("A4", validList, v));
		A4_item.setFont(displayInfo);
		panel.add(A4_item);
		list.add(A4_item);
		A4_item.setEnabled(false);

		JComboBox<String> B1_item = new JComboBox<String>(formatArray("B1", validList, v));
		B1_item.setFont(displayInfo);
		panel.add(B1_item);
		list.add(B1_item);
		B1_item.setEnabled(false);

		JComboBox<String> B2_item = new JComboBox<String>(formatArray("B2", validList, v));
		B2_item.setFont(displayInfo);
		panel.add(B2_item);
		list.add(B2_item);
		B2_item.setEnabled(false);

		JComboBox<String> B3_item = new JComboBox<String>(formatArray("B3", validList, v));
		B3_item.setFont(displayInfo);
		panel.add(B3_item);
		list.add(B3_item);
		B3_item.setEnabled(false);

		JComboBox<String> B4_item = new JComboBox<String>(formatArray("B4", validList, v));
		B4_item.setFont(displayInfo);
		panel.add(B4_item);
		list.add(B4_item);
		B4_item.setEnabled(false);
		
		// Refresh Button
		JButton refreshInstructionsButton = new JButton("Refresh");
		panel.add(refreshInstructionsButton);
		refreshInstructionsButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		refreshInstructionsButton.setBounds(panel.getWidth() - (refreshInstructionsButton.getPreferredSize().width + 50), 25,
		refreshInstructionsButton.getPreferredSize().width, refreshInstructionsButton.getPreferredSize().height);
		refreshInstructionsButton.addActionListener(e -> refreshInstructions(panel, v, machine_index));

		// Remove Action Listeners
		remove_item1.addItemListener(e -> removeList(A1_item, v, 0, e));

		remove_item2.addItemListener(e -> removeList(A2_item, v, 1, e));

		remove_item3.addItemListener(e -> removeList(A3_item, v, 2, e));

		remove_item4.addItemListener(e -> removeList(A4_item, v, 3, e));

		remove_item5.addItemListener(e -> removeList(B1_item, v, 4, e));

		remove_item6.addItemListener(e -> removeList(B2_item, v, 5, e));

		remove_item7.addItemListener(e -> removeList(B3_item, v, 6, e));

		remove_item8.addItemListener(e -> removeList(B4_item, v, 7, e));

		//Instructions Status
		JLabel instructionStatus = new JLabel("Press Enter to Confirm");
		panel.add(instructionStatus);
		
		// Add Action Listeners
		JButton enterInstructions = new JButton("Send Instructions");
		panel.add(enterInstructions);
		enterInstructions.addActionListener(e -> addList(instructionStatus,list, v));
		
		

		// Layout
		int gap = 30;
		int start_Y = 25;
		int start_X = (panel.getWidth() - remove_item1.getPreferredSize().width) / 2;

		// Dimensions for checkboxes
		int checkBox_width = remove_item1.getPreferredSize().width;
		int checkBox_height = remove_item1.getPreferredSize().height;

		// Dimensions for comboBoxes
		int cb_width = A1_item.getPreferredSize().width;
		int cb_height = A1_item.getPreferredSize().height;

		// Remove CheckBoxes
		removeLabel.setBounds(start_X, start_Y, removeLabel.getPreferredSize().width,
				removeLabel.getPreferredSize().height);
		remove_item1.setBounds(start_X, removeLabel.getY() + gap, remove_item1.getPreferredSize().width,
				remove_item1.getPreferredSize().height);
		remove_item2.setBounds(start_X, remove_item1.getY() + gap, checkBox_width, checkBox_height);
		remove_item3.setBounds(start_X, remove_item2.getY() + gap, checkBox_width, checkBox_height);
		remove_item4.setBounds(start_X, remove_item3.getY() + gap, checkBox_width, checkBox_height);
		remove_item5.setBounds(start_X, remove_item4.getY() + gap, checkBox_width, checkBox_height);
		remove_item6.setBounds(start_X, remove_item5.getY() + gap, checkBox_width, checkBox_height);
		remove_item7.setBounds(start_X, remove_item6.getY() + gap, checkBox_width, checkBox_height);
		remove_item8.setBounds(start_X, remove_item7.getY() + gap, checkBox_width, checkBox_height);

		// Add ComboBoxes
		addLabel.setBounds(start_X, remove_item8.getY() + gap * 2, addLabel.getPreferredSize().width,
				addLabel.getPreferredSize().height);
		A1_item.setBounds(start_X, addLabel.getY() + gap, A1_item.getPreferredSize().width,
				A1_item.getPreferredSize().height);
		A2_item.setBounds(start_X, A1_item.getY() + (gap + 10), cb_width, cb_height);
		A3_item.setBounds(start_X, A2_item.getY() + (gap + 10), cb_width, cb_height);
		A4_item.setBounds(start_X, A3_item.getY() + (gap + 10), cb_width, cb_height);
		B1_item.setBounds(start_X, A3_item.getY() + (gap + 10), cb_width, cb_height);
		B2_item.setBounds(start_X, B1_item.getY() + (gap + 10), cb_width, cb_height);
		B3_item.setBounds(start_X, B2_item.getY() + (gap + 10), cb_width, cb_height);
		B4_item.setBounds(start_X, B3_item.getY() + (gap + 10), cb_width, cb_height);

		// Enter Button
		enterInstructions.setBounds(panel.getWidth() / 2 - enterInstructions.getPreferredSize().width / 2,
		B4_item.getY() + gap * 2, enterInstructions.getPreferredSize().width,
		enterInstructions.getPreferredSize().height);
		
		//Instructions Status
		instructionStatus.setBounds(panel.getWidth()/2 - instructionStatus.getPreferredSize().width/2, enterInstructions.getY() + gap, instructionStatus.getPreferredSize().width, instructionStatus.getPreferredSize().height);
	}
	
	public static void refreshInstructions(JPanel panel, VendingMachine v, int machine_index){
		panel.removeAll();
		addInstructionContent(panel, v, machine_index);
	}

	public static void removeList(JComboBox<String> box, VendingMachine v, int index, ItemEvent e) {
		if (e.getStateChange() == 1) {
			Management.addToRemove(v, index);
			box.setEnabled(true);
		} else {
			Management.delToRemove(v, index);
			box.setEnabled(false);
		}
	}

	public static void addList(JLabel instructionStatus, ArrayList<JComboBox<String>> list, VendingMachine v) {
		Management.addToAdd(list, v);
		instructionStatus.setText("Instructions Sent");
	}

	public static String[] formatArray(String itemLocation, ArrayList<Items> validList, VendingMachine v) {
		String[] validItems = new String[validList.size() + 1];

		validItems[0] = String.format("%-5s %-10s %-8s %-10s", itemLocation, "Empty", "", "");
		int index = 0;
		String itemName = "";

		for (int i = 0; i < validList.size(); i++) {
			int quantity = 10;

			for (int j = 0; j < v.inventory.size(); j++) {
				if (itemLocation.equals(v.inventory.get(j).itemLocation)) {
					itemName = v.inventory.get(j).itemName;
				}

				if (validList.get(i).itemName.equals(v.inventory.get(j).itemName)
						&& (validList.get(i).itemStatus.equals("OK")))
					quantity = quantity - v.inventory.get(j).itemCount;
			}

			if (itemName.equals(validList.get(i).itemName) && (validList.get(i).itemStatus.equals("OK"))) {
				index = i + 1;
			}

			validItems[i + 1] = String.format("%-5s %-10s %-8s %-10s", itemLocation, validList.get(i).itemName,
					validList.get(i).itemPrice, " Quantity: " + quantity);
		}

		String temp = validItems[0];
		validItems[0] = validItems[index];
		validItems[index] = temp;

		return validItems;
	}

	public static String formatRemoveInfo(int index, ArrayList<Items> inventory) {
		return String.format("%-5s %-10s %-8s %-5s %-10s", inventory.get(index).itemLocation,
				inventory.get(index).itemName, "$" + inventory.get(index).itemPrice, inventory.get(index).itemCount,
				inventory.get(index).itemStatus);
	}


	public static void refreshPanels() throws ParseException {
		machineTabs.removeAll();
		machine1.removeAll();
		machine2.removeAll();
		machine3.removeAll();

		machineTabs.addTab("Vending Machine #1", machine1);
		makeTabs(machine1, Management.machines.get(0), 0);

		machineTabs.addTab("Vending Machine #2", machine2);
		makeTabs(machine2, Management.machines.get(1), 1);

		machineTabs.addTab("Vending Machine #3", machine3);
		makeTabs(machine3, Management.machines.get(2), 2);
	}

}
