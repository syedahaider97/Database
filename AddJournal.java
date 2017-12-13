import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddJournal extends JFrame {
	
	private JTextField titleField, newtitleField, pyearField, pmonthField, pdayField, publisherField, paddrField, jvolfield, jissfield, scopefield, cedfield, invfield;
	
	private int type = 0; // 0 = add, 1 = update
	
	public AddJournal(int type, String line) {
		// Set Title and Type of Operation to perform (type Add or Update)
		super(line);
		this.type = type;
		
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
		
		if (type == 1) { // If we are updating, prompt for a replacement title
			// Title entry field
			JPanel newtitlePanel = new JPanel();
			JLabel newtitleLabel = new JLabel("New Title:");
			newtitleField = new JTextField(15);
			newtitlePanel.add(newtitleLabel); newtitlePanel.add(newtitleField);
			
			//panel.add(new JLabel(" "));
			panel.add(newtitlePanel);
		}
		
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
		
		// Journal Volume entry field
		JPanel jvolpanel = new JPanel();
		JLabel jvollabel = new JLabel("Volume:");
		jvolfield = new JTextField(15);
		jvolpanel.add(jvollabel); jvolpanel.add(jvolfield);
		
		//panel.add(new JLabel(" "));
		panel.add(jvolpanel);
		
		// Journal Issue entry field
		JPanel jisspanel = new JPanel();
		JLabel jisslabel = new JLabel("Issue #:");
		jissfield = new JTextField(15);
		jisspanel.add(jisslabel); jisspanel.add(jissfield);
		
		//panel.add(new JLabel(" "));
		panel.add(jisspanel);
		
		// Journal Scope entry field
		JPanel scopepanel = new JPanel();
		JLabel scopelabel = new JLabel("Scope:");
		scopefield = new JTextField(15);
		scopepanel.add(scopelabel); scopepanel.add(scopefield);
		
		//panel.add(new JLabel(" "));
		panel.add(scopepanel);
		
		// Chief Editor entry field
		JPanel cedpanel = new JPanel();
		JLabel cedlabel = new JLabel("Chief Editor:");
		cedfield = new JTextField(15);
		cedpanel.add(cedlabel); cedpanel.add(cedfield);
		
		//panel.add(new JLabel(" "));
		panel.add(cedpanel);
		
		// Inv Editor entry field
		JPanel invpanel = new JPanel();
		JLabel invlabel = new JLabel("Guest Editor:");
		invfield = new JTextField(15);
		invpanel.add(invlabel); invpanel.add(invfield);
		
		//panel.add(new JLabel(" "));
		panel.add(invpanel);
		
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
			
			String jvol = jvolfield.getText();
			String jiss = jissfield.getText();
			String scope = scopefield.getText(); 
			String ceditor = cedfield.getText(); 
			String inveditor = invfield.getText();
			
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
			
			if (jiss.compareTo("") != 0 && (Integer.parseInt(jiss) > 10 || Integer.parseInt(jiss) < 1)) {
				new popupMsg("Error", "Journal Issue must be between 1-10");
			} else {
				if (yrmatcher.matches() && momatcher.matches()) {
					if (!(title.compareTo("") == 0 || pubName.compareTo("") == 0 || pubAddr.compareTo("") == 0 || jvol.compareTo("") == 0 || jiss.compareTo("") == 0 || scope.compareTo("") == 0 || ceditor.compareTo("") == 0 || inveditor.compareTo("") == 0)) {
						String pubDate = "";
						if (Integer.parseInt(month) < 10) {
							month = "0" + Integer.parseInt(month);
						} else if (Integer.parseInt(month) > 31) {
							month = "31";
							pmonthField.setText("31");
						}
						
						if (Integer.parseInt(day) < 1) {
							day = "1";
							pdayField.setText("1");
						} else if (Integer.parseInt(day) > 31) {
							day = "31";
							pdayField.setText("31");
						}
						
						if (Integer.parseInt(year) < currYr) {
							pubDate = year+"-"+month+"-"+day;
						} else {
							pubDate = "2017-"+month+"-"+day;
							pyearField.setText("2017");
						}
						
						System.out.println(pubDate);
						if(type == 0) {
							boolean success = Server.addNewJournal(title, pubDate, pubName, pubAddr, jvol, jiss, scope, ceditor, inveditor, title);
							if (success)
								new popupMsg("Document Added ", "Journal Issue "+jiss+", Volume "+jvol);
						} else if(type == 1) {
							//Update Query
							int docid = Server.findJournalDocID(title); 
							if (docid != 0) {
								String newtitle = newtitleField.getText();
								if (newtitle.compareTo("") != 0) {
									boolean success = false;
									boolean DocAdded = Server.updateDoc(title, pubDate, pubName, pubAddr, newtitle);
									if (DocAdded)
										success = Server.updateJournal(docid, jvol, jiss, scope, ceditor, inveditor, newtitle);
									if (success)
										new popupMsg("Document Updated ", "Journal Issue "+jiss+", Volume "+jvol);
								}
							}
						}
					} else {
						new popupMsg("Error", "Document information is missing.");
					}
				} else {
					new popupMsg("Error", "Date is invalid.");
				}
			}
			
		}
	}

}
