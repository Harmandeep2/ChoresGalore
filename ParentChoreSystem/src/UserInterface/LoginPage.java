package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LoginPage extends JFrame implements ActionListener{


	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private ParentRegistration parent;

	public LoginPage() {

		//this.parent = parent;
		setTitle("Account Login");
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField(15);

		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(15);

		loginButton = new JButton("Login");
		loginButton.addActionListener(this);

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(loginButton);

		add(panel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());

		// Check if the entered username and password match any saved account
		if (authenticate(username, password)) 
		{
			JOptionPane.showMessageDialog(this, "Login successful!");
			openParentAccountGUI();
            dispose(); // Close the login window
		} 
		else {
			JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
		}
	}

	private boolean authenticate(String username, String password) {
		try {
			FileReader fileReader = new FileReader("accounts.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {

				if(line.contains(":")) {
					String[] parts = line.split(":");

					if(parts.length == 2) {
						String savedUsername = parts[0];
						String savedPassword = parts[1];


						if (savedUsername.equals(username) && savedPassword.equals(password)) {
							bufferedReader.close();
							return true;
						}
					}
				}
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to authenticate!");
		}

		return false;
	}
	
	private void openParentAccountGUI() {
        // Create the ParentAccount object
        ParentAccount parentAccount = new ParentAccount(usernameField.getText(), new String(passwordField.getPassword()));

        // Open the ParentAccountGUI window
        new ParentAccountGUI(parentAccount);
    }


	public static void main(String[] args) {
		new LoginPage();
	}


}