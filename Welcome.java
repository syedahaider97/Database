import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends JFrame {

	public Welcome() {
		
		super("Welcome To The City Library");

		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		
		JLabel welcome = new JLabel("WELCOME");
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcome.setFont(new Font("Helvetica",Font.BOLD,32));
		
		JLabel logIn = new JLabel("Log In As:");
		logIn.setFont(new Font("Helvetica",Font.BOLD,24));
		logIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton admin = new JButton("Administrator");
		admin.setAlignmentX(Component.CENTER_ALIGNMENT);
		admin.addActionListener(new Admin());
		
		JButton reader = new JButton("Reader");
		reader.setAlignmentX(Component.CENTER_ALIGNMENT);
		reader.addActionListener(new Reader());
		
		JButton quit = new JButton("Quit");
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.addActionListener(new Quit());
		
		panel.add(new JLabel(" "));
		panel.add(welcome);
		panel.add(logIn);
		panel.add(new JLabel(" "));
		panel.add(admin);
		panel.add(new JLabel(" "));
		panel.add(reader);
		panel.add(new JLabel(" "));
		panel.add(quit);
		
		add(panel);
		
		
		setSize(300,270);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public static void main(String args[]) {
		
		new Welcome();
	}
	
	
	public class Admin implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new AdminPortal().setVisible(true);
		}


	}
	class Reader implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ReaderPortal().setVisible(true);
		}
	}
	class Quit implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
