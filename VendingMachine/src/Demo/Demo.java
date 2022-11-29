package Demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Demo extends JFrame {
	static String itemSelection;


	public Demo() {
		setTitle("Customer UI");

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel panel1, panel2, panel3;

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		tabbedPane.addTab("Vending Machine #1", panel1);
		addContent(panel1, Main.machines.get(0));

		tabbedPane.addTab("Vending Machine #2", panel2);
		addContent(panel2, Main.machines.get(1));

		tabbedPane.addTab("Vending Machine #3", panel3);
		addContent(panel3, Main.machines.get(2));

		this.add(tabbedPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 800);
		this.setVisible(true);
	}
	
	public void addContent(JPanel panel, VendingMachine v) {
		panel.setLayout(null);

		//////////////// Section A///////////////////////
		// Row A
		int gap_A = 40;
		int startY_A = 50;
		int rowStartX = 50;

		JLabel rowA1 = new JLabel("1");
		JLabel rowA2 = new JLabel("2");
		JLabel rowA3 = new JLabel("3");
		JLabel rowA4 = new JLabel("4");

		rowA1.setBounds(rowStartX, startY_A, 15, 15);
		rowA1.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowA1);

		rowA2.setBounds(rowStartX, startY_A + gap_A, 15, 15);
		rowA2.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowA2);

		rowA3.setBounds(rowStartX, startY_A + gap_A * 2, 15, 15);
		rowA3.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowA3);

		rowA4.setBounds(rowStartX, startY_A + gap_A * 3, 15, 15);
		rowA4.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowA4);

		// A label
		JLabel sectionA = new JLabel("A");
		sectionA.setBounds(195, startY_A + gap_A * 3 + 45, 15, 20);
		sectionA.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(sectionA);

		// Section A Items
		int itemStartX = 120;

		JLabel itemA1 = new JLabel(
				String.format("%-25s %-5s", v.inventory.get(0).itemName, "$" + v.inventory.get(0).itemPrice));
		JLabel itemA2 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(1).itemName, "$" + v.inventory.get(1).itemPrice));
		JLabel itemA3 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(2).itemName, "$" + v.inventory.get(2).itemPrice));
		JLabel itemA4 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(3).itemName, "$" + v.inventory.get(3).itemPrice));

		itemA1.setBounds(itemStartX, startY_A, 250, 20);
		itemA1.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemA1);

		itemA2.setBounds(itemStartX, startY_A + gap_A, 250, 20);
		itemA2.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemA2);

		itemA3.setBounds(itemStartX, startY_A + gap_A * 2, 250, 20);
		itemA3.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemA3);

		itemA4.setBounds(itemStartX, startY_A + gap_A * 3, 250, 20);
		itemA4.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemA4);

		//////////////////////// Section B/////////////////////////////
		int gap_B = 40;
		int startY_B = startY_A + gap_A * 3 + 100;

		JLabel rowB1 = new JLabel("1");
		JLabel rowB2 = new JLabel("2");
		JLabel rowB3 = new JLabel("3");
		JLabel rowB4 = new JLabel("4");

		rowB1.setBounds(rowStartX, startY_B, 15, 15);
		rowB1.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowB1);

		rowB2.setBounds(rowStartX, startY_B + gap_B, 15, 15);
		rowB2.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowB2);

		rowB3.setBounds(rowStartX, startY_B + gap_B * 2, 15, 15);
		rowB3.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowB3);

		rowB4.setBounds(rowStartX, startY_B + gap_B * 3, 15, 15);
		rowB4.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(rowB4);

		// B label
		JLabel sectionB = new JLabel("B");
		sectionB.setBounds(195, startY_B + gap_B * 3 + 45, 15, 20);
		sectionB.setFont(new Font("Roboto", Font.PLAIN, 20));
		panel.add(sectionB);

		// Section B Items
		JLabel itemB1 = new JLabel(
				String.format("%-25s %-5s", v.inventory.get(4).itemName, "$" + v.inventory.get(4).itemPrice));
		JLabel itemB2 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(5).itemName, "$" + v.inventory.get(5).itemPrice));
		JLabel itemB3 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(6).itemName, "$" + v.inventory.get(6).itemPrice));
		JLabel itemB4 = new JLabel(
				String.format("%-28s %-5s", v.inventory.get(7).itemName, "$" + v.inventory.get(7).itemPrice));

		itemB1.setBounds(itemStartX, startY_B, 250, 20);
		itemB1.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemB1);

		itemB2.setBounds(itemStartX, startY_B + gap_B, 250, 20);
		itemB2.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemB2);

		itemB3.setBounds(itemStartX, startY_B + gap_B * 2, 250, 20);
		itemB3.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemB3);

		itemB4.setBounds(itemStartX, startY_B + gap_B * 3, 250, 20);
		itemB4.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(itemB4);

		//////////////////// Text Fields//////////////////
		JTextField fundsOutput = new JTextField("Funds: $" + v.credit);
		fundsOutput.setBounds(230, 500, 80, 30);
		panel.add(fundsOutput);

		JTextArea output = new JTextArea("Please Select an Item> ");
		output.setBounds(230, 550, 160, 60);
		panel.add(output);

		itemSelection = "";

		///////////////////// Buttons//////////////////////////
		int size = 50;
		int bGap = 5 + size;
		int bStartX = 30;
		int bStartY = 500;

		JButton button1 = new JButton("A");
		button1.setBounds(bStartX, bStartY, size, size);
		button1.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button1);
		button1.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button2 = new JButton("1");
		button2.setBounds(bStartX + bGap, bStartY, size, size);
		button2.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button2);
		button2.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button3 = new JButton("2");
		button3.setBounds(bStartX + bGap * 2, 500, size, size);
		button3.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button3);
		button3.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button4 = new JButton("B");
		button4.setBounds(bStartX, bStartY + bGap, size, size);
		button4.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button4);
		button4.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button5 = new JButton("3");
		button5.setBounds(bStartX + bGap, bStartY + bGap, size, size);
		button5.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button5);
		button5.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton button6 = new JButton("4");
		button6.setBounds(bStartX + bGap * 2, bStartY + bGap, size, size);
		button6.setFont(new Font("Roboto", Font.PLAIN, 12));
		panel.add(button6);
		button6.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		JButton enter = new JButton("ENTER");
		enter.setBounds(bStartX, bStartY + bGap * 2, 77, size);
		enter.setFont(new Font("Roboto", Font.PLAIN, 8));
		panel.add(enter);
		enter.addActionListener(e -> input(v, e.getActionCommand(), output, fundsOutput));

		// Money
		JButton fiveDollars = new JButton("$5");
		fiveDollars.setBounds(400, 400, 77, size);
		fiveDollars.setFont(new Font("Roboto", Font.PLAIN, 8));
		panel.add(fiveDollars);
		fiveDollars.addActionListener(e -> addFunds(v, fundsOutput, "5.0"));

		// Change
		JButton change = new JButton("CHANGE");
		change.setBounds(bStartX + 77 + 5, bStartY + bGap * 2, 77, size);
		change.setFont(new Font("Roboto", Font.PLAIN, 8));
		panel.add(change);
		change.addActionListener(e -> changeBack(v, output, fundsOutput));

	}

	public void changeBack(VendingMachine v, JTextArea output, JTextField fundsOuput) {
		String message = "Change Back: " + v.credit + "\n" + "Quarters: " + Customer.giveChangeBack(v);
		fundsOuput.setText("Funds: $" + v.credit);
		output.setText(message);

		TimerTask task = new TimerTask() {
			public void run() {
				output.setText("Please Select Item>");
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 2000);

	}

	public void addFunds(VendingMachine v, JTextField fundsOutput, String fund) {
		v.credit = v.credit + Double.parseDouble(fund);
		String updated = "Funds: $" + v.credit;
		fundsOutput.setText(updated);
	}

	public void input(VendingMachine v, String button, JTextArea output, JTextField fundsOutput) {
		if (!(button.equals("ENTER"))) {
			v.itemSelection = v.itemSelection + button;
			output.setText("Please Select an Item> " + v.itemSelection);
		} else {
			if (Customer.validInput(v, v.itemSelection)) {
				String message = Customer.purchaseItem(v, v.itemSelection);
				output.setText(message);;
				addFunds(v, fundsOutput, "0");
			}
			else {
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

}