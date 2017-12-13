
/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Server {
	private static Connection con;
	private static Statement stmt;

	private static void connectToDB() {
		System.out.println("Connecting to MySQL\n");

		// loading driver, need to catch exceptions
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
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
			// String URL = "jdbc:mysql://sql2.njit.edu/" + UCID;
			//
			// establishing connection
			// supply URL, user, password
			//

			con = DriverManager.getConnection(URL, UCID, passwd);
			stmt = con.createStatement();

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
	}

	// Used in AdminPortal.java
	public static boolean libExists(int libID) {

		connectToDB();
		String n = "";

		try {
			String query = "SELECT LIBID FROM BRANCH WHERE LIBID=" + libID + ";";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				new popupMsg("Error", "Invalid ID or password entered.");
				return false;
			}
			// close resources
			stmt.close();
			con.close();
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
			String query = "SELECT READERID FROM READER WHERE READERID=" + readID + ";";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				new popupMsg("Error", "Invalid Card Number entered.");
				return false;
			}
			// close resources
			stmt.close();
			con.close();
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
			String query = "SELECT RNAME FROM READER WHERE READERID=" + readID + ";";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				n = rs.getString("RNAME");
				System.out.println(n);
			}
			// close resources
			stmt.close();
			con.close();
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
			String query = "SELECT LNAME FROM BRANCH WHERE LIBID=" + adminID + ";";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				n = rs.getString("LNAME");
				System.out.println(n);
			}
			// close resources
			stmt.close();
			con.close();
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
			String query = "SELECT LLOCATION FROM BRANCH WHERE LIBID=" + adminID + ";";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				n = rs.getString("LLOCATION");
				System.out.println(n);
			}
			// close resources
			stmt.close();
			con.close();
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
			readerIDRS = stmt
					.executeQuery("SELECT RESUMBER FROM RESERVES WHERE readerID=" + readerIDReservations + ";");
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
			searchDocCopyRS = stmt.executeQuery("SELECT * FROM COPY WHERE DOCID = " + docID + " AND COPYNO = " + copyNo
					+ "AND LIBID = " + libID + ";");
			searchDocBorrowsRS = stmt.executeQuery("SELECT * FROM BORROWS WHERE DOCID = " + docID + " AND COPYNO = "
					+ copyNo + "AND LIBID = " + libID + ";");
			searchDocReservesRS = stmt.executeQuery("SELECT * FROM RESERVES WHERE DOCID = " + docID + " AND COPYNO = "
					+ copyNo + "AND LIBID = " + libID + ";");

			while (searchDocCopyRS.next()) {
				String searchDocCopy = searchDocCopyRS.getString("DOCID");
				System.out.println(searchDocCopy); // Outputs Search Results for
													// Document Copies. If
													// empty, doesn't
													// exist.
			}
			while (searchDocBorrowsRS.next()) {
				String searchDocBorrows = searchDocBorrowsRS.getString("DOCID");
				System.out.println(searchDocBorrows); // Outputs Search Results
														// for Documents
														// Borrowed
			}
			while (searchDocReservesRS.next()) {
				String searchDocReserves = searchDocReservesRS.getString("DOCID");
				System.out.println(searchDocReserves); // Outputs Search Results
														// for Documents
														// Reserves
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
			topTenReadersRS = stmt
					.executeQuery("SELECT R.RNAME, COUNT(*) FROM BORROWS AS B, READER AS R WHERE B.LIBID='" + libID
							+ "' AND B.READERID=R.READERID GROUP BY R.READERID ORDER BY COUNT(*) LIMIT 10;");
			while (topTenReadersRS.next()) {
				Object entry[] = { topTenReadersRS.getString("RNAME"), topTenReadersRS.getInt("COUNT(*)") };
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
			topTenBooksRS = stmt.executeQuery(
					"SELECT D.TITLE, D.DOCID FROM DOCUMENT AS D WHERE EXISTS (SELECT B.DOCID, COUNT(*) FROM BORROWS AS B WHERE B.LIBID='"
							+ libID + "' AND D.DOCID=B.DOCID GROUP BY B.DOCID ORDER BY COUNT(*)) LIMIT 10;");
			while (topTenBooksRS.next()) {
				Object entry[] = { topTenBooksRS.getString("TITLE"), "" };
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
			topTenBooksYrRS = stmt.executeQuery(
					"SELECT D.TITLE, B2.BDTIME FROM DOCUMENT AS D, BORROWS AS B2 WHERE EXISTS (SELECT B.DOCID, COUNT(*) FROM BORROWS AS B WHERE B.LIBID='"
							+ libID
							+ "' AND D.DOCID=B.DOCID AND B.DOCID=B2.DOCID GROUP BY B.DOCID ORDER BY COUNT(*)) LIMIT 10;");
			while (topTenBooksYrRS.next()) {
				Object entry[] = { topTenBooksYrRS.getString("TITLE"), topTenBooksYrRS.getString("BDTIME") };

				// Check if borrow was made in 2017. If it was, add to result
				if (((String) entry[1]).substring(0, 4).compareTo(year) == 0) {
					result[i++] = entry;
				}
			}

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to obtain top 10 books (year).");
		}

		return result;
	}

	// Used in AdminFunctions.java
	public static boolean addReader(String addReaderType, String addReaderName, String addReaderAddress) { // passed
																											// in
																											// //
																											// from
																											// text
																											// field

		connectToDB();
		// Query to add Reader by passed in ReaderID
		ResultSet checkExistsRS;
		try {

			ResultSet rs = stmt.executeQuery("SELECT MAX(READERID) FROM READER;");
			System.out.println("Current copies:");
			int lastRID = 0;
			if (rs.next()) {
				lastRID = rs.getInt("MAX(READERID)");
				System.out.println(lastRID);
			}

			stmt.executeUpdate("INSERT INTO READER (READERID, RTYPE, RNAME, ADDRESS) " + "VALUES (" + ++lastRID + ",'"
					+ addReaderType + "','" + addReaderName + "','" + addReaderAddress + "')");
			System.out.println("Reader added!");
			new popupMsg("Reader Added", "Added new reader " + addReaderName + " with card # " + lastRID + ".");
			return true;
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

		ResultSet checkExistsRS;
		ResultSet POSrs;
		try {
			// Check if docid exists and location already exists for document.
			POSrs = stmt.executeQuery("SELECT C.POSITION FROM COPY AS C WHERE C.POSITION='" + position
					+ "' AND C.LIBID='" + libID + "';");
			if (!POSrs.next()) {
				checkExistsRS = stmt.executeQuery("SELECT * FROM DOCUMENT AS D WHERE D.DOCID = '" + docID + "';");
				if (checkExistsRS.next()) {
					// Figure out new Copy Number
					String query = "SELECT MAX(COPYNO) FROM COPY WHERE LIBID='" + libID + "' AND DOCID='" + docID
							+ "';";
					ResultSet rs = stmt.executeQuery(query);
					System.out.println("Current copies:");
					int lastCopyNum = 0;
					if (rs.next()) {
						lastCopyNum = rs.getInt("MAX(COPYNO)");
						System.out.println(lastCopyNum);
					}

					stmt.executeUpdate("INSERT INTO COPY (DOCID, COPYNO, LIBID, POSITION) " + "VALUES (" + docID + ",'"
							+ ++lastCopyNum + "','" + libID + "','" + position + "')");
					System.out.println("Copy added!");
					new popupMsg("Copy Added", "Added new copy " + lastCopyNum + " with doc ID " + docID + ".");
					return true;
				} else {
					new popupMsg("Error", "No Document with ID '" + docID + "' exists!");
				}
			} else {
				new popupMsg("Error", "Copy at '" + position + "' already exists!");
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

	// Used in removeDocument.java
	public static boolean removeByID(String docId) {
		connectToDB();

		try {
			Integer.parseInt(docId);
			String query = "DELETE FROM INV_EDITOR WHERE DOCID='" + docId + "';"; // Delete
																					// INV_EDITOR
																					// in
																					// the
																					// case
																					// of
																					// a
																					// Journal
																					// being
																					// removed
			stmt.execute(query);
			query = "DELETE FROM DOCUMENT WHERE DOCID = " + docId + ";"; // Delete
																			// Document
			stmt.execute(query);
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to remove for document.");
			System.err.println(" SQL Exceptions \n");
			while (e != null) {
				System.out.println("Error Description: " + e.getMessage());
				System.out.println("SQL State:  " + e.getSQLState());
				System.out.println("Vendor Error Code: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		} catch (Exception e) {
			new popupMsg("Error", "Please enter a valid, existing Document ID to remove");
		}
		return false;
	}

	// Used in ReaderFunctions.java
	public static Object[][] getReservationData(int readerId) {
		connectToDB();
		Object rtn[][] = new Object[30][4];
		String query = "SELECT D.TITLE, R.DTIME, D.DOCID, C.LIBID " + "FROM DOCUMENT D, RESERVES R, COPY C "
				+ "WHERE C.DOCID = R.DOCID AND C.LIBID = R.LIBID AND C.COPYNO = R.COPYNO AND R.READERID = " + readerId
				+ " AND C.DOCID = D.DOCID;";
		int i = 0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object entry[] = { rs.getInt("DOCID"), rs.getInt("LIBID"), rs.getString("TITLE"), "Reserved", "$0", rs.getTimestamp("DTIME") };
				rtn[i++] = entry;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query2 = "SELECT D.TITLE, B.BDTIME, C.LIBID, D.DOCID " + "FROM DOCUMENT D, BORROWS B, COPY C "
				+ "WHERE C.DOCID = B.DOCID AND C.LIBID = B.LIBID AND C.COPYNO = B.COPYNO AND C.DOCID = D.DOCID AND B.READERID = "
				+ readerId + " AND RDTIME IS NULL;";
		try {
			ResultSet rs = stmt.executeQuery(query2);
			while (rs.next()) {
				Timestamp time = rs.getTimestamp("BDTIME");
				double fine = 0;
				
				Calendar calendar1 = Calendar.getInstance();
			    Calendar calendar2 = Calendar.getInstance();
			    calendar2.setTimeInMillis(time.getTime());	
			    calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
			    long milsecs1= calendar1.getTimeInMillis();
			    long milsecs2 = calendar2.getTimeInMillis();
			    long diff = milsecs1 - milsecs2;
			    long ddays = diff / (24 * 60 * 60 * 1000) - 20;
			    if (ddays > 0) {
			    	fine =  0.2 * ddays;
			    }
			    String fine2 = String.format("$%.2f", fine);
				Object entry[] = { rs.getInt("DOCID"), rs.getInt("LIBID"), rs.getString("TITLE"), "Borrowed", fine2, rs.getTimestamp("BDTIME") };
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

	// Used in ReaderFunctions
	public static Object[][] searchByDocId(String docId) {

		connectToDB();
		Object[][] rtn = new Object[30][3];
		int i = 0;

		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID " + "WHERE D.DOCID = " + docId
				+ ";";

		try {
			Integer.parseInt(docId);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getString("TITLE"), rs.getString("PUBNAME") };
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
			new popupMsg("Error", "Please enter a valid Document ID");
		}
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
	}

	// Used in ReaderFunctions.java
	public static Object[][] searchByTitle(String title) {

		connectToDB();
		Object rtn[][] = new Object[30][3];
		int i = 0;

		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID " + "WHERE D.TITLE = '" + title
				+ "';";

		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getString("TITLE"), rs.getString("PUBNAME") };
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

	// Used for ReaderFunctions.java
	public static Object[][] searchByPublisher(String publisher) {

		connectToDB();
		Object rtn[][] = new Object[30][3];
		int i = 0;

		String query = "SELECT D.DOCID, D.TITLE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID " + "WHERE P.PUBNAME = '"
				+ publisher + "';";

		try {

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getString("TITLE"), rs.getString("PUBNAME") };
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

	// Used for ViewDocument.java, found in Modify.java
	public static Object[][] viewAllDocs() {
		connectToDB();
		Object[][] rtn = new Object[30][4];
		int i = 0;
		String query = "SELECT D.DOCID, D.TITLE, D.PDATE, P.PUBNAME "
				+ "FROM DOCUMENT D JOIN PUBLISHER P ON D.PUBLISHERID = P.PUBLISHERID;";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getString("TITLE"), rs.getString("PUBNAME"),
						rs.getTimestamp("PDATE") };
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

	// Used for AdminFunctions.java
	public static Object[][] viewAllCopies(int libID) {
		connectToDB();
		Object[][] rtn = new Object[30][4];
		int i = 0;
		String query = "SELECT C.DOCID, C.COPYNO, C.POSITION " + "FROM COPY C WHERE LIBID = '" + libID + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getInt("COPYNO"), rs.getString("POSITION") };
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

	// Used in AdminFunctions.java
	public static Object[][] searchByCopyID(String copyNO, int libID) {

		connectToDB();
		Object[][] rtn = new Object[30][3];
		int i = 0;

		String query = "SELECT C.DOCID, C.COPYNO, C.POSITION " + "FROM COPY C WHERE LIBID = '" + libID
				+ "' AND COPYNO='" + copyNO + "';";

		try {
			Integer.parseInt(copyNO);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object[] entry = { rs.getInt("DOCID"), rs.getInt("COPYNO"), rs.getString("POSITION") };
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
			new popupMsg("Error", "Please enter a valid Copy Number");
		}
		rtn = Arrays.copyOfRange(rtn, 0, i);
		return rtn;
	}

	// Used in AddBook.java, AddJournal, AddProceeding
	public static boolean addNewDoc(String titleField, String pdateField, String publisherField, String paddrField) {

		connectToDB();

		ResultSet checkExistsRS;
		try {

			// Check if document already exists
			checkExistsRS = stmt.executeQuery(
					"SELECT * FROM DOCUMENT WHERE TITLE='" + titleField + "' AND PDATE='" + pdateField + "'");
			if (!checkExistsRS.next()) {

				// Find last DOCID in list
				checkExistsRS = stmt.executeQuery("SELECT MAX(DOCID) FROM DOCUMENT;");
				System.out.println("Current docs:");
				int lastID = 1;
				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("MAX(DOCID)") + 1;
					System.out.println(lastID);
				}

				// Check if publisher already exists
				checkExistsRS = stmt
						.executeQuery("SELECT PUBLISHERID FROM PUBLISHER WHERE PUBNAME='" + publisherField + "';");
				boolean current = checkExistsRS.next();
				int lastpID = 1;
				if (!current) {
					// Find last PUBLISHERID in list
					checkExistsRS = stmt.executeQuery("SELECT MAX(PUBLISHERID) FROM PUBLISHER;");
					System.out.println("PID list:");

					if (checkExistsRS.next()) {
						lastpID = checkExistsRS.getInt("MAX(PUBLISHERID)") + 1;
						System.out.println("PID " + lastpID);
					}

					// Insert new Publisher Data
					stmt.executeUpdate("INSERT INTO PUBLISHER (PUBLISHERID, PUBNAME, ADDRESS) " + "VALUES (" + lastpID
							+ ",'" + publisherField + "','" + paddrField + "')");
					System.out.println("New Publisher Added!");
				} else {
					if (current) {
						lastpID = checkExistsRS.getInt("PUBLISHERID");
						System.out.println(lastpID);
					}

				}

				// Insert new Document Data
				stmt.executeUpdate("INSERT INTO DOCUMENT (DOCID, TITLE, PDATE, PUBLISHERID) " + "VALUES (" + lastID
						+ ",'" + titleField + "','" + pdateField + "','" + lastpID + "')");
				System.out.println("New Document Added!");

				// new popupMsg("Document Added", "Added '" + titleField + "' to
				// library!");
				return true;
			} else {
				new popupMsg("Error", "'" + titleField + "' already exists in the library.");
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new document to library.");
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

	// Used in AddBook.java
	public static boolean addNewBook(String isbn, String author, String title) {

		connectToDB();

		ResultSet checkExistsRS;
		try {
			// Check if author already exists
			checkExistsRS = stmt.executeQuery("SELECT AUTHORID FROM AUTHOR WHERE ANAME='" + author + "';");
			int lastID = 0;
			if (!checkExistsRS.next()) {

				// Find last Author ID
				checkExistsRS = stmt.executeQuery("SELECT MAX(AUTHORID) FROM AUTHOR;");

				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("MAX(AUTHORID)") + 1;
					System.out.println(lastID);
				}

				// Insert new Author Data
				stmt.executeUpdate(
						"INSERT INTO AUTHOR (AUTHORID, ANAME) " + "VALUES (" + lastID + ",'" + author + "')");
				System.out.println("New Author Added!");
			} else {
				lastID = checkExistsRS.getInt("AUTHORID");
				System.out.println(lastID);
			}

			// Get DocID that ISBN belongs to
			int docid = 0;
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='" + title + "';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
			}

			// Check if ISBN already exists
			checkExistsRS = stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN='" + isbn + "';");
			String lastISBN = isbn;
			if (!checkExistsRS.next()) {

				// Insert new Book Data
				stmt.executeUpdate("INSERT INTO BOOK (DOCID, ISBN) " + "VALUES (" + docid + ",'" + isbn + "')");
				System.out.println("New Book Added!");
			}

			checkExistsRS = stmt
					.executeQuery("SELECT * FROM WRITES WHERE AUTHORID='" + lastID + "' AND DOCID='" + docid + "';");
			if (!checkExistsRS.next()) {
				// Insert new Writes Data
				stmt.executeUpdate("INSERT INTO WRITES (AUTHORID, DOCID) " + "VALUES (" + lastID + ",'" + docid + "')");
				System.out.println("Writes Data Added!");
			}

			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new book to library.");
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

	// Used in AddBook.java
	public static boolean isbnExists(String isbn) {
		// Check if ISBN already exists
		
		connectToDB();
		
		try {
			ResultSet checkExistsRS = stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN='" + isbn + "';");
			if (!checkExistsRS.next()) {
				return true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// Used in AddJournal.java
	public static boolean addNewJournal(String titleField, String pdateField, String publisherField, String paddrField,
			String jvol, String jiss, String scope, String ceditor, String inveditor, String title) {

		/*
		 * Arguments
		 * 
		 * jvol = Journal Volume # jiss = Journal Issue # scope = Scope ceditor
		 * = Chief Editor Name inveditor = Inv Editor Name title = Document
		 * title
		 */

		connectToDB();

		ResultSet checkExistsRS;
		try {

			System.out.println("test1");
			// Check if document already exists
			checkExistsRS = stmt.executeQuery(
					"SELECT DOCID FROM DOCUMENT WHERE TITLE='" + titleField + "' AND PDATE='" + pdateField + "'");

			int docid = 0;
			boolean addJVol = false;

			if (checkExistsRS.next()) {
				System.out.println("test2");
				docid = checkExistsRS.getInt("DOCID");

				/*
				 * Check if Journal Volume already exists -- If volume already
				 * exists, set flag to use same Journal
				 */
				checkExistsRS = stmt.executeQuery(
						"SELECT JVOLUME FROM JOURNAL_VOLUME WHERE JVOLUME='" + jvol + "' AND DOCID='" + docid + "';");
				if (!checkExistsRS.next()) {
					System.out.println("in1");
					addJVol = true;
				} else {
					/*
					 * Check if Journal issue already exists for selected
					 * Journal Volume -- If already exists, show popupMsg and
					 * return false -- If does not exist, proceed
					 */
					addJVol = false;
					System.out.println("test3");
					checkExistsRS = stmt.executeQuery("SELECT ISSUE_NO FROM JOURNAL_ISSUE WHERE DOCID='" + docid
							+ "' AND ISSUE_NO='" + jiss + "';");
					if (checkExistsRS.next()) {
						System.out.println("in3");
						new popupMsg("Error", "Journal Issue " + jiss + " for Volume " + jvol + " already exists.");
						return false;
					}
				}
			} else {
				System.out.println("test4");
				// Find last DOCID in list
				checkExistsRS = stmt.executeQuery("SELECT MAX(DOCID) FROM DOCUMENT;");
				System.out.println("Current docs:");
				int lastID = 1;
				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("MAX(DOCID)") + 1;
					System.out.println(lastID);
				}

				// Check if publisher already exists
				System.out.println("test5");
				checkExistsRS = stmt
						.executeQuery("SELECT PUBLISHERID FROM PUBLISHER WHERE PUBNAME='" + publisherField + "';");
				boolean current = checkExistsRS.next();
				int lastpID = 1;
				if (!current) {
					System.out.println("test6");
					// Find last PUBLISHERID in list
					checkExistsRS = stmt.executeQuery("SELECT MAX(PUBLISHERID) FROM PUBLISHER;");
					System.out.println("PID list:");

					if (checkExistsRS.next()) {
						lastpID = checkExistsRS.getInt("MAX(PUBLISHERID)") + 1;
						System.out.println("PID " + lastpID);
					}

					// Insert new Publisher Data
					stmt.executeUpdate("INSERT INTO PUBLISHER (PUBLISHERID, PUBNAME, ADDRESS) " + "VALUES (" + lastpID
							+ ",'" + publisherField + "','" + paddrField + "')");
					System.out.println("New Publisher Added!");
				} else {
					if (current) {
						lastpID = checkExistsRS.getInt("PUBLISHERID");
						System.out.println(lastpID);
					}

				}
				System.out.println("test7");
				// Insert new Document Data
				stmt.executeUpdate("INSERT INTO DOCUMENT (DOCID, TITLE, PDATE, PUBLISHERID) " + "VALUES (" + lastID
						+ ",'" + titleField + "','" + pdateField + "','" + lastpID + "')");
				System.out.println("New Document Added!");
				docid = lastID;
				addJVol = true;
			}

			/*
			 * Check if Chief Editor already exists -- If Chief Editor exists,
			 * save Chief Editor's EDITOR_ID - If Chief Editor does not exist,
			 * INSERT new Chief Editor with name ceditor and new EDITOR_ID using
			 * MAX() function
			 */

			int ceditorID = 1;
			System.out.println("test8");
			checkExistsRS = stmt.executeQuery("SELECT EDITOR_ID FROM CHIEF_EDITOR WHERE ENAME='" + ceditor + "';");
			if (!checkExistsRS.next()) {
				// Insert new CHIEF_EDITOR Data
				System.out.println("test9");
				checkExistsRS = stmt.executeQuery("SELECT MAX(EDITOR_ID) FROM CHIEF_EDITOR;");
				if (checkExistsRS.next()) {
					ceditorID = checkExistsRS.getInt("MAX(EDITOR_ID)") + 1;
				}

				stmt.executeUpdate("INSERT INTO CHIEF_EDITOR (EDITOR_ID, ENAME) " + "VALUES (" + ceditorID + ",'"
						+ ceditor + "')");
			} else {
				ceditorID = checkExistsRS.getInt("EDITOR_ID");
			}
			/*
			 * 1. Insert new JOURNAL_VOLUME 2. Insert new JOURNAL_ISSUE
			 */

			if (addJVol) {
				System.out.println("test10");
				// Insert new JOURNAL_VOLUME Data
				stmt.executeUpdate("INSERT INTO JOURNAL_VOLUME (DOCID, JVOLUME, EDITOR_ID) " + "VALUES (" + docid + ",'"
						+ jvol + "','" + ceditorID + "')");
			}

			// Insert new JOURNAL_ISSUE Data
			System.out.println("test11");
			stmt.executeUpdate("INSERT INTO JOURNAL_ISSUE (DOCID, ISSUE_NO, SCOPE) " + "VALUES (" + docid + ",'" + jiss
					+ "','" + scope + "')");

			/*
			 * Check if INV Editor already exists -- If Inv editor exists,
			 * (IENAME matches inveditor) do nothing -- If Inv editor does not
			 * exist, INSERT new INV_EDITOR with DOCID and ISSUE NO
			 */
			System.out.println("test12");
			checkExistsRS = stmt.executeQuery("SELECT * FROM INV_EDITOR WHERE DOCID='" + docid + "' AND ISSUE_NO='"
					+ jiss + "' AND IENAME='" + inveditor + "';");
			if (!checkExistsRS.next()) {
				// Insert new INV_EDITOR Data
				System.out.println("test13");
				stmt.executeUpdate("INSERT INTO INV_EDITOR (DOCID, ISSUE_NO, IENAME) " + "VALUES (" + docid + ",'"
						+ jiss + "','" + inveditor + "')");
			}

			// SHOW POPUPMSG stating that Journal Volume '#' with issue '#' has
			// been added.
			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new Journal to library.");
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

	// Used in AddProceeding.java
	public static boolean addNewProceeding(String pubDate, String cloc, String chair, String title) {

		/*
		 * Arguments
		 * 
		 * pubDate = Conference Date cloc = Conference Location chair =
		 * Conference Chair Name title = Document title
		 */

		connectToDB();

		ResultSet checkExistsRS;
		try {

			/*
			 * Get DOCID by searching for TITLE='title' in DOCUMENT
			 */
			int docid = 0;
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='" + title + "';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
			}

			/*
			 * Check if Proceedings already exists (same Date, Location, and
			 * Editor) -- If exists, do nothing and show popupMsg and return
			 * false;
			 */
			checkExistsRS = stmt.executeQuery("SELECT * FROM PROCEEDINGS WHERE DOCID='" + docid + "' AND CDATE='"
					+ pubDate + "' AND CLOCATION='" + cloc + "' AND CEDITOR='" + chair + "';");
			if (!checkExistsRS.next()) {
				/*
				 * INSERT new Proceedings
				 */
				stmt.executeUpdate("INSERT INTO PROCEEDINGS (DOCID, CDATE, CLOCATION, CEDITOR) " + "VALUES (" + docid
						+ ",'" + pubDate + "','" + cloc + "','" + chair + "')");
				System.out.println("New Proceedings Added!");
			} else {
				new popupMsg("Error", "Conference Proceeding already exists.");
				return false;
			}

			// SHOW POPUPMSG stating that Conference Proceedings has been added.

			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to add new Conference Proceeding to library.");
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

	// Used in AddBook.java, AddProceeding
	public static boolean updateDoc(String titleField, String pdateField, String publisherField, String paddrField,
			String newTitle) {

		connectToDB();

		ResultSet checkExistsRS;
		try {

			// Check if document already exists
			checkExistsRS = stmt
					.executeQuery("SELECT DOCID, PUBLISHERID FROM DOCUMENT WHERE TITLE='" + titleField + "';");
			int docid = 0;
			int pubid = 0;
			if (checkExistsRS.next()) { // if exists, update
				docid = checkExistsRS.getInt("DOCID");
				pubid = checkExistsRS.getInt("PUBLISHERID");

				// Check if publisher already exists
				checkExistsRS = stmt.executeQuery("SELECT PUBLISHERID FROM PUBLISHER WHERE PUBLISHERID='" + pubid
						+ "' AND PUBNAME='" + publisherField + "';");
				boolean current = checkExistsRS.next();
				int lastpID = 1;
				if (!current) { // If no publisher found, create a new one
					// Find last PUBLISHERID in list
					checkExistsRS = stmt.executeQuery("SELECT MAX(PUBLISHERID) FROM PUBLISHER;");
					System.out.println("PID list:");

					if (checkExistsRS.next()) {
						lastpID = checkExistsRS.getInt("MAX(PUBLISHERID)") + 1;
						System.out.println("PID " + lastpID);
					}

					// Insert new Publisher Data
					stmt.executeUpdate("INSERT INTO PUBLISHER (PUBLISHERID, PUBNAME, ADDRESS) " + "VALUES (" + lastpID
							+ ",'" + publisherField + "','" + paddrField + "')");
					System.out.println("New Publisher Added!");
				} else { // If publisher exists, save PUBLISHERID for Update
							// operation
					// Check if publisher already exists with same Address
					checkExistsRS = stmt.executeQuery("SELECT PUBLISHERID FROM PUBLISHER WHERE PUBLISHERID='" + pubid
							+ "' AND ADDRESS!='" + paddrField + "';");
					if (checkExistsRS.next()) {
						lastpID = checkExistsRS.getInt("PUBLISHERID");
						System.out.println(lastpID);

						// UPDATE Publisher Address
						stmt.executeUpdate("UPDATE PUBISHER SET ADDRESS='" + paddrField + "' WHERE PUBLISHERID='"
								+ lastpID + "';");
						System.out.println("Document Updated!");
					}
				}

				// UPDATE new Document Data
				stmt.executeUpdate("UPDATE DOCUMENT SET TITLE='" + newTitle + "', PDATE='" + pdateField
						+ "', PUBLISHERID='" + lastpID + "' WHERE DOCID='" + docid + "';");
				System.out.println("Document Updated!");

				// new popupMsg("Document Added", "Added '" + titleField + "' to
				// library!");
				return true;
			} else {
				new popupMsg("Error", "'" + titleField + "' does not exist in the library.");
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to update document in library.");
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

	// Used in AddBook.java
	public static int findBookDocID(String title) {
		
		connectToDB();
		
		// Get DocID that ISBN belongs to
		int docid = 0;
		ResultSet checkExistsRS;
		try {
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='" + title + "';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
				// Make sure document is a Book
				checkExistsRS = stmt.executeQuery("SELECT DOCID FROM BOOK WHERE DOCID='" + docid + "';");
				if (!checkExistsRS.next()) {
					new popupMsg("Error", "'" + title + "' is not a Book. Please enter a valid Book.");
					return 0;
				}
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docid;
	}

	// Used in AddBook.java
	public static boolean updateBook(int docid, String isbn, String author, String title) {

		connectToDB();

		ResultSet checkExistsRS;
		try {
			// Check if ISBN already exists for different document
			checkExistsRS = stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN='" + isbn + "' AND DOCID!='"+docid+"';");
			if (!checkExistsRS.next()) {
				// Update Book Data
				stmt.executeUpdate("UPDATE BOOK SET ISBN='" + isbn + "' WHERE DOCID='" + docid + "';");
				System.out.println("Book Updated!");
			} else {
				new popupMsg("Error", "Another book with ISBN "+isbn+" exists. Please enter a unique ISBN.");
				return false;
			}

			// Check if author already exists
			checkExistsRS = stmt.executeQuery("SELECT AUTHORID FROM AUTHOR WHERE ANAME='" + author + "';");
			int lastID = 0;
			if (!checkExistsRS.next()) {

				// Find last Author ID
				checkExistsRS = stmt.executeQuery("SELECT MAX(AUTHORID) FROM AUTHOR;");

				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("MAX(AUTHORID)") + 1;
					System.out.println(lastID);
				}

				// Insert new Author Data
				stmt.executeUpdate(
						"INSERT INTO AUTHOR (AUTHORID, ANAME) " + "VALUES (" + lastID + ",'" + author + "')");
				System.out.println("New Author Added!");
			} else {
				// Find current Author ID, if exists
				lastID = checkExistsRS.getInt("AUTHORID");
				System.out.println(lastID);
			}

			checkExistsRS = stmt
					.executeQuery("SELECT * FROM WRITES WHERE AUTHORID='" + lastID + "' AND DOCID='" + docid + "';");
			if (!checkExistsRS.next()) {
				// Insert new Writes Data
				stmt.executeUpdate("UPDATE WRITES SET AUTHORID='" + lastID + "' WHERE DOCID='" + docid + "';");
				System.out.println("Writes Data Added!");
			}

			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to update book in library.");
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

	// Used in AddBook.java
	public static int findJournalDocID(String title) {
		
		connectToDB();
		
		// Get DocID that ISBN belongs to
		int docid = 0;
		ResultSet checkExistsRS;
		try {
			System.out.println("test1");
			/*
			 * Get DOCID by searching for TITLE='title' in DOCUMENT
			 */
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='" + title + "';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
				// Make sure document is a Journal
				checkExistsRS = stmt.executeQuery("SELECT DOCID FROM JOURNAL_VOLUME WHERE DOCID='" + docid + "';");
				if (!checkExistsRS.next()) {
					new popupMsg("Error", "'" + title + "' is not a Journal. Please enter a valid Journal.");
					return 0;
				}
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docid;
	}

	// Used in AddJournal.java
	public static boolean updateJournal(int docid, String jvol, String jiss, String scope, String ceditor,
			String inveditor, String title) {

		/*
		 * Arguments
		 * 
		 * jvol = Journal Volume # jiss = Journal Issue # scope = Scope ceditor
		 * = Chief Editor Name inveditor = Inv Editor Name title = Document
		 * title
		 */

		connectToDB();

		ResultSet checkExistsRS;
		try {

			/*
			 * Check if Chief Editor already exists -- If Chief Editor exists,
			 * save Chief Editor's EDITOR_ID - If Chief Editor does not exist,
			 * INSERT new Chief Editor with name ceditor and new EDITOR_ID using
			 * MAX() function
			 */

			int ceditorID = 1;
			System.out.println("test8");
			checkExistsRS = stmt.executeQuery("SELECT EDITOR_ID FROM CHIEF_EDITOR WHERE ENAME='" + ceditor + "';");
			if (!checkExistsRS.next()) {
				// Insert new CHIEF_EDITOR Data
				System.out.println("test9");
				checkExistsRS = stmt.executeQuery("SELECT MAX(EDITOR_ID) FROM CHIEF_EDITOR;");
				if (checkExistsRS.next()) {
					ceditorID = checkExistsRS.getInt("MAX(EDITOR_ID)") + 1;
				}

				stmt.executeUpdate("INSERT INTO CHIEF_EDITOR (EDITOR_ID, ENAME) " + "VALUES (" + ceditorID + ",'"
						+ ceditor + "')");
			} else {
				ceditorID = checkExistsRS.getInt("EDITOR_ID");
			}
			/*
			 * 1. Update new JOURNAL_VOLUME 2. Update new JOURNAL_ISSUE
			 */

			System.out.println("test10");
			// Update JOURNAL_VOLUME Data
			stmt.executeUpdate("UPDATE JOURNAL_VOLUME SET JVOLUME='" + jvol + "', EDITOR_ID='" + ceditorID
					+ "' WHERE DOCID='" + docid + "';");

			// Update JOURNAL_ISSUE Data
			System.out.println("test11");
			stmt.executeUpdate("UPDATE JOURNAL_ISSUE SET SCOPE='" + scope + "' WHERE DOCID='" + docid
					+ "' AND ISSUE_NO='" + jiss + "'");

			/*
			 * Check if INV Editor already exists -- If Inv editor exists,
			 * (IENAME matches inveditor) do nothing -- If Inv editor does not
			 * exist, INSERT new INV_EDITOR with DOCID and ISSUE NO
			 */
			System.out.println("test12");
			checkExistsRS = stmt.executeQuery("SELECT * FROM INV_EDITOR WHERE DOCID='" + docid + "' AND ISSUE_NO='"
					+ jiss + "' AND IENAME='" + inveditor + "';");
			if (!checkExistsRS.next()) {
				// Insert new INV_EDITOR Data
				System.out.println("test13");
				stmt.executeUpdate("INSERT INTO INV_EDITOR (DOCID, ISSUE_NO, IENAME) " + "VALUES (" + docid + ",'"
						+ jiss + "','" + inveditor + "')");
			}

			// SHOW POPUPMSG stating that Journal Volume '#' with issue '#' has
			// been added.
			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to update Journal in library.");
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

	// Used in AddBook.java
	public static int findProceedingDocID(String title) {
		
		connectToDB();
		
		// Get DocID that ISBN belongs to
		int docid = 0;
		ResultSet checkExistsRS;
		try {
			System.out.println("test1");
			/*
			 * Get DOCID by searching for TITLE='title' in DOCUMENT
			 */
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='" + title + "';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
				// Make sure document is a Proceeding
				checkExistsRS = stmt.executeQuery("SELECT DOCID FROM PROCEEDINGS WHERE DOCID='" + docid + "';");
				if (!checkExistsRS.next()) {
					new popupMsg("Error", "'" + title + "' is not a Proceeding. Please enter a valid Proceeding.");
					return 0;
				}
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docid;
	}

	// Used in AddProceeding.java
	public static boolean updateProceeding(int docid, String pubDate, String cloc, String chair, String title) {

		/*
		 * Arguments
		 * 
		 * pubDate = Conference Date cloc = Conference Location chair =
		 * Conference Chair Name title = Document title
		 */

		connectToDB();

		ResultSet checkExistsRS;
		try {

			/*
			 * Check if Proceedings already exists (same Date, Location, and
			 * Editor) -- If exists, update
			 */
			checkExistsRS = stmt.executeQuery("SELECT * FROM PROCEEDINGS WHERE DOCID='" + docid + "';");
			if (checkExistsRS.next()) {
				/*
				 * UPDATE Proceedings
				 */
				stmt.executeUpdate("UPDATE PROCEEDINGS SET CDATE='" + pubDate + "', CLOCATION='" + cloc + "', CEDITOR='"
						+ chair + "' WHERE DOCID='" + docid + "';");
				System.out.println("Proceedings Updated!");
			} else {
				new popupMsg("Error", "Conference Proceeding does not exist.");
				return false;
			}

			// SHOW POPUPMSG stating that Conference Proceedings has been added.

			System.out.println("Success");
			stmt.close();
			con.close();
			return true;

		} catch (SQLException e) {
			new popupMsg("Error", "Unable to update Conference Proceeding in library.");
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

	// For Use with ReaderFunction -> Reserve
	public static boolean reserve(int readerId, String docId, String libId) {

		connectToDB();
		int resNumber = 0;
		int copyno = 0;
		try {
			// Integer.parseInt(docId); Integer.parseInt(libId);

			String resCountQuery = "SELECT MAX(RESUMBER) FROM RESERVES;";
			ResultSet rs = stmt.executeQuery(resCountQuery);
			if (rs.next()) {
				resNumber = rs.getInt("MAX(RESUMBER)") + 1;
			}

			ArrayList<Integer> copiesTaken = new ArrayList<Integer>();
			String resCountCopy = "SELECT COPYNO FROM RESERVES WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs1 = stmt.executeQuery(resCountCopy);
			while (rs1.next()) {
				copiesTaken.add(rs1.getInt("COPYNO"));
			}
			String borCountCopy = "SELECT COPYNO FROM BORROWS WHERE RDTIME IS NULL AND LIBID = " + libId
					+ " AND DOCID = " + docId + ";";
			ResultSet rs2 = stmt.executeQuery(borCountCopy);
			while (rs2.next()) {
				copiesTaken.add(rs2.getInt("COPYNO"));
			}

			String copyQuery = "SELECT COPYNO FROM COPY WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs3 = stmt.executeQuery(copyQuery);
			while (rs3.next()) {
				if (!copiesTaken.contains(rs3.getInt("COPYNO"))) {
					copyno = rs3.getInt("COPYNO");
					break;
				}
			}
			if (copyno == 0) {
				new popupMsg("Error", "No more copies left. Please check again later");
				return false;
			} else {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = sdf.format(cal.getTime());
				System.out.println(date);
				String query = String.format("INSERT INTO RESERVES (RESUMBER,READERID,DOCID,COPYNO,LIBID,DTIME) "
						+ "VALUES (%d,%d,%s,%d,%s,'%s');", resNumber, readerId, docId, copyno, libId, date);
				System.out.println(query);
				stmt.execute(query);
			}
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
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
			new popupMsg("Error", "Please enter valid Document Id and Library Id");
		}
		return false;

	}

	// For Use with ReaderFunction -> Borrow.java
	public static boolean borrow(int readerId, String docId, String libId) {
		connectToDB();
		int borNumber = 0;
		int copyno = 0;
		try {
			// Integer.parseInt(docId); Integer.parseInt(libId);

			String borCountQuery = "SELECT MAX(BORNUMBER) FROM BORROWS;";
			ResultSet rs = stmt.executeQuery(borCountQuery);
			if (rs.next()) {
				borNumber = rs.getInt("MAX(BORNUMBER)") + 1;
			}

			ArrayList<Integer> copiesTaken = new ArrayList<Integer>();
			String resCountCopy = "SELECT COPYNO FROM RESERVES WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs1 = stmt.executeQuery(resCountCopy);
			while (rs1.next()) {
				copiesTaken.add(rs1.getInt("COPYNO"));
			}
			String borCountCopy = "SELECT COPYNO FROM BORROWS WHERE RDTIME IS NULL AND LIBID = " + libId
					+ " AND DOCID = " + docId + ";";
			ResultSet rs2 = stmt.executeQuery(borCountCopy);
			while (rs2.next()) {
				copiesTaken.add(rs2.getInt("COPYNO"));
			}

			String copyQuery = "SELECT COPYNO FROM COPY WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs3 = stmt.executeQuery(copyQuery);
			while (rs3.next()) {
				if (!copiesTaken.contains(rs3.getInt("COPYNO"))) {
					copyno = rs3.getInt("COPYNO");
					break;
				}
			}
			if (copyno == 0) {
				new popupMsg("Error", "No more copies left. Please check again later");
				return false;
			} else {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = sdf.format(cal.getTime());
				System.out.println(date);
				String query = String.format(
						"INSERT INTO BORROWS (BORNUMBER,READERID,DOCID,COPYNO,LIBID,BDTIME,RDTIME) "
								+ "VALUES (%d,%d,%s,%d,%s,'%s',NULL);",
						borNumber, readerId, docId, copyno, libId, date);
				System.out.println(query);
				stmt.execute(query);
			}
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
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
			new popupMsg("Error", "Please enter valid Document ID and Library ID");
		}
		return false;

	}

	public static boolean pickup(int readerId, String docId, String libId) {

		connectToDB();

		int resNumber = 0;
		int copyno = 0;
		int borNumber = 0;

		try {
			String borCountQuery = "SELECT MAX(BORNUMBER) FROM BORROWS;";
			ResultSet rs = stmt.executeQuery(borCountQuery);
			if (rs.next()) {
				borNumber = rs.getInt("MAX(BORNUMBER)") + 1;
			}

			String query = "SELECT R.RESUMBER, R.COPYNO, R.LIBID " + "FROM RESERVES R WHERE READERID = " + readerId
					+ " AND DOCID = " + docId + " AND LIBID = " + libId +";";
			System.out.println(query);
			ResultSet rs1 = stmt.executeQuery(query);

			if (rs1.next()) {
				resNumber = rs1.getInt("RESUMBER");
				copyno = rs1.getInt("COPYNO");
			}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = sdf.format(cal.getTime());

			String insertQuery = String
					.format("INSERT INTO BORROWS (BORNUMBER,READERID,DOCID,COPYNO,LIBID,BDTIME,RDTIME) "
							+ "VALUES (%d,%d,%s,%d,%s,'%s',NULL);", borNumber, readerId, docId, copyno, libId, date);

			stmt.execute(insertQuery);

			String removeQuery = "DELETE FROM RESERVES WHERE RESUMBER = " + resNumber + ";";
			stmt.execute(removeQuery);

			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			new popupMsg("Error", "Please enter a valid document id");
			return false;
		}
	}

	public static boolean returnCopy(int readerId, String docId, String libId) {

		connectToDB();
		String query = "SELECT B.BORNUMBER " + "FROM BORROWS B WHERE READERID = " + readerId + " AND DOCID = " + docId
				+ " AND LIBID = " + libId + " AND RDTIME IS NULL;";
		System.out.println(query);
		int borNumber = 0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				borNumber = rs.getInt("BORNUMBER");
			}
			System.out.println(borNumber);
			String date = getDate();
			String updateQuery = "UPDATE BORROWS " + "SET RDTIME = '" + date + "' WHERE BORNUMBER = " + borNumber + ";";
			System.out.println(updateQuery);
			stmt.execute(updateQuery);
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			new popupMsg("Error", "Unable to return copy");
			return false;
		}
	}

	// Used to find average. fine in AdminFunctions.java
	public static float avgFine(int libid) {

		connectToDB();

		ArrayList<String[]> data = new ArrayList<String[]>();

		try {
			ResultSet rs = stmt.executeQuery("SELECT READERID, BDTIME FROM BORROWS WHERE LIBID =" + libid + " AND RDTIME IS NULL" + ";");
			while (rs.next()) {
				int num = rs.getInt("READERID");
				Timestamp ts = rs.getTimestamp("BDTIME");
				Date date = new Date();
				date.setTime(ts.getTime());
				String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
				SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
				Date d = sd.parse(dateString);
				data.add(new String[] {String.valueOf(num), Long.toString(d.getTime())});
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error! " + "\n" + e.getMessage() + "\n" + e.getSQLState() + "\n" + e.getErrorCode());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		float sum = 0;
		int count = 0;

		for (String[] aData : data) {
			
			count++;

			long borrowedMillis = Long.valueOf(aData[1]);
			long currMillis = System.currentTimeMillis();
			long days = TimeUnit.MILLISECONDS.toDays(currMillis - borrowedMillis); // How long has it been borrowed in days

			if (days > 20) {
				double numDaysOverDue = Math.ceil(days - 20);
				double fineInDollars = numDaysOverDue * .2;
				sum += fineInDollars;
			}
		}

		float result = 0;
		
		if (count == 0) {
			return 0;
		}
		else {
			result = sum / count;
		}

		return result;
	}

}
