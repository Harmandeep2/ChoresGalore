package UserInterface;



import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;  // To import the local date
import java.time.ZoneId;
import java.awt.FlowLayout;  // To center/left/right align the text as need be

import javax.swing.*;
import javax.swing.table.*;

import accountsModule.*;
import choresModule.*;
import databaseModule.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;

//Class for Parent Account GUI
public class ParentAccountGUI extends JFrame{

	// Instance variables used
	private ParentAccount parentAccount;
	private JLabel welcomeLabel, dateLabel;
	private JComboBox<ChildAccount> childDropdown;
	private JComboBox<String> priorityDropdown, choresDropdown;
	private JTextField choreNameField, choreCategoryField, choreTimeField, chorePaymentField, choreDescriptionField;
	private JButton createChoreButton, assignChoreButton, payChoreButton, deleteChoreButton, checkBalanceButton, addChildButton;
	private JButton logoutButton, competitionStandingsButton, addCompetitionButton, removeChildButton, choreDetailButton;
	private JTable choreTable;
	private JDateChooser deadlineChooser;
	private Map<String, List<Integer>> assignedChoresMap = new HashMap<>();


	 // Constructor created for ParentAccountGUI
	public ParentAccountGUI(ParentAccount parentAccount) {
		this.parentAccount = parentAccount;
		initialize();
		// Refresh the chore table with updated chore data
		displayParentChores(); 

	}

