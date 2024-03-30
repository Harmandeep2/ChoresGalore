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
    
    @Test
    public void testSortAlphabetically2() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Z", "Education", 60, 5.0));
        chores.add(new Chore("A", "Household", 30, 3.0));
        chores.add(new Chore("C", "Education", 45, 4.0));

        List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);
        assertEquals("A", sortedChores.get(0).getName());
        assertEquals("C", sortedChores.get(1).getName());
        assertEquals("Z", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByTime2() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // 1 hour chore
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // 30-minute chore
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // 2 hour chore

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment2() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void ExcelCreation() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // 1 hour chore
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // 30-minute chore
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // 2 hour chore

        ChoresUtils.createExcelSheet(chores);
    }
    
    @Test
    public void ExcelCreation2 () {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Laundry", "Clothes", 180, 15.0));
        chores.add(new Chore("Clean room", "Household", 30, 3.0));
        chores.add(new Chore("Dishes", "Kitchen", 20, 5.0));
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0));

        ChoresUtils.createExcelSheet(chores);
    }
    
    @Test
    public void testSortByPayment3() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment4() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment5() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment6() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment7() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment8() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment9() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }
    
    @Test
    public void testSortByPayment10() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // $5 payment
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // $3 payment
        chores.add(new Chore("Mow lawn", "Outdoor", 120, 10.0)); // $10 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Mow lawn", sortedChores.get(2).getName());
    }


}
