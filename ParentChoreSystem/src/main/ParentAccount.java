package main;

import java.util.List;

public class ParentAccount {
	
	private String choreName;
	private double payment;
	private double test;
	
	
	public ParentAccount(String choreName) {
		this.choreName= choreName;
		this.payment = 0.0;
		
	}

	public String getChoreName() {
		return choreName;
	}

	public double getPayment() {
		return payment;
	}
	

}
