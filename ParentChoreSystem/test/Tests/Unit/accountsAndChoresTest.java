package Tests.Unit;


import accountsModule.*;
import choresModule.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;

class accountsAndChoresTest {
	
	ParentAccount parentAccount;
	ChildAccount childAccount1;
	ChildAccount childAccount2;
	Chore chore1;
	Chore chore2;
	Chore chore3;
	Chore chore4;
	
	@BeforeEach
	public void setUp() throws Exception {
		parentAccount = new ParentAccount("parent", "test");
		childAccount1 = new ChildAccount("child1", "test");
		childAccount2 = new ChildAccount("child2", "test");
		chore1 = new Chore("chore1", "category1", 20.0, 10.0);
		chore2 = new Chore("chore2", "category1", 10.0, 5.0);
		chore3 = new Chore("chore3", "category1", 10.0, 5.0);
		chore4 = new Chore("chore4", "category1", 20.0, 10.0);
		
		parentAccount.addChildAccount(childAccount1);
		parentAccount.addChildAccount(childAccount2);
		
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		
		parentAccount = null;
		childAccount1 = null;
		childAccount2 = null;
		chore1 = null;
		chore2 = null;
		chore3 = null;
		chore4 = null;
	}
	
	@Test
	public void testEditChore() {
		
		parentAccount.assignChore(childAccount1, chore1);
		
		parentAccount.editChore(childAccount1.getChores().get(0), "payment", 20.0);
		parentAccount.editChore(childAccount1.getChores().get(0), "name", "editTestName");
		
		assertEquals(20.0, childAccount1.getChores().get(0).getPayment());
		assertEquals("editTestName", childAccount1.getChores().get(0).getName());
	}
	
	@Test
	public void testVerifyChores() {
		
		parentAccount.assignChore(childAccount1, chore1);
		parentAccount.assignChore(childAccount1, chore2);
		parentAccount.assignChore(childAccount2, chore3);
		parentAccount.assignChore(childAccount2, chore4);
		
		childAccount1.markChoreCompleted(chore1);
		childAccount2.markChoreCompleted(chore3);
		
		parentAccount.verifyChores();
		
		assertEquals(10.0, parentAccount.checkChildBalance(childAccount1));
		assertEquals(5.0, parentAccount.checkChildBalance(childAccount2));
		
		assertTrue(chore1.isPaid());
		assertTrue(chore3.isPaid());
		assertTrue(!chore2.isPaid());
		assertTrue(!chore4.isPaid());
		
	}
	
	/**
	 * Series of tests used to check the functioning of chore edit and deletion.
	 * Multiple tests used in order to test the scalability of each function.
	 */
	
	@Test
    public void testAddChore1() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        choresList.addChore(chore1);
        assertTrue(choresList.getChores().contains(chore1));
    }
	
	@Test
    public void testAddChore2() {
        ChoresList choresList2 = new ChoresList();
        Chore chore2 = new Chore("Make bed sheets", "Household", 30, 3.0); // 1 hour chore
        choresList2.addChore(chore2);
        assertTrue(choresList2.getChores().contains(chore2));
    }


    @Test
    public void testRemoveChore() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        choresList.addChore(chore1);
        choresList.removeChore(chore1);
        assertFalse(choresList.getChores().contains(chore1));
    }
    
    @Test
    public void testRemoveChore2() {
        ChoresList choresList2 = new ChoresList();
        Chore chore2 = new Chore("Dusting Shelves", "Household", 60, 10.0); // 1 hour chore
        choresList2.addChore(chore2);
        choresList2.removeChore(chore2);
        assertFalse(choresList2.getChores().contains(chore2));
    }
    

    @Test
    public void testRemoveChoreNonexistent() {
        ChoresList choresList = new ChoresList();
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        choresList.addChore(chore);
        Chore nonExistentChore = new Chore("Clean room", "Household", 30, 3.0); // Nonexistent chore
        choresList.removeChore(nonExistentChore);
        assertEquals(1, choresList.getNumberOfChores()); // Number of chores should remain unchanged
    }
    
    @Test
    public void testRemoveChoreNonexistent2() {
        ChoresList choresList2 = new ChoresList();
        Chore chore = new Chore("Laundry", "Bedroom", 60, 5.0); // 1 hour chore
        choresList2.addChore(chore);
        Chore nonpresentChore = new Chore("Clean room", "Household", 60, 8.0); // Nonexistent chore as well
        choresList2.removeChore(nonpresentChore);
        assertEquals(1, choresList2.getNumberOfChores()); // Number of chores should remain unchanged
    }

    @Test
    public void testGetNumberOfChoresEmptyList() {
        ChoresList choresList = new ChoresList();
        assertEquals(0, choresList.getNumberOfChores()); // Number of chores should be zero initially
    }

    @Test
    public void testGetNumberOfChores() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores2() {
        ChoresList choresList2 = new ChoresList();
        Chore chore1 = new Chore("Mop the floor", "Household", 40, 5.0); // 1 hour chore
        Chore chore2 = new Chore("stack the pantry", "Household", 30, 3.0); // 30-minute chore
        choresList2.addChore(chore1);
        choresList2.addChore(chore2);
        assertEquals(2, choresList2.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores3() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores4() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores5() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores6() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores7() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores8() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores9() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
    
    @Test
    public void testGetNumberOfChores10() {
        ChoresList choresList = new ChoresList();
        Chore chore1 = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        Chore chore2 = new Chore("Clean room", "Household", 30, 3.0); // 30-minute chore
        choresList.addChore(chore1);
        choresList.addChore(chore2);
        assertEquals(2, choresList.getNumberOfChores()); // Number of chores should be two
    }
}