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
	private JTextField IDfield; // field for ID number
	
	public AdminPortal() {
		super("Admin Portal"); // Name of window
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		// Title
		JLabel login = new JLabel("Log in as Administrator");
		login.setFont(new Font("Helvetica",Font.BOLD,32));
		login.setAlignmentX(CENTER_ALIGNMENT);
		
		// ID Text field
		JPanel ID = new JPanel(new FlowLayout());
		JLabel name = new JLabel("ID:             ");
		name.setFont(new Font("Helvetica",Font.BOLD,20));
		IDfield = new JTextField(20); // set text field to accept value
		IDfield.setText("Enter ID number here"); // set temporary text
		ID.setPreferredSize(new Dimension(250,50));
		ID.add(name); ID.add(IDfield);
		
		// Password Text field
		JPanel pass = new JPanel(new FlowLayout());
		JLabel passLabel = new JLabel("Password: ");
		passLabel.setFont(new Font("Helvetica",Font.BOLD,20));
		JTextField passText = new JTextField(20);
		pass.setPreferredSize(new Dimension(250,50));
		pass.add(passLabel); pass.add(passText);
		
		// Action button
		JButton go = new JButton("Log in");
		go.setAlignmentX(CENTER_ALIGNMENT);
		go.addActionListener(new Go());
		
		// Add labels and items to panel
		panel.add(login);
		panel.add(new JLabel(" "));
		panel.add(ID);
		panel.add(pass);
		panel.add(go);
		panel.add(new JLabel(" "));
		
		// Set panel actions
		add(panel);
		setSize(400,230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	// Go button actions
	class Go implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Fields for saving and converting input
			int adminID=-1;
			String IDstr = IDfield.getText();
			boolean isValidInput = false; // flag if input is valid
			
			// Check if input is not null
			if (IDstr != null) {
				isValidInput = true; // assume input is valid
				try { // try to convert ID to Integer
                    adminID = (Integer.parseInt(IDstr));
                    if (adminID < 1) { // If integer is negative, show Error
                    	isValidInput = false;
                    	new popupMsg("Error", "Invalid Administrator ID '" + IDstr + "' entered.");
                    }
                } catch (NumberFormatException nfe) { // If input is not a number, show Error
                	isValidInput = false;
                	new popupMsg("Error", "Invalid Administrator ID '" + IDstr + "' entered.");
                }
				
				// if no errors occurred, open Admin Functions
				if (isValidInput) {
					if (Server.libExists(adminID)) {
						dispose();
						new AdminFunctions(adminID);
					}
				}
        	} else { // Call default Admin Functions window in default case
        		new AdminFunctions();
        	}
		}
		
	}
	
}
