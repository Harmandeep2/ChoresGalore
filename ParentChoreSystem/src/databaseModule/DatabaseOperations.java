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

    /*public static List<Chore> getAllChores() {
        List<Chore> chores = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chores");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Chore chore = new Chore();
                chore.setId(resultSet.getInt("id"));
                chore.setName(resultSet.getString("name"));
                chore.setCategory(resultSet.getString("category"));
                chore.setTime(resultSet.getDouble("time"));
                chore.setPayment(resultSet.getDouble("payment"));
                chore.setPaid(resultSet.getBoolean("isPaid"));
                chore.setCompleted(resultSet.getBoolean("isCompleted"));
                chores.add(chore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chores;
    }*/

    // PARENT CHILD RELATIONSHIP TABLE OPERATIONS

    public static void insertParentChildRelationship(int parentID, int childID) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ParentChildRelationship (parentID, childID) VALUES (?, ?)")) {
            preparedStatement.setInt(1, parentID);
            preparedStatement.setInt(2, childID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CHORE ASSIGNMENT TABLE OPERATIONS

    public static void insertChoreAssignment(int choreID, int childID) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ChoreAssignment (choreID, childID) VALUES (?, ?)")) {
            preparedStatement.setInt(1, choreID);
            preparedStatement.setInt(2, childID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
