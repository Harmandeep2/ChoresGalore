package databaseModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSetup {

	private static final String DB_NAME = "ParentChoreSystem";
    private static final String CREATE_DATABASE_SQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
    private static final String USE_DATABASE_SQL = "USE " + DB_NAME;

    private static final String CREATE_ACCOUNTS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Accounts ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "username VARCHAR(255) NOT NULL,"
            + "password VARCHAR(255) NOT NULL,"
            + "accountType ENUM('Parent', 'Child') NOT NULL)";

    private static final String CREATE_CHORES_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Chores ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "name VARCHAR(255) NOT NULL,"
            + "category VARCHAR(255) NOT NULL,"
            + "time DOUBLE NOT NULL,"
            + "payment DOUBLE NOT NULL,"
            + "isPaid BOOLEAN NOT NULL,"
            + "isCompleted BOOLEAN NOT NULL)";

    private static final String CREATE_PARENT_CHILD_RELATIONSHIP_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ParentChildRelationship ("
            + "parentID INT,"
            + "childID INT,"
            + "PRIMARY KEY (parentID, childID),"
            + "FOREIGN KEY (parentID) REFERENCES Accounts(id),"
            + "FOREIGN KEY (childID) REFERENCES Accounts(id))";

    private static final String CREATE_CHORE_ASSIGNMENT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ChoreAssignment ("
            + "choreID INT,"
            + "childID INT,"
            + "PRIMARY KEY (choreID, childID),"
            + "FOREIGN KEY (choreID) REFERENCES Chores(id),"
            + "FOREIGN KEY (childID) REFERENCES Accounts(id))";

    public static void setupDatabase() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement createDatabaseStatement = connection.prepareStatement(CREATE_DATABASE_SQL);
             PreparedStatement useDatabaseStatement = connection.prepareStatement(USE_DATABASE_SQL);
             PreparedStatement createAccountsTableStatement = connection.prepareStatement(CREATE_ACCOUNTS_TABLE_SQL);
             PreparedStatement createChoresTableStatement = connection.prepareStatement(CREATE_CHORES_TABLE_SQL);
             PreparedStatement createParentChildRelationshipTableStatement = connection.prepareStatement(CREATE_PARENT_CHILD_RELATIONSHIP_TABLE_SQL);
             PreparedStatement createChoreAssignmentTableStatement = connection.prepareStatement(CREATE_CHORE_ASSIGNMENT_TABLE_SQL)) {

            // Create the database if not exists
            createDatabaseStatement.executeUpdate();

            // Switch to the created or existing database
            useDatabaseStatement.executeUpdate();

            // Create the 'Accounts' table if not exists
            createAccountsTableStatement.executeUpdate();

            // Create the 'Chores' table if not exists
            createChoresTableStatement.executeUpdate();

            // Create the 'ParentChildRelationship' table if not exists
            createParentChildRelationshipTableStatement.executeUpdate();

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
