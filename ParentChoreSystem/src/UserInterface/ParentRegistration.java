package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

/**
 * This ParentRegistration class represents the graphical user interface for parent registration.
 * It allows parents to create a new account by providing a username and password.
 */
public class ParentRegistration extends JFrame implements ActionListener {
	
	// Components for the parent registration page
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;

    /**
     * Constructor for the ParentRegistration class.
     * Setting up the GUI components for parent registration.
     */
    public ParentRegistration() {
    	// Setting the title of the window
        setTitle("Parent Registration Page");
        // Closing the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the size of the window
        setSize(500, 200);
        // Centering the frame on the screen
        setLocationRelativeTo(null); 

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        // Creating a panel with a 4x1 grid layout
        JPanel panel = new JPanel(new GridLayout(4, 1)); 
        
        // Labels for username, password, and confirm password fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        
        // Text fields for username, password, and confirm password
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();  
        
        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check if the Enter key is pressed (key code 10)
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Simulate a click on the login button
                	registerButton.doClick();
                }
            }
        });
   

        // Registering button with action listener
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        
        // Back button created to return to the main page
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Creating a new instance of the MainPage and display it
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
				// Closing the registration window
            	dispose(); 
            }
        });

        
        // Adding components to parent registration panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);

        // Adding panel to the main panel
        mainPanel.add(panel, BorderLayout.CENTER);
        
        // Add the main panel to the frame
        add(mainPanel);
        // Make the frame visible
        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     */
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
        
        // Open the ParentLoginPage
        new ParentLoginPage(); 
        // Close the registration window
        dispose();
        }
		else
		{
			JOptionPane.showMessageDialog(this, "Check that Password's are entered correctly");
		}
    }
    
   
    /**
     * Main method used to create an instance of ParentRegistration and display the registration page.
     */
    public static void main(String[] args) {
        new ParentRegistration();
    }

}
