package databaseModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseConnector {
	
	private static final String CONFIG_FILE_PATH = "src/databaseModule/config.properties";

    public static Connection getConnection() throws SQLException {
    	Properties properties = loadConfig();
    	
		String url = properties.getProperty("db.url");
	    String username = properties.getProperty("db.username");
	    String password = properties.getProperty("db.password");
	
	    try {
	        return DriverManager.getConnection(url, username, password);
	    } 
	    catch (SQLException e) {
	    	throw new RuntimeException("Error connecting to the database", e);
	    }
	}
    
    private static Properties loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
        return properties;
    }

}
