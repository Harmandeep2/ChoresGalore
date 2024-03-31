package UserInterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import accountsModule.*;
import choresModule.*;
import databaseModule.*;


public class ChildAccountGUI extends JFrame{

	/**
	 * // Buttons for sorting and filtering chores
	 */
	private JButton sortByNameButton, filterByCategoryButton, sortByTimeButton, sortByPaymentButton;
	private JButton filterByIsCompletedButton, filterByIsPaidButton;
	private JButton filterByIsNotCompletedButton, filterByIsNotPaidButton;
	private JButton defaultSortButton;

	// Buttons for other functionalities
	private JButton checkBalanceButton, exportChoresButton, competitionStandingsButton, choreHistoryButton;
	private JButton logoutButton, choreDetailButton;
	private JButton markAsCompletedButton, markAsNotCompletedButton, hoursWorkedButton;
	private JLabel welcomeLabel, dateLabel, choreLabel, completionStatusLabel;
	private ChildAccount childAccount;
	private JTable choreTable;
	private int choreCount = 0;

	/**
	 * 
	 * @param childAccount
	 * Constructor is used to initialise child account GUI with specific childAccount.
	 */
	public ChildAccountGUI(ChildAccount childAccount) {
		this.childAccount = childAccount;
		initialize();
		displayChildChores();
		
	}

