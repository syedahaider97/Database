/*
 * Written by:
 * James Brancale
 * Syed Haider
 * Ammar Zia
 */

import java.sql.*;
class cs631lib {
    public static void main(String[] args) {

        System.out.println("Hello from MySQL!\n");

        //loading driver, need to catch exceptions
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: ");
            System.err.println("Message: " + e.getMessage());
        }

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
                stmt = con.createStatement();
                
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
                  
                
                // BOOK
                System.out.println("BOOK");
                query = "SELECT * FROM BOOK;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tISBN");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    String n = rs.getString("ISBN");
                    System.out.println(s + "\t" + n);
                }
                //close resources
                 
                
            	// BORROWS
                System.out.println("BORROWS");
                query = "SELECT * FROM BORROWS;";
                rs = stmt.executeQuery(query);
                System.out.println("BORNUMBER\tREADERID\tCOPYNO\tDOCID\tLIBID\tBDTIME\tRDTIME");
                while (rs.next()) {
                    int s = rs.getInt("BORNUMBER");
                    int n = rs.getInt("READERID");
                    int o = rs.getInt("COPYNO");
                    int p = rs.getInt("DOCID");
                    int q = rs.getInt("LIBID");
                    Timestamp r = rs.getTimestamp("BDTIME");
                    Timestamp t = rs.getTimestamp("RDTIME");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p + "\t" + q + "\t" + r + "\t" + t);
                }
                //close resources
                  
                
                // BRANCH
                System.out.println("BRANCH");
                query = "SELECT * FROM BRANCH;";
                rs = stmt.executeQuery(query);
                System.out.println("LIBID\tLNAME\tLLOCATION");
                while (rs.next()) {
                    int s = rs.getInt("LIBID");
                    String n = rs.getString("LNAME");
                    String o = rs.getString("LLOCATION");
                    System.out.println(s + "\t" + n + "\t" + o);
                }
                //close resources
                  
                
                // CHIEF_EDITOR
                System.out.println("CHIEF_EDITOR");
                query = "SELECT * FROM CHIEF_EDITOR;";
                rs = stmt.executeQuery(query);
                System.out.println("EDITOR_ID\tENAME");
                while (rs.next()) {
                    int s = rs.getInt("EDITOR_ID");
                    String n = rs.getString("ENAME");
                    System.out.println(s + "\t" + n);
                }
                //close resources
                 
                
            	// COPY
                System.out.println("COPY");
                query = "SELECT * FROM COPY;";
                rs = stmt.executeQuery(query);
                System.out.println("COPYNO\tDOCID\tLIBID\tPOSITION");
                while (rs.next()) {
                    int s = rs.getInt("COPYNO");
                    int n = rs.getInt("DOCID");
                    int o = rs.getInt("LIBID");
                    String p = rs.getString("POSITION");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p);
                }
                //close resources
                  
                
            	// DOCUMENT
                System.out.println("DOCUMENT");
                query = "SELECT * FROM DOCUMENT;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tTITLE\tPDATE\tPUBLISHERID");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    String n = rs.getString("TITLE");
                    Date o = rs.getDate("PDATE");
                    String p = rs.getString("PUBLISHERID");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p);
                }
                //close resources
                  
                
            	// INV_EDITOR
                System.out.println("INV_EDITOR");
                query = "SELECT * FROM INV_EDITOR;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tISSUE_NO\tIENAME");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("ISSUE_NO");
                    String p = rs.getString("IENAME");
                    System.out.println(s + "\t" + n + "\t" + p);
                }
                //close resources
                  
                
            	// JOURNAL_ISSUE
                System.out.println("JOURNAL_ISSUE");
                query = "SELECT * FROM JOURNAL_ISSUE;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tISSUE_NO\tSCOPE");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("ISSUE_NO");
                    String p = rs.getString("SCOPE");
                    System.out.println(s + "\t" + n + "\t" + p);
                }
                //close resources
                  
                
            	// JOURNAL_VOLUME
                System.out.println("JOURNAL_VOLUME");
                query = "SELECT * FROM JOURNAL_VOLUME;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tJVOLUME\tEDITOR_ID");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("JVOLUME");
                    int p = rs.getInt("EDITOR_ID");
                    System.out.println(s + "\t" + n + "\t" + p);
                }
                //close resources
                 
                
            	// PROCEEDINGS
                System.out.println("PROCEEDINGS");
                query = "SELECT * FROM PROCEEDINGS;";
                rs = stmt.executeQuery(query);
                System.out.println("DOCID\tCDATE\tCLOCATION\tCEDITOR");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    Date n = rs.getDate("CDATE");
                    String o = rs.getString("CLOCATION");
                    String p = rs.getString("CEDITOR");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p);
                }
                //close resources
                  
                
            	// PUBLISHER
                System.out.println("PUBLISHER");
                query = "SELECT * FROM PUBLISHER;";
                rs = stmt.executeQuery(query);
                System.out.println("PUBLISHERID\tPUBNAME\tADDRESS");
                while (rs.next()) {
                    int s = rs.getInt("PUBLISHERID");
                    String o = rs.getString("PUBNAME");
                    String p = rs.getString("ADDRESS");
                    System.out.println(s + "\t" + o + "\t" + p);
                }
                //close resources
                  
                
            	// READER
                System.out.println("READER");
                query = "SELECT * FROM READER;";
                rs = stmt.executeQuery(query);
                System.out.println("READERID\tRTYPE\tRNAME\tADDRESS");
                while (rs.next()) {
                    int s = rs.getInt("READERID");
                    String n = rs.getString("RTYPE");
                    String o = rs.getString("RNAME");
                    String p = rs.getString("ADDRESS");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p);
                }
                //close resources
                  
                
            	// RESERVES
                System.out.println("RESERVES");
                query = "SELECT * FROM RESERVES;";
                rs = stmt.executeQuery(query);
                System.out.println("RESNUMBER\tREADERID\tDOCID\tCOPYNO\tLIBID\tDTIME");
                while (rs.next()) {
                    int s = rs.getInt("RESNUMBER");
                    int n = rs.getInt("READERID");
                    int o = rs.getInt("DOCID");
                    int p = rs.getInt("COPYNO");
                    int q = rs.getInt("LIBID");
                    Timestamp r = rs.getTimestamp("DTIME");
                    System.out.println(s + "\t" + n + "\t" + o + "\t" + p + "\t" + q + "\t" + r);
                }
                //close resources
                  
                
                // WRITES
                System.out.println("WRITES");
                query = "SELECT * FROM WRITES;";
                rs = stmt.executeQuery(query);
                System.out.println("AUTHORID\tDOCID");
                while (rs.next()) {
                    int s = rs.getInt("AUTHORID");
                    int n = rs.getInt("DOCID");
                    System.out.println(s + "\t" + n);
                }
                //close resources
                stmt.close(); con.close();


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
}

