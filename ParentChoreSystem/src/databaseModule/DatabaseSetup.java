package databaseModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSetup {
	
	/**
	 * Creating strings that allow interactions with the database 
	 * 
	 */

	private static final String DB_NAME = "ParentChoreSystem";
    private static final String CREATE_DATABASE_SQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
    private static final String USE_DATABASE_SQL = "USE " + DB_NAME;

    private static final String CREATE_ACCOUNTS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Accounts ("
            + "username VARCHAR(255) PRIMARY KEY,"
            + "password VARCHAR(255) NOT NULL,"
            + "accountType ENUM('Parent', 'Child') NOT NULL)";
    
    private static final String CREATE_CHILD_ACCOUNTS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ChildAccounts ("
            + "childUsername VARCHAR(255) PRIMARY KEY,"
            + "parentUsername VARCHAR(255) DEFAULT NULL,"
            + "balance DOUBLE DEFAULT 0.0,"
            + "hoursWorked DOUBLE DEFAULT 0.0,"
            + "FOREIGN KEY (childUsername) REFERENCES Accounts(username),"
            + "FOREIGN KEY (parentUsername) REFERENCES Accounts(username) ON DELETE SET NULL)";



    private static final String CREATE_CHORES_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Chores ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "name VARCHAR(255) NOT NULL,"
            + "category VARCHAR(255) NOT NULL,"
            + "time DOUBLE NOT NULL,"
            + "payment DOUBLE NOT NULL,"
            + "parentUsername VARCHAR(255),"
            + "isPaid BOOLEAN NOT NULL,"
            + "isCompleted BOOLEAN NOT NULL,"
            + "completedBy VARCHAR(255) DEFAULT NULL,"
			+ "FOREIGN KEY (completedBy) REFERENCES ChildAccounts(childUsername) ON DELETE SET NULL,"
    		+ "FOREIGN KEY (parentUsername) REFERENCES Accounts(username))";

    private static final String CREATE_CHORE_ASSIGNMENT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ChoreAssignment ("
            + "choreID INT,"
            + "childUsername VARCHAR(255),"
            + "PRIMARY KEY (choreID, childUsername),"
            + "FOREIGN KEY (choreID) REFERENCES Chores(id),"
            + "FOREIGN KEY (childUsername) REFERENCES Accounts(username))";
    
    private static final String CREATE_COMPETITIONS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Competitions ("
    		+ "competitionName VARCHAR(255) NOT NULL,"
    		+ "parentUsername VARCHAR(255) NOT NULL,"
    		+ "childUsername VARCHAR(255) NOT NULL,"
    		+ "choreID INT NOT NULL,"
    		+ "PRIMARY KEY (competitionName, parentUsername, childUsername, choreID),"
    		+ "FOREIGN KEY (parentUsername) REFERENCES Accounts(username),"
    		+ "FOREIGN KEY (childUsername) REFERENCES ChildAccounts(childUsername),"
    		+ "FOREIGN KEY (choreID) REFERENCES Chores(id))";

    //NEW TABLE CREATION
    private static final String CREATE_CHOREADDITIONALDETAILS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ChoreAdditionalDetails ("
    		+ "id INT PRIMARY KEY,"
    		+ "priority ENUM('high','mid','low') NOT NULL,"
    		+ "choreDescription VARCHAR(255) NOT NULL,"
    		+ "deadline DATE NOT NULL,"
    		+ "FOREIGN KEY (id) REFERENCES Chores(id))";
    		
    
    /**
     * Preparing and executing database
     */

    public static void setupDatabase() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement createDatabaseStatement = connection.prepareStatement(CREATE_DATABASE_SQL);
             PreparedStatement useDatabaseStatement = connection.prepareStatement(USE_DATABASE_SQL);
             PreparedStatement createAccountsTableStatement = connection.prepareStatement(CREATE_ACCOUNTS_TABLE_SQL);
			 PreparedStatement createChildAccountsTableStatement = connection.prepareStatement(CREATE_CHILD_ACCOUNTS_TABLE_SQL);
             PreparedStatement createChoresTableStatement = connection.prepareStatement(CREATE_CHORES_TABLE_SQL);
             PreparedStatement createChoreAssignmentTableStatement = connection.prepareStatement(CREATE_CHORE_ASSIGNMENT_TABLE_SQL)) {
        	 PreparedStatement createCompetitionsTableStatement = connection.prepareStatement(CREATE_COMPETITIONS_TABLE_SQL);
        	 PreparedStatement createChoreAdditionalDetails = connection.prepareStatement(CREATE_CHOREADDITIONALDETAILS_TABLE_SQL);
        	
            // Create the database if not exists
            createDatabaseStatement.executeUpdate();

            // Switch to the created or existing database
            useDatabaseStatement.executeUpdate();

            // Create the 'Accounts' table if not exists
            createAccountsTableStatement.executeUpdate();
            
			// Create the 'ChildAccounts' table if not exists
			createChildAccountsTableStatement.executeUpdate();

            // Create the 'Chores' table if not exists
            createChoresTableStatement.executeUpdate();

            // Create the 'ChoreAssignment' table if not exists
            createChoreAssignmentTableStatement.executeUpdate();

			// Create the 'Competitions' table if not exists
			createCompetitionsTableStatement.executeUpdate();
			
			//Create the 'ChoreAdditionalDetails' table if not exists
			createChoreAdditionalDetails.executeUpdate();
			
            System.out.println("Database setup completed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param args
     * 
     * Main function to set up database
     */

    public static void main(String[] args) {
        setupDatabase();
    }
}
