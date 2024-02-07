package main;

import java.util.ArrayList;

public class ChoreEditing {
	
	private ArrayList<ParentAccount> chores;
	
	public ChoreEditing(){
		
		chores = new ArrayList<>();
	}
	
	public void addChore(ParentAccount choreName) {
		chores.add(choreName);
	}
	
	public void deleteChore(ParentAccount choreName) {
		chores.remove(choreName);
	}

	public ArrayList<ParentAccount> getChores() {
		return chores;
	}
	
}
