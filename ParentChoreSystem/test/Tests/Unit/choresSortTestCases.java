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
    void testSortAlphabetically1() {
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
    void testSortAlphabetically2() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Make bed", "Bedroom", 40,12.0));
        chores.add(new Chore("Feed pets", "pet care", 35, 15.0));
        chores.add(new Chore("Stack shelves", "kitchen", 20, 21.0));

        List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

        assertEquals("Feed pets", sortedChores.get(0).getName());
        assertEquals("Make bed", sortedChores.get(1).getName());
        assertEquals("Stack shelves", sortedChores.get(2).getName());
    }
	
	@Test
	void testSortAlphabetically3() {
	    List<Chore> chores = new ArrayList<>();
	    chores.add(new Chore("Wash dishes", "Kitchen", 30, 10.0));
	    chores.add(new Chore("Sweep floors", "Living Room", 25, 8.0));
	    chores.add(new Chore("Take out trash", "Garage", 15, 5.0));

	    List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	    assertEquals("Sweep floors", sortedChores.get(0).getName());
	    assertEquals("Take out trash", sortedChores.get(1).getName());
	    assertEquals("Wash dishes", sortedChores.get(2).getName());
	}

	@Test
	void testSortAlphabetically4() {
	    List<Chore> chores = new ArrayList<>();
	    chores.add(new Chore("Vacuum carpet", "Living Room", 40, 12.0));
	    chores.add(new Chore("Water plants", "Garden", 35, 15.0));
	    chores.add(new Chore("Wash car", "Driveway", 20, 21.0));

	    List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	    assertEquals("Vacuum carpet", sortedChores.get(0).getName());
	    assertEquals("Wash car", sortedChores.get(1).getName());
	    assertEquals("Water plants", sortedChores.get(2).getName());
	}

	@Test
	void testSortAlphabetically5() {
	    List<Chore> chores = new ArrayList<>();
	    chores.add(new Chore("Clean windows", "Outside", 45, 18.0));
	    chores.add(new Chore("Organize closet", "Bedroom", 30, 14.0));
	    chores.add(new Chore("Rake leaves", "Backyard", 20, 20.0));

	    List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	    assertEquals("Clean windows", sortedChores.get(0).getName());
	    assertEquals("Organize closet", sortedChores.get(1).getName());
	    assertEquals("Rake leaves", sortedChores.get(2).getName());
	}
	
	 @Test
	    void testSortAlphabetically6() {
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
	    public void testSortAlphabetically7() {
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
	  void testSortAlphabetically8() {
	      List<Chore> chores = new ArrayList<>();
	      chores.add(new Chore("Fold laundry", "Laundry Room", 25, 8.0));
	      chores.add(new Chore("Make bed", "Bedroom", 30, 10.0));
	      chores.add(new Chore("Vacuum stairs", "Hallway", 15, 6.0));

	      List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	      assertEquals("Fold laundry", sortedChores.get(0).getName());
	      assertEquals("Make bed", sortedChores.get(1).getName());
	      assertEquals("Vacuum stairs", sortedChores.get(2).getName());
	  }

	  @Test
	  void testSortAlphabetically9() {
	      List<Chore> chores = new ArrayList<>();
	      chores.add(new Chore("Clean dishes", "Kitchen", 35, 12.0));
	      chores.add(new Chore("Mow lawn", "Backyard", 40, 18.0));
	      chores.add(new Chore("Sort recycling", "Garage", 20, 7.0));

	      List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	      assertEquals("Clean dishes", sortedChores.get(0).getName());
	      assertEquals("Mow lawn", sortedChores.get(1).getName());
	      assertEquals("Sort recycling", sortedChores.get(2).getName());
	  }

	  @Test
	  void testSortAlphabetically10() {
	      List<Chore> chores = new ArrayList<>();
	      chores.add(new Chore("Dust furniture", "Living Room", 30, 9.0));
	      chores.add(new Chore("Set table", "Dining Room", 25, 8.0));
	      chores.add(new Chore("Wash windows", "Outside", 20, 11.0));

	      List<Chore> sortedChores = ChoresUtils.sortAlphabetically(chores);

	      assertEquals("Dust furniture", sortedChores.get(0).getName());
	      assertEquals("Set table", sortedChores.get(1).getName());
	      assertEquals("Wash windows", sortedChores.get(2).getName());
	  }

    @Test
    void testSortByTime1() {
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
    public void testSortByTime2() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Do homework", "Education", 60, 5.0)); // 1 hour chore
        chores.add(new Chore("Clean room", "Household", 30, 3.0)); // 30-minute chore
        chores.add(new Chore("Rake lawn", "Outdoor", 120, 10.0)); // 2 hour chore

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);
        assertEquals("Clean room", sortedChores.get(0).getName());
        assertEquals("Do homework", sortedChores.get(1).getName());
        assertEquals("Rake lawn", sortedChores.get(2).getName());
    }
    
    @Test
    void testSortByTime3() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Sweep Patio", "Outdoor", 20, 8.0));
        chores.add(new Chore("Water Plants", "Gardening", 10, 4.0));
        chores.add(new Chore("Clean Garage", "Household", 45, 15.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Water Plants", sortedChores.get(0).getName());
        assertEquals("Sweep Patio", sortedChores.get(1).getName());
        assertEquals("Clean Garage", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime4() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash Car", "Car Maintenance", 35, 12.0));
        chores.add(new Chore("Fold Laundry", "Laundry", 25, 10.0));
        chores.add(new Chore("Clean Windows", "Household", 60, 20.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Fold Laundry", sortedChores.get(0).getName());
        assertEquals("Wash Car", sortedChores.get(1).getName());
        assertEquals("Clean Windows", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime5() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Mop Floor", "Cleaning", 30, 9.0));
        chores.add(new Chore("Trim Hedges", "Gardening", 40, 18.0));
        chores.add(new Chore("Organize Closet", "Household", 20, 6.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Organize Closet", sortedChores.get(0).getName());
        assertEquals("Mop Floor", sortedChores.get(1).getName());
        assertEquals("Trim Hedges", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime6() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Clean Oven", "Kitchen", 45, 16.0));
        chores.add(new Chore("Paint Fence", "Yard Work", 60, 25.0));
        chores.add(new Chore("Dust Shelves", "Cleaning", 20, 7.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Dust Shelves", sortedChores.get(0).getName());
        assertEquals("Clean Oven", sortedChores.get(1).getName());
        assertEquals("Paint Fence", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime7() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum Carpets", "Cleaning", 40, 14.0));
        chores.add(new Chore("Plant Flowers", "Gardening", 30, 11.0));
        chores.add(new Chore("Wash Windows", "Household", 35, 12.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Plant Flowers", sortedChores.get(0).getName());
        assertEquals("Wash Windows", sortedChores.get(1).getName());
        assertEquals("Vacuum Carpets", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime8() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Clean Fridge", "Kitchen", 25, 9.0));
        chores.add(new Chore("Rake Leaves", "Yard Work", 45, 16.0));
        chores.add(new Chore("Scrub Bathroom", "Cleaning", 30, 11.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Clean Fridge", sortedChores.get(0).getName());
        assertEquals("Scrub Bathroom", sortedChores.get(1).getName());
        assertEquals("Rake Leaves", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime9() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash Dishes", "Kitchen", 20, 7.0));
        chores.add(new Chore("Trim Bushes", "Gardening", 35, 13.0));
        chores.add(new Chore("Dust Furniture", "Household", 30, 10.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Wash Dishes", sortedChores.get(0).getName());
        assertEquals("Dust Furniture", sortedChores.get(1).getName());
        assertEquals("Trim Bushes", sortedChores.get(2).getName());
    }

    @Test
    void testSortByTime10() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Sweep Porch", "Outdoor", 15, 5.0));
        chores.add(new Chore("Clean Grill", "Yard Work", 25, 9.0));
        chores.add(new Chore("Organize Pantry", "Kitchen", 30, 11.0));

        List<Chore> sortedChores = ChoresUtils.sortByTime(chores);

        assertEquals("Sweep Porch", sortedChores.get(0).getName());
        assertEquals("Clean Grill", sortedChores.get(1).getName());
        assertEquals("Organize Pantry", sortedChores.get(2).getName());
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
    public void testSortByPayment3() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash car", "Car Maintenance", 45, 8.0)); // $8 payment
        chores.add(new Chore("Clean garage", "Household", 30, 5.0)); // $5 payment
        chores.add(new Chore("Water plants", "Gardening", 20, 3.0)); // $3 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Water plants", sortedChores.get(0).getName());
        assertEquals("Clean garage", sortedChores.get(1).getName());
        assertEquals("Wash car", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment4() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum living room", "Cleaning", 30, 6.0)); // $6 payment
        chores.add(new Chore("Trim hedges", "Gardening", 40, 7.0)); // $7 payment
        chores.add(new Chore("Dust shelves", "Cleaning", 20, 4.0)); // $4 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Dust shelves", sortedChores.get(0).getName());
        assertEquals("Vacuum living room", sortedChores.get(1).getName());
        assertEquals("Trim hedges", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment5() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash dishes", "Kitchen", 25, 5.0)); // $5 payment
        chores.add(new Chore("Rake leaves", "Yard Work", 45, 8.0)); // $8 payment
        chores.add(new Chore("Organize closet", "Household", 20, 4.0)); // $4 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Organize closet", sortedChores.get(0).getName());
        assertEquals("Wash dishes", sortedChores.get(1).getName());
        assertEquals("Rake leaves", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment6() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Clean oven", "Kitchen", 35, 7.0)); // $7 payment
        chores.add(new Chore("Paint fence", "Yard Work", 60, 12.0)); // $12 payment
        chores.add(new Chore("Dust furniture", "Household", 30, 6.0)); // $6 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Dust furniture", sortedChores.get(0).getName());
        assertEquals("Clean oven", sortedChores.get(1).getName());
        assertEquals("Paint fence", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment7() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash dishes", "Kitchen", 20, 4.0)); // $4 payment
        chores.add(new Chore("Trim bushes", "Gardening", 35, 6.0)); // $6 payment
        chores.add(new Chore("Dust furniture", "Household", 30, 5.0)); // $5 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Wash dishes", sortedChores.get(0).getName());
        assertEquals("Dust furniture", sortedChores.get(1).getName());
        assertEquals("Trim bushes", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment8() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Sweep porch", "Outdoor", 15, 3.0)); // $3 payment
        chores.add(new Chore("Clean grill", "Yard Work", 25, 4.0)); // $4 payment
        chores.add(new Chore("Organize pantry", "Kitchen", 30, 5.0)); // $5 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Sweep porch", sortedChores.get(0).getName());
        assertEquals("Clean grill", sortedChores.get(1).getName());
        assertEquals("Organize pantry", sortedChores.get(2).getName());
    }

    @Test
    public void testSortByPayment9() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Clean fridge", "Kitchen", 25, 4.0)); // $4 payment
        chores.add(new Chore("Rake leaves", "Yard Work", 45, 7.0)); // $7 payment
        chores.add(new Chore("Scrub bathroom", "Cleaning", 30, 6.0)); // $6 payment

        List<Chore> sortedChores = ChoresUtils.sortByPayment(chores);
        assertEquals("Clean fridge", sortedChores.get(0).getName());
        assertEquals("Scrub bathroom", sortedChores.get(1).getName());
        assertEquals("Rake leaves", sortedChores.get(2).getName());
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
    public void ExcelCreation3() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Vacuum living room", "Cleaning", 40, 8.0));
        chores.add(new Chore("Water plants", "Gardening", 25, 4.0));
        chores.add(new Chore("Wash car", "Car Maintenance", 35, 10.0));

        ChoresUtils.createExcelSheet(chores);
    }

    @Test
    public void ExcelCreation4() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Clean oven", "Kitchen", 45, 7.0));
        chores.add(new Chore("Trim hedges", "Gardening", 30, 6.0));
        chores.add(new Chore("Dust furniture", "Household", 20, 5.0));

        ChoresUtils.createExcelSheet(chores);
    }

    @Test
    public void ExcelCreation5() {
        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash dishes", "Kitchen", 25, 5.0));
        chores.add(new Chore("Rake leaves", "Yard Work", 40, 8.0));
        chores.add(new Chore("Organize closet", "Household", 30, 6.0));

        ChoresUtils.createExcelSheet(chores);
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
    
    @Test
    public void testSortByPayment11() {
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
    public void testSortByPayment12() {
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
