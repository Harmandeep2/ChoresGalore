package choresModule;


public class Chore {
	//instance variables
	private int id;
	private String name;				//case sensitive
	private String category;			//case sensitive
	private double time;			//in minutes
	private double payment;
	private String parentUsername;
	private boolean completed;			//status of chore, true = completed, false = incomplete
	private boolean paid;				//status of payment, true = paid, false = unpaid
	private int rating;               //Rating of chore, default = -1, range 1-5
	

	/**
	 * Constructor
	 * @param name
	 * @param category
	 * @param time
	 * @param payment
	 * @param rating
	 */
	public Chore(String name, String category, double time, double payment) {
		this.name = name;
		this.category = category;
		this.time = time;
		this.payment = payment;
		this.completed = false;
		this.paid = false;
		this.rating = -1;
	}
	
	//Getters and Setters
	
	/**
	 * getId method for choreID
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setID of chore
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * getName of chore
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setName for chore
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getCategory of chore
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * setCategory of chore
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * getTime required to finish chore
	 * @return time
	 */
	public double getTime() {
	    return time;
	}
	
	/**
	 * setTime for chore
	 * @param time
	 */
	public void setTime(double time) {
	    this.time = time;
	}
	
	/**
	 * getPayment for chore
	 * @return payment
	 */
	public double getPayment() {
	    return payment;
	}
	
	/**
	 * setPaymeny for chore
	 * @param payment
	 */
	public void setPayment(double payment) {
	    this.payment = payment;
	}
	
	/**
	 * get username of assigned parent
	 * @return parentUsername
	 */
	public String getParentUsername() {
		return parentUsername;
	}

	/**
	 * set name for assigned parent
	 * @param parentUsername
	 */
	public void setParentUsername(String parentUsername) {
		this.parentUsername = parentUsername;
	}
	
	/**
	 * checks if chore completed
	 * @return completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	public void markCompleted() {
		completed = true;
	}
	
	/**
	 * isPaid method to make sure a chore is paid
	 * @return paid
	 */
	public boolean isPaid() {
		return paid;
	}

	public void markPaid() {
		paid = true;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Chore [name=" + name + ", category=" + category + ", time=" + time + ", payment=" + payment + ", rating= "+ rating + "]";
	}

	

	
	
}
