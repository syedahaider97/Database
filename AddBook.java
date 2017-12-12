import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBook extends JFrame {
	
	private JTextField titleField, pyearField, pmonthField, pdayField, publisherField, paddrField, isbnfield, authorfield;
	
	private int type = 0; // 0 = add, 1 = update
	
	public AddBook(int type, String line) {
		// Set Title and Type of Operation to perform (type Add or Update)
		super(line);
		type = this.type;
		
		// Set Panel layout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		// Set panel title
		JLabel title = new JLabel(line);
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(new JLabel(" "));
		panel.add(title);
		
		// Title entry field
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Title:");
		titleField = new JTextField(15);
		titlePanel.add(titleLabel); titlePanel.add(titleField);
		
		//panel.add(new JLabel(" "));
		panel.add(titlePanel);
		
		// Publisher date entry field
		
		// Year published
		JPanel pyearPanel = new JPanel();
		JLabel pyearLabel = new JLabel("Year:");
		pyearField = new JTextField(4);
		pyearPanel.add(pyearLabel); pyearPanel.add(pyearField);
		
		//panel.add(new JLabel(" "));
		panel.add(pyearPanel);
		
		// Month published
		JPanel pmonthPanel = new JPanel();
		JLabel pmonthLabel = new JLabel("Month:");
		pmonthField = new JTextField(4);
		pmonthPanel.add(pmonthLabel); pmonthPanel.add(pmonthField);
		
		//panel.add(new JLabel(" "));
		panel.add(pmonthPanel);
		
		// Day published
		JPanel pdayPanel = new JPanel();
		JLabel pdayLabel = new JLabel("Day:");
		pdayField = new JTextField(4);
		pdayPanel.add(pdayLabel); pdayPanel.add(pdayField);
		
		//panel.add(new JLabel(" "));
		panel.add(pdayPanel);
		
		// Publisher name entry field
		JPanel publisherPanel = new JPanel();
		JLabel publisherLabel = new JLabel("Publisher Name:");
		publisherField = new JTextField(15);
		publisherPanel.add(publisherLabel); publisherPanel.add(publisherField);
		
		//panel.add(new JLabel(" "));
		panel.add(publisherPanel);
		
		// Publisher address entry field
		JPanel paddrPanel = new JPanel();
		JLabel paddrLabel = new JLabel("Publisher Addr:");
		paddrField = new JTextField(15);
		paddrPanel.add(paddrLabel); paddrPanel.add(paddrField);
		
		//panel.add(new JLabel(" "));
		panel.add(paddrPanel);
		
		// Book ISBN entry field
		JPanel isbnpanel = new JPanel();
		JLabel isbnlabel = new JLabel("Book ISBN:");
		isbnfield = new JTextField(15);
		isbnpanel.add(isbnlabel); isbnpanel.add(isbnfield);
		
		//panel.add(new JLabel(" "));
		panel.add(isbnpanel);
		
		// Book Author entry field
		JPanel authorpanel = new JPanel();
		JLabel authorlabel = new JLabel("Author Name:");
		authorfield = new JTextField(15);
		authorpanel.add(authorlabel); authorpanel.add(authorfield);
		
		//panel.add(new JLabel(" "));
		panel.add(authorpanel);
		
		// Back button
		JPanel buttonPanel = new JPanel();
		
		JButton back = new JButton("Back");
		back.addActionListener(new Back());
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel space = new JLabel("      ");
		
		// Submit button
		JButton submit = new JButton("Submit");
		submit.addActionListener(new Submit());
		submit.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanel.add(back); buttonPanel.add(space); buttonPanel.add(submit);

		panel.add(new JLabel(" "));
		panel.add(buttonPanel);
		panel.add(new JLabel(" "));
		
		// Panel characteristics
		add(panel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	

	
	class Back implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	class Submit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String isbn = isbnfield.getText(); 
			
			Pattern ipattern = Pattern.compile("[0-9]{10}"); //isbn is 10 digits
			Matcher imatcher = ipattern.matcher(isbn);
			
			String author = authorfield.getText();
			
			String title = titleField.getText(); 
			String pubName = publisherField.getText(); 
			String pubAddr = paddrField.getText();
			
			String year = pyearField.getText();
			String month = pmonthField.getText();
			String day = pdayField.getText();
			
			Pattern yrpattern = Pattern.compile("[0-9]{4}"); //year format ####
			Matcher yrmatcher = yrpattern.matcher(year);
			
			int currYr = Integer.parseInt(Server.getDate().substring(0, 4));
			
			Pattern mopattern = Pattern.compile("((0?[1-9])|(1[0-2]))"); //month format from 01 to 12
			Matcher momatcher = mopattern.matcher(month);
			
			Pattern dpattern = Pattern.compile("((0?[1-9])|([1-2][0-9])|(3[01]))"); //day format from 01 or 31
			Matcher dmatcher = mopattern.matcher(day);
			
			if (imatcher.matches() && Server.isbnExists(isbn)) {
				if (yrmatcher.matches() && momatcher.matches() && dmatcher.matches()) {
					if (!(title.compareTo("") == 0 || pubName.compareTo("") == 0 || pubAddr.compareTo("") == 0 || isbn.compareTo("") == 0 || author.compareTo("") == 0)) {
						
						String pubDate = "";
						if (Integer.parseInt(month) < 10) {
							month = "0" + month;
						}
						
						if (Integer.parseInt(day) < 10) {
							day = "0" + day;
						}
						
						if (Integer.parseInt(year) < currYr) {
							pubDate = year+"-"+month+"-"+day;
						} else {
							pubDate = "2017-"+month+"-"+day;
							pyearField.setText("2017");
						}
						
						System.out.println(pubDate);
						if(type == 0) {
							boolean DocAdded = Server.addNewDoc(title, pubDate, pubName, pubAddr);
							if (DocAdded)
								Server.addNewBook(isbn, author, title);
							
						}
						else if(type == 1) {
							//Update Query
						}
					} else {
						new popupMsg("Error", "Document information is missing.");
					}
				} else {
					new popupMsg("Error", "Date is invalid.");
				}
			} else {
				new popupMsg("Error", "ISBN must be a unique 10-digit number.");
			}
			
		}
	}

}
