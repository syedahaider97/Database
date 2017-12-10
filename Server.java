/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;
import java.util.Arrays;
class Server {
	private static Connection con;
    private static Statement stmt;
	
 
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
                String passwd = "root";
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
    
    // Used in AdminPortal.java
    public static boolean libExists(int libID) {
    	
    	connectToDB();
    	String n = "";
    	
		try {
            String query = "SELECT LIBID FROM BRANCH WHERE LIBID="+libID+";";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
            	new popupMsg("Error", "Invalid ID or password entered.");
                return false;
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
		
		return true;
	}
    
    // Used in ReaderPortal.java
    public static boolean readerExists(int readID) {
    	
    	connectToDB();
    	String n = "";
    	
		try {
            String query = "SELECT READERID FROM READER WHERE READERID="+readID+";";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
            	new popupMsg("Error", "Invalid Card Number entered.");
                return false;
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
		
		return true;
	}
    
    // Used in ReaderFunctions.java
    public static String getRName(int readID) {
    	
    	connectToDB();
    	String n = "";
    	
		try {
            String query = "SELECT RNAME FROM READER WHERE READERID="+readID+";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                n = rs.getString("RNAME");
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
    public static boolean addDocCopy(int docID, int libID, String position) {
    	
    	connectToDB();
	    // Query to add Reader by passed in ReaderID
	    ResultSet checkExistsRS;
		try {
			checkExistsRS = stmt.executeQuery("SELECT * FROM DOCUMENT AS D WHERE D.DOCID = '" + docID + "';");
			if (checkExistsRS.next()) {			
				
				// Figure out new Copy Number
				String query = "SELECT COPYNO FROM COPY WHERE LIBID='"+libID+"' AND DOCID='"+docID+"';";
	            ResultSet rs = stmt.executeQuery(query);
	            System.out.println("Current copies:");
	            int lastCopyNum = 0;
	            while (rs.next()) {
	                lastCopyNum = rs.getInt("COPYNO");
	                System.out.println(lastCopyNum);
	            }
				
			 stmt.executeUpdate("INSERT INTO COPY (DOCID, COPYNO, LIBID, POSITION) " + "VALUES (" + docID + ",'" + ++lastCopyNum + "','" + libID + "','" + position + ",')");
			 System.out.println("Copy added!");
			 new popupMsg("Reader Added", "Added new copy " + lastCopyNum + " with doc ID " + docID + ".");
			 return true;
			} else {
				new popupMsg("Error", "No Document with ID '" + docID + "' exists!");
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



	//Used in removeDocument.java
	public static void removeByID(int docId) {
		connectToDB();
		String query = "DELETE FROM READER WHERE READERID = " + docId + ";";
		System.out.println(query);
		try {
			stmt.execute(query);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Used in ReaderFunctions.java
	public static Object[][] getReservationData(int readerId) {
		connectToDB();
		Object rtn[][] = new Object[30][4];
		String query = "SELECT D.TITLE, R.DTIME "
				+ "FROM DOCUMENT D, RESERVES R, COPY C "
				+ "WHERE C.DOCID = R.DOCID AND C.LIBID = R.LIBID AND C.COPYNO = R.COPYNO AND R.READERID = " + readerId + " AND C.DOCID = D.DOCID;";
		int i = 0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object entry[] = {rs.getString("TITLE"),"Reserved","$0",rs.getTimestamp("DTIME")};
				rtn[i++] = entry;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query2 = "SELECT D.TITLE ,B.BDTIME "
				+ "FROM DOCUMENT D, BORROWS B, COPY C "
				+ "WHERE C.DOCID = B.DOCID AND C.LIBID = B.LIBID AND C.COPYNO = B.COPYNO AND C.DOCID = D.DOCID AND B.READERID = " + readerId +";";
		try {
			ResultSet rs = stmt.executeQuery(query2);
			while(rs.next()) {
				Object entry[] = {rs.getString("TITLE"),"Borrowed","$2",rs.getTimestamp("BDTIME")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
		
	}
	//Used in ReaderFunctions
	public static Object[][] searchByDocId(String docId) {
		
		connectToDB();
		Object[][] rtn = new Object[30][3];
		int i = 0;
		
		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID "
				+ "WHERE D.DOCID = " + docId + ";";
		
		try {
			Integer.parseInt(docId);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getString("TITLE"),rs.getString("PUBNAME")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to search for document.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		} catch (Exception e) {
			new popupMsg("Error","Please enter a valid Document ID");
		}
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
	}
	//Used in ReaderFunctions.java
	public static Object[][] searchByTitle(String title) {

		connectToDB();
		Object rtn[][] = new Object[30][3];
		int i = 0;
		
		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID "
				+ "WHERE D.TITLE = '" + title + "';";
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getString("TITLE"),rs.getString("PUBNAME")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to search for document.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		} 
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
	}

	public static Object[][] searchByPublisher(String publisher) {

		connectToDB();
		Object rtn[][] = new Object[30][3];
		int i = 0;
		
		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID "
				+ "WHERE P.PUBNAME = '" + publisher + "';";
		
		try {

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getString("TITLE"),rs.getString("PUBNAME")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to search for document.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		} 
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
		
	}
}
