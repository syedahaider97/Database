import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ReturnCopy extends JFrame {
	
	int readerId;
	ReaderFunctions frame;
	JTextField docField,libField;
	public ReturnCopy(ReaderFunctions frame, int readerId) {
		
		super("Return a Copy");
		this.readerId = readerId;
		this.frame = frame;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("Return a Copy");
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel docIdPanel = new JPanel();
		JLabel docLabel = new JLabel("Document ID:      ");
		docField = new JTextField(15);
		docIdPanel.add(docLabel); docIdPanel.add(docField);
		
		JPanel libIdPanel = new JPanel();
		JLabel libIdLabel = new JLabel("Library ID:           ");
		libField = new JTextField(15);
		libIdPanel.add(libIdLabel); libIdPanel.add(libField);
		
		JButton submit = new JButton("Submit");
		submit.setAlignmentX(CENTER_ALIGNMENT);
		submit.addActionListener(new Submit());
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(docIdPanel);
		panel.add(new JLabel(" "));
		panel.add(libIdPanel);
		panel.add(new JLabel(" "));
		panel.add(submit);
		panel.add(new JLabel(" "));
		
		add(panel);
		setSize(300,250);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	class Submit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(Server.returnCopy(readerId,docField.getText(),libField.getText())) {
				dispose();
				frame.dispose();
				new ReaderFunctions(readerId);
			}
		}
		
	}
}
