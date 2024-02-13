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
	
	public int calculatePoints(List<Chore> chores) {
		for (Chore chore : chores) {
			double time = chore.getTime();
			rewardPoints += time / 10;
		}
		return rewardPoints;
	}
	
	public int getRewardPoints() {
		return rewardPoints;
	}
}
