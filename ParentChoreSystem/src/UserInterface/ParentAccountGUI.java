package UserInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JTextField;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;

public class ParentAccountGUI extends JFrame{

	 	private ParentAccount parentAccount;
	    private JComboBox<ChildAccount> childDropdown;
	    private JTextField choreNameField, choreCategoryField, choreTimeField, chorePaymentField;
	    private JButton createChoreButton, checkBalanceButton, addChildButton;
	    private DefaultListModel<Chore> choreListModel;
	    private JList<Chore> choreList;
	    private JButton logoutButton;


	    public ParentAccountGUI(ParentAccount parentAccount) {
	        this.parentAccount = parentAccount;
	        initialize();
	    }

	    private void initialize() {
	        setTitle("Parent Account");
	        setSize(550, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

	        // Child Dropdown
	        List<ChildAccount> children = parentAccount.getChildren();
	        childDropdown = new JComboBox<>(children.toArray(new ChildAccount[0]));
	        mainPanel.add(childDropdown);
	        
	     // Add Child Button
	        addChildButton = new JButton("Add Child"); // Added button
	        mainPanel.add(addChildButton); // Added button

	        // Chore Creation Panel
	        JPanel chorePanel = new JPanel();
	        chorePanel.setBorder(BorderFactory.createTitledBorder("Chore Creation"));

	        choreNameField = new JTextField(15);
	        choreCategoryField = new JTextField(15);
	        choreTimeField = new JTextField(5);
	        chorePaymentField = new JTextField(5);
	        createChoreButton = new JButton("Create Chore");
	        
	        JButton assignChoreButton = new JButton("Assign Chore");
	        mainPanel.add(assignChoreButton);

	        // Add action listener for the assign chore button
	        assignChoreButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                assignChore();
	            }
	        });
	        
	        // Add Chore List Panel
	        JPanel choreListPanel = new JPanel();
	        choreListPanel.setBorder(BorderFactory.createTitledBorder("Chore List"));
	        mainPanel.add(choreListPanel);

	        // Existing initialization code...

	        // Add action listener for creating a chore
	        createChoreButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                createChore();
	            }
	        });
	        
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


	        chorePanel.add(new JLabel("Name: "));
	        chorePanel.add(choreNameField);
	        chorePanel.add(new JLabel("Category: "));
	        chorePanel.add(choreCategoryField);
	        chorePanel.add(new JLabel("Time: "));
	        chorePanel.add(choreTimeField);
	        chorePanel.add(new JLabel("Payment: "));
	        chorePanel.add(chorePaymentField);
	        chorePanel.add(createChoreButton);
	        
	        mainPanel.add(chorePanel);

	        // Check Balance Button
	        checkBalanceButton = new JButton("Check Balance");
	        mainPanel.add(checkBalanceButton);

	        // Log Out Button
	        mainPanel.add(logoutButton);

	      
	        createChoreButton.addActionListener((ActionListener) new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                createChore();
	            }
	        });

	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkBalance();
	            }
	        });
	        
	        addChildButton.addActionListener(new ActionListener() { // Add action listener for add child button
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                addChild();
	            }
	        });

	        getContentPane().add(mainPanel);
	        setVisible(true);
	    }
	    ///////////////
	    
	    private void addChild() {
	        String childName = JOptionPane.showInputDialog("Enter child's name:");
	        if (childName != null && !childName.isEmpty()) {
	            ChildAccount newChild = new ChildAccount(childName, childName);
	            parentAccount.addChildAccount(newChild);
	            childDropdown.addItem(newChild);
	            saveChild(childName);
	       
	        }
	    }

	    private void createChore() {
	        String choreName = choreNameField.getText();
	        String choreCategory = choreCategoryField.getText();
	        double choreTime = Double.parseDouble(choreTimeField.getText());
	        double chorePayment = Double.parseDouble(chorePaymentField.getText());

	        ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	        Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
	        parentAccount.assignChore(selectedChild, newChore);
	        choreListModel.addElement(newChore);
	    }
	    
	 // Define a method to handle chore assignment
	    private void assignChore() {
	        String choreName = choreNameField.getText();
	        String choreCategory = choreCategoryField.getText();
	        double choreTime = Double.parseDouble(choreTimeField.getText());
	        double chorePayment = Double.parseDouble(chorePaymentField.getText());

	        ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	        Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
	        parentAccount.assignChore(selectedChild, newChore);

	        // Optionally, provide feedback to the user
	        JOptionPane.showMessageDialog(this, "Chore assigned to " + selectedChild.getUsername());
	    }
	    
	    private void saveChild(String childName) {
	        try {
	            FileWriter writer = new FileWriter("childNames.txt", true); // append mode
	            writer.write(childName+ "\n" );
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Failed to save account!");}
	        }
	    
	  

	    private void checkBalance() {
	        ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	        double balance = parentAccount.checkChildBalance(selectedChild);
	        JOptionPane.showMessageDialog(this, selectedChild+ "'s Balance: $" + balance);
	    }

	    public static void main(String[] args) {
	        
	        ParentAccount parentAccount = new ParentAccount("parentUsername", "parentPassword");
	        ParentAccountGUI parentAccountGUI = new ParentAccountGUI(parentAccount);
	        //new ParentAccount(title, title);
	    }
	
	

}
