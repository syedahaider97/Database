/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;
class Server {
	private static Connection con;
    private static Statement stmt;
	
    public static void main(String[] args) {
    	removeByID(1);
    }
    
    private static void connectToDB() {
        System.out.println("Hello from MySQL!\n");

        //loading driver, need to catch exceptions
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: ");
            System.err.println("Message: " + e.getMessage());
        }

        try {
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
                stmt = con.createStatement();


        // catch database exceptions and print info
        } catch(SQLException e) {
        	System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        }
    }
    
    public static void removeByID(int docId) {
    	
    	connectToDB();
		
		try {
			// AUTHOR
            System.out.println("AUTHOR");
            String query = "SELECT * FROM AUTHOR;";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("AUTHORID\tANAME");
            while (rs.next()) {
                int s = rs.getInt("AUTHORID");
                String n = rs.getString("ANAME");
                System.out.println(s + "\t" + n);
            }
            //close resources
            stmt.close(); con.close();
		} catch (SQLException e) {
			//new popupMsg();
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		}
	}
}

