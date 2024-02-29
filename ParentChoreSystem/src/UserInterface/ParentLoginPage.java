package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.ParentAccount;

public class ParentLoginPage extends JFrame implements ActionListener{
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public ParentLoginPage() {
        setTitle("Parent Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        
     // Add KeyListener to passwordField
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	 // Check if the entered username and password match any parent account
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    
                    // Check if the entered username and password match any parent account
                    if (authenticate(username, password)) {
                        JOptionPane.showMessageDialog(ParentLoginPage.this, "Login successful!");
                        openParentAccountGUI();
                        dispose(); // Close the login window
                    } else {
                        JOptionPane.showMessageDialog(ParentLoginPage.this, "Invalid username or password. Please try again.");
                    }
                }
            }
        });

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
            openParentAccountGUI();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("parentAccounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void openParentAccountGUI() {
        ParentAccount parentAccount = new ParentAccount(usernameField.getText(), new String(passwordField.getPassword()));
        new ParentAccountGUI(parentAccount);
    }

    public static void main(String[] args) {
        new ParentLoginPage();
    }

}
