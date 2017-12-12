import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class popupMsg extends JFrame {
	public popupMsg() {
		super("Error");
		
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel popupWindow = new JLabel("An error has occurred.");
        
        popupWindow.setAlignmentX(CENTER_ALIGNMENT);
        popupWindow.setAlignmentY(CENTER_ALIGNMENT);
        
        panel.add(new JLabel("  "));
        panel.add(popupWindow);
        panel.add(new JLabel("  "));
        
        JPanel submit = new JPanel(new FlowLayout());
		JButton close = new JButton("Close");
		close.addActionListener(new Close());
		submit.add(close);
		
		panel.add(submit);
		
        add(panel);
		setSize(350,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public popupMsg(String title, String Message) {
		super(title);
		
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel popupWindow = new JLabel(Message);
        
        popupWindow.setAlignmentX(CENTER_ALIGNMENT);
        popupWindow.setAlignmentY(CENTER_ALIGNMENT);
        
        panel.add(new JLabel("  "));
        panel.add(popupWindow);
        panel.add(new JLabel("  "));
        
        JPanel submit = new JPanel(new FlowLayout());
		JButton close = new JButton("Close");
		close.addActionListener(new Close());
		submit.add(close);
		
		panel.add(submit);
		
        add(panel);
		setSize(500,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	class Close implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
