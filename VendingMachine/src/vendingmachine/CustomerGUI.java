package vendingmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class CustomerGUI extends JFrame {
	static String itemSelection;
	static JTabbedPane tabbedPane;
	static JPanel panel1, panel2, panel3;
	
	public CustomerGUI(){
		setTitle("Customer UI");

		tabbedPane = new JTabbedPane();

		//JPanel panel1, panel2, panel3;

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		tabbedPane.addTab("Vending Machine #1", panel1);
		addContent(panel1, Management.machines.get(0));

		tabbedPane.addTab("Vending Machine #2", panel2);
		addContent(panel2, Management.machines.get(1));

		tabbedPane.addTab("Vending Machine #3", panel3);
		addContent(panel3, Management.machines.get(2));
		
		this.setResizable(false);
		this.add(tabbedPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.setVisible(true);
	}

	public static void addContent(JPanel panel, VendingMachine v) {
		panel.setLayout(null);
		// panel.setBackground(Color.white);
		Font itemFont = new Font("Consolas", Font.BOLD, 18);
		Font rowFont = new Font("Consolas", Font.BOLD, 18);

		//////////////// Section A///////////////////////
		// Row A
		int gap_A = 40;
		int startY_A = 50;
		int rowStartX = 50;

		JLabel rowA1 = new JLabel("1");
		JLabel rowA2 = new JLabel("2");
		JLabel rowA3 = new JLabel("3");
		JLabel rowA4 = new JLabel("4");

		rowA1.setBounds(rowStartX, startY_A, 15, 20);
		rowA1.setFont(rowFont);
		panel.add(rowA1);

		rowA2.setBounds(rowStartX, startY_A + gap_A, 15, 20);
		rowA2.setFont(rowFont);
		panel.add(rowA2);

		rowA3.setBounds(rowStartX, startY_A + gap_A * 2, 15, 20);
		rowA3.setFont(rowFont);
		panel.add(rowA3);

		rowA4.setBounds(rowStartX, startY_A + gap_A * 3, 15, 20);
		rowA4.setFont(rowFont);
		panel.add(rowA4);

		// A label
		JLabel sectionA = new JLabel("A");
		sectionA.setBounds(195, startY_A + gap_A * 3 + 45, 15, 20);
		sectionA.setFont(rowFont);
		panel.add(sectionA);

		// Section A Items
		int itemStartX = 120;

		JLabel itemA1 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(0).itemName, "$" + v.inventory.get(0).itemPrice));
		JLabel itemA2 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(1).itemName, "$" + v.inventory.get(1).itemPrice));
		JLabel itemA3 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(2).itemName, "$" + v.inventory.get(2).itemPrice));
		JLabel itemA4 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(3).itemName, "$" + v.inventory.get(3).itemPrice));

		itemA1.setBounds(itemStartX, startY_A, 350, 20);
		itemA1.setFont(itemFont);
		panel.add(itemA1);

		itemA2.setBounds(itemStartX, startY_A + gap_A, 250, 20);
		itemA2.setFont(itemFont);
		panel.add(itemA2);

		itemA3.setBounds(itemStartX, startY_A + gap_A * 2, 250, 20);
		itemA3.setFont(itemFont);
		panel.add(itemA3);

		itemA4.setBounds(itemStartX, startY_A + gap_A * 3, 250, 20);
		itemA4.setFont(itemFont);
		panel.add(itemA4);

		//////////////////////// Section B/////////////////////////////
		int gap_B = 40;
		int startY_B = startY_A + gap_A * 3 + 100;

		JLabel rowB1 = new JLabel("1");
		JLabel rowB2 = new JLabel("2");
		JLabel rowB3 = new JLabel("3");
		JLabel rowB4 = new JLabel("4");

		rowB1.setBounds(rowStartX, startY_B, 15, 20);
		rowB1.setFont(rowFont);
		panel.add(rowB1);

		rowB2.setBounds(rowStartX, startY_B + gap_B, 15, 20);
		rowB2.setFont(rowFont);
		panel.add(rowB2);

		rowB3.setBounds(rowStartX, startY_B + gap_B * 2, 15, 20);
		rowB3.setFont(rowFont);
		panel.add(rowB3);

		rowB4.setBounds(rowStartX, startY_B + gap_B * 3, 15, 20);
		rowB4.setFont(rowFont);
		panel.add(rowB4);

		// B label
		JLabel sectionB = new JLabel("B");
		sectionB.setBounds(195, startY_B + gap_B * 3 + 45, 15, 20);
		sectionB.setFont(rowFont);
		panel.add(sectionB);

		// Section B Items
		JLabel itemB1 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(4).itemName, "$" + v.inventory.get(4).itemPrice));
		JLabel itemB2 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(5).itemName, "$" + v.inventory.get(5).itemPrice));
		JLabel itemB3 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(6).itemName, "$" + v.inventory.get(6).itemPrice));
		JLabel itemB4 = new JLabel(
				String.format("%-15s %-5s", v.inventory.get(7).itemName, "$" + v.inventory.get(7).itemPrice));

		itemB1.setBounds(itemStartX, startY_B, 250, 20);
		itemB1.setFont(itemFont);
		panel.add(itemB1);

		itemB2.setBounds(itemStartX, startY_B + gap_B, 250, 20);
		itemB2.setFont(itemFont);
		panel.add(itemB2);

		itemB3.setBounds(itemStartX, startY_B + gap_B * 2, 250, 20);
		itemB3.setFont(itemFont);
		panel.add(itemB3);

		itemB4.setBounds(itemStartX, startY_B + gap_B * 3, 250, 20);
		itemB4.setFont(itemFont);
		panel.add(itemB4);

		//////////////////// Text Fields//////////////////
		JTextField fundsOutput = new JTextField("Funds: $" + v.getCredit());
		fundsOutput.setBounds(230, 500, 120, 30);
		panel.add(fundsOutput);
		fundsOutput.setEditable(false);

		JTextArea output = new JTextArea("Please Select an Item> ");
		output.setBounds(230, 550, 160, 60);
		panel.add(output);
		output.setEditable(false);
		output.setLineWrap(true);

		itemSelection = "";

		///////////////////// Buttons//////////////
		int size = 50;
		int bGap = 5 + size;
		int bStartX = 30;
		int bStartY = 500;
		Font buttonFont = new Font("Sans-serif", Font.BOLD, 15);

		JButton button1 = new JButton("A");
		button1.setBounds(bStartX, bStartY, size, size);
		button1.setFont(buttonFont);
		panel.add(button1);
		button1.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button2 = new JButton("1");
		button2.setBounds(bStartX + bGap, bStartY, size, size);
		button2.setFont(buttonFont);
		panel.add(button2);
		button2.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button3 = new JButton("2");
		button3.setBounds(bStartX + bGap * 2, 500, size, size);
		button3.setFont(buttonFont);
		panel.add(button3);
		button3.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button4 = new JButton("B");
		button4.setBounds(bStartX, bStartY + bGap, size, size);
		button4.setFont(buttonFont);
		panel.add(button4);
		button4.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button5 = new JButton("3");
		button5.setBounds(bStartX + bGap, bStartY + bGap, size, size);
		button5.setFont(buttonFont);
		panel.add(button5);
		button5.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button6 = new JButton("4");
		button6.setBounds(bStartX + bGap * 2, bStartY + bGap, size, size);
		button6.setFont(buttonFont);
		panel.add(button6);
		button6.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton enter = new JButton("ENTER");
		enter.setBounds(bStartX, bStartY + bGap * 2, 77, size);
		enter.setFont(new Font("Consolas", Font.BOLD, 13));
		panel.add(enter);
		enter.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		// Money
		int start_X = 450;
		int start_Y = 100;
		int gap = 50;
		
		JButton fiftyDollars = new JButton("$50");
		fiftyDollars.setFont(buttonFont);
		panel.add(fiftyDollars);
		fiftyDollars.addActionListener(e -> addFunds(v, fundsOutput, output, "50.0"));
		fiftyDollars.setBounds(start_X, start_Y, fiftyDollars.getPreferredSize().width, fiftyDollars.getPreferredSize().height);
		
		JButton twentyDollars = new JButton("$20");
		twentyDollars.setFont(buttonFont);
		panel.add(twentyDollars);
		twentyDollars.addActionListener(e -> addFunds(v, fundsOutput,output, "20.0"));
		twentyDollars.setBounds(start_X, fiftyDollars.getY() + fiftyDollars.getPreferredSize().height + gap, twentyDollars.getPreferredSize().width, twentyDollars.getPreferredSize().height);
		
		JButton tenDollars = new JButton("$10");
		tenDollars.setFont(buttonFont);
		panel.add(tenDollars);
		tenDollars.addActionListener(e -> addFunds(v, fundsOutput,output, "10.0"));
		tenDollars.setBounds(start_X, twentyDollars.getY() + twentyDollars.getPreferredSize().height + gap, tenDollars.getPreferredSize().width, tenDollars.getPreferredSize().height);
		
		JButton fiveDollars = new JButton("$5");
		fiveDollars.setFont(buttonFont);
		panel.add(fiveDollars);
		fiveDollars.addActionListener(e -> addFunds(v, fundsOutput,output, "5.0"));
		fiveDollars.setBounds(start_X, tenDollars.getY() + tenDollars.getPreferredSize().height + gap, fiveDollars.getPreferredSize().width, fiveDollars.getPreferredSize().height);
		
		JButton oneDollars = new JButton("$1");
		oneDollars.setFont(buttonFont);
		panel.add(oneDollars);
		oneDollars.addActionListener(e -> addFunds(v, fundsOutput,output, "1.0"));
		oneDollars.setBounds(start_X, fiveDollars.getY() + fiveDollars.getPreferredSize().height + gap, oneDollars.getPreferredSize().width, oneDollars.getPreferredSize().height);
		
		JButton quarter = new JButton("$0.25");
		quarter.setFont(buttonFont);
		panel.add(quarter);
		quarter.addActionListener(e -> addFunds(v, fundsOutput,output, "0.25"));
		quarter.setBounds(start_X, oneDollars.getY() + oneDollars.getPreferredSize().height + gap, quarter.getPreferredSize().width, quarter.getPreferredSize().height);
		
		JButton dime = new JButton("$0.10");
		dime.setFont(buttonFont);
		panel.add(dime);
		dime.addActionListener(e -> addFunds(v, fundsOutput,output, "0.1"));
		dime.setBounds(start_X, quarter.getY() + quarter.getPreferredSize().height + gap, dime.getPreferredSize().width, dime.getPreferredSize().height);
		
		// Change
		JButton change = new JButton("CHANGE");
		change.setBounds(bStartX + 77 + 5, bStartY + bGap * 2, 77, size);
		change.setFont(new Font("Consolas", Font.BOLD, 13));
		panel.add(change);
		change.addActionListener(e -> changeBack(v, output, fundsOutput));

	}

	public static void changeBack(VendingMachine v, JTextArea output, JTextField fundsOuput) {
		String message = "Change Back: " + v.getCredit() + "\n" + "Quarters: " + Customer.giveChangeBack(v);
		fundsOuput.setText("Funds: $" + v.getCredit());
		output.setText(message);

		TimerTask task = new TimerTask() {
			public void run() {
				output.setText("Please Select Item>");
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 2000);

	}

	public static void addFunds(VendingMachine v, JTextField fundsOutput, JTextArea output, String fund) {

		if(Customer.paymentValidity(fund) == true) {
			Double credit = v.getCredit() + Double.parseDouble(fund);
			v.setCredit(credit);
			String updated = "Funds: $" + credit;
			fundsOutput.setText(updated);
		}else {
			output.setText("Invalid Currency. Try Again.");
			TimerTask task = new TimerTask() {
				public void run() {
					output.setText("Please Select Item>");
				}
			};

			Timer timer = new Timer();
			timer.schedule(task, 2000);
		}
	}

	public static void input(VendingMachine v, String button, JTextArea output, JTextField fundsOutput) {
		if (!(button.equals("ENTER"))) {
			v.itemSelection = v.itemSelection + button;
			output.setText("Please Select an Item> " + v.itemSelection);
		} else {
			if (Customer.validInput(v, v.itemSelection)) {
				String message = Customer.purchaseItem(v, v.itemSelection);
				output.setText(message);
				addFunds(v, fundsOutput, output,"0.0");
				
			} else {
				output.setText("Invalid Selection!\nTry again");
			}

			TimerTask task = new TimerTask() {
				public void run() {
					output.setText("Please Select Item>");
				}
			};

			Timer timer = new Timer();
			timer.schedule(task, 2000);
			v.itemSelection = "";
		}

	}
	
	public static void refreshPanels() throws ParseException {
		tabbedPane.removeAll();
		panel1.removeAll();
		panel2.removeAll();
		panel3.removeAll();
		
		tabbedPane.addTab("Vending Machine #1", panel1);
		addContent(panel1, Management.machines.get(0));

		tabbedPane.addTab("Vending Machine #2", panel2);
		addContent(panel2, Management.machines.get(1));

		tabbedPane.addTab("Vending Machine #3", panel3);
		addContent(panel3, Management.machines.get(2));
	}

}