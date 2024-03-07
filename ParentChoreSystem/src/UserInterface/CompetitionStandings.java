package UserInterface;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import accountsModule.*;


public class CompetitionStandings extends JFrame implements ActionListener{
	private JButton back;
	private JButton goBack;
	private ParentAccount parent;
	private ChildAccount child;
	private ArrayList<ChildAccount> childList;
	private JTable table;
	
	public CompetitionStandings(ParentAccount parent,
			ArrayList<ChildAccount> childList, ChildAccount child) throws HeadlessException {
		super();
		this.parent = parent;
		this.childList = childList;
		this.child=child;
		competition();
	}

	public void competition() {
		
		setTitle("Competition Standings");
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		back = new JButton("Return");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Will add a return to parent account/child account home page later
				
			}
			
		});
		if (!childList.isEmpty() && parent.getChildren()!=null) {
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("Competition Name");
			tableModel.addColumn("Winner");
			// Create the chore table using the table model
			table = new JTable(tableModel);
			JScrollPane scrollPane = new JScrollPane(table);
			panel.add(scrollPane);

		} else {
			goBack = new JButton("No Registered Parents with children");
		}
		panel.add(back);
		add(panel);
		setVisible(true);
	}
	//
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		ArrayList<ChildAccount> childList = new ArrayList<ChildAccount>();
		ChildAccount child = new ChildAccount("username","password");
		ParentAccount parent = new ParentAccount("username","password");
		childList.add(child);
		parent.addChildAccount(child);
		new CompetitionStandings(parent,childList,child);
	}
}
