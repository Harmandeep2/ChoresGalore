package accountsModule;
import choresModule.*;
import java.util.*;

public class ChildAccount extends Account {

	/*Children cannot add and remove chores by themselves, so addChore and removeChore
	methods are not given in this class. Parent can assign chores to children.*/
	
	private ParentAccount parent;
	private List<Chore> chores;
	protected double balance;
	private double hoursWorked;
	
	public ChildAccount(String username, String password) {
		super(username, password);
		chores = new ArrayList<>();
		balance = 0;
		hoursWorked = 0;
	}
	
	//Child can only mark a chore as completed. Payment is done by parent after verifying.
	public void markChoreCompleted(Chore chore) {
        if (chores.contains(chore)) {
            chore.markCompleted();
            hoursWorked += chore.getTime()/60;
        }
    }
		
	public void setParent(ParentAccount parent) {
		this.parent = parent;
	}
	
	public ParentAccount getParent() {
		return parent;
	}
	
	public List<Chore> getChores() {
		return chores;
	}
	
	public double getBalance() {
		return balance;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}
	

}
