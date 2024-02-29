package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import accountsModule.Account;
import accountsModule.ChildAccount;
import accountsModule.ParentAccount;

public class LoginPage extends JFrame implements ActionListener{


	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private ParentRegistration parent;
	private JButton backButton;

	public LoginPage() {

		//this.parent = parent;
		setTitle("Account Login");
		setSize(300,200);
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
                    if (authenticate(username, password) != null) {
                        JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                        openParentAccountGUI();
                        dispose(); // Close the login window
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password. Please try again.");
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

		// Check if the entered username and password match any saved account
		String role = authenticate(username, password);
		if (role != null) 
		{
			JOptionPane.showMessageDialog(this, "Login successful!");
			
			if(role.equals("child"))
			{
			openChildAccountGUI();		
			}
			else if(role.equals("parent"))
			{
			openParentAccountGUI();
			}
	
            dispose(); // Close the login window
		} 
		else {
			JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
		}
	}

	private String authenticate(String username, String password) {
		try {
			FileReader fileReader = new FileReader("accounts.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {

				if(line.contains(":")) {
					String[] parts = line.split(":");

					if(parts.length == 3) {
						String savedUsername = parts[0];
						String savedPassword = parts[1];
						String savedRole = parts[2];
			

						if (savedUsername.equals(username) && savedPassword.equals(password)) {
							bufferedReader.close();
							return savedRole;
						}
					}
				}
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to authenticate!");
		}

		return null;
	}
	
	private void openParentAccountGUI() {
        // Create the ParentAccount object
        ParentAccount parentAccount = new ParentAccount(usernameField.getText(), new String(passwordField.getPassword()));

        // Open the ParentAccountGUI window
        new ParentAccountGUI(parentAccount);
    }
	
	
	private void openChildAccountGUI() {
        ChildAccount childAccount = new ChildAccount(usernameField.getText(), new String(passwordField.getPassword()));
        new ChildAccountGUI(childAccount);

	}


	public static void main(String[] args) {
		new LoginPage();
	}
	


}
