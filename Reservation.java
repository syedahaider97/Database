import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation extends JFrame {

	JTextField docField, libField;
	String type;
	int readerId;
	ReaderFunctions frame;
	
	public Reservation(ReaderFunctions frame,int readerID, String type) {
		
		super(type + " A Book");
		System.out.println(type);
		this.type = type;
		this.readerId = readerID;
		this.frame = frame;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel(type + " A Book");
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel docIdPanel = new JPanel();
		JLabel docLabel = new JLabel("Document ID:      ");
		docField = new JTextField(15);
		docIdPanel.add(docLabel); docIdPanel.add(docField);
		
		JPanel libIdPanel = new JPanel();
		JLabel libLabel = new JLabel("Library ID:             ");
		libField = new JTextField(15);
		libIdPanel.add(libLabel); libIdPanel.add(libField);
		
		JButton submit = new JButton(type);
		submit.setAlignmentX(CENTER_ALIGNMENT);
		submit.addActionListener(new Submit());

		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(docIdPanel);
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
			if(type.equals("Reserve")) {
				if(Server.reserve(readerId,docField.getText(),libField.getText())) {
					dispose();
					frame.dispose();
					new ReaderFunctions(readerId);
				
				}
				
			}
			else if (type.equals("Borrow")) {
				Server.borrow(readerId,docField.getText(),libField.getText());
			}
		}
		
		
	}
}