	private void initialize() {
		setTitle("Child Account");
		setSize(900, 850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame on the screen
		setResizable(true);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		/**
		 * Creating a welcome panel to display to user so they know which account is logged in
		 */
		JPanel welcomePanel = new JPanel();
		//will display "Welcome, [username]"
		welcomeLabel = new JLabel("Welcome, " + childAccount.getUsername()+ "!");
		//making the text bold and of the calibri font type, with size 18
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		//making the text blue
		welcomeLabel.setForeground(Color.BLUE);
		//aligning the text to the top middle of the GUI
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		//adding the label onto the panel
		welcomePanel.add(welcomeLabel);
		//adding welcome panel onto main panel
		mainPanel.add(welcomePanel);

		mainPanel.add(welcomePanel);
		
		
		

		// Creating a date panel on the GUI to show the parent what the date is
		JPanel datePanel = new JPanel();
		JPanel chorePanel = new JPanel();

		// Creating an object that will store the local date
		LocalDate dateObj = LocalDate.now();

		// Putting todays date in the label
		dateLabel = new JLabel("Today's Date: " + dateObj);
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		choreLabel = new JLabel("Chores Completed: " + choreCount);
		chorePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Adding it to the date panel and then the main panel.
		datePanel.add(dateLabel);
		chorePanel.add(choreLabel);
		mainPanel.add(datePanel);
		mainPanel.add(chorePanel);

		// Initialize table model and chore table format
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Chore ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");
		tableModel.addColumn("isCompleted");
		tableModel.addColumn("isPaid");
		tableModel.addColumn("Rating");
		choreTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(choreTable);
		//adding the chore table to the main panel
		mainPanel.add(scrollPane);

		// Panel for sorting and filtering buttons
		JPanel sortButtonPanel = new JPanel();
		JPanel sortButtonPanel2 = new JPanel();
		//sortButtonPanel.setBorder(BorderFactory.createTitledBorder("Sort and filter by"));
		sortButtonPanel.setLayout(new BoxLayout(sortButtonPanel, BoxLayout.X_AXIS));
		sortButtonPanel2.setLayout(new BoxLayout(sortButtonPanel2, BoxLayout.X_AXIS));
		sortButtonPanel2.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding to separate rows


		/**
		 * Adding sorting and filtering features by use of buttons
		 */

		//Button created to sort by Name
		sortByNameButton = new JButton("Sort by Name");
		sortButtonPanel.add(sortByNameButton);

		//Button created to sort by chore category
		filterByCategoryButton = new JButton("Filter by Category");
		sortButtonPanel.add(filterByCategoryButton);

		//Button created to sort by duration of chore
		sortByTimeButton = new JButton("Sort by Time");
		sortButtonPanel.add(sortByTimeButton);

		//Button created to sort by payment value
		sortByPaymentButton = new JButton("Sort by Payment");
		sortButtonPanel.add(sortByPaymentButton);

		//Button created to sort by chores marked complete
		filterByIsCompletedButton = new JButton("Filter by isCompleted");
		sortButtonPanel.add(filterByIsCompletedButton);

		//Button created to sort by payment already made
		filterByIsPaidButton = new JButton("Filter by isPaid");
		sortButtonPanel2.add(filterByIsPaidButton);

		//Button created to sort by incomplete chores
		filterByIsNotCompletedButton = new JButton("Filter by isNotCompleted");
		sortButtonPanel2.add(filterByIsNotCompletedButton);

		//Button created to sort by unpaid chores
		filterByIsNotPaidButton = new JButton("Filter by isNotPaid");
		sortButtonPanel2.add(filterByIsNotPaidButton);

		//Default sorting button
		defaultSortButton = new JButton("Default");
		sortButtonPanel2.add(defaultSortButton);




		/**
		 * Adding action listeners for sorting and filtering buttons
		 */

		//ActionListener enabled for sorting by name
		sortByNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortByName();
			}
		});

		//ActionListener enabled for sorting by chore category
		filterByCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByCategory();
			}
		});

		//ActionListener enabled for sorting by chore duration 
		sortByTimeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortByTime();
			}
		});

		//ActionListener enabled for sorting by payment value 
		sortByPaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortByPayment();
			}
		});

		//ActionListener enabled for sorting by completed chores
		filterByIsCompletedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByIsCompleted();
			}
		});

		//ActionListener enabled for sorting by paid chores
		filterByIsPaidButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByIsPaid();
			}
		});

		//ActionListener enabled for sorting by incompleted chores
		filterByIsNotCompletedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByIsNotCompleted();
			}
		});

		//ActionListener enabled for sorting by unpaid chores
		filterByIsNotPaidButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterByIsNotPaid();
			}

		});

		//ActionListener enabled for default sorting
		defaultSortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayChildChores();
			}
		});

		JLabel sortFilterLabel = new JLabel("Sort and Filter");
		sortFilterLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(sortFilterLabel);
		//mainPanel.add(new JLabel("Sort and Filter"));
		mainPanel.add(sortButtonPanel);
		mainPanel.add(new JLabel(" "));
		mainPanel.add(sortButtonPanel2);

		sortButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), // Border around the panel itself
				sortButtonPanel.getBorder())); // Existing titled border for the first row panel
		sortButtonPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), // Border around the panel itself
				BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Add padding to the panel


		//Add some gap
		mainPanel.add(new JLabel(" "));

		JPanel buttonPanel = new JPanel();

		// Check Balance Button
		checkBalanceButton = new JButton("Check Balance");
		buttonPanel.add(checkBalanceButton);

		choreDetailButton = new JButton("Chore Full Details");
		buttonPanel.add(choreDetailButton);

		//Mark as completed chore button
		markAsCompletedButton = new JButton("Mark as Completed");
		buttonPanel.add(markAsCompletedButton);

		markAsNotCompletedButton = new JButton("Mark as Not Completed");
		buttonPanel.add(markAsNotCompletedButton);

		//hours worked chore button
		hoursWorkedButton = new JButton("Hours Worked");
		buttonPanel.add(hoursWorkedButton);

		//Chore exporting as csv file button
		exportChoresButton = new JButton("Export Chores as CSV");
		buttonPanel.add(exportChoresButton);

		//Competition Standings button
		competitionStandingsButton = new JButton("Competition Standings");
		buttonPanel.add(competitionStandingsButton);

		//Chore History button
		choreHistoryButton = new JButton("History");
		buttonPanel.add(choreHistoryButton);

		// Log Out Button
		logoutButton = new JButton("Log Out");
		buttonPanel.add(logoutButton);

		//Action Listener to check balance through checkBalance button
		checkBalanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBalance();
			}
		});

		choreDetailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the choreDetail() method when the button is clicked
				choreDetails();
			}
		});

		//Action Listener to mark completed chore through markAsCompleted button
		markAsCompletedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				markAsCompleted();
				choreLabel.setText("Chores Completed: " + choreCount);
				chorePanel.add(choreLabel);
				displayChildChores();
			}
		});

		//Action Listener to mark not completed (accidently marked as completed) chore through markAsNotCompleted button
		markAsNotCompletedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				markAsNotCompleted();
				displayChildChores();
			}
		});

		//Action Listener to logout of an account through logout button
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Logged Out Successfully");
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
				dispose(); // Close login window
			}
		});

		//Action Listener to show hours worked by child through hoursWorked button
		hoursWorkedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHoursWorked();
			}
		});

		//Action Listener to show list of chores complete/incomplete by child. 
		choreHistoryButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				showChoreHistory();
			}
		});

		//Action Listener to export chore list and info to a csv file through exportChores button
		exportChoresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportChores();
			}
		});

		//Action Listener to view the competition standings through the competitionStandings button
		competitionStandingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				showCompetitionStandings();
			}
		});

		mainPanel.add(buttonPanel);
		getContentPane().add(mainPanel);
		setVisible(true);
		
		 // Create a panel for the completion status
	    JPanel statusPanel = new JPanel();
	    statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

	    // Create a JLabel for displaying the completion status
	    JLabel completionStatusLabel = new JLabel();
	    completionStatusLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
	    statusPanel.add(completionStatusLabel);

	    // Add the status panel to the main panel
	    getContentPane().add(statusPanel, BorderLayout.NORTH);


	    // Update the completion status label based on the number of pending chores
	    updateCompletionStatusLabel(completionStatusLabel);

	}

	/**
	 * Methods for button functionalities
	 */

	// Method to check balance
	private void checkBalance() {
		double balance = DatabaseOperations.getChildBalance(childAccount.getUsername());
		JOptionPane.showMessageDialog(this, "Your Balance: $" + balance);
	}


	// Method to display child chores
	private void displayChildChores() {
		// Clear existing data from the chore table
		DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
		tableModel.setRowCount(0); // Remove all rows from the table
		
		// Fetch and display chores associated with the parent account
		List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
		for (Chore chore : childChores) {
			String ratingStatus = chore.getRating() == -1 ? "Not rated yet" : String.valueOf(chore.getRating());
			Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
					chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
							chore.isPaid() ? "Yes" : "No", ratingStatus};
			tableModel.addRow(rowData);
		}
		
		
	}

	private void choreDetails() {
		int selectedRow = choreTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore to see full details!");
			return;
		}

		int choreId = (int) choreTable.getValueAt(selectedRow, 0);
		new SingleChoreDetails(choreId, this);
	}

	 // Method to display chore history of child
    private void showChoreHistory() {
    	
    	// Create a new JFrame for the popup window
        JFrame frame = new JFrame("Chore History");
        // Set the default close operation to dispose the window when closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Get the table model associated with the chore table
	    DefaultTableModel historyTableModel = new DefaultTableModel();
	    // adding copy column for Chore ID
	    historyTableModel.addColumn("Chore ID");
	    // adding copy column for name
	    historyTableModel.addColumn("Name");
	    // adding copy column for Category
	    historyTableModel.addColumn("Category");
	    // adding copy column for Time
	    historyTableModel.addColumn("Time");
	    // adding copy column for Payment
	    historyTableModel.addColumn("Payment");
	    // adding copy column for whether chore completed or not
	    historyTableModel.addColumn("isCompleted");
	    // adding copy column for whether chore is paid or not 
	    historyTableModel.addColumn("isPaid");
	    // adding copy column for chore rating 
	    historyTableModel.addColumn("Rating");
	    
	    JTable historyTable = new JTable(historyTableModel);

	    
	    // Fetch all chores associated with the child account from the database
	    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	    // Get the current date
	    Date currentDate = new Date(System.currentTimeMillis());
	    
	    // Iterate through each chore fetched for the child
	    for (Chore chore : childChores) {
	    	// Determine the rating status for the chore
	    	String ratingStatus = chore.getRating() == -1 ? "Not rated yet" : String.valueOf(chore.getRating());
	    	// Check if the chore is marked as completed
	    	boolean completed = chore.isCompleted();
	    	// Get the deadline for the chore from the database
            Date deadline = DatabaseOperations.getChoreDeadline(chore.getId());
            // Check if the chore's deadline is after the current date
            boolean withinDeadline = deadline != null && deadline.after(currentDate); 
        
            
            // Adding chore details to the table model with appropriate color
            Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
                    chore.getPayment(), completed ? "Yes" : "No",
                    chore.isPaid() ? "Yes" : "No", ratingStatus};
            historyTableModel.addRow(rowData);
            
            /*
             * if deadline has passed by after current date and chore
             * is not marked complete, then mark as red
             */
			
            /* 
             * if deadline is in the future and chore is marked 
             * complete, then highlight in green. 
             * 
             */
         // Set cell renderer based on completion status and deadline
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            if (completed && withinDeadline) {
                cellRenderer.setForeground(Color.GREEN); // Completed within deadline
            } else {
                cellRenderer.setForeground(Color.RED); // Not completed by deadline
            }
            

            for (int i = 0; i < historyTableModel.getColumnCount(); i++) {
                historyTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            }
            
        
	    }
	    // Add the table to a JScrollPane and add it to the frame
	    JScrollPane scrollPane = new JScrollPane(historyTable);
	    frame.add(scrollPane);
	

	    // Create a return button
	    JButton returnButton = new JButton("Return");
	    returnButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Close the chore history popup window when the return button is clicked
	            frame.dispose();
	        }
	    });
	    
	    // Add the return button to the frame
	    frame.add(returnButton, BorderLayout.SOUTH);

	    // Set the size of the frame
	    frame.setSize(700, 400);
	    // Make the frame visible
	    frame.setVisible(true);
}




