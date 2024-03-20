package UserInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.Account;
import databaseModule.DatabaseOperations;

//Class for Child Registration page
public class ChildRegistration extends JFrame implements ActionListener {
	
	 // child registration GUI components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;

    // Constructor for Child Registration page
    public ChildRegistration() {
        setTitle("Child Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null); // Center the frame on displayed screen

        // Create main panel with border layout and padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        // Create panel with grid layout for components
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
        
  
        
        // Registration button
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);


        // Back button to return to the main page
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
            	dispose(); // Close registration window
            }
        });


        // Adding components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);

        // Add panel to the main panel
        mainPanel.add(panel, BorderLayout.CENTER);
        
        // Add main panel to the frame
        add(mainPanel);
        setVisible(true);
    }
    
    /**
     *  ActionListener method for handling button clicks
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if(password.equals(confirmPassword )) //Check if password and confirm password are the same
		{
        // Insert new child account into the database
        DatabaseOperations.insertAccount(username, password, "Child");

        // Display success message
        JOptionPane.showMessageDialog(this, "Child account registered successfully!");

        // Redirect to login page
        new ChildLoginPage();
        dispose(); // Close registration window
		}
        
        else
		{
        	 // Display error message if passwords do not match
			JOptionPane.showMessageDialog(this, "Check that Password's are entered correctly");
		}

    }
    
   /* private void saveAccount(Account account) {
        try {
            FileWriter writer = new FileWriter("childAccounts.txt", true); // append mode
            writer.write(account.getUsername() + ":" + account.getPassword()+ "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save account!");}
        }
        */
    
    // Main method to start the application
    public static void main(String[] args) {
        new ChildRegistration();
    }

}
