package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.Account;
import accountsModule.ParentAccount;
import databaseModule.DatabaseConnector;
import databaseModule.DatabaseOperations;

public class ParentRegistration extends JFrame implements ActionListener {
	
	private JTextField firstNameField;
	private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField numberOfChildrenField;
    private JButton registerButton;
    private JButton backButton;

    public ParentRegistration() {
        setTitle("Parent Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel numberOfChildrenLabel = new JLabel("Enter number of children:");
    

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        numberOfChildrenField = new JTextField();
        

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
            	dispose(); // Close login window
            }
        });

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(numberOfChildrenLabel);
        panel.add(numberOfChildrenField);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String accountType = "Parent"; // Assuming all registered accounts are parents

        // Insert new parent account into the database
        DatabaseOperations.insertAccount(username, password, accountType);

        // Display success message
        JOptionPane.showMessageDialog(this, "Parent account registered successfully!");
        
        new ParentLoginPage(); 
        dispose();
    }
    
   /* private void saveAccount(ParentAccount account) {
        try {
            FileWriter writer = new FileWriter("parentAccounts.txt", true); // append mode
            writer.write(account.getUsername() + ":" + account.getPassword() + "\n" );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save account!");}
        }*/
    
  
   
    public static void main(String[] args) {
        new ParentRegistration();
    }

}
