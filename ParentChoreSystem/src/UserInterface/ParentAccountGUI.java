package UserInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	    private JButton createChoreButton, checkBalanceButton;

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

	        // Chore Creation Panel
	        JPanel chorePanel = new JPanel();
	        chorePanel.setBorder(BorderFactory.createTitledBorder("Chore Creation"));

	        choreNameField = new JTextField(15);
	        choreCategoryField = new JTextField(15);
	        choreTimeField = new JTextField(5);
	        chorePaymentField = new JTextField(5);
	        createChoreButton = new JButton("Create Chore");

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

	        getContentPane().add(mainPanel);
	        setVisible(true);
	    }

	    private void createChore() {
	        String choreName = choreNameField.getText();
	        String choreCategory = choreCategoryField.getText();
	        double choreTime = Double.parseDouble(choreTimeField.getText());
	        double chorePayment = Double.parseDouble(chorePaymentField.getText());

	        ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	        Chore newChore = new Chore(choreName, choreCategory, choreTime, chorePayment);
	        parentAccount.assignChore(selectedChild, newChore);
	    }

	    private void checkBalance() {
	        ChildAccount selectedChild = (ChildAccount) childDropdown.getSelectedItem();
	        double balance = parentAccount.checkChildBalance(selectedChild);
	        JOptionPane.showMessageDialog(this, "Child's Balance: " + balance);
	    }

	    public static void main(String[] args) {
	        // Example usage
	        ParentAccount parentAccount = new ParentAccount("parentUsername", "parentPassword");
	        ParentAccountGUI parentAccountGUI = new ParentAccountGUI(parentAccount);
	    }
	
	

}
