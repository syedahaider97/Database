/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    
    // Used in ReaderFunctions.java
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
    public static void searchDoc(int docID, int libID, int copyNo) {

        connectToDB();
        // Query to Search Documents
        ResultSet searchDocCopyRS;
        ResultSet searchDocBorrowsRS;
        ResultSet searchDocReservesRS;
        try {
            searchDocCopyRS = stmt.executeQuery("SELECT * FROM COPY WHERE DOCID = " + docID + " AND COPYNO = " + copyNo + "AND LIBID = " + libID + ";");
            searchDocBorrowsRS = stmt.executeQuery("SELECT * FROM BORROWS WHERE DOCID = " + docID + " AND COPYNO = " + copyNo + "AND LIBID = " + libID + ";");
            searchDocReservesRS = stmt.executeQuery("SELECT * FROM RESERVES WHERE DOCID = " + docID + " AND COPYNO = " + copyNo + "AND LIBID = " + libID + ";");

            while (searchDocCopyRS.next()) {
                String searchDocCopy = searchDocCopyRS.getString("DOCID");
                System.out.println(searchDocCopy); // Outputs Search Results for Document Copies. If empty, doesn't exist.
            }
            while (searchDocBorrowsRS.next()) {
                String searchDocBorrows = searchDocBorrowsRS.getString("DOCID");
                System.out.println(searchDocBorrows); // Outputs Search Results for Documents Borrowed
            }
            while (searchDocReservesRS.next()) {
                String searchDocReserves = searchDocReservesRS.getString("DOCID");
                System.out.println(searchDocReserves); // Outputs Search Results for Documents Reserves
            }

        } catch (SQLException e) {
            new popupMsg("Error", "Unable to search for documents.");
        }

    }

	// Used in AdminFunctions.java
	public static Object[][] topTenBorrowers(int libID) {

		connectToDB();
		// Query to obtain top 10 Borrowers
		Object[][] result = new Object[10][2];
		int i = 0;
		ResultSet topTenReadersRS;
		try {
			topTenReadersRS = stmt.executeQuery("SELECT R.RNAME, COUNT(*) FROM BORROWS AS B, READER AS R WHERE B.LIBID='"+libID+"' AND B.READERID=R.READERID GROUP BY R.READERID ORDER BY COUNT(*) LIMIT 10;");
			while (topTenReadersRS.next()) {
				Object entry[] = {topTenReadersRS.getString("RNAME"),topTenReadersRS.getInt("COUNT(*)")};
				result[i++] = entry;
			}
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 readers.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		}

		return result;
	}

	// Used in AdminFunctions.java
	public static Object[][] topTenBooks(int libID) {

		connectToDB();
		// Query to obtain top 10 Books
		Object[][] result = new Object[10][1];
		int i = 0;
		ResultSet topTenBooksRS;
		try {
			topTenBooksRS = stmt.executeQuery("SELECT D.TITLE, D.DOCID FROM DOCUMENT AS D WHERE EXISTS (SELECT B.DOCID, COUNT(*) FROM BORROWS AS B WHERE B.LIBID='" + libID + "' AND D.DOCID=B.DOCID GROUP BY B.DOCID ORDER BY COUNT(*)) LIMIT 10;");
			while (topTenBooksRS.next()) {
				Object entry[] = {topTenBooksRS.getString("TITLE"),""};
				result[i++] = entry;
			}
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 books.");
		}

		return result;
	}
	
	public static String getDate() {
		// Get the current date
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = sdf.format(cal.getTime());
		return date;
	}
	
	// Used in AdminFunctions.java
	public static Object[][] topTenBooksYr(int libID) {

		connectToDB();
		// Query to obtain top 10 Books (Year)
		String year = getDate().substring(0, 4);
		System.out.println(year);
		
		Object[][] result = new Object[10][1];
		int i = 0;
		ResultSet topTenBooksYrRS;
		try {
			topTenBooksYrRS = stmt.executeQuery("SELECT D.TITLE, B2.BDTIME FROM DOCUMENT AS D, BORROWS AS B2 WHERE EXISTS (SELECT B.DOCID, COUNT(*) FROM BORROWS AS B WHERE B.LIBID='" + libID + "' AND D.DOCID=B.DOCID AND B.DOCID=B2.DOCID GROUP BY B.DOCID ORDER BY COUNT(*)) LIMIT 10;");
			while (topTenBooksYrRS.next()) {
				Object entry[] = {topTenBooksYrRS.getString("TITLE"), topTenBooksYrRS.getString("BDTIME")};
			
				// Check if borrow was made in 2017. If it was, add to result
				if (((String) entry[1]).substring(0,4).compareTo(year) == 0) {
					result[i++] = entry;
				}
			}
			
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 books (year).");
		}

		return result;
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
			 new popupMsg("Copy Added", "Added new copy " + lastCopyNum + " with doc ID " + docID + ".");
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
	public static boolean removeByID(String docId) {
		connectToDB();
		String count  = "SELECT COUNT(*) "
				+ " FROM DOCUMENT WHERE DOCID = " + docId + ";";
		String query = "DELETE FROM DOCUMENT WHERE DOCID = " + docId + ";";
		
		try {
			Integer.parseInt(docId);
			ResultSet countSet = stmt.executeQuery(count);
			
			if (countSet.next() && countSet.getInt("COUNT(*)") == 0) {
				throw new Exception();
			}
			
			stmt.execute(query);
			stmt.close();
			con.close();
			return true;
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
			new popupMsg("Error","Please enter a valid, existing Document ID to remove");
		}
		return false;
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
	
	//Used for ReaderFunctions.java
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
	//Used for ViewDocument.java, found in Modify.java
	public static Object[][] viewAllDocs() {
		connectToDB();
		Object[][] rtn = new Object[30][4];
		int i = 0;
		String query = "SELECT D.DOCID, D.TITLE, D.PDATE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getString("TITLE"),rs.getString("PUBNAME"),rs.getTimestamp("PDATE")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to gather documents.");
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
	
	//Used for AdminFunctions.java
	public static Object[][] viewAllCopies(int libID) {
		connectToDB();
		Object[][] rtn = new Object[30][4];
		int i = 0;
		String query = "SELECT C.DOCID, C.COPYNO, C.POSITION "
				+ "FROM COPY C WHERE LIBID = '"+libID+"';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getInt("COPYNO"),rs.getString("POSITION")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to gather copies.");
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
	
	//Used in AdminFunctions.java
	public static Object[][] searchByCopyID(String copyNO, int libID) {
		
		connectToDB();
		Object[][] rtn = new Object[30][3];
		int i = 0;
		
		String query = "SELECT C.DOCID, C.COPYNO, C.POSITION "
				+ "FROM COPY C WHERE LIBID = '"+libID+"' AND COPYNO='"+copyNO+"';";
		
		try {
			Integer.parseInt(copyNO);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Object[] entry = {rs.getInt("DOCID"),rs.getInt("COPYNO"),rs.getString("POSITION")};
				rtn[i++] = entry;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to search for copies.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
		} catch (Exception e) {
			new popupMsg("Error","Please enter a valid Copy Number");
		}
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
	}
		//For Use with ReaderFunction -> Reserve
	public static boolean reserve(int readerId, String docId, String libId) {

		connectToDB();
		int resNumber = 0;
		int copyno = 0;
		try {
			//Integer.parseInt(docId); Integer.parseInt(libId);

			String resCountQuery = "SELECT COUNT(*) FROM RESERVES;";
			ResultSet rs = stmt.executeQuery(resCountQuery);
			if(rs.next()) {
				resNumber = rs.getInt("COUNT(*)") + 1;
			}
			
			ArrayList<Integer> copiesTaken = new ArrayList<Integer>();
			String resCountCopy = "SELECT COPYNO FROM RESERVES WHERE LIBID = " + libId + " AND DOCID = " + docId +";";
			ResultSet rs1 = stmt.executeQuery(resCountCopy);
			while(rs1.next()) {
				copiesTaken.add(rs1.getInt("COPYNO"));
			}
			
			String copyQuery = "SELECT COPYNO FROM COPY WHERE LIBID = " + libId + " AND DOCID = " + docId +";";
			ResultSet rs2 = stmt.executeQuery(copyQuery);
			while(rs2.next()) {
				if(!copiesTaken.contains(rs2.getInt("COPYNO"))) {
					copyno = rs2.getInt("COPYNO");
					break;
				}
			}
			if(copyno == 0) {
				 new popupMsg("Error","No more copies left. Please check again later");
				 return false;
			} else{
				String date = getDate();
				System.out.println(date);
				String query = String.format("INSERT INTO RESERVES (RESUMBER,READERID,DOCID,COPYNO,LIBID,DTIME) "
										    +"VALUES (%d,%d,%s,%d,%s,'%s');",resNumber,readerId,docId,copyno,libId,date);
				System.out.println(query);
				stmt.execute(query);
			}
			stmt.close();
			con.close();
			return true;
		} catch(SQLException e) {
			new popupMsg("Error", "Unable to process request.");
			System.err.println(" SQL Exceptions \n");
            while (e != null) {
                System.out.println("Error Description: " + e.getMessage());
                System.out.println("SQL State:  " + e.getSQLState());
                System.out.println("Vendor Error Code: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            } 
		} catch (Exception e) {
			e.printStackTrace();
			new popupMsg("Error","Please enter valid Document Id and Library Id");
		}
		return false;
		
	}
	//For Use with ReaderFunction -> Borrow.java
	public static void borrow(int readerId, String docId, String libId) {
		// TODO Auto-generated method stub
		
	}
}
