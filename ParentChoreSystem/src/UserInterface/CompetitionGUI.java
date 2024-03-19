package UserInterface;

import java.util.List;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;
import databaseModule.DatabaseOperations;
import accountsModule.ChildAccount;


// Class for Competition GUI
public class CompetitionGUI extends JFrame{
	
	// Reference to the previous frame
	private JFrame prev; 
	// Parent account associated with the competition
    private ParentAccount parentAccount;
    // Table to display available chores
    private JTable jTable; 
    // List to display children
    private JList<ChildAccount> jList; 
    // Buttons for starting competition and returning
    private JButton startCompetitionButton, backButton; 
    // Field for entering competition name
    private JTextField competitionNameField; 
    
	
	/**
	 * Constructor for Competition GUI
	 * @param parent
	 * @param prev
	 */
	public CompetitionGUI(ParentAccount parent, JFrame prev) {
		this.parentAccount = parent;
		this.prev = prev;
		// Initialize the GUI components
		initialize(); // Initialize the GUI components
		// Display chores not completed by parent
		displayParentChoresNotCompleted();
	}
	
	 /** 
	  * Method to initialize the GUI components
	  */
	
	public void initialize() {
		//JLabel
		//JText
		//jtable of children to assign task to (doesnt have to be jtable)
		//jtable of chore to assign
		//add a start button
		//upon start all chosen children are assigned the chosen tasks
		//upon start parent is taken back to their gui
	
		// Setting title of the frame
		setTitle("Competition"); 
		// Setting size of the frame
		setSize(1000, 500); 
		// Centering of frame on the screen
		setLocationRelativeTo(null); 
		// Allowing for frame resizing
		setResizable(true); 
		
		// Create a main panel
		JPanel mainPanel = new JPanel();
		// Set layout to BoxLayout
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		 // Panel for competition name
		JPanel competitionNamePanel = new JPanel();
		competitionNamePanel.setLayout(new BoxLayout(competitionNamePanel, BoxLayout.X_AXIS));
		competitionNamePanel.setMaximumSize(new Dimension(300, 30));
		JLabel competitionLabel = new JLabel("Competition Name: ");
		competitionNameField = new JTextField(15);

		competitionNamePanel.add(competitionLabel);
		competitionNamePanel.add(competitionNameField);
		// Adding competition name panel to the main panel
		mainPanel.add(competitionNamePanel); 
		
		// Add space between components
		mainPanel.add(new JLabel(" "));
		// Add space between components
        mainPanel.add(new JLabel(" ")); 
		
        // Label created for children list
		JLabel label = new JLabel("List of your Children"); 
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		// Adding label to main panel
		mainPanel.add(label); 
		
		// Fetching children associated with the parent account
		List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
		DefaultListModel<ChildAccount> model = new DefaultListModel<>();
		for (ChildAccount child : children) {
			model.addElement(child);
		}
		
		// Creating JList for children
		jList = new JList<>(model);  
		jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(jList);
		// Adding JList to main panel
		mainPanel.add(scrollPane); 
		
		 // Add space between components
		 mainPanel.add(new JLabel(" "));
		 // Add space between components
	     mainPanel.add(new JLabel(" ")); 
		
	    // Label for chores table
		JLabel label2 = new JLabel("List of Chores available to be assigned");
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		// Add label to main panel
		mainPanel.add(label2);
		
		// Creating table model for chores table
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Chore ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");
		tableModel.addColumn("isCompleted");
		tableModel.addColumn("isPaid");

		// Create the chore table using the table model
		jTable = new JTable(tableModel);
		JScrollPane scrollPaneTable = new JScrollPane(jTable);
		// Adding JTable to main panel
		mainPanel.add(scrollPaneTable);
		
		// Add space between components
		mainPanel.add(new JLabel(" "));
		// Add space between components
		mainPanel.add(new JLabel(" "));
		
		// Button to start competition
		startCompetitionButton = new JButton("Start Competition");
		// Adding button to main panel
		mainPanel.add(startCompetitionButton);
		
		// Button to go back
		backButton = new JButton("Back");
		// Adding button to main panel
		mainPanel.add(backButton);
		
		// Action listener for start competition button
		startCompetitionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Starting competition method
				startCompetition();
			}
		});
		
		// Action listener for back button
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Hide the current frame
				setVisible(false);
				// Show the previous frame
				prev.setVisible(true);
			}
		});
		
		// Add main panel to content pane
		getContentPane().add(mainPanel);
		// Set frame visibility to true
		setVisible(true);
	}
	
	 // Method used to display chores not completed by the parent
	private void displayParentChoresNotCompleted() {
		// Clear existing data from the chore table
		DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		tableModel.setRowCount(0); // Remove all rows from the table
		
		// Fetch and display chores associated with the parent account
		List<Chore> parentChores = DatabaseOperations.getAllChoresofParent(parentAccount.getUsername());
		List<Chore> notCompletedChores = ChoresUtils.filterNotCompleted(parentChores);
		for (Chore chore : notCompletedChores) {
			Object[] rowData = {chore.getId(), chore.getName(), chore.getCategory(), chore.getTime(),
					chore.getPayment(), chore.isCompleted() ? "Yes" : "No",
					chore.isPaid() ? "Yes" : "No"};
			 // Adding chore data to table
			tableModel.addRow(rowData); 
		}
	}
	
	// Method to start the competition
	private void startCompetition() {
		// Get competition name
		String competitionName = competitionNameField.getText(); 
		// Get selected children
		List<ChildAccount> selectedChildren = jList.getSelectedValuesList();
		
		if(jTable.getSelectedRow() == -1) {
			// Show error if no chore selected
			JOptionPane.showMessageDialog(this, "Please select a chore");
			return;
		}
		// Get selected chore ID
		int choreId = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
		
		if(competitionName.isEmpty()) {
			// Show error if competition name empty
			JOptionPane.showMessageDialog(this, "Please enter a competition name");
			return;
		}
		
		if(selectedChildren.isEmpty() || selectedChildren.size() == 1) {
			// Show error if less than two children selected
			JOptionPane.showMessageDialog(this, "Please select at least two children");
			return;
		}
		
		if (!DatabaseOperations.checkCompetitionAlreadyExists(competitionName)) {
            DatabaseOperations.addCompetition(competitionName, parentAccount, selectedChildren, choreId);

            // Show a custom JOptionPane with "OK" button
            int option = showCustomOptionPane("Competition created successfully!");

            // Check user's response
            if (option == JOptionPane.OK_OPTION) {
                // Take appropriate action, e.g., close the current frame and show the previous one
                closeFrameAndShowPrevious();
            }
		}
		else {
			//showing error message display
			JOptionPane.showMessageDialog(this, "Competition already exists, please change the name!");
		}
	}
	// Method to show a custom JOptionPane
	private int showCustomOptionPane(String message) {
	    JPanel panel = new JPanel();
	    panel.add(new JLabel(message));

	    // Show a JOptionPane with "OK" button
	    return JOptionPane.showOptionDialog(
	            this,
	            panel,
	            "Information",
	            JOptionPane.OK_OPTION,
	            JOptionPane.INFORMATION_MESSAGE,
	            null,
	            new Object[]{"OK"},
	            "OK"
	    );
	}

	// Method used to close the current frame and show the previous frame
	private void closeFrameAndShowPrevious() {
	    // Close the current frame
	    Window window = SwingUtilities.getWindowAncestor(this);
	    if (window != null) {
	    	// Closing the current frame
	        window.dispose();
	    }

	    // Show the previous frame
	    if (prev != null) {
	    	// Used to make the previous frame visible to the user
	        prev.setVisible(true);
	    }
	}

	// Main method used to start the application
	public static void main(String[] args) {
		// Creating a parent account
		ParentAccount p = new ParentAccount ("name","pass");
		// Creating a parent GUI
		ParentAccountGUI parent = new ParentAccountGUI(p);
		// Creating a competition GUI
		new CompetitionGUI(p, parent);
	}
}
