package UserInterface;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;
import databaseModule.DatabaseOperations;


public class ChildAccountGUI extends JFrame{

	    private JTextField chores;
	    private JButton checkBalanceButton;
	    private JButton logoutButton;
	    private JButton markAsCompletedButton, hoursWorkedButton;
		private ChildAccount childAccount;
	 	private ParentAccount parentAccount;
	 	private JTable choreTable;


	    public ChildAccountGUI(ChildAccount childAccount) {
	        this.childAccount = childAccount;
	        initialize();
	        displayChildChores();
	    }

	    private void initialize() {
	        setTitle("Child Account");
	        setSize(550, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        


	        // Chore Creation Panel
	        
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

	        
	        // Check Balance Button
	        checkBalanceButton = new JButton("Check Balance");
	        //mainPanel.add(checkBalanceButton);
        	add(checkBalanceButton,BorderLayout.NORTH);

	        // Log Out Button
        	add(logoutButton,BorderLayout.PAGE_END);
	        //mainPanel.add(logoutButton);


	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance();
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
			
			markAsCompletedButton = new JButton("Mark as Completed");
			mainPanel.add(markAsCompletedButton);
			
			hoursWorkedButton = new JButton("Hours Worked");
			mainPanel.add(hoursWorkedButton);
			
			markAsCompletedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					markAsCompleted();
					displayChildChores();
				}
			});
			
			hoursWorkedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getHoursWorked();
				}
			});

	        getContentPane().add(mainPanel);
	        setVisible(true);

	    }
	    
    
	    private void checkBalance() {
	        double balance = DatabaseOperations.getChildBalance(childAccount.getUsername());
	        JOptionPane.showMessageDialog(this, "Your Balance: $" + balance);
	    }
	    
	    private void displayChildChores() {
			// Clear existing data from the chore table
			DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
			tableModel.setRowCount(0); // Remove all rows from the table
			
			// Fetch and display chores associated with the parent account
			List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
			for (Chore chore : childChores) {
				Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
						chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
						chore.isPaid() ? "Yes" : "No"};
				tableModel.addRow(rowData);
			}
		}
	    
	    private void markAsCompleted() {
	    	int selectedRow = choreTable.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a chore to mark as completed");
			}
			
			int choreId = (int) choreTable.getValueAt(selectedRow, 0);
			
			if(choreTable.getValueAt(selectedRow, 5).equals("No")) {
				DatabaseOperations.markChoreAsCompleted(choreId, childAccount.getUsername());
				JOptionPane.showMessageDialog(this, "Chore recorded as completed successfully!");
			}
			else {
				JOptionPane.showMessageDialog(this, "Chore already completed!");
			}
	    }

	    private void getHoursWorked() {
		    double hoursWorked = Math.round(DatabaseOperations.getHoursWorkedByChild(childAccount.getUsername()) * 100.0) / 100.0;
		    JOptionPane.showMessageDialog(this, "You worked " + hoursWorked + " hours!");
	    }

	    public static void main(String[] args) {
	        
	        ChildAccount childAccount = new ChildAccount("childUsername", "childPassword");
	        new ChildAccountGUI(childAccount);
	    }

}
