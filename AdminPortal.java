import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminPortal extends JFrame {

	public AdminPortal() {
		super("Admin Portal");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel login = new JLabel("Log in as Administrator");
		login.setFont(new Font("Helvetica",Font.BOLD,32));
		login.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel ID = new JPanel(new FlowLayout());
		JLabel name = new JLabel("ID:             ");
		name.setFont(new Font("Helvetica",Font.BOLD,20));
		JTextField text = new JTextField(20);
		ID.setPreferredSize(new Dimension(250,50));
		ID.add(name); ID.add(text);
		
		JPanel pass = new JPanel(new FlowLayout());
		JLabel passLabel = new JLabel("Password: ");
		passLabel.setFont(new Font("Helvetica",Font.BOLD,20));
		JTextField passText = new JTextField(20);
		pass.setPreferredSize(new Dimension(250,50));
		pass.add(passLabel); pass.add(passText);
		
		JButton go = new JButton("Go");
		go.setAlignmentX(CENTER_ALIGNMENT);
		go.addActionListener(new Go());
		
		
		panel.add(login);
		panel.add(new JLabel(" "));
		panel.add(ID);
		panel.add(pass);
		panel.add(go);
		panel.add(new JLabel(" "));
		
		
		add(panel);
		setSize(400,230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public String hello(){
		System.out.println("Hello");
		return "Hello";
	}
	class Go implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			dispose();
			new AdminFunctions();
		}
		
	}
	
}
