package UserInterface;

import java.util.List;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import accountsModule.ParentAccount;
import choresModule.Chore;
import accountsModule.ChildAccount;



public class CompetitionGUI extends JFrame implements ActionListener {
	private JFrame prev;
	private JTable table;
	private DefaultTableModel model;
	private JTextField choreNameField, choreCategoryField, choreTimeField, chorePaymentField;
	public CompetitionGUI(JFrame prev) {
		this.prev = prev;
		initialize();
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
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Chore ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Category");
		tableModel.addColumn("Time");
		tableModel.addColumn("Payment");
		tableModel.addColumn("isCompleted");
		tableModel.addColumn("isPaid");
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		mainPanel.add(scrollPane);
		getContentPane().add(mainPanel);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		ParentAccount p = new ParentAccount ("name","pass");
		ParentAccountGUI parent = new ParentAccountGUI(p);
		new CompetitionGUI(parent);
	}
}
