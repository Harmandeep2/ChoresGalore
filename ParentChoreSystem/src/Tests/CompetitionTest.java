package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import choresModule.Chore;
import competitionModule.Competition;

class CompetitionTest {
	
	Competition competition;
	ChildAccount child1;
    ChildAccount child2;
    List<Chore> chores1;
    List<Chore> chores2;

	
	@BeforeEach
	
	void init() {
		competition = new Competition();
	}


	@Test
	void test() {
		fail("Not yet implemented");
	}

}
