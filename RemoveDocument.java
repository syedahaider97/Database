import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RemoveDocument extends JFrame {
	
	JTextField docField;

	public RemoveDocument() {
		super("Remove a Document");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("Remove a Document");
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel docIdPanel = new JPanel();
		JLabel docLabel = new JLabel("Document ID:      ");
		docField = new JTextField(15);
		docIdPanel.add(docLabel); docIdPanel.add(docField);
		
		JPanel buttonPanel = new JPanel();
		
		JButton back = new JButton("Back");
		back.addActionListener(new Back());
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel space = new JLabel("      ");
		
		JButton submit = new JButton("Remove");
		submit.addActionListener(new Remove());
		submit.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanel.add(back); buttonPanel.add(space); buttonPanel.add(submit);
		
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(docIdPanel);
		panel.add(new JLabel(" "));
		panel.add(buttonPanel);
		panel.add(new JLabel(" "));
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,200);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}

	class Back implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	class Remove implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			int docId = Integer.parseInt(docField.getText());
			//Remove Query
			Server.removeByID(docId);
			//Success Message
		}
	}
	
}
