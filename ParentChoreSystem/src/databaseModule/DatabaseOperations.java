package databaseModule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import accountsModule.*;
import choresModule.Chore;

public class DatabaseOperations {


    // ACCOUNTS TABLE OPERATIONS

	public static void insertAccount(String username, String password, String accountType) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO Accounts (username, password, accountType) VALUES (?, ?, ?)")) {

	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        preparedStatement.setString(3, accountType);
	        preparedStatement.executeUpdate();

	        if ("child".equalsIgnoreCase(accountType)) {
	            // Insert into ChildAccounts if it is a child account
	            insertChildAccount(username);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static void insertChildAccount(String childUsername) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO ChildAccounts (childUsername) VALUES (?)")) {

	        preparedStatement.setString(1, childUsername);
	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void addParentToChild(String parentUsername, String childUsername) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE ChildAccounts SET parentUsername = ? WHERE childUsername = ?")) {

	        preparedStatement.setString(1, parentUsername);
	        preparedStatement.setString(2, childUsername);
	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean checkIfChildExists(String childUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ChildAccounts WHERE childUsername = ?")) {
			preparedStatement.setString(1, childUsername);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkIfParentExists(String parentUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts WHERE username = ? AND accountType = 'Parent'")) {
			preparedStatement.setString(1, parentUsername);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<ChildAccount> getAllChildrenofParent(String parentUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ChildAccounts WHERE parentUsername = ?")) {
			preparedStatement.setString(1, parentUsername);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				List<ChildAccount> children = new ArrayList<>();
				while(resultSet.next()) {
					ChildAccount child = new ChildAccount(resultSet.getString("childUsername"),"");
					child.setBalance(resultSet.getDouble("balance"));
					child.setHoursWorked(resultSet.getDouble("hoursWorked"));
					
					children.add(child);
				}
				return children;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static double getChildBalance(String parentUsername, String childUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ChildAccounts WHERE parentUsername = ? AND childUsername = ?")) {
			preparedStatement.setString(1, parentUsername);
			preparedStatement.setString(2, childUsername);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.next())
					return resultSet.getDouble("balance");
				else
					return 0.0;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0.0;
	}
	
    public static List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Account account;
            	if ("child".equalsIgnoreCase(resultSet.getString("accountType"))) {
					account = new ChildAccount(resultSet.getString("username"),
							resultSet.getString("password"));
				}
            	else {
            		account = new ParentAccount(resultSet.getString("username"),
							resultSet.getString("password"));
            	}
            	
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // CHORES TABLE OPERATIONS

    public static void insertChore(Chore chore, String parentUsername) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Chores (name, category, time, payment, parentUsername, isPaid, isCompleted) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, chore.getName());
            preparedStatement.setString(2, chore.getCategory());
            preparedStatement.setDouble(3, chore.getTime());
            preparedStatement.setDouble(4, chore.getPayment());
            preparedStatement.setString(5, parentUsername);
            preparedStatement.setBoolean(6, chore.isPaid());
            preparedStatement.setBoolean(7, chore.isCompleted());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Chore> getAllChoresofChild(String childUsername) {
        List<Chore> chores = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.* FROM Chores c " +
                     "JOIN ChoreAssignment ca ON c.id = ca.choreID " +
                     "WHERE ca.childUsername = ?")) {

            preparedStatement.setString(1, childUsername);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	while (resultSet.next()) {
                    Chore chore = new Chore(resultSet.getString("name"),
                    		resultSet.getString("category"),
                    		resultSet.getDouble("time"),
                    		resultSet.getDouble("payment"));
                    chore.setId(resultSet.getInt("id"));
                    if(resultSet.getBoolean("isPaid")) {
    					chore.markPaid();
    				}
                    if(resultSet.getBoolean("isCompleted")) {
                    	chore.markCompleted();
                    }
                    
                    chores.add(chore);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chores;
    }
    
    public static List<Chore> getAllChoresofParent(String parentUsername) {
		 List<Chore> chores = new ArrayList<>();

		    try (Connection connection = DatabaseConnector.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "SELECT * FROM Chores WHERE parentUsername = ?")) {

		        preparedStatement.setString(1, parentUsername);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		        	while (resultSet.next()) {
		                Chore chore = new Chore(resultSet.getString("name"),
		                		resultSet.getString("category"),
		                		resultSet.getDouble("time"),
		                		resultSet.getDouble("payment"));
		                chore.setId(resultSet.getInt("id"));
		                chore.setParentUsername(resultSet.getString("parentUsername"));
		                if(resultSet.getBoolean("isPaid")) {
							chore.markPaid();
						}
		                if(resultSet.getBoolean("isCompleted")) {
		                	chore.markCompleted();
		                }
		                
		                chores.add(chore);
		        	}
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return chores;
	}

    public static List<Chore> getAllChores() {
        List<Chore> chores = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chores");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Chore chore = new Chore(resultSet.getString("name"),
                		resultSet.getString("category"),
                		resultSet.getDouble("time"),
                		resultSet.getDouble("payment"));
                chore.setId(resultSet.getInt("id"));
                if(resultSet.getBoolean("isPaid")) {
					chore.markPaid();
				}
                if(resultSet.getBoolean("isCompleted")) {
                	chore.markCompleted();
                }
                
                chores.add(chore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chores;
    }

    // CHORE ASSIGNMENT TABLE OPERATIONS

    public static void insertChoreAssignment(int choreID, String childUsername) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ChoreAssignment (choreID, childUsername) VALUES (?, ?)")) {
            preparedStatement.setInt(1, choreID);
            preparedStatement.setString(2, childUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