// Method to allow child to mark chore complete
private void markAsCompleted() {
	// Initialize the chore count variable
	choreCount = 0;
	// Get the index of the selected row in the chore table
	int selectedRow = choreTable.getSelectedRow();
	// Check if a chore is selected
	if (selectedRow == -1) {
		// Display error message if no chore is selected
		JOptionPane.showMessageDialog(this, "Please select a chore to mark as completed");
		return;
	}

	// Get the chore ID from the selected row
	int choreId = (int) choreTable.getValueAt(selectedRow, 0);

	// Check if the selected chore is not already marked as completed
	if(choreTable.getValueAt(selectedRow, 5).equals("No")) {
		// Mark the chore as completed in the database
		DatabaseOperations.markChoreAsCompleted(choreId, childAccount.getUsername(), true);
		// Show success message
		JOptionPane.showMessageDialog(this, "Chore recorded as completed successfully!");
		// Increment the chore count
		choreCount += 1;
		// Update the chore count label
		choreLabel.setText("Chores Completed: " + choreCount);
		
		
	}
	else {
		// Display message if the chore is already completed
		JOptionPane.showMessageDialog(this, "Chore already completed!");
		
	}
}

private void updateCompletionStatusLabel(JLabel completionStatusLabel) {
    // Fetch the list of child chores
    List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());

    // Check if any chore is incomplete
    boolean anyChoreIncomplete = false;
    for (Chore chore : childChores) {
        if (!chore.isCompleted()) {
            anyChoreIncomplete = true;
            break;
        }
    }

    // Update the completion status label text and color based on whether any chore is incomplete
    if (anyChoreIncomplete) {
        completionStatusLabel.setText("Pending chores to do");
        completionStatusLabel.setForeground(Color.RED);
    } else {
        completionStatusLabel.setText("All chores completed");
        completionStatusLabel.setForeground(Color.GREEN);
    }
}

