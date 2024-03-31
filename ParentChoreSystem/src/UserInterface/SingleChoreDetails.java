package UserInterface;

import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import databaseModule.DatabaseOperations;

public class SingleChoreDetails extends JFrame {

    private int choreId;
    private JFrame previousFrame;
    private JButton backButton;

    public SingleChoreDetails(int choreId, JFrame previousFrame) {
        this.choreId = choreId;
        this.previousFrame = previousFrame;
        initialize();
    }

    private void initialize() {
        setTitle("Chore Full Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(true);

        //creating main panel
        JPanel mainPanel = new JPanel();
        //setting a layout for main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //setting a border
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some margin

        // Get chore details from the database
        Map<String, Object> choreMap = DatabaseOperations.getDetailOfSingleChore(this.choreId);

        if (choreMap != null) {
            String[] columnNames = {"Attribute", "Value"};
            Object[][] rowData = new Object[choreMap.size() + 1][2];
            int index = 0;

            // Fill rowData with chore details
            for (Map.Entry<String, Object> entry : choreMap.entrySet()) {
                rowData[index][0] = entry.getKey();
                rowData[index][1] = entry.getValue() == null || entry.getValue().toString().isEmpty() ? "<Not Specified>" : entry.getValue();
                if (entry.getValue() instanceof Boolean) {
                    rowData[index][1] = (Boolean) entry.getValue() ? "Yes" : "No";
                }
                if(entry.getKey().equals("Completed By")) {
                	rowData[index][1] = entry.getValue() == null || entry.getValue().toString().isEmpty() ? "<Not Yet Completed>" : entry.getValue();
                }
                if(entry.getKey().equals("Rating")) {
					rowData[index][1] = (int) entry.getValue() == -1 || entry.getValue().toString().isEmpty() ? "<Not Yet Rated>" : entry.getValue();
                }
                index++;
            }
            
            List<String> assignees = DatabaseOperations.getChildAssignedToChore(this.choreId);
            
			if(!assignees.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < assignees.size(); i++) {
					String child = assignees.get(i);
					
					if(i != assignees.size() - 1) {
						sb.append(child).append(", ");
					}
					else {
						sb.append(child);
					}
				}
				rowData[index][0] = "Assigned To";
				rowData[index][1] = sb.toString();
			}
			else {
				rowData[index][0] = "Assigned To";
				rowData[index][1] = "<Not Yet Assigned to any child>";
			}

            // Create JTable with the data
            JTable table = new JTable(new DefaultTableModel(rowData, columnNames));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
        	//displaying error message when chore details are not found
            JLabel label = new JLabel("Chore details not found.");
            mainPanel.add(label, BorderLayout.CENTER);
        }

        //creating a back button
        backButton = new JButton("Back");
        //adding the back button to the main panel
        mainPanel.add(backButton);
        
        backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Hide the current frame
				setVisible(false);
				// Show the previous frame
				previousFrame.setVisible(true);
			}
		});
        // Add the main panel to the JFrame
        add(mainPanel);

        // Display the JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
       new SingleChoreDetails(1, new JFrame());
    }
}
