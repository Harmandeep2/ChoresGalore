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



public class CompetitionGUI extends JFrame{
	private JFrame prev;
	private ParentAccount parentAccount;
	private JTable jTable;
	private JList<ChildAccount> jList;
	private JButton startCompetitionButton, backButton;
	private JTextField competitionNameField;
	
	
	public CompetitionGUI(ParentAccount parent, JFrame prev) {
		this.parentAccount = parent;
		this.prev = prev;
		initialize();
		displayParentChoresNotCompleted();
	}
	
	public void initialize() {
		//JLabel
		//JText
		//jtable of children to assign task to (doesnt have to be jtable)
		//jtable of chore to assign
		//add a start button
		//upon start all chosen children are assigned the chosen tasks
		//upon start parent is taken back to their gui
	
		setTitle("Competition");
		setSize(1000, 500);
		setLocationRelativeTo(null); // Center the frame on the screen
		setResizable(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel competitionNamePanel = new JPanel();
		competitionNamePanel.setLayout(new BoxLayout(competitionNamePanel, BoxLayout.X_AXIS));
		competitionNamePanel.setMaximumSize(new Dimension(300, 30));
		JLabel competitionLabel = new JLabel("Competition Name: ");
		competitionNameField = new JTextField(15);

		competitionNamePanel.add(competitionLabel);
		competitionNamePanel.add(competitionNameField);
		mainPanel.add(competitionNamePanel);
		
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		
		JLabel label = new JLabel("List of your Children");
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPanel.add(label);
		
		List<ChildAccount> children = DatabaseOperations.getAllChildrenofParent(parentAccount.getUsername());
		DefaultListModel<ChildAccount> model = new DefaultListModel<>();
		for (ChildAccount child : children) {
			model.addElement(child);
		}
		jList = new JList<>(model);
		jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(jList);
		mainPanel.add(scrollPane);
		
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		
		// Create a table model
		JLabel label2 = new JLabel("List of Chores available to be assigned");
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPanel.add(label2);
		
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
		mainPanel.add(scrollPaneTable);
		
		mainPanel.add(new JLabel(" "));
		mainPanel.add(new JLabel(" "));
		
		startCompetitionButton = new JButton("Start Competition");
		mainPanel.add(startCompetitionButton);
		
		backButton = new JButton("Back");
		mainPanel.add(backButton);
		
		startCompetitionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startCompetition();
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prev.setVisible(true);
			}
		});
		
		getContentPane().add(mainPanel);
		setVisible(true);
	}
	
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
			tableModel.addRow(rowData);
		}
	}
	
	private void startCompetition() {
		String competitionName = competitionNameField.getText();
		List<ChildAccount> selectedChildren = jList.getSelectedValuesList();
		
		if(jTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select a chore");
			return;
		}
		int choreId = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
		
		if(competitionName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a competition name");
			return;
		}
		
		if(selectedChildren.isEmpty() || selectedChildren.size() == 1) {
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
			JOptionPane.showMessageDialog(this, "Competition already exists, please change the name!");
		}
	}
	
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

	private void closeFrameAndShowPrevious() {
	    // Close the current frame
	    Window window = SwingUtilities.getWindowAncestor(this);
	    if (window != null) {
	        window.dispose();
	    }

	    // Show the previous frame
	    if (prev != null) {
	        prev.setVisible(true);
	    }
	}

	public static void main(String[] args) {
		ParentAccount p = new ParentAccount ("name","pass");
		ParentAccountGUI parent = new ParentAccountGUI(p);
		new CompetitionGUI(p, parent);
	}
}
