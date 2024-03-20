package accountsModule;

public class Account {
	//Account Class instance variables
	private String username;
	private String password;
	
	/**
	 * Constructor for the Account Class
	 * @param username
	 * @param password
	 */
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Getter method
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter method
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return boolean value if username = username (param) and password = password (param)
	 */
	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
}
