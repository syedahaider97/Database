import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddJournal extends JFrame {
	
	private JTextField titleField, pdateField, publisherField, paddrField, jvolfield, jissfield, scopefield, cedfield, invfield;
	
	private String type;
	
	public AddJournal(String type, String line) {
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
		
		// Journal Volume entry field
		JPanel jvolpanel = new JPanel();
		JLabel jvollabel = new JLabel("Volume #:");
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
		JLabel scopelabel = new JLabel("Issue #:");
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
