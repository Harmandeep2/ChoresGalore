package accountsModule;
import choresModule.*;
import java.util.*;

/*All the methods are in accordance of our project INFORMATION ACCESS rules described in our
user stories, for example, parents can view child's balance, only parent can add/remove chores
and make payments, etc.*/

public class ParentAccount extends Account {

	private List<ChildAccount> childAccounts;
    
	public ParentAccount(String username, String password) {
		super(username, password);
		childAccounts = new ArrayList<>();
	}
	
	public void addChildAccount(ChildAccount childAccount) {
		childAccounts.add(childAccount);
	}
	
	public void deleteChildAccount(ChildAccount childAccount) {
		childAccounts.remove(childAccount);
	}

	public void assignChore(ChildAccount childAccount, Chore chore) {
		if (childAccounts.contains(childAccount)) {
			childAccount.getChores().add(chore);
		}
	}

	public void deleteChore(ChildAccount childAccount, Chore chore) {
		if (childAccounts.contains(childAccount)) {
			childAccount.getChores().remove(chore);
		}
	}
	
	public List<ChildAccount> getChildren() {
		return childAccounts;
	}
	
	public void depositChorePayment(ChildAccount childAccount, Chore chore) {
		if(childAccounts.contains(childAccount) && childAccount.getChores().contains(chore)) {
			childAccount.balance += chore.getPayment();
		}
	}
	
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
}
