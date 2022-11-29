package Demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class ManagementGUI extends JFrame {

	public ManagementGUI() {
		setTitle("Management UI");

		JTabbedPane machineTabs = new JTabbedPane();

		JPanel machine1, machine2, machine3;

		machine1 = new JPanel();
		machine2 = new JPanel();
		machine3 = new JPanel();

		machineTabs.addTab("Vending Machine #1", machine1);
		makeTabs(machine1, Main.machines.get(0));

		machineTabs.addTab("Vending Machine #2", machine2);
		makeTabs(machine2, Main.machines.get(1));

		machineTabs.addTab("Vending Machine #3", machine3);
		makeTabs(machine3, Main.machines.get(2));

		this.add(machineTabs);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 900);
		this.setVisible(true);
	}

	public void makeTabs(JPanel panel, VendingMachine v) {
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
		addInstructionContent(viewInstructions, v);

		panel.add(managementTabs);
	}

	// Overview Content displays: Machine Location, Machine Status, Inventory and
	// Purchase History
	public void addOverviewContent(JPanel panel, VendingMachine v) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		int STARTX = 50;
		int STARTY = 50;
		int gap = 50;

		// Machine Location
		JLabel location = new JLabel("Location: " + v.machineLocation);
		location.setBounds(STARTX, STARTY, 50, location.getSize().width);
		panel.add(location);

		// Machine Status
		JLabel status = new JLabel("Vending Machine Status: " + Management.displayMachineStatus(v));
		status.setBounds(STARTX, STARTY + gap * 2, 50, status.getSize().width);
		panel.add(status);

		// Inventory
		JLabel inventory = new JLabel("Vending Machine Inventory");
		panel.add(inventory);

		JTextArea inventoryContent = new JTextArea();
		inventoryContent.setBounds(0, 0, inventoryContent.getSize().height, inventoryContent.getSize().width);
		panel.add(inventoryContent);
		setInventoryContent(inventoryContent, v);

		JButton inventoryRefresh = new JButton("Refresh");
		inventoryRefresh.setBounds(250, 30, 50, 100);
		panel.add(inventoryRefresh);
		inventoryRefresh.addActionListener(e -> setInventoryContent(inventoryContent, v));

		// Purchase History
		JTextArea purchases = new JTextArea();
		purchases.setBounds(30, 30, 200, 500);
		panel.add(purchases);
		setHistoryContent(purchases, v);

		JButton purchasesRefresh = new JButton("Refresh");
		purchasesRefresh.setBounds(250, 30, 50, 100);
		panel.add(purchasesRefresh);
		purchasesRefresh.addActionListener(e -> setHistoryContent(purchases, v));

	}

	// Displays Inventory
	public void setInventoryContent(JTextArea inventory, VendingMachine v) {

		String message = "";

		message = Management.displayInventory(v.inventory);

		inventory.setText(message);
	}

	// Displays History
	public void setHistoryContent(JTextArea purchases, VendingMachine v) {

		if (v.history.size() != 0) {
			purchases.setText("");

			String message = Management.displayHistory(v.history);

			purchases.setText(message);
		} else {
			purchases.setText("No Current History");
		}
	}

	// Analytics display: Item Popularity, Expired Items, and Recalled Items
	public void addAnalyticsContent(JPanel panel, VendingMachine v) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Popular Items
		JTextArea popular = new JTextArea();
		popular.setBounds(30, 30, 200, 500);
		panel.add(popular);
		setPopularContent(popular, v);

		JButton popularRefresh = new JButton("Refresh");
		popularRefresh.setBounds(250, 30, 50, 100);
		panel.add(popularRefresh);
		popularRefresh.addActionListener(e -> setPopularContent(popular, v));
	}

	public void setPopularContent(JTextArea popular, VendingMachine v) {

		popular.setText("");

		String message = Management.displayPopular(v);

		popular.setText(message);

	}

	public void addInstructionContent(JPanel panel, VendingMachine v) {

	}

}
