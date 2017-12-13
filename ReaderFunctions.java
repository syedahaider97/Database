import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ReaderFunctions extends JFrame{
	private int readerID = 0;
	ReaderFunctions frame;
	private JTextField docText, titleText, pubText;
	
	// Default Constructor -- Displays error if no input passed in to menu
	public ReaderFunctions() {
		this.readerID = 0;
		// Show Error message
		new popupMsg("Error", "No Card Number entered.");
	}
	
	public ReaderFunctions(int readID) {
		// Set Window title and set readerID variable for use
		super("Reader Functions");
		this.readerID = readID;
		frame = this;
		
		// Set panel Layout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		// Set panel title
		JLabel title = new JLabel("Reader Functions Menu");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Helvetica",Font.BOLD,32));
		
		// Display READER data
        JLabel readLabel = new JLabel("Logged in as " + Server.getRName(readID) + " with Card Number: " + readerID);
        panel.add(readLabel);
        Dimension sizeBranch = readLabel.getPreferredSize();
        readLabel.setBounds(55, 100, sizeBranch.width, sizeBranch.height);
        readLabel.setAlignmentX(CENTER_ALIGNMENT);
        // END Display READER data
		
        // Search by Doc ID
		JLabel search = new JLabel("Search");
		search.setAlignmentX(CENTER_ALIGNMENT);
		search.setFont(new Font("Helvetica",Font.BOLD,24));
		
		JPanel docSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel dSearch = new JLabel("          Search by Document ID");
		docSearch.add(dSearch);
		
		JPanel docPanel = new JPanel();
		docText = new JTextField(30);
		
		String docTextMessage = "Enter Document ID or \"VIEW ALL *\" to see all";
		docText.setText(docTextMessage);
		// Focus Listener
		docText.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
		  
				System.out.println(docText.getText());
		  		if (docText.getText().compareTo(docTextMessage) == 0) {
		  			System.out.println("YES!!");
		  			docText.setText("");
		  		}
		   }
		
		   public void focusLost(FocusEvent e) {
		     if (docText.getText().compareTo("") == 0) {
		    	 docText.setText(docTextMessage);
		     }
		   }
		});
		
		JButton docButton = new JButton("Search");
		docButton.addActionListener(new DocButton());
		docPanel.add(docText); docPanel.add(docButton);
		
		// Search by Title
		JPanel titleSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tSearch = new JLabel("          Search by Title");
		titleSearch.add(tSearch);
		
		JPanel titlePanel = new JPanel();
		titleText = new JTextField(30);
		
		String titleTextMessage = "Enter Title or \"VIEW ALL *\" to see all";
		titleText.setText(titleTextMessage);
		// Focus Listener
		titleText.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
		  
				System.out.println(titleText.getText());
		  		if (titleText.getText().compareTo(titleTextMessage) == 0) {
		  			System.out.println("YES!!");
		  			titleText.setText("");
		  		}
		   }
		
		   public void focusLost(FocusEvent e) {
		     if (titleText.getText().compareTo("") == 0) {
		    	 titleText.setText(titleTextMessage);
		     }
		   }
		});
		
		JButton titleButton = new JButton("Search");
		titleButton.addActionListener(new TitleButton());
		titlePanel.add(titleText); titlePanel.add(titleButton);
		
		// Search by Publisher
		JPanel pubSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pSearch = new JLabel("          Search by Publisher Name");
		pubSearch.add(pSearch);
		
		JPanel pubPanel = new JPanel();
		pubText = new JTextField(30);
		
		String pubTextMessage = "Enter Publisher Name or \"VIEW ALL *\" to see all";
		pubText.setText(pubTextMessage);
		// Focus Listener
		pubText.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
		  
				System.out.println(pubText.getText());
		  		if (pubText.getText().compareTo(pubTextMessage) == 0) {
		  			System.out.println("YES!!");
		  			pubText.setText("");
		  		}
		   }
		
		   public void focusLost(FocusEvent e) {
		     if (pubText.getText().compareTo("") == 0) {
		    	 pubText.setText(pubTextMessage);
		     }
		   }
		});
		
		JButton pubButton = new JButton("Search");
		pubButton.addActionListener(new PubButton());
		pubPanel.add(pubText); pubPanel.add(pubButton);
		
		// Show reservations
		JLabel reservation = new JLabel("Current Reservations");
		reservation.setAlignmentX(CENTER_ALIGNMENT);
		reservation.setFont(new Font("Helvetica",Font.BOLD,24));
		
		String[] columns = {"Title","Status","Fine","Date Borrowed"};
		
		
		//COMPUTE ACTUAL DATA BASED ON PASSED IN READER ID
		Object[][] data = getReservationData(readerID);
		
		JTable table = new JTable(data,columns);
		JScrollPane scroll = new JScrollPane(table);
		
		JPanel buttonPanel = new JPanel();
		
		JButton reserve = new JButton("New Reservation");
		reserve.setAlignmentX(CENTER_ALIGNMENT);
		reserve.addActionListener(new Reserve());
		
		JButton pickup = new JButton("Pickup Reservation");
		pickup.setAlignmentX(CENTER_ALIGNMENT);
		pickup.addActionListener(new Pickup());
		
		JButton borrow = new JButton("Borrow a Book");
		borrow.setAlignmentX(CENTER_ALIGNMENT);
		borrow.addActionListener(new Borrow());
		
		JButton rtnCopy = new JButton("Return Book");
		rtnCopy.setAlignmentX(CENTER_ALIGNMENT);
		rtnCopy.addActionListener(new Return());
		
		buttonPanel.add(reserve);
		buttonPanel.add(pickup);
		buttonPanel.add(borrow);
		buttonPanel.add(rtnCopy);
		
		JButton quit = new JButton("Quit");
		quit.setAlignmentX(CENTER_ALIGNMENT);
		quit.addActionListener(new Quit());
		
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(search);
		panel.add(docSearch);
		panel.add(docPanel);
		panel.add(titleSearch);
		panel.add(titlePanel);
		panel.add(pubSearch);
		panel.add(pubPanel);
		panel.add(new JLabel(" "));
		panel.add(reservation);
		panel.add(new JLabel(" "));
		panel.add(scroll);
		panel.add(new JLabel(" "));
		panel.add(buttonPanel);
		panel.add(new JLabel(" "));
		panel.add(quit);
		panel.add(new JLabel(" "));
		
		add(panel);
		setSize(550,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public Object[][] getReservationData(int readerId) {
		
		return Server.getReservationData(readerId);
	}
	
	

	class DocButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {

	    	String docId = docText.getText();
	    	Object[][] table = null;
	    	
	    	if (docId.compareTo("VIEW ALL *") == 0) {
	    		table = Server.viewAllDocs();
	    	} else {
	    		table = Server.searchByDocId(docId);
	    	}
	    	
	    	if(table == null || table.length == 0) {
	    		new popupMsg("Error","No results found.");
	    	}
	    	else{
	    		new SearchResult(table);
	    	}
		}
	}
	
	class TitleButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {

	    	String title = titleText.getText();
	    	Object[][] table = null;
	    	if (title.compareTo("VIEW ALL *") == 0) {
	    		table = Server.viewAllDocs();
	    	} else {
	    		table = Server.searchByTitle(title);
	    	}
	    	if(table == null || table.length == 0) {
	    		new popupMsg("Error","No results found.");
	    	}
	    	else{
	    		new SearchResult(table);
	    	}
		}
	}
	
	class PubButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {
	    	
	    	String publisher = pubText.getText();
	    	
	    	Object[][] table = null;
	    	if (publisher.compareTo("VIEW ALL *") == 0) {
	    		table = Server.viewAllDocs();
	    	} else {
	    		table = Server.searchByPublisher(publisher);
	    	}
	    	if(table == null || table.length == 0) {
	    		new popupMsg("Error","No results found.");
	    	}
	    	else{
	    		new SearchResult(table);
	    	}
		}
	}
	class Reserve implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			new Reservation(frame,readerID,"Reserve");
			
		}
	}
	
	class Pickup implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			new PickupReservation(frame,readerID);
			
		}
	}
	
	class Return implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			new ReturnCopy(frame,readerID);
			
		}
	}
	class Borrow implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new Reservation(frame,readerID,"Borrow");
			
		}
		
	}
	class Quit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
}