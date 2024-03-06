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
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public ChildLoginPage() {
        setTitle("Child Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
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
        panel.add(loginButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            openChildAccountGUI();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    private boolean authenticate(String username, String password) {
    	try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM Accounts WHERE username = ? AND password = ? AND accountType = 'Child'")) {
               preparedStatement.setString(1, username);
               preparedStatement.setString(2, password);
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   return resultSet.next();
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           return false;
    }

    private void openChildAccountGUI() {
        ChildAccount childAccount = new ChildAccount(usernameField.getText(),new String(passwordField.getPassword()));
        new ChildAccountGUI(childAccount);
    }

    public static void main(String[] args) {
        new ChildLoginPage();
    }

}
