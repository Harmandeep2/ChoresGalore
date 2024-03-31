package databaseModule;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static boolean addParentToChild(String parentUsername, String childUsername) {
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE ChildAccounts SET parentUsername = ? WHERE childUsername = ? AND parentUsername IS NULL")) {

	        preparedStatement.setString(1, parentUsername);
	        preparedStatement.setString(2, childUsername);

	        int rowsAffected = preparedStatement.executeUpdate();
	        
	        // Check if any rows were affected (update was successful)
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Return false in case of an exception
	        return false;
	    }
	}
	
	public static boolean removeParentFromChild(String parentUsername, String childUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"UPDATE ChildAccounts SET parentUsername = NULL WHERE childUsername = ? AND parentUsername = ?")) {
			preparedStatement.setString(1, childUsername);
			preparedStatement.setString(2, parentUsername);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
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

	public static double getChildBalance(String childUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ChildAccounts WHERE childUsername = ?")) {
			preparedStatement.setString(1, childUsername);
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
	
	public static double getHoursWorkedByChild(String childUsername) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ChildAccounts WHERE childUsername = ?")) {
			preparedStatement.setString(1, childUsername);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.next())
					return resultSet.getDouble("hoursWorked");
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
    public static void insertChoreDetails(String desc, Date deadline, String priority, int ChoreID) {
    	try (Connection connection = DatabaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO ChoreAdditionalDetails (id, priority, choreDescription, deadline) VALUES (?, ?, ?, ?)")) {
               preparedStatement.setInt(1, ChoreID);
               preparedStatement.setString(2, priority);
               preparedStatement.setString(3, desc);
               preparedStatement.setDate(4, deadline);
               
               preparedStatement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
    }
    
    // get chore deadline so that chore history can be implemented 
    public static Date getChoreDeadline(int choreID) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT deadline FROM ChoreAdditionalDetails WHERE id = ?")) {
            preparedStatement.setInt(1, choreID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDate("deadline");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean isChoreCompletedByDeadline(int choreID) {
	    try (Connection connection = DatabaseConnector.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(
		            "SELECT completedByDeadline FROM ChoreAdditionalDetails WHERE id = ?")) {
	        preparedStatement.setInt(1, choreID);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		        if (resultSet.next()) {
		            return resultSet.getBoolean("completedByDeadline");
		        }
	        }
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }
	    return false;
    }
    
    public static int getChoreCompletedCountOfChild(String childUsername) {
	    try (Connection connection = DatabaseConnector.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(
		            "SELECT COUNT(*) FROM Chores "
		            + "JOIN ChoreAssignment ON Chores.id = ChoreAssignment.choreID "
		            + "WHERE childUsername = ? AND isCompleted = 1")) {           
	        preparedStatement.setString(1, childUsername);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		        if (resultSet.next()) {
		            return resultSet.getInt(1);
		        }
	        }
	    } catch (SQLException e) {
		    e.printStackTrace();
		   return 0;
	    }
	    return 0;
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
                    if(resultSet.getInt("rating") != -1) {
						chore.setRating(resultSet.getInt("rating"));
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
		                if(resultSet.getInt("rating") != -1) {
		                	chore.setRating(resultSet.getInt("rating"));
		                }
		                
		                chores.add(chore);
		        	}
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return chores;
	}
    
    public static String getChoreCompletingChildUsername(int choreID) {
		try(Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chores WHERE id = ?")) {
			preparedStatement.setInt(1, choreID);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.next())
					return resultSet.getString("completedBy");
				else
					return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
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
                if(resultSet.getInt("rating") != -1) {
					chore.setRating(resultSet.getInt("rating"));
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
    
    public static boolean checkIfChoreAlreadyAssignedToChild(int choreID, String childUsername) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM ChoreAssignment WHERE choreID = ? AND childUsername = ?")) {
            preparedStatement.setInt(1, choreID);
            preparedStatement.setString(2, childUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next())
            	return true;
            else
            	return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public static void markChoreAsCompleted(int choreID, String childUsername, boolean completedByDeadline) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Update Chores table
            try (PreparedStatement updateChores = connection.prepareStatement(
                    "UPDATE Chores SET isCompleted = true, completedBy = ? WHERE id = ?")) {
                updateChores.setString(1, childUsername);
            	updateChores.setInt(2, choreID);
                updateChores.executeUpdate();
            }

            // Update ChildAccounts table
            try (PreparedStatement updateChildAccounts = connection.prepareStatement(
                    "UPDATE ChildAccounts " +
                            "SET hoursWorked = hoursWorked + (SELECT time/60 FROM Chores WHERE id = ?) " +
                            "WHERE childUsername = ?")) {
                updateChildAccounts.setInt(1, choreID);
                updateChildAccounts.setString(2, childUsername);
                updateChildAccounts.executeUpdate();
            }
            
            try (PreparedStatement updateChoreAdditionalDetails = connection.prepareStatement(
                    "UPDATE ChoreAdditionalDetails SET completedByDeadline = ? WHERE id = ?")) {
                updateChoreAdditionalDetails.setBoolean(1, completedByDeadline);
                updateChoreAdditionalDetails.setInt(2, choreID);
                updateChoreAdditionalDetails.executeUpdate();
            }
            
            
        }
            catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static void markChoreAsNotCompleted(int choreID, String childUsername, boolean completedByDeadline) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Update Chores table
            try (PreparedStatement updateChores = connection.prepareStatement(
                    "UPDATE Chores SET isCompleted = false, completedBy = ? WHERE id = ?")) {
                updateChores.setString(1, childUsername);
            	updateChores.setInt(2, choreID);
                updateChores.executeUpdate();
            }

            // Update ChildAccounts table
            try (PreparedStatement updateChildAccounts = connection.prepareStatement(
                    "UPDATE ChildAccounts " +
                            "SET hoursWorked = hoursWorked - (SELECT time/60 FROM Chores WHERE id = ?) " +
                            "WHERE childUsername = ?")) {
                updateChildAccounts.setInt(1, choreID);
                updateChildAccounts.setString(2, childUsername);
                updateChildAccounts.executeUpdate();
            }
            
            try (PreparedStatement updateChoreAdditionalDetails = connection.prepareStatement(
                    "UPDATE ChoreAdditionalDetails SET completedByDeadline = ? WHERE id = ?")) {
                updateChoreAdditionalDetails.setBoolean(1, completedByDeadline);
                updateChoreAdditionalDetails.setInt(2, choreID);
                updateChoreAdditionalDetails.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public static void markChoreAsPaid(int choreID, String childUsername) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Update Chores table
            try (PreparedStatement updateChores = connection.prepareStatement(
                    "UPDATE Chores SET isPaid = true WHERE id = ?")) {
                updateChores.setInt(1, choreID);
                updateChores.executeUpdate();
            }

            // Update ChildAccounts table
            try (PreparedStatement updateChildAccounts = connection.prepareStatement(
                    "UPDATE ChildAccounts " +
                            "SET balance = balance + (SELECT payment FROM Chores WHERE id = ?) " +
                            "WHERE childUsername = ?")) {
                updateChildAccounts.setInt(1, choreID);
                updateChildAccounts.setString(2, childUsername);
                updateChildAccounts.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void storeChoreRating(int choreID, int rating) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Chores SET rating = ? WHERE id = ?")) {

            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, choreID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // COMPETITIONS TABLE OPERATIONS
    public static void addCompetition(String competitionName, ParentAccount parent, List<ChildAccount> children, int choreId) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement competitionStatement = connection.prepareStatement(
                    "INSERT INTO Competitions (competitionName, parentUsername, childUsername, choreID) VALUES (?, ?, ?, ?)")) {

                for (ChildAccount child : children) {
                    competitionStatement.setString(1, competitionName);
                    competitionStatement.setString(2, parent.getUsername());
                    competitionStatement.setString(3, child.getUsername());
                    competitionStatement.setInt(4, choreId);
                    competitionStatement.addBatch();
                }

                competitionStatement.executeBatch();
            }

            // Insert records into the ChoreAssignment table for each child and chore
            try (PreparedStatement choreAssignmentStatement = connection.prepareStatement(
                    "INSERT INTO ChoreAssignment (choreID, childUsername) VALUES (?, ?)")) {

                for (ChildAccount child : children) {
                    choreAssignmentStatement.setInt(1, choreId);
                    choreAssignmentStatement.setString(2, child.getUsername());
                    choreAssignmentStatement.addBatch();
                }

                choreAssignmentStatement.executeBatch();
            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean checkCompetitionAlreadyExists(String competitionName) {
	    try(Connection connection = DatabaseConnector.getConnection();
	    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Competitions WHERE competitionName = ?")) {
	    	preparedStatement.setString(1, competitionName);
	    	try(ResultSet resultSet = preparedStatement.executeQuery()) {
	    		return resultSet.next();
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return false;
	}
    
    public static Map<String, String> getCompetitionWinnersForParent(String parentUsername) {
        Map<String, String> competitionWinners = new HashMap<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.competitionName, c.choreID, ch.completedBy FROM Competitions c " +
                     "LEFT JOIN Chores ch ON c.choreID = ch.id" +
					 " WHERE c.parentUsername = ?")) {
        	preparedStatement.setString(1, parentUsername);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String competitionName = resultSet.getString("competitionName");
                    String winner = resultSet.getString("completedBy");

                    if (winner != null && !winner.isEmpty()) {
                        competitionWinners.put(competitionName, winner);
                    } else {
                        competitionWinners.put(competitionName, "Not completed by anyone yet");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competitionWinners;
    }
    
    public static Map<String, String> getCompetitionWinnersForChild(String childUsername) {
        Map<String, String> competitionWinners = new HashMap<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.competitionName, c.choreID, ch.completedBy FROM Competitions c " +
                             "LEFT JOIN Chores ch ON c.choreID = ch.id" +
                             " WHERE c.childUsername = ?")) {
            preparedStatement.setString(1, childUsername);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String competitionName = resultSet.getString("competitionName");
                    String winner = resultSet.getString("completedBy");

                    if (winner != null && !winner.isEmpty()) {
                        competitionWinners.put(competitionName, winner);
                    } else {
                        competitionWinners.put(competitionName, "Not completed by anyone yet");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competitionWinners;
    }

    
    public static List<String> getParticipantsOfCompetitionForChild(String competitionName, String childUsername) {
	    try(Connection connection = DatabaseConnector.getConnection();
	    		PreparedStatement preparedStatement = connection.prepareStatement(
	    		"SELECT * FROM Competitions WHERE choreId IN (SELECT choreId FROM Competitions WHERE childUsername = ?) AND competitionName = ?")) {
	    	preparedStatement.setString(1, childUsername);
	    	preparedStatement.setString(2, competitionName);
	    	try(ResultSet resultSet = preparedStatement.executeQuery()) {
	    		List<String> participants = new ArrayList<>();
	    		while(resultSet.next()) {
	    			participants.add(resultSet.getString("childUsername"));
	    		}
	    		return participants;
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
    }
    
    public static List<String> getParticipantsOfCompetitionForParent(String competitionName, String parentUsername) {
	    try(Connection connection = DatabaseConnector.getConnection();
	    		PreparedStatement preparedStatement = connection.prepareStatement(
	    		"SELECT * FROM Competitions WHERE competitionName = ? AND parentUsername = ?")) {
	    	preparedStatement.setString(1, competitionName);
	    	preparedStatement.setString(2, parentUsername);
	    	try(ResultSet resultSet = preparedStatement.executeQuery()) {
	    		List<String> participants = new ArrayList<>();
	    		while(resultSet.next()) {
	    			participants.add(resultSet.getString("childUsername"));
	    		}
	    		return participants;
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
    }
   
    public static Map<String, Object> getDetailOfSingleChore(int choreId){
    	Map<String, Object> map = new HashMap<>();
    	
    	try(Connection connection = DatabaseConnector.getConnection()){
    		connection.setAutoCommit(false);
    		
    		try(PreparedStatement preparedStatement = connection.prepareStatement(
	    		"SELECT * FROM Chores WHERE id = ?")) {
		    	preparedStatement.setInt(1, choreId);
		    	try(ResultSet resultSet = preparedStatement.executeQuery()) {
		    		if(resultSet.next()) {
		    			map.put("Chore ID", resultSet.getInt("id"));
		    			map.put("Chore Name", resultSet.getString("name"));
		    			map.put("Time", resultSet.getDouble("time"));
		    			map.put("Payment", resultSet.getDouble("payment"));
		    			map.put("isCompleted", resultSet.getBoolean("isCompleted"));
		    			map.put("isPaid", resultSet.getBoolean("isPaid"));
		    			map.put("Rating", resultSet.getInt("rating"));
		    			map.put("Completed By", resultSet.getString("completedBy"));
		    		}
		    		else
		    			return null;
		    	}
	    	}
    		
    		try(PreparedStatement preparedStatement = connection.prepareStatement(
    	    		"SELECT * FROM ChoreAdditionalDetails WHERE id = ?")) {
    		    	preparedStatement.setInt(1, choreId);
    		    	try(ResultSet resultSet = preparedStatement.executeQuery()) {
    		    		if(resultSet.next()) {
    		    			map.put("Chore Description", resultSet.getString("choreDescription"));
    		    			map.put("Priority", resultSet.getString("priority"));
    		    			map.put("Deadline", resultSet.getDate("deadline"));
    		    		}
    		    		else
    		    			return null;
    		    	}
    	    	}
    		
    		return map;
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
    }


    public static List<String> getChildAssignedToChore(int choreID) {
        List<String> assignedChildren = new ArrayList<>();

        String query = "SELECT childUsername FROM ChoreAssignment WHERE choreID = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, choreID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String childUsername = rs.getString("childUsername");
                assignedChildren.add(childUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your exception appropriately
        }

        return assignedChildren;
    }


    public static boolean deleteChore(int choreID) {

    	    try (Connection connection = DatabaseConnector.getConnection()) {
    	        connection.setAutoCommit(false); // Start a transaction

    	        try (PreparedStatement deleteChoreDetails = connection.prepareStatement(
    	                    "DELETE FROM ChoreAdditionalDetails WHERE id = ?")) {
    	            deleteChoreDetails.setInt(1, choreID);
    	            deleteChoreDetails.executeUpdate();
    	        }

    	        try (PreparedStatement deleteChoreAssignments = connection.prepareStatement(
    	                    "DELETE FROM ChoreAssignment WHERE choreID = ?")) {
    	            deleteChoreAssignments.setInt(1, choreID);
    	            deleteChoreAssignments.executeUpdate();
    	        }

    	        // Delete related entries in the competitions table
    	        try (PreparedStatement deleteCompetitions = connection.prepareStatement(
    	                    "DELETE FROM competitions WHERE choreID = ?")) {
    	            deleteCompetitions.setInt(1, choreID);
    	            deleteCompetitions.executeUpdate();
    	        }

    	        try (PreparedStatement deleteChore = connection.prepareStatement(
    	                    "DELETE FROM Chores WHERE id = ?")) {
    	            deleteChore.setInt(1, choreID);
    	            deleteChore.executeUpdate();
    	        }

    	        connection.commit(); // Commit the transaction
    	        return true; // Deletion successful
    	    } catch (SQLException e) {
    	        System.err.println("Error deleting chore: " + e.getMessage());
    	        e.printStackTrace();
    	        return false; // Return false if deletion failed
    	    }
    	}
    

}

    




