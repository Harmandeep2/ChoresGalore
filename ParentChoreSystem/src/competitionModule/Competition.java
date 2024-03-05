package competitionModule;

import java.util.ArrayList;
import java.util.List;

import accountsModule.ChildAccount;
import accountsModule.ParentAccount;
import choresModule.Chore;


public class Competition {
	
//double rewardPoints1; //storing rewards points 1
//double rewardPoints2; //storing rewards points 2
//private ParentAccount parent;
//private ChildAccount child1; //child account 1 declaration
//private ChildAccount child2; //child account 2 declaration
//private final double CHILD1_HOURS_TILL_NOW;
//private final double CHILD2_HOURS_TILL_NOW;
private Chore chore;
private ArrayList<ChildAccount> children =  new ArrayList<ChildAccount>();


    
    /*
     * Competition constructor for this class
     * @param child1 ChildAccount object representing child 1
     * @param child2 ChildAccount object representing child 2
     */
	 public Competition(ParentAccount parent, ArrayList<ChildAccount> children, Chore chore) {
		 //this.parent = parent;
		 this.children = children;
		 for (int i = 0; i<children.size();i++) {
			 //this.CHILD_HOURS_TILL_NOW = children.get(i).getHoursWorked();
			 parent.assignChore(children.get(i), chore);  //initializing 
		 }
		 this.chore = chore;
	 	}



//	public Competition(ParentAccount parent, ChildAccount child1, ChildAccount child2, Chore chore) {
//		this.parent = parent;
//		this.child1 = child1;  //Assigning child 1 account
//		this.child2 = child2;  //Assigning child 2 account
//		this.CHILD1_HOURS_TILL_NOW = child1.getHoursWorked();    
//		this.CHILD2_HOURS_TILL_NOW = child2.getHoursWorked();    
//		this.chore = chore;
//		parent.assignChore(child1, chore);  //initializing reward points 1 
//		parent.assignChore(child2, chore);  //initializing reward points 2 
//	}
	
	//rewardPoints1 = 0
	//rewardPoints2 = 0
	
	/* 
	 * Method used to calculate competition points for list of chores 
	 * @param chores List of chores for which points need to be calculated
	 * @return total rewardPoints for the given list of chores
	 */
//	public double calculatePoints(List<Chore> chores) {
//		/*
//		 * for loop used to iterate through the chores list and add reward points for child 
//		 */
//		double rewardPoints = 0; 			//Initializing reqardPoints
//		for (Chore chore : chores) {
//			double time = chore.getTime();  //getter used to get time of specific chore
//			rewardPoints += time / 10;      //Adding points based on chore time
//		}
//		return rewardPoints; //returning rewardPoints
//	} 


public ChildAccount getWinner() {
	ChildAccount winner = children.get(0);
	for (int i = 0;i<children.size();i++) {
		for (int j = i+1;j<children.size();j++) {
			if (children.get(j).getHoursWorked() > winner.getHoursWorked()) {
				winner = children.get(i);
			} else if (children.get(i).getHoursWorked() == winner.getHoursWorked()){
				//to implement if its a tie (both have same time)
				winner = children.get(i);
				continue;
			} else {
				winner = children.get(j);
			}
		}
	}
	return winner;
}
    
	
	
	
	
//	/*
//	 * Method used to calculate rewardPoints for each child
//	 */
//	public void calculatePointsForEachChild() {
//		this.rewardPoints1 = child1.getHoursWorked()*6;
//		this.rewardPoints2 = child2.getHoursWorked()*6;
//	}
//	
//	
//	
//	
//	/**
//	 * Getter method used for rewardPoints of child 1      
//	 * @return Reward points of child 1
//	 */
//	public double getRewardPointsOfChild1() {
//		return rewardPoints1;
//	}
//	
//	
//	
//	
//	/*
//	 * 
//	 * Getter method used for rewardPoints of child 2      
//	 * @return Reward points of child 2
//	 */
//	
//	public double getRewardPointsOfChild2() {
//		return rewardPoints2;
//	}
}
