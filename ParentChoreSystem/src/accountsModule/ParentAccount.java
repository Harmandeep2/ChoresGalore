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
}
