package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.Account;
import databaseModule.DatabaseOperations;

public class ChildRegistration extends JFrame implements ActionListener {
	
	
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;

    public ChildRegistration() {
        setTitle("Child Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
   

        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        
        

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


        
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

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
        dispose();
		}
        
        else
		{
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

    public static void main(String[] args) {
        new ChildRegistration();
    }

}
