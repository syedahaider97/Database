import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFunctions extends JFrame {
	private int libId = 0;
	private JTextField addCopyDocID, addCopyLoc, addReaderID, addReaderType, addReaderName, addReaderAddress, searchField;
	
	// Default Constructor -- Displays error if no input passed in to menu
	public AdminFunctions() {
		this.libId = 0;
		// Show Error message
		new popupMsg("Error", "No Administrator ID entered.");
	}

	// Create Admin Functions menu, based on administrator ID
    public AdminFunctions(int libId) {
    	this.libId = libId;
    	System.out.println(libId);
    	
    	// Create frame for content
        JFrame frame = new JFrame("Administrator Functions");
        
        // Create panel to display content on frame
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        
        // Title for the window
        JLabel adminLabel = new JLabel("Administrator Functions:");
        panel.add(adminLabel);
        Dimension sizeAdmin = adminLabel.getPreferredSize();
        adminLabel.setBounds(25, 25, sizeAdmin.width, sizeAdmin.height);
        
        // Display LIBRARY BRANCH data
        JLabel branchLabel = new JLabel("Logged in as " + Server.getLibName(libId) + " in " + Server.getLibLoc(libId));
        panel.add(branchLabel);
        Dimension sizeBranch = branchLabel.getPreferredSize();
        branchLabel.setBounds(55, 55, sizeBranch.width, sizeBranch.height);
        // END Display LIBRARY BRANCH data
        
        // Add Copy Text field
        JLabel addCopyLabel = new JLabel("Add Document Copy:");
        panel.add(addCopyLabel);
        Dimension sizeCopy = addCopyLabel.getPreferredSize();
        addCopyLabel.setBounds(25, 85, sizeCopy.width, sizeCopy.height);
        
        JButton addCopyBtn = new JButton("Add");
        addCopyBtn.setBounds(450, 85, 55, 30);
        addCopyBtn.setBorder(null);
        addCopyBtn.addActionListener(new addCopy());
        panel.add(addCopyBtn);
        
        addCopyDocID = new JTextField(10);
        addCopyDocID.setBounds(20, 100, 200, 20);
        addCopyDocID.setText("Doc ID");
        panel.add(addCopyDocID);
        
        addCopyLoc = new JTextField(10);
        addCopyLoc.setBounds(220, 100, 200, 20);
        addCopyLoc.setText("###L##");
        panel.add(addCopyLoc);
        
        // Add Reader Text field
        JLabel addReaderLabel = new JLabel("Add New Reader:");
        panel.add(addReaderLabel);
        Dimension sizeReader = addReaderLabel.getPreferredSize();
        addReaderLabel.setBounds(25, 135, sizeReader.width, sizeReader.height);
        
        addReaderID = new JTextField(10);
        addReaderID.setBounds(20, 150, 100, 20);
        addReaderID.setText("Reader ID");
        panel.add(addReaderID);
        
        addReaderType = new JTextField(10);
        addReaderType.setBounds(120, 150, 100, 20);
        addReaderType.setText("Type");
        panel.add(addReaderType);
        
        addReaderName = new JTextField(10);
        addReaderName.setBounds(220, 150, 100, 20);
        addReaderName.setText("Name");
        panel.add(addReaderName);
        
        addReaderAddress = new JTextField(10);
        addReaderAddress.setBounds(320, 150, 100, 20);
        addReaderAddress.setText("Address");
        panel.add(addReaderAddress);
        
        JButton addreaderBtn = new JButton("Add");
        addreaderBtn.setBounds(450, 135, 55, 30);
        addreaderBtn.setBorder(null);
        addreaderBtn.addActionListener(new addReader());
        panel.add(addreaderBtn);

        // Search Copy Text field
        JLabel searchCopyLabel = new JLabel("Search Document Copy:");
        panel.add(searchCopyLabel);
        Dimension searchSize = searchCopyLabel.getPreferredSize();
        searchCopyLabel.setBounds(25, 185, searchSize.width, searchSize.height);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(450, 185, 55, 30);
        searchBtn.setBorder(null);
        searchBtn.addActionListener(new Quit());
        panel.add(searchBtn);

        searchField = new JTextField(10);
        searchField.setBounds(20, 200, 400, 20);
        panel.add(searchField);
        
        // Average Fine display
        JLabel fineLabel = new JLabel("Avg. Fine for All:");
        panel.add(fineLabel);
        Dimension sizeFine = fineLabel.getPreferredSize();
        fineLabel.setBounds(25, 465, sizeFine.width, sizeFine.height);
        
        // Quit button
        JButton logOutBtn = new JButton("Quit");
        logOutBtn.setBounds(170, 500, 80, 30);
        logOutBtn.setBorder(null);
        logOutBtn.addActionListener(new Quit());
        panel.add(logOutBtn);
        
        // Modify button
        JButton modifyBtn = new JButton("Modify");
        modifyBtn.setBounds(270, 500, 80, 30);
        modifyBtn.setBorder(null);
        modifyBtn.addActionListener(new modify());
        panel.add(modifyBtn);
        
        // Top 10 borrowed field
        JLabel topTenBorrowLabel = new JLabel("Top 10 Borrowers:");
        panel.add(topTenBorrowLabel);
        Dimension sizeTenBorrow = topTenBorrowLabel.getPreferredSize();
        topTenBorrowLabel.setBounds(25, 240, sizeTenBorrow.width, sizeTenBorrow.height);
        
        /*Object[][] topTenBorrowersData =
            {{"RName 1", "13"},
                    {"RName 2", "12"},
                    {"RName 3", "35"},
                    {"RName 4", "33"},
                    {"RName 5", "13"},
                    {"RName 6", "32"},
                    {"RName 7", "100"},
                    {"RName 8", "1"},
                    {"RName 9", "0"},
                    {"RName 10", "2"}};*/
	    
	    Object[][] topTenBorrowersData = topTenBorrowers(libId);

	    String[] topTenBorrowersColumns = {"Name", "# Borrowed"};
	    
	    Server.topTenBorrowers(libId);
	
	    JTable borrowsTable = new JTable(topTenBorrowersData, topTenBorrowersColumns);
	    JScrollPane scrollBorrows = new JScrollPane(borrowsTable);
	    scrollBorrows.setBounds(10, 260, 175, 150);
	    panel.add(scrollBorrows);
        
        // Top 10 books field
        JLabel topTenBooksLabel = new JLabel("Top 10 Books:");
        panel.add(topTenBooksLabel);
        Dimension sizeTenBooks = topTenBooksLabel.getPreferredSize();
        topTenBooksLabel.setBounds(200, 240, sizeTenBooks.width, sizeTenBooks.height);
        
        Server.topTenBooks(libId);
        
        String[] topTenBooksColumns = {"Title"};
        /*Object[][] topTenBooksData =
                {{"Book Name 1", "13"},
                        {"Book Name 2", ""},
                        {"Book Name 3", ""},
                        {"Book Name 4", ""},
                        {"Book Name 5", ""},
                        {"Book Name 6", ""},
                        {"Book Name 7", ""},
                        {"Book Name 8", ""},
                        {"Book Name 9", ""},
                        {"Book Name 10", ""}};*/
	     Object[][] topTenBooksData = topTenBooks(libId);

        JTable topTenBooksTable = new JTable(topTenBooksData, topTenBooksColumns);
        JScrollPane scrollTopTenBooks = new JScrollPane(topTenBooksTable);
        scrollTopTenBooks.setBounds(200, 260, 160, 150);
        panel.add(scrollTopTenBooks);
        
        // Top 10 this year label
        JLabel topTenYr = new JLabel("Top 10 Books (Year):");
        panel.add(topTenYr);
        Dimension sizeTenYr = topTenYr.getPreferredSize();
        topTenYr.setBounds(380, 240, sizeTenYr.width, sizeTenYr.height);
        
        Server.topTenBooksYr(libId);
        
        String[] topTenBooksYrColumns = {"Title"};
        /*Object[][] topTenBooksYrData =
                {{"Book Name 1", "13"},
                        {"Book Name 2", ""},
                        {"Book Name 3", ""},
                        {"Book Name 4", ""},
                        {"Book Name 5", ""},
                        {"Book Name 6", ""},
                        {"Book Name 7", ""},
                        {"Book Name 8", ""},
                        {"Book Name 9", ""},
                        {"Book Name 10", ""}};*/
	    
	    Object[][] topTenBooksYrData = topTenBooksYr(libId);

        JTable topTenBooksYrTable = new JTable(topTenBooksYrData, topTenBooksYrColumns);
        JScrollPane scrollTopTenBooksYr = new JScrollPane(topTenBooksYrTable);
        scrollTopTenBooksYr.setBounds(380, 260, 160, 150);
        panel.add(scrollTopTenBooksYr);
        
        // Set table characteristics
        frame.setSize(570, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    
    class Quit implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
    
    class modify implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new Modify();
		}
	}
    
    class addReader implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String readerID = addReaderID.getText();
			String readerType = addReaderType.getText();
			String readerName = addReaderName.getText();
			String readerAddr = addReaderAddress.getText();
			
			boolean isValidInput = false; // flag if input is valid
			
			// Check if input is not null
			if (readerID != null && readerType != null && readerName != null && readerAddr != null) {
				int readID = 0;
				isValidInput = true; // assume input is valid
				try { // try to convert ID to Integer
					readID = (Integer.parseInt(readerID));
                    
                    if (readID < 1) { // If integer is negative, show Error
                    	isValidInput = false;
                    	new popupMsg("Error", "Invalid Reader ID '" + readID + "' entered.");
                    }
                    
                  
                } catch (NumberFormatException nfe) { // If input is not a number, show Error
                	isValidInput = false;
                	new popupMsg("Error", "Invalid Reader ID '" + readID + "' entered.");
                }
				
				// if no errors occurred, open Admin Functions
				if (isValidInput) {
					//Run Query
        			boolean querySuccess = Server.addReader(readID, readerType, readerName, readerAddr);
        			if (querySuccess) {
        				addReaderID.setText("Reader ID");
        		        addReaderType.setText("Type");
        		        addReaderName.setText("Name");
        		        addReaderAddress.setText("Address");
        			}
				}
        	} else {
        		new popupMsg("Error", "Some reader information is missing. Check input.");
        	}
		}
	}
    
    class addCopy implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String docid = addCopyDocID.getText();
			String position = addCopyLoc.getText();
			
			boolean isValidInput = false; // flag if input is valid
			
			// Check if input is not null
			if (docid != null && position != null) {
				int docID = 0;
				isValidInput = true; // assume input is valid
				try { // try to convert ID to Integer
					docID = (Integer.parseInt(docid));
                    
                    if (docID < 1) { // If integer is negative, show Error
                    	isValidInput = false;
                    	new popupMsg("Error", "Invalid Document ID '"+docid+"' entered.");
                    }
                    
                  
                } catch (NumberFormatException nfe) { // If input is not a number, show Error
                	isValidInput = false;
                	new popupMsg("Error", "Invalid Document ID '"+docid+"' entered.");
                }
				
				// if no errors occurred, open Admin Functions
				if (isValidInput) {
					//Run Query
        			boolean querySuccess = Server.addDocCopy(docID, libId, position);
        			if (querySuccess) {
        				addCopyDocID.setText("Doc ID");
        		        addCopyLoc.setText("###L##");
        			}
				}
        	} else {
        		new popupMsg("Error", "Some reader information is missing. Check input.");
        	}
		}
	}
	
    public Object[][] topTenBorrowers(int libid) {
        return Server.topTenBorrowers(libid);
    }
    public Object[][] topTenBooks(int libid) {
        return Server.topTenBooks(libid);
    }
    public Object[][] topTenBooksYr(int libid) {
        return Server.topTenBooksYr(libid);
    }
    
    /*public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	if (args.length > 0) {
            		AdminFunctions adminFntn = new AdminFunctions(Integer.parseInt(args[0]));
            	} else {
            		AdminFunctions adminFntn = new AdminFunctions();
            	}
            }
        });
    }*/
}
