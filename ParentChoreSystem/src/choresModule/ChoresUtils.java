package choresModule;

import java.util.*;

public class ChoresUtils {
	
	
	//public ArrayList<Chore> chores = new ArrayList<Chore>();


	//Might have to make a constructor for this class
	////import all chores
	////import all categories
	
	/*//
	SORTING & FILTERING
	///*
	 * Sort Alphabetically
	 * Sort by amount of time
	 * Sort by payment amount
	 * 
	 * Filter Completed/Not Completed
	 * Filter paid or not
	 * Filter by Category
	 * 
	 */
	
	public static List<Chore> sortAlphabetically(List<Chore> chores) {
	//	//making a string arrayList because collection sort only works on java made objects
	//	ArrayList<String> choresString = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		//using a for loop to convert each element from chores into a string
	//		choresString.add(chores.get(i).toString());
	//	}
	//	//sorting the string arrayList by alphabets
	//    Collections.sort(choresString);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getName));
		return secondaryList;
	}
	
	public static List<Chore> sortByTime(List<Chore> chores) {
		//chore.getTime();
		
		//import 
		
		//return sorted list
	//	ArrayList<Double> choresTime = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresTime.add(chores.get(i).getTime());
	//	}
	//	Collections.sort(choresTime);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getTime));
		return secondaryList;
		
	}
	
	public static List<Chore> sortByPayment(List<Chore> chores) {
		//chore.getPayment();
		
		//import 
		
		//return sorted list
	//	ArrayList<Double> choresPayment = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresPayment.add(chores.get(i).getPayment());
	//	}
	//	Collections.sort(choresPayment);
		
		List<Chore> secondaryList = new ArrayList<>(chores);
		Collections.sort(secondaryList, Comparator.comparing(Chore::getPayment));
		return secondaryList;
	}
	
	public static List<Chore> filterCompleted(List<Chore> chores) {
		//chore.isCompleted();
		
		//import 
		
		//return filtered list
		
		/*We can add an option where if a person clicks on a button
		 * It will display the ones that are completed and vice versa.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<Boolean> choresComplete = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresComplete.add(chores.get(i).isCompleted());
	//	}
	//	Collections.sort(choresComplete);
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).isCompleted()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterNotCompleted(List<Chore> chores) {
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(!chores.get(i).isCompleted()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterPaid(List<Chore> chores) {
		//chore.isPaid();
		
		//import 
	
		//return filtered list
		/*We can add an option where if a person clicks on a button
		 * It will display the ones that are paid and vice versa.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<Boolean> choresPaid = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresPaid.add(chores.get(i).isPaid());
	//	}
	//	Collections.sort(choresPaid);
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).isPaid()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterNotPaid(List<Chore> chores) {
		
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(!chores.get(i).isPaid()) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
	public static List<Chore> filterCategory(String category, List<Chore> chores) {
		//chore.getCatogory();
		
		//import 
		
		//return filtered list
		/*We can add an option where if a person clicks on a button
		 * It will display the specified category.
		 * For that we need some more code classes, and once that is done
		 * we can edit this method by adding an if statement*/
	//	ArrayList<String> choresCategory = new ArrayList<>();
	//	for (int i=0;i<chores.size();i++) {
	//		choresCategory.add(chores.get(i).getCategory());
	//	}
	//	Collections.sort(choresCategory);
	
		List<Chore> secondaryList = new ArrayList<>();
		for(int i = 0; i < chores.size(); i++) {
			if(chores.get(i).getCategory().equals(category)) {
				secondaryList.add(chores.get(i));
			}
		}
		return secondaryList;
	}
	
}
