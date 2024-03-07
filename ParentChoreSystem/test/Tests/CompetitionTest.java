package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import competitionModule.Competition;

class CompetitionTest {
	
	private ParentAccount parent;
	private ChildAccount child1;
    private ChildAccount child2;
    private Chore chore;
    private double child1HoursAtStart;
    private double child2HoursAtStart;
	
	@BeforeEach
	
	public void setUp() {
		parent = new ParentAccount("parent", "parentpass");
        child1 = new ChildAccount("child1", "child1pass");
        child2 = new ChildAccount("child2", "child2pass");
        chore = new Chore("Clean Bedroom", "Household", 60, 10.0);
        parent.addChildAccount(child1);
        parent.addChildAccount(child2);

        
        child1HoursAtStart = child1.getHoursWorked();
        child2HoursAtStart = child2.getHoursWorked();
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		
		parent = null;
		child1 = null;
		child2 = null;
		chore = null;

		
	}

	@Test
    public void testGetWinner_Child1Wins() {
		Competition competition = new Competition(parent, new ArrayList<>(List.of(child1, child2)), chore);
        child1.markChoreCompleted(chore);
        assertEquals(child1, competition.getWinner());
    }
	
	
	@Test
    public void testGetWinner_Child2Wins() {
		Competition competition = new Competition(parent, new ArrayList<>(List.of(child1, child2)), chore);
        child2.markChoreCompleted(chore);
        assertEquals(child2, competition.getWinner());
    }
	

}
