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

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;


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
	        mainPanel.add(checkBalanceButton);
        	//add(checkBalanceButton,BorderLayout.SOUTH);

	        // Log Out Button
        	add(logoutButton,BorderLayout.PAGE_END);
	        //mainPanel.add(logoutButton);


	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance();
	            }
	        });
	        

	        
	     // Initialize JList with chores from ChildAccount
//	        List<Chore> chores = childAccount.getChores();
//	        DefaultListModel<Chore> listModel = new DefaultListModel<>();
//	        for (Chore chore : chores) {
//	            listModel.addElement(chore);
//	        }
//	        JList<Chore> list = new JList<>(listModel);
//
//	        // Add the JList to the panel within a scroll pane
//	        JScrollPane scrollPane = new JScrollPane(list);
//	        mainPanel.add(scrollPane);
	        
	        String[] choreArray = {"Chore 1", "Chore 2", "Chore 3"};
	        JList choreList = new JList<>(choreArray); // Initialize JList with String array

	        // Add the JList to the panel
	        mainPanel.add(choreList);

	        
	        
	        getContentPane().add(mainPanel);
	        setVisible(true);
	        
	    }
	    
    
	    private void checkBalance() {
	        ChildAccount selectedChild = (ChildAccount) childAccount;
	        double balance = parentAccount.checkChildBalance(selectedChild);
	        JOptionPane.showMessageDialog(this, "Your Balance: $" + balance);
	    }









	    public static void main(String[] args) {
	        
	        ChildAccount childAccount = new ChildAccount("childUsername", "childPassword");
	        ChildAccountGUI childAccountGUI = new ChildAccountGUI(childAccount);
	    }

}


