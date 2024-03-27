package Tests.Unit;

import accountsModule.*;
import choresModule.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;


public class ChildAccountTest {
	
	@Test
    public void testMarkChoreCompletedValidChore() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        childAccount.markChoreCompleted(chore);
        assertTrue(chore.isCompleted());
        assertEquals(1.0, childAccount.getHoursWorked(), 0.01); // 1 hour worked
    }

    @Test
    public void testMarkChoreCompletedInvalidChoreAlreadyCompleted() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        chore.markCompleted();
        childAccount.getChores().add(chore);
        childAccount.markChoreCompleted(chore);
        assertTrue(chore.isCompleted());
        assertEquals(0.0, childAccount.getHoursWorked(), 0.01); // No hours worked
    }

    @Test
    public void testMarkChoreCompletedInvalidChoreNotAssigned() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.markChoreCompleted(chore);
        assertFalse(chore.isCompleted());
        assertEquals(0.0, childAccount.getHoursWorked(), 0.01); // No hours worked
    }

    @Test
    public void testSetParent() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        childAccount.setParent(parentAccount);
        assertEquals(parentAccount, childAccount.getParent());
    }

    @Test
    public void testGetChores() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0);
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0);
        childAccount.getChores().add(chore1);
        childAccount.getChores().add(chore2);
        assertEquals(2, childAccount.getChores().size());
        assertTrue(childAccount.getChores().contains(chore1));
        assertTrue(childAccount.getChores().contains(chore2));
    }

    @Test
    public void testGetBalance1() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        childAccount.setBalance(50.0);
        assertEquals(50.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testGetHoursWorked1() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        childAccount.setHoursWorked(10.5);
        assertEquals(10.5, childAccount.getHoursWorked(), 0.01);
    }

    @Test
    public void testToString1() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        assertEquals("childUser", childAccount.toString());
    }

    @Test
    public void testGetBalance2() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        childAccount.setBalance(1000.0);
        assertEquals(1000.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testGetHoursWorked2() {
        ChildAccount childAccount = new ChildAccount("childUser", "password123");
        childAccount.setHoursWorked(0);
        assertEquals(0, childAccount.getHoursWorked(), 0.01);
    }

    @Test
    public void testToString2() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString3() {
        ChildAccount childAccount = new ChildAccount("childUser2", "password1234");
        assertEquals("childUser2", childAccount.toString());
    }
    
    @Test
    public void testToString4() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString5() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString6() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString7() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString8() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString9() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString10() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString11() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }
    
    @Test
    public void testToString12() {
        ChildAccount childAccount = new ChildAccount("childUser1", "password123");
        assertEquals("childUser1", childAccount.toString());
    }

}
