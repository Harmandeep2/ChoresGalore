package competitionModule;

import java.util.ArrayList;
import java.util.List;

import accountsModule.ChildAccount;
import choresModule.Chore;


public class Competition {
public int rewardPoints; //storing rewards points 
	
	public Competition() {
		rewardPoints = 0;  //initializing reward points 
	}
	
	/* 
	 * Method used to calculate competition points for list of chores 
	 */
	public int calculatePoints(List<Chore> chores) {
		/*
		 * for loop used to iterate through the chores list and add reward points for child 
		 */
		for (Chore chore : chores) {
			double time = chore.getTime();
			rewardPoints += time / 10;
		}
		return rewardPoints;
	}
	
	/*
	 * getter method used for rewardPoints
	 */
	public int getRewardPoints() {
		return rewardPoints;
	}
}
