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
	private JButton createChoreButton, checkBalanceButton, addChildButton;
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
		setSize(600, 400);
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
		JButton assignChoreButton = new JButton("Assign Chore");
		mainPanel.add(assignChoreButton);

		// Add action listener for the assign chore button
		assignChoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assignChore();
			}
		});

		// Add Chore List Panel
		JPanel choreListPanel = new JPanel();
		choreListPanel.setBorder(BorderFactory.createTitledBorder("Chore List"));
		mainPanel.add(choreListPanel);

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
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");

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
		DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
		Object[] rowData = {choreName, choreCategory, choreTime, chorePayment};
		tableModel.addRow(rowData);



		// Display success message
		JOptionPane.showMessageDialog(this, "Chore created successfully!");

		 displayParentChores();
	}




	// Define a method to handle chore assignment
	private void assignChore() {
		String choreName = choreNameField.getText();
		String choreCategory = choreCategoryField.getText();
		double choreTime = Double.parseDouble(choreTimeField.getText());
		double chorePayment = Double.parseDouble(chorePaymentField.getText());

		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
		parentAccount.assignChore(selectedChild, newChore);

		// Optionally, provide feedback to the user
		JOptionPane.showMessageDialog(this, "Chore assigned to " + selectedChild.getUsername());
	}

	private void displayParentChores() {
		// Clear existing data from the chore table
		DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
		//tableModel.setRowCount(0); // Remove all rows from the table

		// Fetch and display chores associated with the parent account
		List<Chore> parentChores = DatabaseOperations.getAllChoresofParent(parentAccount.getUsername());
		for (Chore chore : parentChores) {
			Object[] rowData = {chore.getName(), chore.getCategory(), chore.getTime(), chore.getPayment()};
			tableModel.addRow(rowData);
		}
	}

	private void checkBalance() {
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		double balance = parentAccount.checkChildBalance(selectedChild);
		JOptionPane.showMessageDialog(this, selectedChild+ "'s Balance: $" + balance);
	}

	public static void main(String[] args) {

		ParentAccount parentAccount = new ParentAccount("parentUsername", "parentPassword");
		new ParentAccountGUI(parentAccount);

	}



}
