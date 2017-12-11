import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBook extends JFrame {
	
	private JTextField titleField, pdateField, publisherField, paddrField, isbnfield, authorfield;
	
	private String type;
	
	public AddBook(String type, String line) {
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
		JPanel pdatePanel = new JPanel();
		JLabel pdateLabel = new JLabel("Publisher Date:");
		pdateField = new JTextField(15);
		pdatePanel.add(pdateLabel); pdatePanel.add(pdateField);
		
		//panel.add(new JLabel(" "));
		panel.add(pdatePanel);
		
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
			
			System.out.println(titleField.getText() + pdateField.getText() + publisherField.getText());
			
			if(type == "add") {
				//Add Query
			}
			else if(type == "update") {
				//Update Query
			}
			
		}
	}

}
