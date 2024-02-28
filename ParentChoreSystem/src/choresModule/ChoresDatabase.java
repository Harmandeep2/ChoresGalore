package choresModule;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChoresDatabase {
    public static void main(String[] args) {
        //making connections
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/choresgalorelogin","root","Koyal-001");
				Statement statement = con.createStatement();){
			String SQL_Table = "CREATE TABLE `choresgalorelogin`.`choretable` ("+
                "`ChoreName` VARCHAR(50) NOT NULL,"+
                "`Category` VARCHAR(50) NOT NULL,"+
                "`Time` DOUBLE NOT NULL,"+
                "`Payment` DOUBLE NOT NULL,"+
                "PRIMARY KEY (`ChoreName`))";
			statement.executeUpdate(SQL_Table);
			System.out.println("SQL Table created");
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
    }
}
