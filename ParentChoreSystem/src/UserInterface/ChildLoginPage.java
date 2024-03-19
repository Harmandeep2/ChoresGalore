package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.ChildAccount;
import databaseModule.DatabaseConnector;

public class ChildLoginPage extends JFrame implements ActionListener {
	
	// Components for the login page
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    /**
     *  Constructor for the child login page
     *  allowing for appropriate display of  
     *  login detail entries
     */
    public ChildLoginPage() {
        setTitle("Child Login");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Username entry field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        // Password entry field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        
        // Login button to review login details 
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
        // Back button to return to previous page
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
            	dispose(); // Close login window
            }
        });
        
        // Adding username and password panels to the main panel
        JPanel usernamePanel = new JPanel();
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		panel.add(usernamePanel);
		panel.add(passwordPanel);
        
		// Adding button panel to the main panel
        JPanel buttonPanel = new JPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(backButton);
		
		panel.add(buttonPanel);
        add(panel);
        setVisible(true);
    }

    /**
     * ActionListener method for handling button clicks
     * @param e
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // User login authentication for detailed verification
        if (authenticate(username, password)) { //for successful login open child account gui
            JOptionPane.showMessageDialog(this, "Login successful!"); 
            openChildAccountGUI();
            dispose();
        } else { //for incorrect login details entered, error message dialogue provided for re-entry
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }
    /**
     *  Method to authenticate the user through database usage
     * @param username
     * @param password
     * @return
     */
    private boolean authenticate(String username, String password) {
    	// Establishing a database connection
    	try (Connection connection = DatabaseConnector.getConnection();
    			// Creating a PreparedStatement to execute a SQL query
                PreparedStatement preparedStatement = connection.prepareStatement(
                		// SQL query to select user account with provided username, password, and account type as 'Child'
                        "SELECT * FROM Accounts WHERE username = ? AND password = ? AND accountType = 'Child'")) {
    		   // Set parameters for the prepared statement
               preparedStatement.setString(1, username);
               preparedStatement.setString(2, password);
               // Execute the query and obtain a ResultSet
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	   // If ResultSet contains any data, authentication is successful, return true
                   return resultSet.next();
               }
           } catch (SQLException ex) {
        	   // Print stack trace in case of SQL exception
               ex.printStackTrace();
           }
           return false;
    }
    
    // Method to open the child account GUI
    private void openChildAccountGUI() {
        ChildAccount childAccount = new ChildAccount(usernameField.getText(),new String(passwordField.getPassword()));
        new ChildAccountGUI(childAccount);
    }
    
    // Main method to start the application
    public static void main(String[] args) {
        new ChildLoginPage();
    }

}
