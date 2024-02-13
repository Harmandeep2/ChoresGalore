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
	
		public List<Chore> pointCalculation() {

		int totalpoints = 0;
		
		ArrayList<Double> chore = new ArrayList<>();
		for (double i=0;i<chore.size();i++) {
					chore.add(chore.get(i).getPayment());
				}
		
		double hoursWorked = chores.get(1).getTime()
		double hoursWorked = child.getHoursWorked();
		rewardPoints += (int) (hoursWorked / 10);
		for (Chore chore : chores) {
			int chorePoints = calculateChorePoints(chore);
			totalpoints += chorePoints;
		}
		return totalpoints
		
	}}

}
