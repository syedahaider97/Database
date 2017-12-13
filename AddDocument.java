import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDocument extends JFrame {
	
	JTextField docField, titleField, pdateField, publisherField;
	
	String type;
	public AddDocument(String type, String line) {
		super(line);
		type = this.type;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel(line);
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel docIdPanel = new JPanel();
		JLabel docLabel = new JLabel("Document ID:      ");
		docField = new JTextField(15);
		docIdPanel.add(docLabel); docIdPanel.add(docField);
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Title:                      ");
		titleField = new JTextField(15);
		titlePanel.add(titleLabel); titlePanel.add(titleField);
		
		JPanel pdatePanel = new JPanel();
		JLabel pdateLabel = new JLabel("Publisher Date:   ");
		pdateField = new JTextField(15);
		pdatePanel.add(pdateLabel); pdatePanel.add(pdateField);
		
		JPanel publisherPanel = new JPanel();
		JLabel publisherLabel = new JLabel("Publisher Name: ");
		publisherField = new JTextField(15);
		publisherPanel.add(publisherLabel); publisherPanel.add(publisherField);
		
		JPanel buttonPanel = new JPanel();
		
		JButton back = new JButton("Back");
		back.addActionListener(new Back());
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel space = new JLabel("      ");
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new Submit());
		submit.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanel.add(back); buttonPanel.add(space); buttonPanel.add(submit);
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(docIdPanel);
		panel.add(new JLabel(" "));
		panel.add(titlePanel);
		panel.add(new JLabel(" "));
		panel.add(pdatePanel);
		panel.add(new JLabel(" "));
		panel.add(publisherPanel);
		panel.add(new JLabel(" "));
		panel.add(buttonPanel);
		panel.add(new JLabel(" "));
		
		add(panel);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setSize(300,400);
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
			
			System.out.println(docField.getText() + titleField.getText() + pdateField.getText() + publisherField.getText());
			
			if(type == "add") {
				//Add Query
			}
			else if(type == "update") {
				//Update Query
			}
			
		}
	}

}