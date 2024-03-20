package choresModule;

import java.util.*;

//This class is not used yet, it can be used in future if we need, or else we will delete it.
public class ChoresList {
	
	/**
	 * Makes a list of chores
	 */

	private List<Chore> chores;
	
	/**
	 * Create an array list of chores
	 */
	public ChoresList() {
		chores = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param chore
	 * Adds chore to list
	 */
	public void addChore(Chore chore) {
		chores.add(chore);
	}
	
	/**
	 * 
	 * @param chore
	 * Removes chore from list
	 */
	public void removeChore(Chore chore) {
		chores.remove(chore);
	}
	
	/**
	 * @return
	 * Gets chore list
	 */
	public List<Chore> getChores() {
		return chores;
	}
	
	/**
	 * @return
	 * Gets size of chore list
	 */
	public int getNumberOfChores() {
		return chores.size();
	}
}
