package databaseModule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import accountsModule.Account;
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

    /*public static List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Accounts");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setAccountType(resultSet.getString("accountType"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }*/

    // CHORES TABLE OPERATIONS

    public static void insertChore(Chore chore) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Chores (name, category, time, payment, isPaid, isCompleted) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, chore.getName());
            preparedStatement.setString(2, chore.getCategory());
            preparedStatement.setDouble(3, chore.getTime());
            preparedStatement.setDouble(4, chore.getPayment());
            preparedStatement.setBoolean(5, chore.isPaid());
            preparedStatement.setBoolean(6, chore.isCompleted());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    // PARENT CHILD RELATIONSHIP TABLE OPERATIONS

    public static void insertParentChildRelationship(String parentUsername, String childUsername) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ParentChildRelationship (parentUsername, childUsername) VALUES (?, ?)")) {
            preparedStatement.setString(1, parentUsername);
            preparedStatement.setString(2, childUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
