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
	
    /*public static void main(String[] args) {
    	removeByID(1);
    }*/
    
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
                String UCID = "root";
                String passwd = "mySQLroot";
                String URL = "jdbc:mysql://localhost/cs631";
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
			new popupMsg();
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
    
    /**/
    public static void currentReserv(int readerIDReservations) {
	    // Query to obtain current reservations by passed in ReaderID
	    ResultSet readerIDRS;
		try {
			readerIDRS = stmt.executeQuery("SELECT RESUMBER FROM RESERVES WHERE readerID=" + readerIDReservations + ";");
			while (readerIDRS.next()) {
		     int rIDReservations = readerIDRS.getInt("DOCID");
		     System.out.println(rIDReservations);
		    }
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain current reservations.");
		}
    }

    public static void topTenBorrowers(int libID) {
	    // Query to obtain top 10 Borrowers
	    ResultSet topTenReadersRS;
		try {
			topTenReadersRS = stmt.executeQuery("SELECT READERID, COUNT(*) FROM BORROWS WHERE LIBID='" + libID + "' GROUP BY READERID ORDER BY COUNT(*) DESC LIMIT 10;");
			while (topTenReadersRS.next()) {
		     int topTenReaders = topTenReadersRS.getInt("READERID");
		     System.out.println(topTenReaders); // Outputs top 10 READERIDs
		    }
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 readers.");
		}
	    
    }

    public static void topTenBooks(int libID) {
	    // Query to obtain top 10 Books
	    ResultSet topTenBooksRS;
		try {
			topTenBooksRS = stmt.executeQuery("SELECT DOCID, COUNT(*) FROM BORROWS WHERE LIBID='" + libID + "' GROUP BY DOCID ORDER BY COUNT(*) DESC LIMIT 10;");
			while (topTenBooksRS.next()) {
		     String topTenBooks = topTenBooksRS.getString("DOCID");
		     System.out.println(topTenBooks); // Outputs top 10 DOCIDs
		    }
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 books.");
		}
	    
	}

    public static void addReader(int addReaderID, int addReaderType, int addReaderName, int addReaderAddress) { // passed in READERID, RTYPE, RNAME, ADDRESS from text field
	    // Query to add Reader by passed in ReaderID
	    ResultSet checkExistsRS;
		try {
			checkExistsRS = stmt.executeQuery("SELECT * FROM READER WHERE READERID = '" + addReaderID + "';");
			if (!checkExistsRS.next()) {
			 stmt.executeUpdate("INSERT INTO READER (READERID, RTYPE, RNAME, ADDRESS) " + "VALUES (" + addReaderID + ",'" + addReaderType + "','" + addReaderName + "','" + addReaderAddress + ",')");
			 System.out.println("Reader added!");

			} else {
			 System.out.println("Reader already exists! Cannot add!");
			}
		} catch (SQLException e1) {
			new popupMsg("Error", "Unable to add new reader to library.");
		}
    }
    /**/
}

