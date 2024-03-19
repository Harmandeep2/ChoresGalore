package UserInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Class for Main Page GUI
public class MainPage extends JFrame implements ActionListener{
	
	// Buttons for parent and child registration and login
	private JButton parentRegisterButton;
	private JButton childRegisterButton;
	private JButton parentLoginButton;
    private JButton childLoginButton;
    

    // Constructor created for Main Page GUI
    public MainPage() {
    	// Set the title of the frame
    	setTitle("Main Page"); 
    	// Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // Set the size of the frame
        setSize(800, 600); 
        // Center the frame on the screen
        setLocationRelativeTo(null); 
        
        // Create a main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Added some padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); 

        // Creating a panel with 4 rows and 1 column
        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
   
        /**
         *  Initialization of buttons for parent and child login and registration
         */
        
        parentLoginButton = new JButton("Parent Login");
        parentLoginButton.addActionListener(this);
        
        childLoginButton = new JButton("Child Login");
        childLoginButton.addActionListener(this);

        parentRegisterButton = new JButton("I'm a new Parent");
        parentRegisterButton.addActionListener(this);
        
        childRegisterButton = new JButton("I'm a new Child");
        childRegisterButton.addActionListener(this);    


        /**
         *  Add buttons to the center panel
         */
        centerPanel.add(parentLoginButton);
        centerPanel.add(childLoginButton);
        centerPanel.add(parentRegisterButton);
        centerPanel.add(childRegisterButton);

        // Adding a center panel to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Adding main panel to content pane
        add(mainPanel);
        // Setting frame visibility to true
        setVisible(true);
    }

    /**
     * Method created to handle button actions
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	// Check which button is clicked and perform appropriate action
        if (e.getSource() == parentRegisterButton) {
        	// Open parent registration page
            new ParentRegistration(); 
        } 
        else if (e.getSource() == childRegisterButton) {
        	// Open child registration page
            new ChildRegistration(); 
        } 
        else if (e.getSource() == parentLoginButton) {
        	// Open parent login page
            new ParentLoginPage(); 
        }
        else if (e.getSource() == childLoginButton) {
        	// Open child login page
            new ChildLoginPage(); 
        }
        // Used to Hide the main page after button is clicked 
        setVisible(false); 
    }

    // Main method to start the application
    public static void main(String[] args) {
    	// Create instance of MainPage to start the application
        new MainPage();
    }
	
		   
}
