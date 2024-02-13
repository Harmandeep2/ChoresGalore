package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPage extends JFrame implements ActionListener{
	
	private JButton registerButton;
    private JButton loginButton;

    public MainPage() {
        setTitle("Registration or Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(registerButton);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            new Registration();
        } else if (e.getSource() == loginButton) {
            new LoginPage();
        }
        setVisible(false);
    }

    public static void main(String[] args) {
        new MainPage();
    }
	
		     

}
