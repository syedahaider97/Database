import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.text.DecimalFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminFunctions extends JFrame {
	JFrame frame;
	private int libId = 0;
	private JTextField addCopyDocID, addCopyLoc, addReaderType, addReaderName, addReaderAddress, searchField;

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
		frame = new JFrame("Administrator Functions");

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

		String CopyDocMessage = "Doc ID";
		addCopyDocID.setText(CopyDocMessage);
		// Focus Listener
		addCopyDocID.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(addCopyDocID.getText());
				if (addCopyDocID.getText().compareTo(CopyDocMessage) == 0) {
					System.out.println("YES!!");
					addCopyDocID.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (addCopyDocID.getText().compareTo("") == 0) {
					addCopyDocID.setText(CopyDocMessage);
				}
			}
		});

		panel.add(addCopyDocID);

		addCopyLoc = new JTextField(10);
		addCopyLoc.setBounds(220, 100, 200, 20);

		String CopyLoc = "###L##";
		addCopyLoc.setText(CopyLoc);
		// Focus Listener
		addCopyLoc.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(addCopyLoc);
				if (addCopyLoc.getText().compareTo(CopyLoc) == 0) {
					System.out.println("YES!!");
					addCopyLoc.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (addCopyLoc.getText().compareTo("") == 0) {
					addCopyLoc.setText(CopyLoc);
				}
			}
		});

		panel.add(addCopyLoc);

		// Add Reader Text field
		JLabel addReaderLabel = new JLabel("Add New Reader:");
		panel.add(addReaderLabel);
		Dimension sizeReader = addReaderLabel.getPreferredSize();
		addReaderLabel.setBounds(25, 135, sizeReader.width, sizeReader.height);

		addReaderType = new JTextField(10);
		addReaderType.setBounds(20, 150, 133, 20);

		String ReaderTypeMessage = "Type";
		addReaderType.setText(ReaderTypeMessage);
		// Focus Listener
		addReaderType.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(addReaderType.getText());
				if (addReaderType.getText().compareTo(ReaderTypeMessage) == 0) {
					System.out.println("YES!!");
					addReaderType.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (addReaderType.getText().compareTo("") == 0) {
					addReaderType.setText(ReaderTypeMessage);
				}
			}
		});

		panel.add(addReaderType);

		addReaderName = new JTextField(10);
		addReaderName.setBounds(153, 150, 133, 20);

		String ReaderNameMessage = "Name";
		addReaderName.setText(ReaderNameMessage);
		// Focus Listener
		addReaderName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(addReaderName.getText());
				if (addReaderName.getText().compareTo(ReaderNameMessage) == 0) {
					System.out.println("YES!!");
					addReaderName.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (addReaderName.getText().compareTo("") == 0) {
					addReaderName.setText(ReaderNameMessage);
				}
			}
		});

		panel.add(addReaderName);

		addReaderAddress = new JTextField(10);
		addReaderAddress.setBounds(286, 150, 133, 20);

		String ReaderAddressMessage = "Address";
		addReaderAddress.setText(ReaderAddressMessage);
		// Focus Listener
		addReaderAddress.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(addReaderAddress.getText());
				if (addReaderAddress.getText().compareTo(ReaderAddressMessage) == 0) {
					System.out.println("YES!!");
					addReaderAddress.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (addReaderAddress.getText().compareTo("") == 0) {
					addReaderAddress.setText(ReaderAddressMessage);
				}
			}
		});

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
		searchBtn.addActionListener(new searchCopy());
		panel.add(searchBtn);

		searchField = new JTextField(10);
		searchField.setBounds(20, 200, 400, 20);

		String searchCopyMessage = "Enter Copy number or \"VIEW ALL *\" to see all";
		searchField.setText(searchCopyMessage);
		// Focus Listener
		searchField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				System.out.println(searchField.getText());
				if (searchField.getText().compareTo(searchCopyMessage) == 0) {
					System.out.println("YES!!");
					searchField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (searchField.getText().compareTo("") == 0) {
					searchField.setText(searchCopyMessage);
				}
			}
		});

		panel.add(searchField);

		// Average Fine display
		Server.avgFine(libId);
		double avgFineData = Server.avgFine(libId);
		DecimalFormat df = new DecimalFormat("0.00");
		JLabel fineLabel = new JLabel("Avg. Fine for All: $" + df.format(avgFineData));
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

		/*
		 * Object[][] topTenBorrowersData = {{"RName 1", "13"}, {"RName 2", "12"},
		 * {"RName 3", "35"}, {"RName 4", "33"}, {"RName 5", "13"}, {"RName 6", "32"},
		 * {"RName 7", "100"}, {"RName 8", "1"}, {"RName 9", "0"}, {"RName 10", "2"}};
		 */

		Object[][] topTenBorrowersData = Server.topTenBorrowers(libId);

		String[] topTenBorrowersColumns = { "Name", "# Borrowed" };

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

		String[] topTenBooksColumns = { "Title" };
		/*
		 * Object[][] topTenBooksData = {{"Book Name 1", "13"}, {"Book Name 2", ""},
		 * {"Book Name 3", ""}, {"Book Name 4", ""}, {"Book Name 5", ""},
		 * {"Book Name 6", ""}, {"Book Name 7", ""}, {"Book Name 8", ""},
		 * {"Book Name 9", ""}, {"Book Name 10", ""}};
		 */
		Object[][] topTenBooksData = Server.topTenBooks(libId);

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

		String[] topTenBooksYrColumns = { "Title" };
		/*
		 * Object[][] topTenBooksYrData = {{"Book Name 1", "13"}, {"Book Name 2", ""},
		 * {"Book Name 3", ""}, {"Book Name 4", ""}, {"Book Name 5", ""},
		 * {"Book Name 6", ""}, {"Book Name 7", ""}, {"Book Name 8", ""},
		 * {"Book Name 9", ""}, {"Book Name 10", ""}};
		 */

		Object[][] topTenBooksYrData = Server.topTenBooksYr(libId);

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
			frame.dispose();
			new Welcome();
		}
	}

	class modify implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			new Modify();
		}
	}

	class addReader implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String readerType = addReaderType.getText();
			String readerName = addReaderName.getText();
			String readerAddr = addReaderAddress.getText();

			// Check if input is not null
			if (readerType != null && readerName != null && readerAddr != null) {

				// Run Query
				boolean querySuccess = Server.addReader(readerType, readerName, readerAddr);
				if (querySuccess) {
					addReaderType.setText("Reader Type");
					addReaderName.setText("Name");
					addReaderAddress.setText("Address");
				}

				/*
				 * } else { new popupMsg("Error", "Invalid Reader information. Check input."); }
				 */
			}
		}
	}

	class addCopy implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String docid = addCopyDocID.getText();
			String position = addCopyLoc.getText();

			Pattern pattern = Pattern.compile("(100|010|001)[A-Z]{1}[0-9]{2}"); // REGEX to check if first three numbers
																				// are integers, followed by a letter,
																				// followed by 2 integers
			Matcher matcher = pattern.matcher(position);

			boolean isValidInput = false; // flag if input is valid

			// Check if input is not null
			if (docid != null && position != null && position.length() == 6) {
				int docID = 0;
				isValidInput = true; // assume input is valid
				try { // try to convert ID to Integer
					docID = (Integer.parseInt(docid));

					if (docID < 1) { // If integer is negative, show Error
						isValidInput = false;
						new popupMsg("Error", "Invalid Document ID '" + docid + "' entered.");
					}

				} catch (NumberFormatException nfe) { // If input is not a number, show Error
					isValidInput = false;
					new popupMsg("Error", "Invalid Document ID '" + docid + "' entered.");
				}

				// if no errors occurred, open Admin Functions
				if (isValidInput && matcher.matches()) {
					// Run Query
					boolean querySuccess = Server.addDocCopy(docID, libId, position);
					if (querySuccess) {
						addCopyDocID.setText("Doc ID");
						addCopyLoc.setText("###L##");
					}
				} else {
					new popupMsg("Error", "Invalid Location Entered. Check input.");
				}
			} else {
				new popupMsg("Error", "Invalid Copy information. Check input.");
			}
		}
	}

	class searchCopy implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String copyID = searchField.getText();

			Object[][] table = null;
			if (copyID.compareTo("VIEW ALL *") == 0) {
				table = Server.viewAllCopies(libId);
			} else {
				table = Server.searchByCopyID(copyID, libId);
			}
			if (table == null || table.length == 0) {
				new popupMsg("Error", "No results found.");
			} else {
				String[] colTitles = { "DocID", "Copy #", "Location" };
				new SearchResult(table, colTitles);
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
	
	public double avgFine(int libid) {
		return Server.avgFine(libid);
	}


	/*
	 * public static void main(String[] args) {
	 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
	 * if (args.length > 0) { AdminFunctions adminFntn = new
	 * AdminFunctions(Integer.parseInt(args[0])); } else { AdminFunctions adminFntn
	 * = new AdminFunctions(); } } }); }
	 */
}
