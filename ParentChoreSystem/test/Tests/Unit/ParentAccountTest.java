package Tests.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;

public class ParentAccountTest {
	
	@Test
    public void testAddChildAccount1() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "123");
        ChildAccount childAccount = new ChildAccount("childUser", "456");
        parentAccount.addChildAccount(childAccount);
        assertTrue(parentAccount.getChildren().contains(childAccount));
    }
	
	@Test
	public void testAddChildAccount2() {
	    ParentAccount parentAccount = new ParentAccount("parentUser", "456");
	    ChildAccount childAccount = new ChildAccount("childUser", "789");
	    parentAccount.addChildAccount(childAccount);
	    assertTrue(parentAccount.getChildren().contains(childAccount));
	}

	@Test
	public void testAddChildAccount3() {
	    ParentAccount parentAccount = new ParentAccount("parentUser", "101112");
	    ChildAccount childAccount = new ChildAccount("childUser", "131415");
	    parentAccount.addChildAccount(childAccount);
	    assertFalse(parentAccount.getChildren().isEmpty());
	}

	@Test
	public void testAddChildAccount4() {
	    ParentAccount parentAccount = new ParentAccount("parentUser", "161718");
	    ChildAccount childAccount = new ChildAccount("childUser", "192021");
	    parentAccount.addChildAccount(childAccount);
	    assertEquals(1, parentAccount.getChildren().size());
	}

	@Test
	public void testAddChildAccount5() {
	    ParentAccount parentAccount = new ParentAccount("parentUser", "222324");
	    ChildAccount childAccount1 = new ChildAccount("childUser1", "252627");
	    ChildAccount childAccount2 = new ChildAccount("childUser2", "282930");
	    parentAccount.addChildAccount(childAccount1);
	    parentAccount.addChildAccount(childAccount2);
	    assertTrue(parentAccount.getChildren().contains(childAccount1));
	    assertTrue(parentAccount.getChildren().contains(childAccount2));
	}
    @Test
    public void testDeleteChildAccount() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        parentAccount.deleteChildAccount(childAccount);
        assertFalse(parentAccount.getChildren().contains(childAccount));
    }

    @Test
    public void testAssignChore1() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }
    
    @Test
    public void testAssignChore2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testAssignChore3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testAssignChore4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Wash dishes", "Kitchen", 20, 4.0);
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testAssignChore5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
        parentAccount.assignChore(childAccount, chore);
        assertTrue(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChore1() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }
    @Test
    public void testDeleteChore2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChore3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChore4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Wash dishes", "Kitchen", 20, 4.0);
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChore5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
        childAccount.getChores().add(chore);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }


    @Test
    public void testDepositChorePayment1() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(5.0, childAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testDepositChorePayment2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(3.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testDepositChorePayment3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(2.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testDepositChorePayment4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Wash dishes", "Kitchen", 20, 4.0);
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(4.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testDepositChorePayment5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
        childAccount.getChores().add(chore);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(10.0, childAccount.getBalance(), 0.01);
    }


    @Test
    public void testVerifyChores1() {
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
    public void testVerifyChores2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        childAccount.getChores().add(chore);
        chore.markCompleted();
        parentAccount.verifyChores();
        assertEquals(3.0, childAccount.getBalance(), 0.01);
        assertTrue(chore.isPaid());
    }

    @Test
    public void testVerifyChores3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        childAccount.getChores().add(chore);
        chore.markCompleted();
        parentAccount.verifyChores();
        assertEquals(2.0, childAccount.getBalance(), 0.01);
        assertTrue(chore.isPaid());
    }

    @Test
    public void testVerifyChores4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Wash dishes", "Kitchen", 20, 4.0);
        childAccount.getChores().add(chore);
        chore.markCompleted();
        parentAccount.verifyChores();
        assertEquals(4.0, childAccount.getBalance(), 0.01);
        assertTrue(chore.isPaid());
    }

    @Test
    public void testVerifyChores5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        parentAccount.addChildAccount(childAccount);
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
        childAccount.getChores().add(chore);
        chore.markCompleted();
        parentAccount.verifyChores();
        assertEquals(10.0, childAccount.getBalance(), 0.01);
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
    public void testCheckChildBalance2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        parentAccount.addChildAccount(childAccount);
        childAccount.setBalance(25.0);
        assertEquals(25.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testCheckChildBalance3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        parentAccount.addChildAccount(childAccount);
        childAccount.setBalance(30.0);
        assertEquals(30.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testCheckChildBalance4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        parentAccount.addChildAccount(childAccount);
        childAccount.setBalance(15.0);
        assertEquals(15.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testCheckChildBalance5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        parentAccount.addChildAccount(childAccount);
        childAccount.setBalance(40.0);
        assertEquals(40.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }

    @Test
    public void testEditChore1() {
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
    public void testEditChore2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Wash dishes", "Kitchen", 30, 4.0);
        
        parentAccount.editChore(chore, "name", "Unload dishwasher");
        assertEquals("Unload dishwasher", chore.getName());
        
        parentAccount.editChore(chore, "time", 20.0);
        assertEquals(20.0, chore.getTime(), 0.01);
        
        parentAccount.editChore(chore, "payment", 5.0);
        assertEquals(5.0, chore.getPayment(), 0.01);
        
        parentAccount.editChore(chore, "category", "Household");
        assertEquals("Household", chore.getCategory());
    }

    @Test
    public void testEditChore3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
        
        parentAccount.editChore(chore, "name", "Trim hedges");
        assertEquals("Trim hedges", chore.getName());
        
        parentAccount.editChore(chore, "time", 90.0);
        assertEquals(90.0, chore.getTime(), 0.01);
        
        parentAccount.editChore(chore, "payment", 8.0);
        assertEquals(8.0, chore.getPayment(), 0.01);
        
        parentAccount.editChore(chore, "category", "Gardening");
        assertEquals("Gardening", chore.getCategory());
    }
    
    @Test
    public void testEditChore4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Walk dog", "Pets", 30, 3.0);
        
        parentAccount.editChore(chore, "name", "Feed cat");
        assertEquals("Feed cat", chore.getName());
        
        parentAccount.editChore(chore, "time", 15.0);
        assertEquals(15.0, chore.getTime(), 0.01);
        
        parentAccount.editChore(chore, "payment", 2.5);
        assertEquals(2.5, chore.getPayment(), 0.01);
        
        parentAccount.editChore(chore, "category", "Pet Care");
        assertEquals("Pet Care", chore.getCategory());
    }

    @Test
    public void testEditChore5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Vacuum living room", "Household", 45, 5.0);
        
        parentAccount.editChore(chore, "name", "Dust furniture");
        assertEquals("Dust furniture", chore.getName());
        
        parentAccount.editChore(chore, "time", 30.0);
        assertEquals(30.0, chore.getTime(), 0.01);
        
        parentAccount.editChore(chore, "payment", 3.0);
        assertEquals(3.0, chore.getPayment(), 0.01);
        
        parentAccount.editChore(chore, "category", "Cleaning");
        assertEquals("Cleaning", chore.getCategory());
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
    public void testDeleteChoreInvalidChild2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }

    @Test
    public void testDeleteChoreInvalidChild3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        parentAccount.deleteChore(childAccount, chore);
        assertFalse(childAccount.getChores().contains(chore));
    }


    @Test
    public void testDepositChorePaymentInvalidChore1() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password456");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(0.0, childAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testDepositChorePaymentInvalidChore2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password789");
        Chore chore = new Chore("Clean room", "Household", 30, 3.0);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(0.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testDepositChorePaymentInvalidChore3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password012");
        Chore chore = new Chore("Take out trash", "Household", 15, 2.0);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(0.0, childAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testDepositChorePaymentInvalidChore4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password345");
        Chore chore = new Chore("Wash dishes", "Kitchen", 20, 4.0);
        parentAccount.depositChorePayment(childAccount, chore);
        assertEquals(0.0, childAccount.getBalance(), 0.01);
    }

    @Test
    public void testDepositChorePaymentInvalidChore5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("childUser", "password678");
        Chore chore = new Chore("Mow lawn", "Outdoor", 120, 10.0);
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
    public void testCheckChildBalanceNonexistentChild2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        ChildAccount childAccount = new ChildAccount("nonexistentChild", "password789");
        assertEquals(0.0, parentAccount.checkChildBalance(childAccount), 0.01);
    }


    @Test
    public void testEditChoreInvalidChangeType2() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType3() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType4() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType5() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType6() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType7() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType8() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }
    
    @Test
    public void testEditChoreInvalidChangeType9() {
        ParentAccount parentAccount = new ParentAccount("parentUser", "password123");
        Chore chore = new Chore("Do homework", "Education", 60, 5.0); // 1 hour chore
        parentAccount.editChore(chore, "invalidChangeType", "NewValue");
        assertEquals("Do homework", chore.getName());
        assertEquals(60.0, chore.getTime(), 0.01);
        assertEquals(5.0, chore.getPayment(), 0.01);
        assertEquals("Education", chore.getCategory());
    }

}
