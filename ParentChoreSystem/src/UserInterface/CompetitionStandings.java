package UserInterface;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import accountsModule.*;
import databaseModule.DatabaseOperations;

//Class for Competition Standings GUI

public class CompetitionStandings extends JFrame{
	
	private JButton back; // Button to return
    private ChildAccount child; // Child account associated with the standings
    private ParentAccount parent; // Parent account associated with the standings
    private JFrame prev; // Reference to the previous frame
    private JTable table; // Table to display competition standings
    private JLabel participantLabel; // Label to display selected competition participants
    private JList<String> jList; // List to display selected competition participants
	
    /**
	 * @param ChildAccount and JFrame
	 * @exception Headless Exception
	 * */
	public CompetitionStandings(ChildAccount account, JFrame prev) throws HeadlessException {
		this.prev = prev;
		this.child = account;
		initialize(); // Initializing the GUI components
        populateTableChild(); // Populating the table with child competition standings
        setupTableSelectionListenerChild(); // Setting up table selection listener for child
	}
	
	/**
	 * Constructor created for ParentAccount
	 * @param account
	 * @param prev
	 * @throws HeadlessException
	 */
	public CompetitionStandings(ParentAccount account, JFrame prev) throws HeadlessException {
		this.prev = prev;
		this.parent = account;
		 initialize(); // Initializing the GUI components
	     populateTableParent(); // Populating the table with parent competition standings
	     setupTableSelectionListenerParent(); // Setting up table selection listener for parent
	}

	// Method to initialize the GUI components
	public void initialize() {
		
		setTitle("Competition Standings"); // Setting the title of the frame
        setSize(600, 400); // Setting the size of the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Setting default close operation
        setLocationRelativeTo(null); // Centering the frame on screen
        setResizable(true); // Allowing frame resizing
		
        // Creating a main panel
		JPanel panel = new JPanel();
		// Setting layout to BoxLayout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Button to return
		back = new JButton("Return");
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev.setVisible(true); // Show the previous frame
                dispose(); // Dispose the current frame
			}	
		});
		
		/**
		 * Creating a table model for competition standings
		 */
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Competition Name");
		tableModel.addColumn("Winner");
		table = new JTable(tableModel); // Creating JTable for competition standings
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane); // Adding JTable to main panel
		
		// Adding space between components
		panel.add(new JLabel(" "));
		// Adding space between components
		panel.add(new JLabel(" "));
		
		// Label added to display selected competition participants
		participantLabel = new JLabel("Select Competition to View Participants");
		// Adding label to main panel
		panel.add(participantLabel);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		// Creating JList for selected competition participants
		jList = new JList<>(model);
		jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPaneList = new JScrollPane(jList);
		// Adding JList to main panel
		panel.add(scrollPaneList);

		back.setAlignmentX(CENTER_ALIGNMENT);
		// Adding button to main panel
		panel.add(back);
		// Adding main panel to content pane
		add(panel);
		// Setting frame visibility to true
		setVisible(true);
	}
	
	/**
	 * Method used to populate the table with parent competition standings
	 */
	private void populateTableParent() {
		Map<String, String> standings = DatabaseOperations.getCompetitionWinnersForParent(parent.getUsername());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// Clear existing data from the table
		model.setRowCount(0); 
		
		for (Map.Entry<String, String> entry : standings.entrySet()) {
			// Add competition standings to the table
			model.addRow(new Object[] {entry.getKey(), entry.getValue()});
		}
	}
	
	/**
	 *  Method used to populate the table with child competition standings
	 */
	
	private void populateTableChild() {
		Map<String, String> standings = DatabaseOperations.getCompetitionWinnersForChild(child.getUsername());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clear existing data from the table
		
		for (Map.Entry<String, String> entry : standings.entrySet()) {
			// Adding competition standings to the table
			model.addRow(new Object[] {entry.getKey(), entry.getValue()});
		}
	}
	
	/**
	 *  Method used to set up table selection listener for parent
	 */
	
	private void setupTableSelectionListenerParent() {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	// Handle table selection for parent
                    handleTableSelectionParent();
                }
            }
        });
    }

	/**
	 * Method used to handle table selection for parent
	 */
    private void handleTableSelectionParent() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            String selectedCompetition = (String) table.getValueAt(selectedRow, 0);

            // Check if more than one competition is selected
            if (table.getSelectedRowCount() > 1) {
                participantLabel.setText("<html><font color='red'><b>Please select only one competition</b></font></html>");
                jList.setModel(new DefaultListModel<>()); // Clear the JList
            } else {
                participantLabel.setText("Participants of " + selectedCompetition);
                // Update participant list for parent
                updateParticipantListParent(selectedCompetition);
            }
        }
    }

 
    /**
     * Method to update participant list for parent
     * @param competitionName
     */
    private void updateParticipantListParent(String competitionName) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> participants = DatabaseOperations.getParticipantsOfCompetitionForParent(competitionName, parent.getUsername());

        for (String participant : participants) {
            model.addElement(participant);
        }

        jList.setModel(model);
    }
    
    /**
     *  Method used to set up table selection listener for child
     */
    private void setupTableSelectionListenerChild() {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	// Handling table selection for child
                    handleTableSelectionChild();
                }
            }
        });
    }

 
    /**
     * Method used to handle table selection for child
     */
    private void handleTableSelectionChild() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            String selectedCompetition = (String) table.getValueAt(selectedRow, 0);

            // Check if more than one competition is selected
            if (table.getSelectedRowCount() > 1) {
                participantLabel.setText("<html><font color='red'><b>Please select only one competition</b></font></html>");
                jList.setModel(new DefaultListModel<>()); // Clear the JList
            } else {
                participantLabel.setText("Participants of " + selectedCompetition);
                // Update participant list for child
                updateParticipantListChild(selectedCompetition);
            }
        }
    }

    /**
     * Method used to update participant list for child
     * @param competitionName
     */
    private void updateParticipantListChild(String competitionName) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> participants = DatabaseOperations.getParticipantsOfCompetitionForChild(competitionName, child.getUsername());

        for (String participant : participants) {
            model.addElement(participant);
        }

        jList.setModel(model);
    }
    
 
    // Main method to start the application
	public static void main(String[] args) {
		/**
		 *  Creating instance of CompetitionStandings for testing of this compeition standing feature
		 */
		new CompetitionStandings(new ChildAccount("test", "test"), new JFrame());
	}
}
