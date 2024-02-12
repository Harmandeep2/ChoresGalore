package Tests;

import accountsModule.*;
import choresModule.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
		
}
