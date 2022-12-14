package vendingmachine;

import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class RestockerGUI extends JFrame {
	public RestockerGUI() {
		setTitle("Restocker UI");
		
		JPanel mainMenu = new JPanel();
		this.add(mainMenu);

		setMainMenu(mainMenu);

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(360, 640);
		this.setVisible(true);
	}

	public static void setMainMenu(JPanel panel) {
		panel.setSize(360, 640);
		panel.setLayout(null);
		panel.removeAll();

		Font headerFont = new Font("Consolas", Font.BOLD, 30);
		Font buttonFont = new Font("Consolas", Font.PLAIN, 20);

		int gap = 50;

		// Main Menu Header
		JLabel menuLabel = new JLabel("Main Menu");
		panel.add(menuLabel);
		menuLabel.setFont(headerFont);
		menuLabel.setBounds((panel.getWidth() - menuLabel.getPreferredSize().width) / 2, gap, menuLabel.getPreferredSize().width, menuLabel.getPreferredSize().height);

		// Machine 1
		JButton machine1 = new JButton("Vending Machine #1");
		panel.add(machine1);
		machine1.setFont(buttonFont);
		machine1.setBounds((panel.getWidth() - machine1.getPreferredSize().width) / 2, menuLabel.getY() + gap * 2, machine1.getPreferredSize().width, machine1.getPreferredSize().height);
		machine1.addActionListener(e -> setOptionsMenu(panel, Management.machines.get(0)));
		
		// Machine 2
		JButton machine2 = new JButton("Vending Machine #2");
		panel.add(machine2);
		machine2.setFont(buttonFont);
		machine2.setBounds((panel.getWidth() - machine2.getPreferredSize().width) / 2, machine1.getY() + gap * 2, machine2.getPreferredSize().width, machine2.getPreferredSize().height);
		machine2.addActionListener(e -> setOptionsMenu(panel, Management.machines.get(1)));
		
		// Machine 3
		JButton machine3 = new JButton("Vending Machine #3");
		panel.add(machine3);
		machine3.setFont(buttonFont);
		machine3.setBounds((panel.getWidth() - machine3.getPreferredSize().width) / 2, machine2.getY() + gap * 2, machine3.getPreferredSize().width, machine3.getPreferredSize().height);
		machine3.addActionListener(e -> setOptionsMenu(panel, Management.machines.get(2)));
	}
	
	public static void setOptionsMenu(JPanel panel, VendingMachine v) {
		panel.setSize(360, 640);
		panel.setLayout(null);
		
		panel.removeAll();

		Font headerFont = new Font("Consolas", Font.BOLD, 25);
		Font labelFont = new Font("Consolas", Font.BOLD,12);
		Font buttonFont = new Font("Consolas", Font.PLAIN, 20);

		int gap = 30;
		
		//Back Button
		JButton backButton = new JButton("Back");
		panel.add(backButton);
		backButton.setFont(new Font("Consolas", Font.PLAIN, 10));
		backButton.setBounds(10, gap, backButton.getPreferredSize().width, backButton.getPreferredSize().height);
		backButton.addActionListener(e -> setMainMenu(panel));

		//Option Menu Header
		JLabel optionLabel = new JLabel("Options Menu");
		panel.add(optionLabel);
		optionLabel.setFont(headerFont);
		optionLabel.setBounds((panel.getWidth() - optionLabel.getPreferredSize().width) / 2, gap, optionLabel.getPreferredSize().width, optionLabel.getPreferredSize().height);
		
		//Location
		JLabel locationLabel = new JLabel("Location: " + v.getLocation());
		panel.add(locationLabel);
		locationLabel.setFont(labelFont);
		locationLabel.setBounds((panel.getWidth() - locationLabel.getPreferredSize().width)/2, optionLabel.getY() + (gap+20), locationLabel.getPreferredSize().width, locationLabel.getPreferredSize().height);
		
		//Status
		JLabel statusLabel = new JLabel("Status: " + v.getStatus());
		panel.add(statusLabel);
		statusLabel.setFont(labelFont);
		statusLabel.setBounds((panel.getWidth() - statusLabel.getPreferredSize().width)/2, locationLabel.getY() + gap, statusLabel.getPreferredSize().width*2, statusLabel.getPreferredSize().height);
		
		//Set Status
		JButton setStatusButton = new JButton("Set Status");
		panel.add(setStatusButton);
		setStatusButton.setFont(buttonFont);
		setStatusButton.setBounds((panel.getWidth() - setStatusButton.getPreferredSize().width) / 2, statusLabel.getY() + gap*2, setStatusButton.getPreferredSize().width, setStatusButton.getPreferredSize().height);
		setStatusButton.addActionListener(e -> setStatusMenu(panel, v));
		
		//Set Status
		JButton viewInstructionsButton = new JButton("View Instructions");
		panel.add(viewInstructionsButton);
		viewInstructionsButton.setFont(buttonFont);
		viewInstructionsButton.setBounds((panel.getWidth() - viewInstructionsButton.getPreferredSize().width) / 2, setStatusButton.getY() + gap*3, viewInstructionsButton.getPreferredSize().width, viewInstructionsButton.getPreferredSize().height);
		viewInstructionsButton.addActionListener(e -> setInstructions(panel, v));
		
	}
	
	public static void setInstructions(JPanel panel, VendingMachine v) {
		panel.setSize(360, 640);
		panel.setLayout(null);
		
		panel.removeAll();

		Font headerFont = new Font("Consolas", Font.BOLD, 25);
		Font labelFont = new Font("Consolas", Font.BOLD,13);
		Font buttonFont = new Font("Consolas", Font.PLAIN, 20);
		Font displayInfo = new Font("Consolas", Font.PLAIN, 14);

		int gap = 30;
		
		//Back Button
		JButton backButton = new JButton("Back");
		panel.add(backButton);
		backButton.setFont(new Font("Consolas", Font.PLAIN, 10));
		backButton.setBounds(10, gap, backButton.getPreferredSize().width, backButton.getPreferredSize().height);
		backButton.addActionListener(e -> setOptionsMenu(panel,v));
		
		//Refresh Button
		JButton refreshButton = new JButton("Refresh");
		panel.add(refreshButton);
		refreshButton.setFont(new Font("Consolas", Font.PLAIN,10));
		refreshButton.setBounds(panel.getWidth() - (refreshButton.getPreferredSize().width + 15), gap, refreshButton.getPreferredSize().width, refreshButton.getPreferredSize().height);
		refreshButton.addActionListener(e -> setInstructions(panel,v));

		//Instructions Menu Header
		JLabel instructionsLabel = new JLabel("Instructions");
		panel.add(instructionsLabel);
		instructionsLabel.setFont(headerFont);
		instructionsLabel.setBounds((panel.getWidth() - instructionsLabel.getPreferredSize().width) / 2, gap, instructionsLabel.getPreferredSize().width, instructionsLabel.getPreferredSize().height);
		
		//Location
		JLabel locationLabel = new JLabel("Location: " + v.getLocation());
		panel.add(locationLabel);
		locationLabel.setFont(labelFont);
		locationLabel.setBounds((panel.getWidth() - locationLabel.getPreferredSize().width)/2, instructionsLabel.getY() + (gap+20), locationLabel.getPreferredSize().width, locationLabel.getPreferredSize().height);
		
		//Status
		JLabel statusLabel = new JLabel("Status: " + v.getStatus());
		panel.add(statusLabel);
		statusLabel.setFont(labelFont);
		statusLabel.setBounds((panel.getWidth() - statusLabel.getPreferredSize().width)/2, locationLabel.getY() + gap, statusLabel.getPreferredSize().width*2, statusLabel.getPreferredSize().height);

		// Remove Items
		JLabel removeLabel = new JLabel("Items to Remove");
		panel.add(removeLabel);
		removeLabel.setFont(labelFont);

		JTextArea removeContent = new JTextArea();
		panel.add(removeContent);
		removeContent.setFont(displayInfo);
		setRemoveContent(removeContent, v);
		removeContent.setEditable(false);

		// Add Items
		JLabel addLabel = new JLabel("Items to Add");
		panel.add(addLabel);
		addLabel.setFont(labelFont);

		JTextArea addContent = new JTextArea();
		panel.add(addContent);
		addContent.setFont(displayInfo);
		setAddContent(addContent, v);
		addContent.setEditable(false);

		// Confirm Completed
		JButton finish = new JButton("Finish");
		panel.add(finish);
		finish.addActionListener(e -> completeInstructions(removeContent, addContent, v));
		
		//Instruction Content Layout
		int border_gap = (panel.getWidth() - removeContent.getPreferredSize().width)/2;
		
		//Remove Layout
		removeLabel.setBounds((panel.getWidth() - removeLabel.getPreferredSize().width)/2, statusLabel.getY() + gap, removeLabel.getPreferredSize().width, removeLabel.getPreferredSize().height);
		removeContent.setBounds((panel.getWidth() - removeContent.getPreferredSize().width)/2, removeLabel.getY() + (gap-10), removeContent.getPreferredSize().width, removeContent.getPreferredSize().height);
		
		//Add Layout
		addLabel.setBounds((panel.getWidth() - addLabel.getPreferredSize().width)/2, removeContent.getY() + removeContent.getPreferredSize().height + (gap-10), addLabel.getPreferredSize().width, addLabel.getPreferredSize().height);
		addContent.setBounds((panel.getWidth() - addContent.getPreferredSize().width)/2, addLabel.getY() + (gap-10), addContent.getPreferredSize().width, addContent.getPreferredSize().height);
		
		//confirm button
		finish.setBounds((panel.getWidth() - finish.getPreferredSize().width)/2, addContent.getY() + addContent.getPreferredSize().height + (gap-10), finish.getPreferredSize().width, finish.getPreferredSize().height);
	}
	
	public static void setStatusMenu(JPanel panel, VendingMachine v) {
		panel.setSize(360, 640);
		panel.setLayout(null);
		
		panel.removeAll();

		Font headerFont = new Font("Consolas", Font.BOLD, 25);
		Font labelFont = new Font("Consolas", Font.BOLD,12);
		Font buttonFont = new Font("Consolas", Font.PLAIN, 20);

		int gap = 30;
		
		//Back Button
		JButton backButton = new JButton("Back");
		panel.add(backButton);
		backButton.setFont(new Font("Consolas", Font.PLAIN, 10));
		backButton.setBounds(10, gap, backButton.getPreferredSize().width, backButton.getPreferredSize().height);
		backButton.addActionListener(e -> setOptionsMenu(panel,v));

		//setStatus Header
		JLabel setStatusLabel = new JLabel("Set Status");
		panel.add(setStatusLabel);
		setStatusLabel.setFont(headerFont);
		setStatusLabel.setBounds((panel.getWidth() - setStatusLabel.getPreferredSize().width) / 2, gap, setStatusLabel.getPreferredSize().width, setStatusLabel.getPreferredSize().height);
		
		//Status
		JLabel locationLabel = new JLabel("Location: " + v.getLocation());
		panel.add(locationLabel);
		locationLabel.setFont(labelFont);
		locationLabel.setBounds((panel.getWidth() - locationLabel.getPreferredSize().width)/2, setStatusLabel.getY() + (gap+20), locationLabel.getPreferredSize().width, locationLabel.getPreferredSize().height);
		
		//Status
		JLabel statusLabel = new JLabel("Status: " + v.getStatus());
		panel.add(statusLabel);
		statusLabel.setFont(labelFont);
		statusLabel.setBounds((panel.getWidth() - statusLabel.getPreferredSize().width)/2, locationLabel.getY() + gap, statusLabel.getPreferredSize().width*2, statusLabel.getPreferredSize().height);
		
		//Set to Restocking
		JButton setRestockingButton = new JButton("Set to Restocking");
		panel.add(setRestockingButton);
		setRestockingButton.setFont(buttonFont);
		setRestockingButton.setBounds((panel.getWidth() - setRestockingButton.getPreferredSize().width) / 2, statusLabel.getY() + gap*2, setRestockingButton.getPreferredSize().width, setRestockingButton.getPreferredSize().height);
		setRestockingButton.addActionListener(e -> setStatus("Restocking", statusLabel, v));
		
		//Set to Online
		JButton setOnlineButton = new JButton("Set to Online");
		panel.add(setOnlineButton);
		setOnlineButton.setFont(buttonFont);
		setOnlineButton.setBounds((panel.getWidth() - setOnlineButton.getPreferredSize().width) / 2, setRestockingButton.getY() + gap*3, setOnlineButton.getPreferredSize().width, setOnlineButton.getPreferredSize().height);
		setOnlineButton.addActionListener(e -> setStatus("Online", statusLabel, v));
	}

	public static void completeInstructions(JTextArea removeContent, JTextArea addContent, VendingMachine v) {
		removeContent.setText("No Current Instructions");
		addContent.setText("No Current Instructions");

		Restocker.updateItems(v);
		try {
			CustomerGUI.refreshPanels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setStatus(String status, JLabel statusLabel, VendingMachine v) {
		statusLabel.setText("Status: " + status);

		Restocker.setMachineStatus(status, v);
	}

	public static void setRemoveContent(JTextArea removeContent, VendingMachine v) {
		String str = "";

		str = Restocker.displayRemoveInstruction(v);

		removeContent.setText(str);
	}

	public static void setAddContent(JTextArea addContent, VendingMachine v) {
		String str = "";

		str = Restocker.displayAddInstruction(v);

		addContent.setText(str);
	}
}
