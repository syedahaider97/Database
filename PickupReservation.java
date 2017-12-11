import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PickupReservation extends JFrame {
	
	JTextField docField;
	int libId;
	int readerId;
	ReaderFunctions frame;
	
	public PickupReservation(ReaderFunctions frame,int readerId) {
		
		this.readerId = readerId;
		this.frame = frame;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("Pickup A Reservation");
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel docIdPanel = new JPanel();
		JLabel docLabel = new JLabel("Document ID:      ");
		docField = new JTextField(15);
		docIdPanel.add(docLabel); docIdPanel.add(docField);
		
		JButton submit = new JButton("Submit");
		submit.setAlignmentX(CENTER_ALIGNMENT);
		submit.addActionListener(new Submit());
		
		panel.add(title);
		panel.add(docIdPanel);
		
		add(panel);
		setSize(300,250);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	class Submit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(Server.pickup(readerId,docField.getText(),libId)) {
				dispose();
				frame.dispose();
				new ReaderFunctions(readerId);
			}
		}
		
	}
}
