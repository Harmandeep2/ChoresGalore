package accountsModule;
import choresModule.*;
import java.util.*;

public class ChildAccount extends Account {

	/*Children cannot add and remove chores by themselves, so addChore and removeChore
	methods are not given in this class. Parent can assign chores to children.*/
	
	/**
	 * instance variables for ChildAccount Class
	 */
	private ParentAccount parent;
	private List<Chore> chores;
	protected double balance;
	private double hoursWorked;
	
	
	/**
	 * Constructor for ChildAccount class
	 * @param username
	 * @param password
	 * initializes balance and hours worked to 0
	 */
	public ChildAccount(String username, String password) {
		super(username, password);
		chores = new ArrayList<>();
		balance = 0;
		hoursWorked = 0;
	}
	
	//Child can only mark a chore as completed. Payment is done by parent after verifying.
	public void markChoreCompleted(Chore chore) {
        if (chores.contains(chore) && !chore.isCompleted()) {
            chore.markCompleted();
            hoursWorked += chore.getTime()/60;
        }
        else
        	System.out.println("Either the chore does not exist or it has already been completed");
    }
	
	/**
	 * Setter method
	 * @param parent
	 */
	public void setParent(ParentAccount parent) {
		this.parent = parent;
	}
	
	/**
	 * GetParent method
	 * @return parent
	 */
	public ParentAccount getParent() {
		return parent;
	}
	
	/**
	 * GetChores method
	 * @return chores
	 */
	public List<Chore> getChores() {
		return chores;
	}
	
	/**
	 * getBalance method
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * setBalance method
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * getHoursWorked method
	 * @return hoursWorked
	 */
	public double getHoursWorked() {
		return hoursWorked;
	}
	
	/**
	 * setHoursWorked method
	 * @param hoursWorked
	 */
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
	    return getUsername(); 
	}

	

}
