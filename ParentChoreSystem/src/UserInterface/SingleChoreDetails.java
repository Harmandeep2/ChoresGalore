package UserInterface;

import java.util.Map;
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some margin

        // Get chore details from the database
        Map<String, Object> choreMap = DatabaseOperations.getDetailOfSingleChore(this.choreId);

        if (choreMap != null) {
            String[] columnNames = {"Attribute", "Value"};
            Object[][] rowData = new Object[choreMap.size()][2];
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

            // Create JTable with the data
            JTable table = new JTable(new DefaultTableModel(rowData, columnNames));
            table.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            JLabel label = new JLabel("Chore details not found.");
            mainPanel.add(label, BorderLayout.CENTER);
        }

        backButton = new JButton("Back");
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