// Method to allow child to mark chore as not completed
private void markAsNotCompleted() {
	// Get the index of the selected row in the chore table
	int selectedRow = choreTable.getSelectedRow();
	// Check if a chore is selected
	if (selectedRow == -1) {
		// Display error message if no chore is selected
		JOptionPane.showMessageDialog(this, "Please select a chore to mark as not completed if accidentally marked as completed");
		return;
	}

	// Get the chore ID from the selected row
	int choreId = (int) choreTable.getValueAt(selectedRow, 0);

	// Check if the selected chore is marked as completed
	if(choreTable.getValueAt(selectedRow, 5).equals("Yes")) {
		// Get the child who completed the chore
		String choreCompletingChildUsername = DatabaseOperations.getChoreCompletingChildUsername(choreId);
		// Check if the chore was completed by the current child account
		if(choreCompletingChildUsername.equals(childAccount.getUsername())) {
			// Mark the chore as not completed in the database
			DatabaseOperations.markChoreAsNotCompleted(choreId, childAccount.getUsername(), false);
			// Show success message
			JOptionPane.showMessageDialog(this, "Chore updated as not completed successfully!");
			// Decrement the chore count
			choreCount -= 1;
			// Update the chore count label
			choreLabel.setText("Chores Completed: " + choreCount);
		}
		else {
			// Display message if the chore was not completed by the current child account
			JOptionPane.showMessageDialog(this, "Chore not yet marked as completed by you, it was marked by " + choreCompletingChildUsername + "!");
		}

	}
	else {
		// Display message if the chore is not marked as completed
		JOptionPane.showMessageDialog(this, "Chore not yet marked as completed!");
	}
}

