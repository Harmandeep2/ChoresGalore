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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.ParentAccount;
import databaseModule.DatabaseConnector;

/**
 * The ParentLoginPage class represents the graphical user interface for parent login.
 * It allows parents to enter their username and password to log in.
 * Upon successful login, it opens the ParentAccountGUI.
 */

public class ParentLoginPage extends JFrame implements ActionListener{
	
	// Components for the login page
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private JCheckBox showPasswordCheckbox;

    /**
     * Constructor for the ParentLoginPage class.
     * Sets up the GUI components for parent login.
     */
    public ParentLoginPage() {
    	 // Set the title of the window
    	 setTitle("Parent Login"); 
    	 // Set the size of the window
         setSize(300, 200); 
         // Center the frame on the screen
         setLocationRelativeTo(null); 
         // Close the application when the window is closed
         setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Username field and label to take in login info
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        // Password field and label for login authentication
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check if the Enter key is pressed (key code 10)
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Simulate a click on the login button
                    loginButton.doClick();
                }
            }
        });

		showPasswordCheckbox = new JCheckBox("Show Password");


        // Login button with action listener
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
        // Back button created to return to the main page
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Create a new instance of the MainPage and display it
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
            	dispose(); // Close login window
            }
        });
        
        showPasswordCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckbox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});

        /**
         *  Panel for username input
         */
        JPanel usernamePanel = new JPanel();
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		
		/**
		 *  Panel for password input
		 */
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		passwordPanel.add(showPasswordCheckbox);
		
		panel.add(usernamePanel);
		panel.add(passwordPanel);
        
		/**
		 *  Panel for buttons
		 */
        JPanel buttonPanel = new JPanel();
		buttonPanel.add(loginButton);
		buttonPanel.add(backButton);
		
		// Adding the button panel to the main panel
		panel.add(buttonPanel); 
		// Adding the main panel to the frame
        add(panel); 
        // Making the frame visible to parent 
        setVisible(true); 
    }

    /**
     * ActionListener implementation for handling button clicks.
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Authentication of username and password
        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Open the parent account GUI
            openParentAccountGUI(); 
            // Close the login window
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    /**
     * Authenticate the parent's username and password.
     * @param username
     * @param password
     * @return true if authentication is successful, false otherwise
     */
    private boolean authenticate(String username, String password) {
    	try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM Accounts WHERE username = ? AND password = ? AND accountType = 'Parent'")) {
               preparedStatement.setString(1, username);
               preparedStatement.setString(2, password);
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	   // Return true if a matching record is found
                   return resultSet.next(); 
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
    	   // Return false if an exception occurs or no matching record is found
           return false;
    }

    /**
     * Opening ParentAccountGUI after successful login.
     */
    private void openParentAccountGUI() {
    	// Create a new ParentAccount instance with the entered username and password
        ParentAccount parentAccount = new ParentAccount(usernameField.getText(),new String(passwordField.getPassword()));
        // Open the ParentAccountGUI with the created ParentAccount instance
        new ParentAccountGUI(parentAccount);
    }
    
    
    

    /**
     * Main method used to create an instance of 
     * ParentLoginPage and display the login page.
     */
    public static void main(String[] args) {
    	// Creating an instance of ParentLoginPage
        new ParentLoginPage();
    }

}
