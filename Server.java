
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
			String passwd = "mySQLroot";
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
				System.out.println(searchDocCopy); // Outputs Search Results for Document Copies. If empty, doesn't
													// exist.
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
	public static boolean addReader(String addReaderType, String addReaderName, String addReaderAddress) { // passed in
																											// // from
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
		String count = "SELECT COUNT(*) " + " FROM DOCUMENT WHERE DOCID = " + docId + ";";
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
			new popupMsg("Error", "Please enter a valid, existing Document ID to remove");
		}
		return false;
	}

	// Used in ReaderFunctions.java
	public static Object[][] getReservationData(int readerId) {
		connectToDB();
		Object rtn[][] = new Object[30][4];
		String query = "SELECT D.TITLE, R.DTIME " + "FROM DOCUMENT D, RESERVES R, COPY C "
				+ "WHERE C.DOCID = R.DOCID AND C.LIBID = R.LIBID AND C.COPYNO = R.COPYNO AND R.READERID = " + readerId
				+ " AND C.DOCID = D.DOCID;";
		int i = 0;
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Object entry[] = { rs.getString("TITLE"), "Reserved", "$0", rs.getTimestamp("DTIME") };
				rtn[i++] = entry;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query2 = "SELECT D.TITLE ,B.BDTIME " + "FROM DOCUMENT D, BORROWS B, COPY C "
				+ "WHERE C.DOCID = B.DOCID AND C.LIBID = B.LIBID AND C.COPYNO = B.COPYNO AND C.DOCID = D.DOCID AND B.READERID = "
				+ readerId + ";";
		try {
			ResultSet rs = stmt.executeQuery(query2);
			while (rs.next()) {
				Object entry[] = { rs.getString("TITLE"), "Borrowed", "$2", rs.getTimestamp("BDTIME") };
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

	// For Use with ReaderFunction -> Reserve
	public static boolean reserve(int readerId, String docId, String libId) {

		connectToDB();
		int resNumber = 0;
		int copyno = 0;
		try {
			// Integer.parseInt(docId); Integer.parseInt(libId);

			String resCountQuery = "SELECT COUNT(*) FROM RESERVES;";
			ResultSet rs = stmt.executeQuery(resCountQuery);
			if (rs.next()) {
				resNumber = rs.getInt("COUNT(*)") + 1;
			}

			ArrayList<Integer> copiesTaken = new ArrayList<Integer>();
			String resCountCopy = "SELECT COPYNO FROM RESERVES WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs1 = stmt.executeQuery(resCountCopy);
			while (rs1.next()) {
				copiesTaken.add(rs1.getInt("COPYNO"));
			}

			String copyQuery = "SELECT COPYNO FROM COPY WHERE LIBID = " + libId + " AND DOCID = " + docId + ";";
			ResultSet rs2 = stmt.executeQuery(copyQuery);
			while (rs2.next()) {
				if (!copiesTaken.contains(rs2.getInt("COPYNO"))) {
					copyno = rs2.getInt("COPYNO");
					break;
				}
			}
			if (copyno == 0) {
				new popupMsg("Error", "No more copies left. Please check again later");
				return false;
			} else {
				String date = getDate();
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

				new popupMsg("Document Added", "Added '" + titleField + "' to library!");
				return true;
			} else {
				new popupMsg("Error", "'" + titleField + "' already exists in the library.");
			}
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
			checkExistsRS = stmt.executeQuery("SELECT ANAME FROM AUTHOR WHERE ANAME='"+author+"';");
			int lastID = 0;
			if (!checkExistsRS.next()) {

				// Find last Author ID
				checkExistsRS = stmt.executeQuery("SELECT MAX(AUTHORID) FROM AUTHOR;");
				
				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("MAX(AUTHORID)") + 1;
					System.out.println(lastID);
				}

				// Insert new Author Data
				stmt.executeUpdate("INSERT INTO AUTHOR (AUTHORID, ANAME) " + "VALUES (" + lastID
						+ ",'" + author + "')");
				System.out.println("New Author Added!");
			} else {
				checkExistsRS = stmt.executeQuery("SELECT AUTHORID FROM AUTHOR WHERE ANAME='"+author+"';");
				if (checkExistsRS.next()) {
					lastID = checkExistsRS.getInt("AUTHORID");
					System.out.println(lastID);
				}

			}
			
			// Get DocID that ISBN belongs to
			int docid = 0;
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='"+title+"';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
			}
			
			// Check if ISBN already exists
			checkExistsRS = stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN='"+isbn+"';");
			String lastISBN = isbn;
			if (!checkExistsRS.next()) {
				
				// Insert new Book Data
				stmt.executeUpdate("INSERT INTO BOOK (DOCID, ISBN) " + "VALUES (" + docid
						+ ",'" + isbn + "')");
				System.out.println("New Book Added!");
			}
			
			checkExistsRS = stmt.executeQuery("SELECT * FROM WRITES WHERE AUTHORID='"+lastID+"' AND DOCID='"+docid+"';");
			if (!checkExistsRS.next()) {
				// Insert new Writes Data
				stmt.executeUpdate("INSERT INTO WRITES (AUTHORID, DOCID) " + "VALUES (" + lastID
						+ ",'" + docid + "')");
				System.out.println("Writes Data Added!");
			}
			
			System.out.println("Success");
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
		try {
			ResultSet checkExistsRS = stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN='"+isbn+"';");
			if (!checkExistsRS.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// Used in AddJournal.java
	public static boolean addNewJournal(String jvol, String jiss, String scope, String ceditor, String inveditor, String title) {
		
		/*
		 * Arguments
		 * 
		 * jvol = Journal Volume #
		 * jiss = Journal Issue #
		 * scope = Scope
		 * ceditor = Chief Editor Name
		 * inveditor = Inv Editor Name
		 * title = Document title
		 */
		
		connectToDB();
		
		ResultSet checkExistsRS;
		try {
			
			/*
			 * Get DOCID by searching for TITLE='title' in DOCUMENT
			 */
			int docid = 0;
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='"+title+"';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
			}
			
			/* Check if Journal Volume already exists
			 * -- If volume already exists, show popupMsg that Journal Volume must be changed
			 */
			
			/*
			 * Check if Chief Editor already exists
			 * -- If Chief Editor exists, save Chief Editor's EDITOR_ID
			 * - If Chief Editor does not exists, INSERT new Chief Editor with name ceditor and new EDITOR_ID using MAX() function
			 */
			
			/*
			 * Check if Journal issue already exists for selected Journal Volume
			 * -- If already exists, show popupMsg and return false
			 * -- If does not exist, proceed
			 */
			
			/*
			 * 1. Insert new JOURNAL_VOLUME
			 * 2. Insert new JOURNAL_ISSUE
			 */
			
			/*
			 * Check if INV Editor already exists
			 * -- If Inv editor exists, (IENAME matches inveditor) do nothing
			 * -- If Inv editor does not exist, INSERT new INV_EDITOR with DOCID and ISSUE NO
			 */
			
			// SHOW POPUPMSG stating that Journal Volume '#' with issue '#' has been added.
			System.out.println("Success");
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
	
	// Used in AddJournal.java
	public static boolean addNewProceeding(String pubDate, String cloc, String chair, String title) {
		
		/*
		 * Arguments
		 * 
		 * pubDate = Conference Date
		 * cloc = Conference Location
		 * chair = Conference Chair Name
		 * title = Document title
		 */
		
		connectToDB();
		
		ResultSet checkExistsRS;
		try {
			
			/*
			 * Get DOCID by searching for TITLE='title' in DOCUMENT
			 */
			int docid = 0;
			checkExistsRS = stmt.executeQuery("SELECT DOCID FROM DOCUMENT WHERE TITLE='"+title+"';");
			if (checkExistsRS.next()) {
				docid = checkExistsRS.getInt("DOCID");
			}
			
			/* Check if Proceedings already exists (same Date, Location, and Editor)
			 * -- If exists, do nothing and show popupMsg and return false;
			 */
			
			/*
			 * INSERT new Proceedings
			 */
			
			// SHOW POPUPMSG stating that Conference Proceedings has been added.
			System.out.println("Success");
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

	// For Use with ReaderFunction -> Borrow.java
	public static boolean borrow(int readerId, String docId, String libId) {
		connectToDB();
		int borNumber = 0;
		int copyno = 0;
		try {
			// Integer.parseInt(docId); Integer.parseInt(libId);

			String borCountQuery = "SELECT COUNT(*) FROM BORROWS;";
			ResultSet rs = stmt.executeQuery(borCountQuery);
			if (rs.next()) {
				borNumber = rs.getInt("COUNT(*)") + 1;
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
			new popupMsg("Error", "Please enter valid Document Id and Library Id");
		}
		return false;
	}

	public static boolean pickup(int readerId, String text, int libId) {
		// TODO Auto-generated method stub
		return false;
	}
}
