package databaseModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSetup {

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
    		+ "FOREIGN KEY (parentUsername) REFERENCES Accounts(username))";

    private static final String CREATE_CHORE_ASSIGNMENT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ChoreAssignment ("
            + "choreID INT,"
            + "childUsername VARCHAR(255),"
            + "PRIMARY KEY (choreID, childUsername),"
            + "FOREIGN KEY (choreID) REFERENCES Chores(id),"
            + "FOREIGN KEY (childUsername) REFERENCES Accounts(username))";

    public static void setupDatabase() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement createDatabaseStatement = connection.prepareStatement(CREATE_DATABASE_SQL);
             PreparedStatement useDatabaseStatement = connection.prepareStatement(USE_DATABASE_SQL);
             PreparedStatement createAccountsTableStatement = connection.prepareStatement(CREATE_ACCOUNTS_TABLE_SQL);
			 PreparedStatement createChildAccountsTableStatement = connection.prepareStatement(CREATE_CHILD_ACCOUNTS_TABLE_SQL);
             PreparedStatement createChoresTableStatement = connection.prepareStatement(CREATE_CHORES_TABLE_SQL);
             PreparedStatement createChoreAssignmentTableStatement = connection.prepareStatement(CREATE_CHORE_ASSIGNMENT_TABLE_SQL)) {

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

            System.out.println("Database setup completed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setupDatabase();
    }
}
