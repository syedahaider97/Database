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
                
                String query = "SELECT * FROM AUTHOR;";
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("  AUTHORID       ANAME");
                while (rs.next()) {
                    int s = rs.getInt("AUTHORID");
                    String n = rs.getString("ANAME");
                    System.out.println(s + "   " + n);
                }
                //close resources
                stmt.close(); con.close();
                
                // BOOK
                
                query = "SELECT * FROM BOOK;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       ISBN");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("ISBN");
                    System.out.println(s + "   " + n);
                }
                //close resources
                stmt.close(); con.close();
                
            	// BORROWS
                
                query = "SELECT * FROM BORROWS;";
                rs = stmt.executeQuery(query);
                System.out.println("  BORNUMBER       READERID       COPYNO       DOCID       LIBID       BDTIME       RDTIME");
                while (rs.next()) {
                    int s = rs.getInt("BORNUMBER");
                    int n = rs.getInt("READERID");
                    int o = rs.getInt("COPYNO");
                    int p = rs.getInt("DOCID");
                    int q = rs.getInt("LIBID");
                    Time r = rs.getTime("BDTIME");
                    Time t = rs.getTime("RDTIME");
                    System.out.println(s + "   " + n + "   " + o + "   " + p + "   " + q + "   " + r + "   " + t);
                }
                //close resources
                stmt.close(); con.close();
                
                // BRANCH
                
                query = "SELECT * FROM BRANCH;";
                rs = stmt.executeQuery(query);
                System.out.println("  LIBID       LNAME       LLOCATION");
                while (rs.next()) {
                    int s = rs.getInt("LIBID");
                    String n = rs.getString("LNAME");
                    String o = rs.getString("LLOCATION");
                    System.out.println(s + "   " + n + "   " + o);
                }
                //close resources
                stmt.close(); con.close();
                
                // CHIEF_EDITOR
                
                query = "SELECT * FROM CHIEF_EDITOR;";
                rs = stmt.executeQuery(query);
                System.out.println("  EDITOR_ID       ENAME");
                while (rs.next()) {
                    int s = rs.getInt("EDITOR_ID");
                    String n = rs.getString("ENAME");
                    System.out.println(s + "   " + n);
                }
                //close resources
                stmt.close(); con.close();
                
            	// COPY
                
                query = "SELECT * FROM COPY;";
                rs = stmt.executeQuery(query);
                System.out.println("  COPYNO       DOCID       LIBID       POSITION");
                while (rs.next()) {
                    int s = rs.getInt("COPYNO");
                    int n = rs.getInt("DOCID");
                    int o = rs.getInt("LIBID");
                    String p = rs.getString("POSITION");
                    System.out.println(s + "   " + n + "   " + o + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// DOCUMENT
                
                query = "SELECT * FROM COPY;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       TITLE       PDATE       PUBLISHERID");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    String n = rs.getString("TITLE");
                    Date o = rs.getDate("PDATE");
                    String p = rs.getString("PUBLISHERID");
                    System.out.println(s + "   " + n + "   " + o + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// INV_EDITOR
                
                query = "SELECT * FROM INV_EDITOR;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       ISSUE_NO       IENAME");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("ISSUE_NO");
                    String p = rs.getString("IENAME");
                    System.out.println(s + "   " + n + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// JOURNAL_ISSUE
                
                query = "SELECT * FROM JOURNAL_ISSUE;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       ISSUE_NO       SCOPE");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("ISSUE_NO");
                    String p = rs.getString("SCOPE");
                    System.out.println(s + "   " + n + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// JOURNAL_VOLUME
                
                query = "SELECT * FROM JOURNAL_VOLUME;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       JVOLUME       EDITOR_ID");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    int n = rs.getInt("JVOLUME");
                    int p = rs.getInt("EDITOR_ID");
                    System.out.println(s + "   " + n + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// PROCEEDINGS
                
                query = "SELECT * FROM PROCEEDINGS;";
                rs = stmt.executeQuery(query);
                System.out.println("  DOCID       CDATE       CLOCATION       CEDITOR");
                while (rs.next()) {
                    int s = rs.getInt("DOCID");
                    Date n = rs.getDate("CDATE");
                    String o = rs.getString("CLOCATION");
                    String p = rs.getString("CEDITOR");
                    System.out.println(s + "   " + n + "   " + o + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// PUBLISHER
                
                query = "SELECT * FROM PUBLISHER;";
                rs = stmt.executeQuery(query);
                System.out.println("  PUBLISHERID       PUBNAME       ADDRESS");
                while (rs.next()) {
                    int s = rs.getInt("PUBLISHERID");
                    String o = rs.getString("PUBNAME");
                    String p = rs.getString("ADDRESS");
                    System.out.println(s + "   " + o + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// READER
                
                query = "SELECT * FROM READER;";
                rs = stmt.executeQuery(query);
                System.out.println("  READERID       RTYPE       RNAME       ADDRESS");
                while (rs.next()) {
                    int s = rs.getInt("READERID");
                    String n = rs.getString("RTYPE");
                    String o = rs.getString("RNAME");
                    String p = rs.getString("ADDRESS");
                    System.out.println(s + "   " + n + "   " + o + "   " + p);
                }
                //close resources
                stmt.close(); con.close();
                
            	// RESERVES
                
                query = "SELECT * FROM RESERVES;";
                rs = stmt.executeQuery(query);
                System.out.println("  RESNUMBER       READERID       DOCID       COPYNO       LIBID       DTIME");
                while (rs.next()) {
                    int s = rs.getInt("RESNUMBER");
                    int n = rs.getInt("READERID");
                    int o = rs.getInt("DOCID");
                    int p = rs.getInt("COPYNO");
                    int q = rs.getInt("LIBID");
                    Time r = rs.getTime("DTIME");
                    System.out.println(s + "   " + n + "   " + o + "   " + p + "   " + q + "   " + r);
                }
                //close resources
                stmt.close(); con.close();
                
                // WRITES
                
                query = "SELECT * FROM WRITES;";
                rs = stmt.executeQuery(query);
                System.out.println("  AUTHORID       DOCID");
                while (rs.next()) {
                    int s = rs.getInt("AUTHORID");
                    int n = rs.getInt("DOCID");
                    System.out.println(s + "   " + n);
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