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

public class ChildRegistration extends JFrame implements ActionListener {
	
	private JTextField firstNameField;
	private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JButton registerButton;
    private JButton backButton;

    public ChildRegistration() {
        setTitle("Child Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel ageLabel = new JLabel("Enter age:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
   

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        ageField = new JTextField();
        

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
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Create an Account object with the entered username and password
        Account newAccount = new Account(username, password);
        saveAccount(newAccount);

        // Perform registration process (e.g., save the account details to a database)
        // For simplicity, let's just print the account details for now
        System.out.println("New Account Registered:");
        System.out.println("Username: " + newAccount.getUsername());
        System.out.println("Password: " + newAccount.getPassword());
        
        new ChildLoginPage(); 
        dispose();

    }
    
    private void saveAccount(Account account) {
        try {
            FileWriter writer = new FileWriter("childAccounts.txt", true); // append mode
            writer.write(account.getUsername() + ":" + account.getPassword()+ "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save account!");}
        }

    public static void main(String[] args) {
        new ChildRegistration();
    }

}
