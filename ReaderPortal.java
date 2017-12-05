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

public class ReaderPortal extends JFrame {

	
	public ReaderPortal() {
		super("Reader Portal");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		JLabel line1 = new JLabel("Please Enter");
		line1.setFont(new Font("Helvetica",Font.BOLD,32));
		line1.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel line2 = new JLabel("Card Number");
		line2.setFont(new Font("Helvetica",Font.BOLD,32));
		line2.setAlignmentX(CENTER_ALIGNMENT);

		JPanel submit = new JPanel(new FlowLayout());
		JTextField text = new JTextField(20);
		JButton go = new JButton("Go");
		go.addActionListener(new Go());
		
		submit.add(text); submit.add(go);
		
		panel.add(new JLabel(" "));
		panel.add(line1);
		panel.add(line2);
		panel.add(new JLabel(" "));
		panel.add(submit);
		panel.add(new JLabel(" "));
		
		add(panel);
		
		setSize(350,220);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	
	class Go implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
			new ReaderFunctions();
		}
		
	}
	
}
