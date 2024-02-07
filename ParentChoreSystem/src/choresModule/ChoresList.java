package choresModule;

import java.util.*;

//This class is not used yet, it can be used in future if we need, or else we will delete it.
public class ChoresList {

	private List<Chore> chores;
	
	public ChoresList() {
		chores = new ArrayList<>();
	}
	
	public void addChore(Chore chore) {
		chores.add(chore);
	}
	
	public void removeChore(Chore chore) {
		chores.remove(chore);
	}
	
	public List<Chore> getChores() {
		return chores;
	}
	
	public int getNumberOfChores() {
		return chores.size();
	}
}
