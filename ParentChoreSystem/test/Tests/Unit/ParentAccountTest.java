package Tests.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;

public class ParentAccountTest {
	
	@Test
    public void testAddChildAccount() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        assertTrue(parentAccount.getChildren().contains(childAccount));
    }

    @Test
    public void testDeleteChildAccount() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        parentAccount.deleteChildAccount(childAccount);
        assertFalse(parentAccount.getChildren().contains(childAccount));
    }

    @Test
    public void testAssignChore() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChore() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDepositChorePayment() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(5.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testVerifyChores() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        chore.markCompleted();
        parentAccount.verifyChores();
        assertEquals(5.0, childAccount.getBalance(), 0.01);
        assertTrue(chore.isPaid());
    }

    @Test
    public void testCheckChildBalance() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        childAccount.setBalance(50.0);
        assertEquals(50.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testEditChore() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "name", "Do math homework");
        assertEquals("Do math homework", chore.getName());
        
        parentAccount.editChore(chore, "time", 90.0);
        assertEquals(90.0, chore.getTime(), 0.01);
        
        parentAccount.editChore(chore, "payment", 7.0);
        assertEquals(7.0, chore.getPayment(), 0.01);
        
        parentAccount.editChore(chore, "category", "Math");
        assertEquals("Math", chore.getCategory());
    }
    
    @Test
    public void testAssignChoreInvalidChild() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.assignChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChoreInvalidChild() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDepositChorePaymentInvalidChore() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(0.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testCheckChildBalanceNonexistentChild() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        assertEquals(0.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testEditChoreInvalidChangeType() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }

}
