package UserInterface;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;
import databaseModule.DatabaseOperations;


public class ChildAccountGUI extends JFrame{

	    private JTextField chores;
	    private JButton checkBalanceButton;
	    private JButton logoutButton;
		private ChildAccount childAccount;
	 	private ParentAccount parentAccount;



	    public ChildAccountGUI(ChildAccount childAccount) {
	        this.childAccount = childAccount;
	        initialize();
	    }

	    private void initialize() {
	        setTitle("Child Account");
	        setSize(550, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        


	        // Chore Creation Panel
	        
	        logoutButton = new JButton("Log Out");
	        logoutButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	    	        JOptionPane.showMessageDialog(null, "Logged Out Successfully");
	            	MainPage mainPage = new MainPage();
					mainPage.setVisible(true);
	            	dispose(); // Close login window
	            }
	        });

	        
	        // Check Balance Button
	        checkBalanceButton = new JButton("Check Balance");
	        //mainPanel.add(checkBalanceButton);
        	add(checkBalanceButton,BorderLayout.NORTH);

	        // Log Out Button
        	add(logoutButton,BorderLayout.PAGE_END);
	        //mainPanel.add(logoutButton);


	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance();
	            }
	        });
	        
	        String[] columnNames = {"Chore", "Category", "Time(min)", "Payment"};
	        Object[][] data = {{"Data 1", "Data 2", "Data 3", "Data 4"}};
	        DefaultTableModel model = new DefaultTableModel(data, columnNames);
	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        mainPanel.add(scrollPane);
	        
	     // Populate the table with assigned chores
	        List<Chore> assignedChores = childAccount.getChores();
	        for (Chore chore : assignedChores) {
	            Object[] rowData = {chore.getName(), chore.getCategory(), chore.getTime(), chore.getPayment()};
	            model.addRow(rowData);
	        }

	        
	        
	        getContentPane().add(mainPanel);
	        setVisible(true);




	    }
	    
    
	    private void checkBalance() {
	        ChildAccount selectedChild = (ChildAccount) childAccount;
	        double balance = DatabaseOperations.getChildBalance(null, selectedChild.getUsername());

	        JOptionPane.showMessageDialog(this, "Your Balance: $" + balance);
	    }




	    public static void main(String[] args) {
	        
	        ChildAccount childAccount = new ChildAccount("childUsername", "childPassword");
	        new ChildAccountGUI(childAccount);
	    }

}