//Method to display hours worked
private void getHoursWorked() {
	double hoursWorked = Math.round(DatabaseOperations.getHoursWorkedByChild(childAccount.getUsername()) * 100.0) / 100.0;
	JOptionPane.showMessageDialog(this, "You worked " + hoursWorked + " hours!");
}

// Method to export chores to a csv file
private void exportChores() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	ChoresUtils.createExcelSheet(childChores);
}

// Method to sort chores by name
private void sortByName() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.sortAlphabetically(childChores);
	displayListOnTable(sortedChores);

}

// Method to filter chores by category
private void filterByCategory() {
	String category = JOptionPane.showInputDialog(this, "Enter the category:");
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.filterCategory(category, childChores);
	displayListOnTable(sortedChores);
}

// Method to filter chores by duration
private void sortByTime() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.sortByTime(childChores);
	displayListOnTable(sortedChores);
}

// Method to filter chores by payment 
private void sortByPayment() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.sortByPayment(childChores);
	displayListOnTable(sortedChores);

}

// Method to filter already completed chores
private void filterByIsCompleted() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.filterCompleted(childChores);
	displayListOnTable(sortedChores);
}

// Method to filter chores been paid
private void filterByIsPaid() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.filterPaid(childChores);
	displayListOnTable(sortedChores);
}

// Method to filter by incompleted chores
private void filterByIsNotCompleted() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.filterNotCompleted(childChores);
	displayListOnTable(sortedChores);
}

// Method to filter unpaid chores 
private void filterByIsNotPaid() {
	List<Chore> childChores = DatabaseOperations.getAllChoresofChild(childAccount.getUsername());
	List<Chore> sortedChores = ChoresUtils.filterNotPaid(childChores);
	displayListOnTable(sortedChores);
}

// Method to display list of chores on the table
private void displayListOnTable(List<Chore> chores) {
	DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
	tableModel.setRowCount(0);


	for (Chore chore : chores) {

		String ratingStatus = chore.getRating() == -1 ? "Not rated yet" : String.valueOf(chore.getRating());
		Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
				chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
						chore.isPaid() ? "Yes" : "No", ratingStatus};
		tableModel.addRow(rowData);
	}
}



//Method to show competition standings
private void showCompetitionStandings() {
	/**
	 *  Instantiate CompetitionStandings window/dialog 
	 *  passing child account and reference to this GUI
	 */
	new CompetitionStandings(this.childAccount, this);
}

// Main method
public static void main(String[] args) {

	ChildAccount childAccount = new ChildAccount("childUsername", "childPassword");
	new ChildAccountGUI(childAccount);
}

}
