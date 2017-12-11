import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProceeding extends JFrame {
	
	private JTextField titleField, pdateField, publisherField, paddrField, cdatefield, clocfield, chairfield;
	
	private String type;
	
	public AddProceeding(String type, String line) {
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
		
		// Conference Date entry field
		JPanel cdatepanel = new JPanel();
		JLabel cdatelabel = new JLabel("Conference Date:");
		cdatefield = new JTextField(15);
		cdatepanel.add(cdatelabel); cdatepanel.add(cdatefield);
		
		//panel.add(new JLabel(" "));
		panel.add(cdatepanel);
		
		// Conference Location entry field
		JPanel clocpanel = new JPanel();
		JLabel cloclabel = new JLabel("Location:");
		clocfield = new JTextField(15);
		clocpanel.add(cloclabel); clocpanel.add(clocfield);
		
		//panel.add(new JLabel(" "));
		panel.add(clocpanel);
		
		// Conference Chair entry field
		JPanel chairpanel = new JPanel();
		JLabel chairlabel = new JLabel("Chairman Name:");
		chairfield = new JTextField(15);
		chairpanel.add(chairlabel); chairpanel.add(chairfield);
		
		//panel.add(new JLabel(" "));
		panel.add(chairpanel);
		
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
