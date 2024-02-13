package UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;

public class ParentAccountGUI extends JFrame implements ActionListener{
	
	private ParentAccount parentAccount;
    private JTextArea childAccountsTextArea;

    public ParentAccountGUI(ParentAccount parentAccount) {
        this.parentAccount = parentAccount;

        setTitle("Parent Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        childAccountsTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(childAccountsTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);
        panel.add(refreshButton, BorderLayout.SOUTH);

        displayChildAccounts();

        add(panel);
        setVisible(true);
    }

    private void displayChildAccounts() {
        List<ChildAccount> childAccounts = parentAccount.getChildren();
        StringBuilder sb = new StringBuilder();
        for (ChildAccount child : childAccounts) {
            sb.append("Username: ").append(child.getUsername()).append(", Balance: ").append(child.getBalance()).append("\n");
            sb.append("Chores:\n");
            for (Chore chore : child.getChores()) {
                sb.append("- ").append(chore.getName()).append(", Payment: ").append(chore.getPayment()).append("\n");
            }
            sb.append("\n");
        }
        childAccountsTextArea.setText(sb.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getActionCommand().equals("Refresh")) {
            displayChildAccounts();
        }
        
    }

    public static void main(String[] args) {
    	ParentAccount parent = new ParentAccount("parent", "password");
        ChildAccount child1 = new ChildAccount("child1", "password");
        ChildAccount child2 = new ChildAccount("child2", "password");
        parent.addChildAccount(child1);
        parent.addChildAccount(child2);
        Chore chore1 = new Chore("Clean Room", "Household", 10.0, 5.00);
        Chore chore2 = new Chore("Do Dishes", "Household", 5.0, 20.00);
        parent.assignChore(child1, chore1);
        parent.assignChore(child2, chore2);
        new ParentAccountGUI(parent);
    }

}