	/**
	 * Method to initialize the GUI components
	 */
	private void initialize() {
		setTitle("Parent Account");
		setSize(1500, 700);
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
		welcomeLabel = new JLabel("Welcome, " + parentAccount.getUsername() + "!");
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
		
		// Creating a date panel on the GUI to show the parent what the date is
		JPanel datePanel = new JPanel();
	
		// Creating an object that will store the local date
		LocalDate dateObj = LocalDate.now();
		
		// Putting todays date in the label
		dateLabel = new JLabel("Today's Date: " + dateObj);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// Adding it to the date panel and then the main panel.
		datePanel.add(dateLabel);		
		mainPanel.add(datePanel);
		
				
		// Child Dropdown
		List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
		childDropdown = new JComboBox<>(children.toArray(new ChildAccount[0]));
		mainPanel.add(childDropdown);
		//Adding vertical spaces
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		
		// Top Buttons Panel
		JPanel topButtonsPanel = new JPanel();

		topButtonsPanel.setLayout(new BoxLayout(topButtonsPanel, BoxLayout.X_AXIS));

		
		// Add Child Button
		addChildButton = new JButton("Add Child"); // Added button
		topButtonsPanel.add(addChildButton); // Added button
		
		// Remove Child Button
		removeChildButton = new JButton("Remove Child");
		topButtonsPanel.add(removeChildButton);

		//Assign Chore Button
		assignChoreButton = new JButton("Assign Chore");
		topButtonsPanel.add(assignChoreButton);
		
		//Pay Chore Button
		payChoreButton = new JButton("Pay Chore");
		topButtonsPanel.add(payChoreButton);
		
		choreDetailButton = new JButton("Chore Full Details");
		topButtonsPanel.add(choreDetailButton);
		
		deleteChoreButton = new JButton("Delete Chore");
		topButtonsPanel.add(deleteChoreButton);
		
		/**
		 *  Adding action listeners to buttons
		 */
		
		// Add action listener to add Child Button 
		addChildButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the addChild() method when the button is clicked
				addChild();
			}
		});
		
		// Add action listener to remove Child Button 
		removeChildButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the removeChild() method when the button is clicked
				removeChild();
			}
		});
		
		assignChoreButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Call the assignChore() method when the button is clicked
		        assignChore();
		    }
		});
		
		// Add action listener for the pay chore button
		payChoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the payChore() method when the button is clicked
				payChore();
				// Refresh the displayed chores after paying a chore
				displayParentChores();
			}
		});
		
		choreDetailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the choreDetail() method when the button is clicked
				choreDetails();
			}
		});
		
		deleteChoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Call the choreDetail() method when the button is clicked
				deleteChore();
			}
		});
		
		
		
		// Add the panel containing the top buttons 
		mainPanel.add(topButtonsPanel);	
		// Add vertical spacing
		mainPanel.add(new JLabel(" "));
		// Add vertical spacing
		mainPanel.add(new JLabel(" "));

		/**
		 *  Create a table model
		 */
		
		// Creating a table model for the chore table
		DefaultTableModel tableModel = new DefaultTableModel();
		// Adding columns to the table model with given titles
		tableModel.addColumn("Chore ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");
		tableModel.addColumn("isCompleted");
		tableModel.addColumn("isPaid");
		tableModel.addColumn("Rating");



		// Creating the chore table using the table model
		choreTable = new JTable(tableModel);
		// Creating a scroll pane to hold the chore table
		JScrollPane scrollPane = new JScrollPane(choreTable);
		// Adding the scroll pane to the main panel
		mainPanel.add(scrollPane);
		


		// Chore Creation Panel
		// Create a panel for chore creation with titled border
		JPanel chorePanel = new JPanel();
		JPanel chorePanel2 = new JPanel();
		//Create borders
		chorePanel.setLayout(new BoxLayout(chorePanel, BoxLayout.X_AXIS));
		chorePanel.setBorder(BorderFactory.createTitledBorder("Mandatory Chore Details"));
		chorePanel2.setLayout(new BoxLayout(chorePanel2, BoxLayout.X_AXIS));
		chorePanel2.setBorder(BorderFactory.createTitledBorder("Additional (Optional) Chore Details"));

		// Create text fields and buttons for chore attributes and creation
		choreNameField = new JTextField(5);
		choreCategoryField = new JTextField(5);
		choreTimeField = new JTextField(5);
		choreTimeField.setPreferredSize(new Dimension(20, 19));
		chorePaymentField = new JTextField(5);
		choreDescriptionField = new JTextField(5);
		createChoreButton = new JButton("Create Chore");
		
		String[] chorepresets = {"Other","Make Bed","Clean Room", "Dishes", "Mow Lawn", "Shovel"};
        choresDropdown = new JComboBox<>(chorepresets);
        choresDropdown.setSelectedIndex(0); // Default selection
        choresDropdown.setMinimumSize(new Dimension(50, 19)); // Match size
        chorePanel.add(new JLabel("Select Chore Preset "));
        chorePanel.add(choresDropdown);
		

		// Add labels and text fields to the chore panel
		chorePanel.add(new JLabel("Name: "));
		chorePanel.add(choreNameField);
		chorePanel.add(new JLabel("Category: "));
		chorePanel.add(choreCategoryField);
		chorePanel.add(new JLabel("Time: "));
		chorePanel.add(choreTimeField);
		chorePanel.add(new JLabel("Payment: $"));
		chorePanel.add(chorePaymentField);
		chorePanel.add(createChoreButton);
		
		
		chorePanel2.add(new JLabel("Chore Description: "));
		chorePanel2.add(choreDescriptionField);	
		chorePanel2.add(new JLabel("Set Deadline (MM/DD/YYYY): "));
		
		deadlineChooser = new JDateChooser();
		deadlineChooser.setAutoscrolls(true);
		deadlineChooser.setRequestFocusEnabled(false);
		deadlineChooser.setPreferredSize(new Dimension(30, 19));
		deadlineChooser.setVerifyInputWhenFocusTarget(false);
		deadlineChooser.setDateFormatString("MM/dd/yyyy");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) deadlineChooser.getDateEditor();
		editor.setEditable(false);
		//BorderLayout borderLayout = (BorderLayout) deadlineChooser.getLayout();
		chorePanel2.add(deadlineChooser);
		
		
		// Replace JTextField with JComboBox for priority field to be selected as a dropdown
        String[] priorityOptions = {"Select","High", "Mid", "Low"};
        priorityDropdown = new JComboBox<>(priorityOptions);
        priorityDropdown.setSelectedIndex(0); // Default selection
        priorityDropdown.setMinimumSize(new Dimension(50, 19)); // Match size
        chorePanel2.add(new JLabel("Priority (High, Mid, Low): "));
        chorePanel2.add(priorityDropdown);

		// Add the chore panel to the main panel
		JLabel choreCreationLabel = new JLabel("Chore Creation");
		choreCreationLabel.setForeground(new Color(0, 0, 160));
		choreCreationLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		choreCreationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(choreCreationLabel);
		
		
		mainPanel.add(chorePanel);
		mainPanel.add(new JLabel(" "));
		mainPanel.add(chorePanel2);
		
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));


		chorePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
		chorePanel.getBorder())); 

		 // Add vertical spacing
		mainPanel.add(new JLabel(" "));
		 // Add vertical spacing



		/*
		 *  Bottom Buttons Panel
		 */
		
        // Creating a panel for bottom buttons
		JPanel bottomButtonsPanel = new JPanel();
		//bottomButtonsPanel.setLayout(new BoxLayout(bottomButtonsPanel, BoxLayout.X_AXIS));
		
		/*
		 *  Checking Balance Button
		 */
		
        // Creating and add a button to check the balance of a child account
		checkBalanceButton = new JButton("Check Balance");
		bottomButtonsPanel.add(checkBalanceButton);

		//Adding logout button
		logoutButton = new JButton("Log Out");
		bottomButtonsPanel.add(logoutButton);
		
        // Creating and add a button to create a competition
		addCompetitionButton = new JButton("Create Competition");
		bottomButtonsPanel.add(addCompetitionButton);
		
		// Creating and add a button to view competition standings
		competitionStandingsButton = new JButton("Competition Standings");
		bottomButtonsPanel.add(competitionStandingsButton);

		// Adding action listener to create chore button
		createChoreButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createChore();
			}
		});

		// Add action listener to check balance button
		checkBalanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkBalance();
			}
		});
		
		// Adding action listener to create competition button
		addCompetitionButton.addActionListener(new ActionListener() { // Add action listener for add child button
			@Override
			public void actionPerformed(ActionEvent e) {
				createCompetition();
				setVisible(false);
			}
		});
		
		// Adding action listener to competition standings button
		competitionStandingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				showCompetitionStandings();
			}
		});
		
		// Adding action listener to competition standings button
		choresDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chorepresets();
			}
		});
		
	
		// Add action listener to logout button
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Logged Out Successfully");
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
				dispose(); // Closing the parent account window
			}
		});
		
		// Adding the bottom buttons panel to the main panel
        mainPanel.add(bottomButtonsPanel);

        // Adding the main panel to the content pane
        getContentPane().add(mainPanel);
        // Setting the frame to visible
        setVisible(true);
	}
	///////////////

	// Method created to add a child to the parent account
	private void addChild() {
		// Prompting the user to enter child's username
	    String childUserName = JOptionPane.showInputDialog("Enter child's username:");
	    // Validation of the entered username
	    if (childUserName != null && !childUserName.isEmpty()) {
	    	// Checking if the child already exists in the database
	        if (DatabaseOperations.checkIfChildExists(childUserName)) {
	        	// Add the parent-child relationship to the database
	            boolean addedSuccessfully = DatabaseOperations.addParentToChild(parentAccount.getUsername(), childUserName);

	            // If added successfully, update the child dropdown and show success message
	            if (addedSuccessfully) {
	                List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
	                childDropdown.setModel(new DefaultComboBoxModel<>(children.toArray(new ChildAccount[0])));
	                JOptionPane.showMessageDialog(this, "Child added successfully!");
	            } else {
	            	 // Showing error message if failed to add child
	                JOptionPane.showMessageDialog(this, "Failed to add child. The child may already have a parent.");
	            }
	        } else {
	        	// Showing error message if child does not exist
	            JOptionPane.showMessageDialog(this, "Child does not exist!");
	        }
	    } else {
	    	 // Showing error message if no username entered
	        JOptionPane.showMessageDialog(this, "Please enter a valid child username!");
	    }
	}

	/**
	 *  Method created to remove a child from the parent account
	 */
	private void removeChild() {
		// Getting the selected child from the dropdown
	    ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	    
	    /**
	     * Bug was present here as when no child is selected an error should pop up
	     * however nothing was showing up. This is the BUG FIX
	     */
	   
	    if (selectedChild == null) {
	        JOptionPane.showMessageDialog(this, "Please choose a child from the drop-down list you want to remove.", "Error", JOptionPane.ERROR_MESSAGE);
	        return; // Exit the method
	    }
	    
	    
	    // Prompt for confirmation to remove the child
	    int option = JOptionPane.showConfirmDialog(this,
	            "<html>Are you sure you want to remove <font color='blue'><b>" + selectedChild.getUsername() + "</b></font>?</html>", "Confirmation", JOptionPane.YES_NO_OPTION);
	    // If confirmed, proceed with removal
	    if (option == JOptionPane.YES_OPTION) {
	    	// Attempt to remove the child from the parent account in the database
	        boolean removed = DatabaseOperations.removeParentFromChild(this.parentAccount.getUsername(), selectedChild.getUsername());

	        // If removal successful, update dropdown and show success message
	        if (removed) {
	            // Child successfully removed from the parent account
	            childDropdown.removeItem(selectedChild);
	            JOptionPane.showMessageDialog(this, "Child removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Show error message if Removal failed
	            JOptionPane.showMessageDialog(this, "Failed to remove the child.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}


	/** 
	 *  Method to create a chore
	 */
	private void createChore() {
		
		    if (choreNameField.getText().isEmpty() || choreCategoryField.getText().isEmpty() ||
		            choreTimeField.getText().isEmpty() || chorePaymentField.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Please fill all the mandatory details!");
		        return;
		    }

		    double choreTime;
		    double chorePayment;
		    try {
		        choreTime = Double.parseDouble(choreTimeField.getText());
		        chorePayment = Double.parseDouble(chorePaymentField.getText());
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(this, "Please enter valid numbers for time and/or payment!", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Retrieval of chore attributes from text fields
		    String choreName = choreNameField.getText();
		    String choreCategory = choreCategoryField.getText();

		    // Additional details
		    String choreDescription = choreDescriptionField.getText();
		    choreDescription = choreDescription.isEmpty() ? null : choreDescription;
		    String chorePriority = (String) priorityDropdown.getSelectedItem();
		    chorePriority = chorePriority.equals("Select") ? null : chorePriority;

		    // Stores the current date
		    java.util.Date currentDate = new java.util.Date(); // Current date

		    // Validate deadline
		    java.util.Date selectedDate = deadlineChooser.getDate();

		    // Convert java.util.Date to LocalDate
		    LocalDate selectedLocalDate = null;
		    if (selectedDate != null) {
		        selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        // Adding a day to the local date so that we can account for today's date
		        selectedLocalDate = selectedLocalDate.plusDays(1);
		    }

		    // Convert LocalDate back to java.util.Date
		    java.util.Date nextDate = null;
		    if (selectedLocalDate != null) {
		        nextDate = java.util.Date.from(selectedLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    }

		    // If statement for if the date is either null or in the past.
		    if (nextDate != null && nextDate.before(currentDate)) {
		        JOptionPane.showMessageDialog(this, "Please select a valid deadline! Deadline cannot be in the past.", "Error", JOptionPane.ERROR_MESSAGE);
		        // Returns to the program
		        return;
		    }

		    // Stores the validated date and validated deadline
		    java.sql.Date deadline = selectedDate != null ? new java.sql.Date(selectedDate.getTime()) : null;

		    // Insert new chore into the database using DatabaseOperations class
		    Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
		    // Insert the chore into the database using DatabaseOperations class
		    DatabaseOperations.insertChore(newChore, parentAccount.getUsername());
		    // Update the table model with the new chore data
		    displayParentChores();

		    int choreId = (int) choreTable.getValueAt(choreTable.getRowCount() - 1, 0);
		    DatabaseOperations.insertChoreDetails(choreDescription, deadline, chorePriority, choreId);

		    // Display success message
		    JOptionPane.showMessageDialog(this, "Chore created successfully!");
		    choreNameField.setText("");
		    choreCategoryField.setText("");
		    choreTimeField.setText("");
		    chorePaymentField.setText("");
		    choreDescriptionField.setText("");
		    priorityDropdown.setSelectedIndex(0);
		    deadlineChooser.setDate(null);
		}

	

	/**
	 *  Method created to pay the children 
	 *  for their chore completion
	 */
	
	private void payChore() {
		
		// Getting the selected child from the dropdown
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		// Getting the selected row from the chore table
		int selectedRow = choreTable.getSelectedRow();
		// Checking if a chore is selected
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore to pay to selected child");
			return;
		}
		
		// Getting the chore ID from the selected row
		int choreId = (int) choreTable.getValueAt(selectedRow, 0);
		
		// Getting the child who completed the chore
		String childWhoCompletedChore = DatabaseOperations.getChoreCompletingChildUsername(choreId);
		
		// Checking if the chore is completed and not paid
		if(choreTable.getValueAt(selectedRow, 5).equals("Yes") && choreTable.getValueAt(selectedRow, 6).equals("No")) {
			// If the selected child completed the chore, mark it as paid
			if(selectedChild.getUsername().equals(childWhoCompletedChore)) {
				
				String ratingInput = JOptionPane.showInputDialog(this, "Please rate the chore completion (1-5):");
	            // Validating the rating input
	            if (ratingInput != null && !ratingInput.isEmpty()) {
	                try {
	                    int rating = Integer.parseInt(ratingInput);
	                    if (rating >= 1 && rating <= 5) {
	                        // Store the rating in the database or perform actions based on the rating
	                        DatabaseOperations.storeChoreRating(choreId, rating);
	                        // Mark the chore as paid
	                        DatabaseOperations.markChoreAsPaid(choreId, selectedChild.getUsername());
	                        JOptionPane.showMessageDialog(this, "Chore paid to " + selectedChild.getUsername());
	                    } else {
	                        JOptionPane.showMessageDialog(this, "Please enter a rating between 1 and 5.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(this, "Invalid rating format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Please enter a rating.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
			}
			else {
				// Shows error message if selected child hasn't complete the chore
				JOptionPane.showMessageDialog(this, "Chore is not completed by " + selectedChild.getUsername() +
						" instead completed by " + childWhoCompletedChore);
			}
		}
		else {
			// Shows error message if selected chore is not completed or already paid
			JOptionPane.showMessageDialog(this, "Either chore is not completed or chore is already paid!");
		}
	}


	// Define a method to handle chore assignment
	private void assignChore() {

    ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
    // Getting the selected row from the chore table
    int selectedRow = choreTable.getSelectedRow();
    // Checking if a chore is selected
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a chore to assign to the selected child");
        return;
    }

    // Get the chore ID from the selected row
    int choreId = (int) choreTable.getValueAt(selectedRow, 0);

    if (DatabaseOperations.checkIfChoreAlreadyAssignedToChild(choreId, selectedChild.getUsername())) {
        JOptionPane.showMessageDialog(this, "<html> Chore <font color='blue'><b><u>already</u></b></font> assigned to <html>" + selectedChild.getUsername());
        return;
    }

    // Check if the chore is not completed
    if (choreTable.getValueAt(selectedRow, 5).equals("No")) {
        // Assigning the chore to the selected child
        DatabaseOperations.insertChoreAssignment(choreId, selectedChild.getUsername());
        JOptionPane.showMessageDialog(this, "Chore assigned to " + selectedChild.getUsername());

        // Update the assignedChoresMap
        List<Integer> assignedChores = assignedChoresMap.getOrDefault(selectedChild.getUsername(), new ArrayList<>());
        assignedChores.add(choreId);
        assignedChoresMap.put(selectedChild.getUsername(), assignedChores);
        System.out.println(selectedChild.getUsername());
    } else {
        // Show error message if the chore is already completed
        JOptionPane.showMessageDialog(this, "Chore already completed!");
    }}
	    	// Initialize the chore count variable
	    	// Get the index of the selected row in the chore table

	
	
//	
//	// Getting the selected child from the dropdown
//	ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
//	// Getting the selected row from the chore table
//	int selectedRow = choreTable.getSelectedRow();
//	// Checking if a chore is selected
//	if (selectedRow == -1) {
//		JOptionPane.showMessageDialog(this, "Please select a chore to assign to selected child");
//		return;
//	}
//	
//	// Get the chore ID from the selected row
//	int choreId = (int) choreTable.getValueAt(selectedRow, 0);
//	
//	if(DatabaseOperations.checkIfChoreAlreadyAssignedToChild(choreId, selectedChild.getUsername())) {
//		JOptionPane.showMessageDialog(this, "<html> Chore <font color='blue'><b><u>already</u></b></font> assigned to <html>" + selectedChild.getUsername());
//		return;
//	}
//	
//	// Check if the chore is not completed
//	if(choreTable.getValueAt(selectedRow, 5).equals("No")) {
//		// Assigning the chore to the selected child
//		DatabaseOperations.insertChoreAssignment(choreId, selectedChild.getUsername());
//		JOptionPane.showMessageDialog(this, "Chore assigned to " + selectedChild.getUsername());
//	}
//	else {
//		// Show error message if the chore is already completed
//		JOptionPane.showMessageDialog(this, "Chore already completed!");
//	}
// Getting the selected child from the dropdown
	private void choreDetails() {
		int selectedRow = choreTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore to see full details!");
			return;
		}
		
		int choreId = (int) choreTable.getValueAt(selectedRow, 0);
		new SingleChoreDetails(choreId, this);
	}
	



	/**
	 *  Method created to display chores associated with the parent account
	 */
	private void displayParentChores() {
		// Clears existing data from the chore table
		DefaultTableModel tableModel = (DefaultTableModel) choreTable.getModel();
		tableModel.setRowCount(0); // Remove all rows from the table
		
		 // Fetching and displayal of chores associated with the parent account
		List<Chore> parentChores = DatabaseOperations.getAllChoresofParent(parentAccount.getUsername());
		for (Chore chore : parentChores) {
			// Determine the rating status
	        String ratingStatus = chore.getRating() == -1 ? "Not rated yet" : String.valueOf(chore.getRating());
	        
			
			// Adding each chore to the table model
			Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
					chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
					chore.isPaid() ? "Yes" : "No", ratingStatus};
			tableModel.addRow(rowData);
		}
	}
	
	/**
	 * Method to delete selected chore from Parent GUI, Child GUI, and database
	 */
	private void deleteChore() {
	    int selectedRow = choreTable.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Please select a chore to delete!");
	        return;
	    }

	    int choreId = (int) choreTable.getValueAt(selectedRow, 0);
	    // Call the method from DatabaseOperations to delete the chore from the database
	    boolean deleted = DatabaseOperations.deleteChore(choreId);
	    if (deleted) {
	        JOptionPane.showMessageDialog(this, "Chore deleted successfully!");
	        // After deleting, refresh the chore table
	        displayParentChores();
	    } else {
	        JOptionPane.showMessageDialog(this, "Failed to delete chore. Please try again.");
	    }
	}

	/**
	 *  Method created to check the balance of a child account
	 */
	private void checkBalance() {
		// Getting the selected child from the dropdown
		ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
		
		// Check if any child is selected
	    if (selectedChild == null) {
	        JOptionPane.showMessageDialog(this, "No child is selected. Please add a child first!", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
		// Retrieving and displaying the balance of the selected child
		double balance = DatabaseOperations.getChildBalance(selectedChild.getUsername());
		JOptionPane.showMessageDialog(this, selectedChild+ "'s Balance: $" + balance);
	}
	
	/**
	 *  Method used to create a competition
	 */
	private void createCompetition() {
		// Opening the competition creation GUI
		new CompetitionGUI(this.parentAccount, this);
	}

	/**
	 *  Method to show competition standings
	 */
	private void showCompetitionStandings() {
		// Open the competition standings GUI
		new CompetitionStandings(this.parentAccount, this);
	}
	
	private void chorepresets() {	        
	
	String selectedPreset = (String) choresDropdown.getSelectedItem();
    switch (selectedPreset) {
    case "Make Bed":
        choreNameField.setText("Make Bed");
        choreCategoryField.setText("Bedroom");
        choreTimeField.setText("5"); // Time in minutes 
        chorePaymentField.setText("5"); // Payment amount
        break;
    case "Clean Room":
        choreNameField.setText("Clean Room");
        choreCategoryField.setText("Bedroom");
        choreTimeField.setText("15"); // Time in minutes 
        chorePaymentField.setText("10"); // Payment amount
        break;
    case "Dishes":
        choreNameField.setText("Dishes");
        choreCategoryField.setText("Kitchen");
        choreTimeField.setText("15");
        chorePaymentField.setText("10");
        break;
    case "Mow Lawn":
        choreNameField.setText("Mow Lawn");
        choreCategoryField.setText("Lawn");
        choreTimeField.setText("30");
        chorePaymentField.setText("15");
        break;
    case "Shovel":
        choreNameField.setText("Shovel");
        choreCategoryField.setText("Driveway");
        choreTimeField.setText("30");
        chorePaymentField.setText("15");
        break;
    case "Other":
    	//Leave blank
        choreNameField.setText("");
        choreCategoryField.setText("");
        choreTimeField.setText("");
        chorePaymentField.setText("");
        break;
    default:
        // Handle default case
        break;
}
}
	
	/**
	 * Main method to test the class
	 * @param args
	 */
	public static void main(String[] args) {

		// Creating a new parent account
		ParentAccount parentAccount = new ParentAccount("parentUsername", "parentPassword");
		// Initializing and displaying the parent account GUI
		new ParentAccountGUI(parentAccount);

	}
	
	
	}




