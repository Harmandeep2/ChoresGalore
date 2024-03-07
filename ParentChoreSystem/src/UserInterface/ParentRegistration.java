package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import databaseModule.DatabaseOperations;

public class ParentRegistration extends JFrame implements ActionListener {
	
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;

    public ParentRegistration() {
        setTitle("Parent Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        JPanel panel = new JPanel(new GridLayout(4, 1));
        
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();  
   

        //Register new Parent Account
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        
        //Return to main page
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
            	dispose(); // Close login window
            }
        });

        
      
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);

        mainPanel.add(panel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String accountType = "Parent"; // Assuming all registered accounts are parents
        

		if(password.equals(confirmPassword )) //Check if password and confirm password are the same
		{
        // Insert new parent account into the database
        DatabaseOperations.insertAccount(username, password, accountType);

        // Display success message
        JOptionPane.showMessageDialog(this, "Parent account registered successfully!");
        
        new ParentLoginPage(); 
        dispose();
        }
		else
		{
			JOptionPane.showMessageDialog(this, "Check that Password's are entered correctly");
		}
    }
    
   
   
    public static void main(String[] args) {
        new ParentRegistration();
    }

}
