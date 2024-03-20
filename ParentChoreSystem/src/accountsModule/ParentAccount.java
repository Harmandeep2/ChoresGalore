package accountsModule;
import choresModule.*;
import java.util.*;
import competitionModule.*;


/*All the methods are in accordance of our project INFORMATION ACCESS rules described in our
user stories, for example, parents can view child's balance, only parent can add/remove chores
and make payments, etc.*/

public class ParentAccount extends Account {

	private List<ChildAccount> childAccounts;
	
	
    /**
     * Constructor for parent account class
     * @param username
     * @param password
     */
	public ParentAccount(String username, String password) {
		super(username, password);
		childAccounts = new ArrayList<>();
	}
	
	/**
	 * AddChildAccount method to add childaccount to arraylist named "childAccounts" under said parent
	 * @param childAccount
	 */
	public void addChildAccount(ChildAccount childAccount) {
		childAccounts.add(childAccount);
	}
	
	/**
	 * deletes said child under said parent
	 * @param childAccount
	 */
	public void deleteChildAccount(ChildAccount childAccount) {
		childAccounts.remove(childAccount);
	}

	/**
	 * assigns said chore to said child, if that chore isn't already assigned
	 * @param childAccount
	 * @param chore
	 */
	public void assignChore(ChildAccount childAccount, Chore chore) {
		if (childAccounts.contains(childAccount)) {
			childAccount.getChores().add(chore);
		}
	}

	/**
	 * deletes said chore from said child if child has that chore assigned
	 * @param childAccount
	 * @param chore
	 */
	public void deleteChore(ChildAccount childAccount, Chore chore) {
		if (childAccounts.contains(childAccount)) {
			childAccount.getChores().remove(chore);
		}
	}
	
	/**
	 * getter method
	 * @return childAccount
	 */
	public List<ChildAccount> getChildren() {
		return childAccounts;
	}
	
	/**
	 * deopsitChorePayment method to deposit the money into the child account
	 * @param childAccount
	 * @param chore
	 */
	public void depositChorePayment(ChildAccount childAccount, Chore chore) {
		if(childAccounts.contains(childAccount) && childAccount.getChores().contains(chore)) {
			childAccount.setBalance(childAccount.getBalance() + chore.getPayment());
		}
	}
	
	/**
	 * Verify chore method to make sure chore is completed and paid
	 */
	public void verifyChores() {
		for(ChildAccount child : childAccounts) {
			for(Chore chore : child.getChores()) {
				if (chore.isCompleted() && !chore.isPaid()) {
					depositChorePayment(child, chore);
					//mark chore as paid
					chore.markPaid();	
				}
			}
		}
	}
	
	/**
	 * checks the child account balance
	 * @param childAccount
	 * @return
	 */
	public double checkChildBalance(ChildAccount childAccount) {
		return childAccount.getBalance();
	}
	
	/**
	 * This method is used to edit the details of a Chore
	 * @param chore	: Chore that needs to be edited
	 * @param changeType : 	"name" if name needs to be changed,
	 * 						"time" if time needs to be changed
	 * 						"payment" if payment needs to be changed,
	 * 						"category" if category needs to be changed
	 * @param value	: Value that needs to be changed
	 */
	public void editChore(Chore chore, String changeType, Object value) {
		if (changeType .equals("name")) {
			if(value instanceof String) {
				chore.setName((String) value);
			}
			else {
				System.out.println("Invalid value, please enter a string value that represents the new name of the chore");
			}
		}
		else if (changeType.equals("time")) {
			if(value instanceof Double) {
				chore.setTime((double) value);
			}
			else {
				System.out.println("Invalid value, please enter a double value that represents the new time of the chore");
			}
		}
		else if (changeType.equals("payment")) {
			if(value instanceof Double) {
				chore.setPayment((double) value);
			}
			else {
				System.out.println("Invalid value, please enter a double value that represents the new payment of the chore");
			}
		}
		else if (changeType.equals("category")) {
			if(value instanceof String) {
				chore.setCategory((String) value);
			}
			else {
				System.out.println("Invalid value, please enter a string value that represents the new category of the chore");
			}
		}
	}
	
	//create instance of createCompetition here
}
