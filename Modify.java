import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Modify extends JFrame {

	public Modify() {
		super("Modify Database");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel label = new JLabel("What Would You");
		label.setFont(new Font("Helvetica",Font.BOLD,24));
		label.setAlignmentX(CENTER_ALIGNMENT);
		JLabel label2 = new JLabel("Like To Do?");
		label2.setFont(new Font("Helvetica",Font.BOLD,24));
		label2.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton addDoc = new JButton("Add Documnts");
		addDoc.setAlignmentX(CENTER_ALIGNMENT);
		addDoc.addActionListener(new AddDoc());
		
		JButton remDoc = new JButton("Remove Documnts");
		remDoc.setAlignmentX(CENTER_ALIGNMENT);
		remDoc.addActionListener(new RemDoc());
		
		JButton updateDoc = new JButton("Update Documnts");
		updateDoc.setAlignmentX(CENTER_ALIGNMENT);
		updateDoc.addActionListener(new UpdateDoc());
		
		JButton back = new JButton("Back");
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addActionListener(new Back());
		
		panel.add(new JLabel(" "));
		panel.add(label);
		panel.add(label2);
		panel.add(new JLabel(" "));
		panel.add(addDoc);
		panel.add(new JLabel(" "));
		panel.add(remDoc);
		panel.add(new JLabel(" "));
		panel.add(updateDoc);
		panel.add(new JLabel(" "));
		panel.add(back);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250,320);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	public static void main(String args[]) {
		
		new Modify();
	}
	
	class AddDoc implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
	
	class RemDoc implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
	class UpdateDoc implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	class Back implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
