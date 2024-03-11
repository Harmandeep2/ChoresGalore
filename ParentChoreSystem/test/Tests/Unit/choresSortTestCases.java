package Tests.Unit;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;
import choresModule.ChoresUtils;

class choresSortTestCases {
	
	@Test
    void testSortAlphabetically() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum", "Cleaning", 30, 10.0));
        chores.add(new Chore("Wash Dishes", "Cleaning", 15, 5.0));
        chores.add(new Chore("Mow Lawn", "Yard Work", 60, 20.0));

        List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

        assertEquals("Mow Lawn", sortedChores.get(0).getName());
        assertEquals("Vacuum", sortedChores.get(1).getName());
        assertEquals("Wash Dishes", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum", "Cleaning", 30, 10.0));
        chores.add(new Chore("Wash Dishes", "Cleaning", 15, 5.0));
        chores.add(new Chore("Mow Lawn", "Yard Work", 60, 20.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Wash Dishes", sortedChores.get(0).getName());
        assertEquals("Vacuum", sortedChores.get(1).getName());
        assertEquals("Mow Lawn", sortedChores.get(2).getName());
    }

    @Test
    void testSortByPayment() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum", "Cleaning", 30, 10.0));
        chores.add(new Chore("Wash Dishes", "Cleaning", 15, 5.0));
        chores.add(new Chore("Mow Lawn", "Yard Work", 60, 20.0));

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);

        assertEquals("Wash Dishes", sortedChores.get(0).getName());
        assertEquals("Vacuum", sortedChores.get(1).getName());
        assertEquals("Mow Lawn", sortedChores.get(2).getName());
    }


}
