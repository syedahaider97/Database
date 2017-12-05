import java.awt.FlowLayout;
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
import javax.swing.JTextField;

public class ReaderFunctions extends JFrame{

	public ReaderFunctions() {
		super("Reader Functions");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Reader Functions Menu");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Helvetica",Font.BOLD,32));
		
		JLabel search = new JLabel("Search");
		search.setAlignmentX(CENTER_ALIGNMENT);
		search.setFont(new Font("Helvetica",Font.BOLD,24));
		
		JPanel docSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel dSearch = new JLabel("          Search by DocID");
		docSearch.add(dSearch);
		
		JPanel docPanel = new JPanel();
		JTextField docText = new JTextField(30);
		JButton docButton = new JButton("Search");
		docButton.addActionListener(new DocButton());
		docPanel.add(docText); docPanel.add(docButton);
		
		JPanel titleSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tSearch = new JLabel("          Search by Title");
		titleSearch.add(tSearch);
		
		JPanel titlePanel = new JPanel();
		JTextField titleText = new JTextField(30);
		JButton titleButton = new JButton("Search");
		titleButton.addActionListener(new TitleButton());
		titlePanel.add(titleText); titlePanel.add(titleButton);
		
		JPanel pubSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pSearch = new JLabel("          Search by Publisher Name");
		pubSearch.add(pSearch);
		
		JPanel pubPanel = new JPanel();
		JTextField pubText = new JTextField(30);
		JButton pubButton = new JButton("Search");
		pubButton.addActionListener(new PubButton());
		pubPanel.add(pubText); pubPanel.add(pubButton);
		
		JLabel reservation = new JLabel("Current Reservations");
		reservation.setAlignmentX(CENTER_ALIGNMENT);
		reservation.setFont(new Font("Helvetica",Font.BOLD,24));
		
		String[] columns = {"Title","Status","Fine","Date Borrowed"};
		Object[][] data = {{"Harry Potter","Borrowed","$2","Today"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"},
						   {"Hunger Games","Returned","$0","Tomorrow"}};
		
		JTable table = new JTable(data,columns);
		JScrollPane scroll = new JScrollPane(table);
		
		JButton quit = new JButton("Quit");
		quit.setAlignmentX(CENTER_ALIGNMENT);
		quit.addActionListener(new Quit());
		
		
		panel.add(new JLabel(" "));
		panel.add(title);
		panel.add(new JLabel(" "));
		panel.add(search);
		panel.add(docSearch);
		panel.add(docPanel);
		panel.add(titleSearch);
		panel.add(titlePanel);
		panel.add(pubSearch);
		panel.add(pubPanel);
		panel.add(new JLabel(" "));
		panel.add(reservation);
		panel.add(new JLabel(" "));
		panel.add(scroll);
		panel.add(new JLabel(" "));
		panel.add(quit);
		panel.add(new JLabel(" "));
		
		add(panel);
		setSize(500,550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	
	public static void main(String args[]) {
		
		new ReaderFunctions();
	}
	
	class DocButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {
				
		}
	}
	
	class TitleButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {
				
		}
	}
	
	class PubButton implements ActionListener {

	    public void actionPerformed(ActionEvent e) {
				
		}
	}
	
	class Quit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	}
}
