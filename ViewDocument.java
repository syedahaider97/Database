import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ViewDocument extends JFrame{

	public ViewDocument(Object[][] entries) {
		super("View Document");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("View All Documents");
		title.setFont(new Font("Helvetica",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		
		String[] columns = {"Document ID","Title","Publisher Name","Publisher Date"};
		
		JPanel tablePanel = new JPanel();
		
		JTable data = new JTable(entries,columns);
		JScrollPane scroll = new JScrollPane(data);
	
		
		JButton back = new JButton("Back");
		back.addActionListener(new Back());
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(scroll);
		panel.add(new JLabel(" "));
		panel.add(back);
		panel.add(new JLabel(" "));
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	class Back implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}		
	}
}