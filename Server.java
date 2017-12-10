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
        System.out.println("Connecting to MySQL\n");

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
                //String URL = "jdbc:mysql://sql2.njit.edu/" + UCID;
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
    
    // Used in RemoveDocument.java
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
    
    // Used in AdminFunctions.java
    public static String getLibName(int adminID) {
    	
    	connectToDB();
    	String n = "";
    	
		try {
            String query = "SELECT LNAME FROM BRANCH WHERE LIBID="+adminID+";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                n = rs.getString("LNAME");
                System.out.println(n);
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
		
		return n;
	}
    
    // Used in AdminFunctions.java
    public static String getLibLoc(int adminID) {
    	
    	connectToDB();
    	String n = "";
    	
		try {
            String query = "SELECT LLOCATION FROM BRANCH WHERE LIBID="+adminID+";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                n = rs.getString("LLOCATION");
                System.out.println(n);
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
		
		return n;
	}
    
    // Used in AdminFunctions.java
    public static void currentReserv(int readerIDReservations) {
    	
    	connectToDB();
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

    // Used in AdminFunctions.java
    public static void topTenBorrowers(int libID) {
    	
    	connectToDB();
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

 // Used in AdminFunctions.java
    public static void topTenBooks(int libID) {
    	
    	connectToDB();
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
	
 // Used in AdminFunctions.java
    public static void topTenBooksYr(int libID) {

        connectToDB();
        // Query to obtain top 10 Books (Year)
        ResultSet topTenBooksYrRS;
        try {
            topTenBooksYrRS = stmt.executeQuery("SELECT DOCID, COUNT(*) FROM BORROWS WHERE YEAR(BDTIME) = 2017 AND LIBID ='" + libID + "' GROUP BY DOCID ORDER BY COUNT(*) DESC LIMIT 10;");
            while (topTenBooksYrRS.next()) {
                String topTenBooksYr = topTenBooksYrRS.getString("DOCID");
                System.out.println(topTenBooksYr); // Outputs top 10 DOCIDs (year)
            }
        } catch (SQLException e) {
            new popupMsg("Error", "Unable to obtain top 10 books (year).");
        }
        
    }

    // Used in AdminFunctions.java
    public static boolean addReader(int addReaderID, String addReaderType, String addReaderName, String addReaderAddress) { // passed in READERID, RTYPE, RNAME, ADDRESS from text field
    	
    	connectToDB();
	    // Query to add Reader by passed in ReaderID
	    ResultSet checkExistsRS;
		try {
			checkExistsRS = stmt.executeQuery("SELECT * FROM READER WHERE READERID = '" + addReaderID + "';");
			if (!checkExistsRS.next()) {
			 stmt.executeUpdate("INSERT INTO READER (READERID, RTYPE, RNAME, ADDRESS) " + "VALUES (" + addReaderID + ",'" + addReaderType + "','" + addReaderName + "','" + addReaderAddress + ",')");
			 System.out.println("Reader added!");
			 new popupMsg("Reader Added", "Added new reader " + addReaderName + " with reader ID " + addReaderID + ".");
			 return true;
			} else {
			 System.out.println("Reader already exists! Cannot add!");
			 new popupMsg("Error", "Reader ID already exists! Please enter another ID.");
			}
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new reader to library.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		}
		return false;
    }
    
    // Used in AdminFunctions.java
    public static boolean addDocCopy(int docID, int copyNO, int libID, String position) {
    	
    	connectToDB();
	    // Query to add Reader by passed in ReaderID
	    ResultSet checkExistsRS;
		try {
			checkExistsRS = stmt.executeQuery("SELECT * FROM COPY AS C WHERE C.COPYNO = '" + copyNO + "' AND C.LIBID = '" + libID + "' AND C.DOCID = '" + docID + "';");
			if (!checkExistsRS.next()) {
				checkExistsRS = stmt.executeQuery("SELECT * FROM DOCUMENT AS D WHERE D.DOCID = '" + docID + "';");
				if (checkExistsRS.next()) {				
				 stmt.executeUpdate("INSERT INTO COPY (DOCID, COPYNO, LIBID, POSITION) " + "VALUES (" + docID + ",'" + copyNO + "','" + libID + "','" + position + ",')");
				 System.out.println("Copy added!");
				 new popupMsg("Reader Added", "Added new copy " + copyNO + " with doc ID " + docID + ".");
				 return true;
				} else {
					new popupMsg("Error", "No Document with ID " + docID + "exists!");
				}
			} else {
			 System.out.println("Copy already exists! Cannot add!");
			 new popupMsg("Error", "Copy already exists! Please enter another.");
			}
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new copy to library.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		}
		return false;
    }
    /**/
}

