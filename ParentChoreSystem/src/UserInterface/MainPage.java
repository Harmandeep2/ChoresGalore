package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPage extends JFrame implements ActionListener{
	
	private JButton parentRegisterButton;
	private JButton childRegisterButton;
	private JButton parentLoginButton;
    private JButton childLoginButton;
    

    public MainPage() {
        setTitle("Registration or Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        
        parentLoginButton = new JButton("Parent Login");
        parentLoginButton.addActionListener(this);
        
        childLoginButton = new JButton("Child Login");
        childLoginButton.addActionListener(this);

        parentRegisterButton = new JButton("I'm a new Parent");
        parentRegisterButton.addActionListener(this);
        
        childRegisterButton = new JButton("I'm a new Child");
        childRegisterButton.addActionListener(this);    


        panel.add(parentLoginButton);
        panel.add(childLoginButton);
        panel.add(parentRegisterButton);
        panel.add(childRegisterButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == parentRegisterButton) {
            new ParentRegistration();
        } 
        else if (e.getSource() == childRegisterButton) {
            new ChildRegistration();
        } 
        else if (e.getSource() == parentLoginButton) {
            new ParentLoginPage();
        }
        else if (e.getSource() == childLoginButton) {
            new ChildLoginPage();
        }
        setVisible(false);
    }

    public static void main(String[] args) {
        new MainPage();
    }
	
		     

}
