
/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;

public class Server {

	public Statement connect() {
		
		try {
			Connection con;
			Statement stmt;

			// URL Syntax: jdbc:TYPE:machine:port/DB_NAME
			// port omitted in this example

			// for local host
			// String URL = "jdbc:mysql://localhost.njit.edu/UCID";

			// for server
			String UCID = "";
			String passwd = "";
			String URL = "jdbc:mysql://sql2.njit.edu/" + UCID;
			//
			// establishing connection
			// supply URL, user, password
			//

			con = DriverManager.getConnection(URL, UCID, passwd);
			return con.createStatement();
			
			// catch database exceptions and print info
		} catch (SQLException e) {
			System.err.println(" SQL Exceptions \n");
			while (e != null) {
				System.out.println("Error Description: " + e.getMessage());
				System.out.println("SQL State:  " + e.getSQLState());
				System.out.println("Vendor Error Code: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		return null;
	}
	public void removeByID(int docId) {
		
		Statement stmt = connect();
		String query = "DELETE FROM READER WHERE READERID = '" + docId +"';";
		
		stmt.executeQuery(sql)
	}
	
}
