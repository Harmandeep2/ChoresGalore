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


public class CompetitionStandings extends JFrame{
	private JButton back;
	private JFrame prev;
	private JTable table;
	private JLabel participantLabel;
	private JList<String> jList;
	
	public CompetitionStandings(JFrame prev) throws HeadlessException {
		this.prev = prev;
		initialize();
		populateTable();
		setupTableSelectionListener();
	}

	public void initialize() {
		
		setTitle("Competition Standings");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame on the screen
		setResizable(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		back = new JButton("Return");
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev.setVisible(true);
				dispose();
			}	
		});
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Competition Name");
		tableModel.addColumn("Winner");
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		
		participantLabel = new JLabel("Select Competition to View Participants");
		panel.add(participantLabel);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		jList = new JList<>(model);
		jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPaneList = new JScrollPane(jList);
		panel.add(scrollPaneList);

		back.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(back);
		add(panel);
		setVisible(true);
	}
	
	private void populateTable() {
		Map<String, String> standings = DatabaseOperations.getCompetitionWinners();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for (Map.Entry<String, String> entry : standings.entrySet()) {
			model.addRow(new Object[] {entry.getKey(), entry.getValue()});
		}
		
	}
	
	private void setupTableSelectionListener() {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    handleTableSelection();
                }
            }
        });
    }

    private void handleTableSelection() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            String selectedCompetition = (String) table.getValueAt(selectedRow, 0);

            // Check if more than one competition is selected
            if (table.getSelectedRowCount() > 1) {
                participantLabel.setText("<html><font color='red'><b>Please select only one competition</b></font></html>");
                jList.setModel(new DefaultListModel<>()); // Clear the JList
            } else {
                participantLabel.setText("Participants of " + selectedCompetition);
                updateParticipantList(selectedCompetition);
            }
        }
    }

    private void updateParticipantList(String competitionName) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> participants = DatabaseOperations.getParticipantsOfCompetition(competitionName);

        for (String participant : participants) {
            model.addElement(participant);
        }

        jList.setModel(model);
    }
	public static void main(String[] args) {
		new CompetitionStandings(new JFrame());
	}
}
