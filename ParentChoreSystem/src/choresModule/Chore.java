package choresModule;

import accountsModule.ChildAccount;

public class Chore {

	private String name;				//case sensitive
	private String category;			//case sensitive
	private double time;			//in minutes
	private double payment;
	private boolean completed;			//status of chore, true = completed, false = incomplete
	private boolean paid;				//status of payment, true = paid, false = unpaid
	
	//Constructors
	public Chore(String name, String category, double time, double payment) {
		this.name = name;
		this.category = category;
		this.time = time;
		this.payment = payment;
		this.completed = false;
		this.paid = false;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getTime() {
	    return time;
	}
	public void setTime(double time) {
	    this.time = time;
	}
	public double getPayment() {
	    return payment;
	}
	public void setPayment(double payment) {
	    this.payment = payment;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void markCompleted() {
		completed = true;
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public void markPaid() {
		paid = true;
	}
	
	@Override
	public String toString() {
		return "Chore [name=" + name + ", category=" + category + ", time=" + time + ", payment=" + payment + "]";
	}

	
	
}
