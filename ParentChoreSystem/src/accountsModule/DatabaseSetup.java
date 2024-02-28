package accountsModule;
import java.sql.*;
public class DatabaseSetup {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {	
        //making connections
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/choresgalorelogin","root","Koyal-001");
				Statement statement = con.createStatement();){
			String SQL_Table = "CREATE TABLE `login` ("+
					  "`Username` varchar(50) NOT NULL,"+
					  "`Password` varchar(50) NOT NULL,"+
					  "`AccountType` enum('Parent','Child') NOT NULL,"+
					  "PRIMARY KEY (`Username`))";
			statement.executeUpdate(SQL_Table);
			System.out.println("SQL Table created");
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}