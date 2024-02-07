package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChoreEditingTest {

	ChoreEditing chore = new ChoreEditing();
	@BeforeEach
	void setUp() throws Exception {
		
		
		ParentAccount chore1 = new ParentAccount("Vaccum");
		chore.addChore(chore1 );
		
	}

	@Test
	void test() {
		
		ArrayList<ParentAccount> choreName = chore.getChores();
		
		assertEquals(choreName.get(0).getChoreName(), "Vaccum");
		
	}

}
