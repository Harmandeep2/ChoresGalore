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
	    private JButton checkBalanceButton, exportChoresButton;
	    private JButton logoutButton;
	    private JButton sortByNameButton, filterByCategoryButton, sortByTimeButton, sortByPaymentButton, filterByIsCompletedButton, filterByIsPaidButton;
	    private JButton filterByIsNotCompletedButton, filterByIsNotPaidButton;
	    private JButton defaultSortButton;
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
	        setSize(1200, 800);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Center the frame on the screen

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        
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
			
			JPanel sortButtonPanel = new JPanel();
			sortButtonPanel.setBorder(BorderFactory.createTitledBorder("Sort and filter by"));
			sortButtonPanel.setLayout(new BoxLayout(sortButtonPanel, BoxLayout.X_AXIS));
			
			sortByNameButton = new JButton("Sort by Name");
			sortButtonPanel.add(sortByNameButton);
			
			filterByCategoryButton = new JButton("Sort by Category");
			sortButtonPanel.add(filterByCategoryButton);
			
			sortByTimeButton = new JButton("Sort by Time");
			sortButtonPanel.add(sortByTimeButton);
			
			sortByPaymentButton = new JButton("Sort by Payment");
			sortButtonPanel.add(sortByPaymentButton);
			
			filterByIsCompletedButton = new JButton("Filter by isCompleted");
			sortButtonPanel.add(filterByIsCompletedButton);
			
			filterByIsPaidButton = new JButton("Filter by isPaid");
			sortButtonPanel.add(filterByIsPaidButton);
			
			filterByIsNotCompletedButton = new JButton("Filter by isNotCompleted");
			sortButtonPanel.add(filterByIsNotCompletedButton);
			
			filterByIsNotPaidButton = new JButton("Filter by isNotPaid");
			sortButtonPanel.add(filterByIsNotPaidButton);
			
			defaultSortButton = new JButton("Default");
			sortButtonPanel.add(defaultSortButton);
			
			sortByNameButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sortByName();
				}
			});
			
			filterByCategoryButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterByCategory();
				}
			});
			
			sortByTimeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sortByTime();
				}
			});
			
			sortByPaymentButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sortByPayment();
				}
			});
			
			filterByIsCompletedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterByIsCompleted();
				}
			});
			
			filterByIsPaidButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterByIsPaid();
				}
			});
			
			filterByIsNotCompletedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterByIsNotCompleted();
				}
			});
			
			filterByIsNotPaidButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterByIsNotPaid();
				}
				
			});
			defaultSortButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					displayChildChores();
				}
			});
			mainPanel.add(sortButtonPanel);
			
			//Add some gap
			mainPanel.add(new JLabel(" "));
			
			JPanel buttonPanel = new JPanel();
	        
	        // Check Balance Button
	        checkBalanceButton = new JButton("Check Balance");
	        buttonPanel.add(checkBalanceButton);
	        
	        markAsCompletedButton = new JButton("Mark as Completed");
			buttonPanel.add(markAsCompletedButton);
			
			hoursWorkedButton = new JButton("Hours Worked");
			buttonPanel.add(hoursWorkedButton);
			
			exportChoresButton = new JButton("Export Chores as CSV");
			buttonPanel.add(exportChoresButton);
			
	        // Log Out Button
	        logoutButton = new JButton("Log Out");
	        buttonPanel.add(logoutButton);


	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance();
	            }
	        });
	       
			markAsCompletedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					markAsCompleted();
					displayChildChores();
				}
			});
			
			logoutButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	    	        JOptionPane.showMessageDialog(null, "Logged Out Successfully");
	            	MainPage mainPage = new MainPage();
					mainPage.setVisible(true);
	            	dispose(); // Close login window
	            }
	        });
			
			hoursWorkedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getHoursWorked();
				}
			});
			
			exportChoresButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exportChores();
				}
			});

	        mainPanel.add(buttonPanel);
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
	    
	    private void exportChores() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    ChoresUtils.createExcelSheet(childChores);
	    }

	    private void sortByName() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.sortAlphabetically(childChores);
		    displayListOnTable(sortedChores);
		    
	    }
	    
	    private void filterByCategory() {
		    String category = JOptionPane.showInputDialog(this, "Enter the category:");
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.filterCategory(category, childChores);
		    displayListOnTable(sortedChores);
	    }
	    
	    private void sortByTime() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.sortByTime(childChores);
		    displayListOnTable(sortedChores);
	    }
	    
	    private void sortByPayment() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.sortByPayment(childChores);
		    displayListOnTable(sortedChores);
		    
	    }
	    
	    private void filterByIsCompleted() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.filterCompleted(childChores);
		    displayListOnTable(sortedChores);
	    }
	    
	    private void filterByIsPaid() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.filterPaid(childChores);
		    displayListOnTable(sortedChores);
	    }
	    
	    private void filterByIsNotCompleted() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.filterNotCompleted(childChores);
		    displayListOnTable(sortedChores);
	    }
	    
	    private void filterByIsNotPaid() {
		    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		    List<Chore> sortedChores = ChoresUtils.filterNotPaid(childChores);
		    displayListOnTable(sortedChores);
	    }
	    private void displayListOnTable(List<Chore> chores) {
	    	DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
			tableModel.setRowCount(0);
			
			for (Chore chore : chores) {
				Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
						chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
						chore.isPaid() ? "Yes" : "No"};
				tableModel.addRow(rowData);
			}
			
			
	    }
	    public static void main(String[] args) {
	        
	        ChildAccount childAccount = new ChildAccount("childUsername", "childPassword");
	        new ChildAccountGUI(childAccount);
	    }

}
