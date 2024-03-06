package UserInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import databaseModule.DatabaseConnector;
import databaseModule.DatabaseOperations;

public class ParentAccountGUI extends JFrame{

	private ParentAccount parentAccount;
	private JComboBox<ChildAccount> childDropdown;
	private JTextField choreNameField, choreCategoryField, choreTimeField, chorePaymentField;
	private JButton createChoreButton, assignChoreButton, payChoreButton, checkBalanceButton, addChildButton;
	private JButton logoutButton;
	private JTable choreTable;

	public ParentAccountGUI(ParentAccount parentAccount) {
		this.parentAccount = parentAccount;
		initialize();
		// Refresh the chore table with updated chore data
		displayParentChores(); 

	}

	private void initialize() {
		setTitle("Parent Account");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Child Dropdown
		List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
		childDropdown = new JComboBox<>(children.toArray(new ChildAccount[0]));
		mainPanel.add(childDropdown);

		// Add Child Button
		addChildButton = new JButton("Add Child"); // Added button
		mainPanel.add(addChildButton); // Added button

		// Chore Creation Panel
		JPanel chorePanel = new JPanel();
		chorePanel.setBorder(BorderFactory.createTitledBorder("Chore Creation"));

		choreNameField = new JTextField(15);
		choreCategoryField = new JTextField(15);
		choreTimeField = new JTextField(5);
		chorePaymentField = new JTextField(5);
		createChoreButton = new JButton("Create Chore");

		//Assign Chore Button
		assignChoreButton = new JButton("Assign Chore");
		mainPanel.add(assignChoreButton);
		
		//Pay Chore Button
		payChoreButton = new JButton("Pay Chore");
		mainPanel.add(payChoreButton);

		// Add action listener for the assign chore button
		assignChoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assignChore();
			}
		});
		
		// Add action listener for the pay chore button
		payChoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payChore();
				displayParentChores();
			}
		});

		//Add logout button
		logoutButton = new JButton("Log Out");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Logged Out Successfully");
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
				dispose(); // Close login window
			}
		});

		// Create a table model
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Chore ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");
		tableModel.addColumn("isCompleted");
		tableModel.addColumn("isPaid");

		// Create the chore table using the table model
		choreTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(choreTable);
		mainPanel.add(scrollPane);




		chorePanel.add(new JLabel("Name: "));
		chorePanel.add(choreNameField);
		chorePanel.add(new JLabel("Category: "));
		chorePanel.add(choreCategoryField);
		chorePanel.add(new JLabel("Time: "));
		chorePanel.add(choreTimeField);
		chorePanel.add(new JLabel("Payment: "));
		chorePanel.add(chorePaymentField);
		chorePanel.add(createChoreButton);

		mainPanel.add(chorePanel);

		// Check Balance Button
		checkBalanceButton = new JButton("Check Balance");
		mainPanel.add(checkBalanceButton);

		// Log Out Button
		mainPanel.add(logoutButton);


		createChoreButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createChore();
			}
		});

		checkBalanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBalance();
			}
		});

		addChildButton.addActionListener(new ActionListener() { // Add action listener for add child button
			@Override
			public void actionPerformed(ActionEvent e) {
				addChild();
			}
		});

		getContentPane().add(mainPanel);
		setVisible(true);
	}
	///////////////

	private void addChild() {
		String childUserName = JOptionPane.showInputDialog("Enter child's username:");
		if (childUserName != null && !childUserName.isEmpty()) {
			if(DatabaseOperations.checkIfChildExists(childUserName)) {
				DatabaseOperations.addParentToChild(this.parentAccount.getUsername(), childUserName);
				List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
				childDropdown.setModel(new DefaultComboBoxModel<>(children.toArray(new ChildAccount[0])));
				JOptionPane.showMessageDialog(this, "Child added successfully!");
			} else {
				JOptionPane.showMessageDialog(this, "Child does not exist!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Please enter a valid child username!");
		}
	}

	private void createChore() {
		String choreName = choreNameField.getText();
		String choreCategory = choreCategoryField.getText();
		double choreTime = Double.parseDouble(choreTimeField.getText());
		double chorePayment = Double.parseDouble(chorePaymentField.getText());

		// Insert new chore into the database using DatabaseOperations class
		Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
		
		DatabaseOperations.insertChore(newChore, parentAccount.getUsername());

		// Update the table model with the new chore data
		displayParentChores();



		// Display success message
		JOptionPane.showMessageDialog(this, "Chore created successfully!");
	}

	private void payChore() {
		
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		int selectedRow = choreTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore to pay to selected child");
		}
		
		int choreId = (int) choreTable.getValueAt(selectedRow, 0);
		
		String childWhoCompletedChore = DatabaseOperations.getChoreCompletingChildUsername(choreId);
		
		if(choreTable.getValueAt(selectedRow, 5).equals("Yes") && choreTable.getValueAt(selectedRow, 6).equals("No")) {
			if(selectedChild.getUsername().equals(childWhoCompletedChore)) {
				DatabaseOperations.markChoreAsPaid(choreId, selectedChild.getUsername());
				JOptionPane.showMessageDialog(this, "Chore paid to " + selectedChild.getUsername());	
			}
			else {
				JOptionPane.showMessageDialog(this, "Chore is not completed by " + selectedChild.getUsername() +
						" instead completed by " + childWhoCompletedChore);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Either chore is not completed or chore is already paid!");
		}
	}


	// Define a method to handle chore assignment
	private void assignChore() {
	
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		int selectedRow = choreTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore to assign to selected child");
		}
		
		int choreId = (int) choreTable.getValueAt(selectedRow, 0);
		
		if(choreTable.getValueAt(selectedRow, 5).equals("No")) {
			DatabaseOperations.insertChoreAssignment(choreId, selectedChild.getUsername());
			JOptionPane.showMessageDialog(this, "Chore assigned to " + selectedChild.getUsername());
		}
		else {
			JOptionPane.showMessageDialog(this, "Chore already completed!");
		}
	}

	private void displayParentChores() {
		// Clear existing data from the chore table
		DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
		tableModel.setRowCount(0); // Remove all rows from the table
		
		// Fetch and display chores associated with the parent account
		List<Chore> parentChores = DatabaseOperations.getAllChoresofParent(parentAccount.getUsername());
		for (Chore chore : parentChores) {
			Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
					chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
					chore.isPaid() ? "Yes" : "No"};
			tableModel.addRow(rowData);
		}
	}

	private void checkBalance() {
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		double balance = DatabaseOperations.getChildBalance(selectedChild.getUsername());
		JOptionPane.showMessageDialog(this, selectedChild+ "'s Balance: $" + balance);
	}

	public static void main(String[] args) {

		ParentAccount parentAccount = new ParentAccount("parentUsername", "parentPassword");
		new ParentAccountGUI(parentAccount);

	}



}
