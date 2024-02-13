package competitionModule;

import java.util.List;

import accountsModule.ChildAccount;
import choresModule.Chore;


public class Competition {
	
	public int rewardPoints; //storing rewards points 
	
	public Competition() {
		rewardPoints = 0;  //initializing reward points 
	}
	
	public int pointCalculation(ChildAccount child, List<Chore> completedChores) {
		int totalpoints = 0;
		for (Chore chore : completedChores) {
			int completionTime = completedChores :: getTime();
		}
	}

}
