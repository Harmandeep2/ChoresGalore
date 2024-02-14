package competitionModule;

import java.util.ArrayList;
import java.util.List;

import accountsModule.ChildAccount;
import choresModule.Chore;


public class Competition {
	
double rewardPoints1; //storing rewards points 1
double rewardPoints2; //storing rewards points 2
public ChildAccount child1; //child account 1 declaration
public ChildAccount child2; //child account 2 declaration
	


    
    /*
     * Competition constructor for this class
     * @param child1 ChildAccount object representing child 1
     * @param child2 ChildAccount object representing child 2
     */
	public Competition(ChildAccount child1, ChildAccount child2) {
		this.child1 = child1;  //Assigning child 1 account
		this.child2 = child2;  //Assigning child 2 account
		rewardPoints1 = 0;     //initializing reward points 1 
		rewardPoints2 = 0;     //initializing reward points 2 
		
	}
	
	
	
	/* 
	 * Method used to calculate competition points for list of chores 
	 * @param chores List of chores for which points need to be calculated
	 * @return total rewardPoints for the given list of chores
	 */
	public double calculatePoints(List<Chore> chores) {
		/*
		 * for loop used to iterate through the chores list and add reward points for child 
		 */
		double rewardPoints = 0; 			//Initializing reqardPoints
		for (Chore chore : chores) {
			double time = chore.getTime();  //getter used to get time of specific chore
			rewardPoints += time / 10;      //Adding points based on chore time
		}
		return rewardPoints; //returning rewardPoints
	}
	
	
	
	
	/*
	 * Method used to calculate rewardPoints for each child
	 */
	public void calculatePointsForEachChild() {
		this.rewardPoints1 = calculatePoints(child1.getChores());
		this.rewardPoints2 = calculatePoints(child2.getChores());
	}
	
	
	
	
	/*
	 * Getter method used for rewardPoints of child 1      
	 * @return Reward points of child 1
	 */
	public double getRewardPointsOfChild1() {
		return rewardPoints1;
	}
	
	
	
	
	/*
	 * 
	 * Getter method used for rewardPoints of child 2      
	 * @return Reward points of child 2
	 */
	
	public double getRewardPointsOfChild2() {
		return rewardPoints2;
	}
}
