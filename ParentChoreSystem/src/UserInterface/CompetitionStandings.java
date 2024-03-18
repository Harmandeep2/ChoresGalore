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
	private ChildAccount child;
	private ParentAccount parent;
	private JFrame prev;
	private JTable table;
	private JLabel participantLabel;
	private JList<String> jList;
	/**
	 * @param ChildAccount and JFrame
	 * @exception Headless Exception
	 * */
	public CompetitionStandings(ChildAccount account, JFrame prev) throws HeadlessException {
		this.prev = prev;
		this.child = account;
		initialize();
		populateTableChild();
		setupTableSelectionListenerChild();
	}
	
	public CompetitionStandings(ParentAccount account, JFrame prev) throws HeadlessException {
		this.prev = prev;
		this.parent = account;
		initialize();
		populateTableParent();
		setupTableSelectionListenerParent();
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
	
	private void populateTableParent() {
		Map<String, String> standings = DatabaseOperations.getCompetitionWinnersForParent(parent.getUsername());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for (Map.Entry<String, String> entry : standings.entrySet()) {
			model.addRow(new Object[] {entry.getKey(), entry.getValue()});
		}
	}
	
	private void populateTableChild() {
		Map<String, String> standings = DatabaseOperations.getCompetitionWinnersForChild(child.getUsername());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for (Map.Entry<String, String> entry : standings.entrySet()) {
			model.addRow(new Object[] {entry.getKey(), entry.getValue()});
		}
	}
	
	private void setupTableSelectionListenerParent() {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    handleTableSelectionParent();
                }
            }
        });
    }

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
                updateParticipantListParent(selectedCompetition);
            }
        }
    }

    private void updateParticipantListParent(String competitionName) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> participants = DatabaseOperations.getParticipantsOfCompetitionForParent(competitionName, parent.getUsername());

        for (String participant : participants) {
            model.addElement(participant);
        }

        jList.setModel(model);
    }
    
    private void setupTableSelectionListenerChild() {
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    handleTableSelectionChild();
                }
            }
        });
    }

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
                updateParticipantListChild(selectedCompetition);
            }
        }
    }

    private void updateParticipantListChild(String competitionName) {
        DefaultListModel<String> model = new DefaultListModel<>();
        List<String> participants = DatabaseOperations.getParticipantsOfCompetitionForChild(competitionName, child.getUsername());

        for (String participant : participants) {
            model.addElement(participant);
        }

        jList.setModel(model);
    }
    
	public static void main(String[] args) {
		new CompetitionStandings(new ChildAccount("test", "test"), new JFrame());
	}
}
