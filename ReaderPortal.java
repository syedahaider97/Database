import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReaderPortal extends JFrame {
	private JTextField IDfield; // field for ID number
	
	public ReaderPortal() {
		super("Reader Portal");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		// Title
		JLabel line1 = new JLabel("Please Enter");
		line1.setFont(new Font("Helvetica",Font.BOLD,32));
		line1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel line2 = new JLabel("Card Number");
		line2.setFont(new Font("Helvetica",Font.BOLD,32));
		line2.setAlignmentX(CENTER_ALIGNMENT);
		// End Title
		
		// Card ID text field
		JPanel submit = new JPanel(new FlowLayout());
		IDfield = new JTextField(20);
		JButton go = new JButton("Log in");
		go.addActionListener(new Go());
		
		String IDfieldMessage = "Enter Card Number here";
		IDfield.setText(IDfieldMessage);
		// Focus Listener
		IDfield.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
		  
				System.out.println(IDfield.getText());
		  		if (IDfield.getText().compareTo(IDfieldMessage) == 0) {
		  			System.out.println("YES!!");
		  			IDfield.setText("");
		  		}
		   }
		
		   public void focusLost(FocusEvent e) {
		     if (IDfield.getText().compareTo("") == 0) {
		    	 IDfield.setText(IDfieldMessage);
		     }
		   }
		});
		
		submit.add(IDfield); submit.add(go);
		
		// Add items to panel
		panel.add(new JLabel(" "));
		panel.add(line1);
		panel.add(line2);
		panel.add(new JLabel(" "));
		panel.add(submit);
		panel.add(new JLabel(" "));
		
		add(panel);
		
		// Set window characteristics
		setSize(350,220);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	
	// Go button actions
		class Go implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// Fields for saving and converting input
				int readerID=-1;
				String IDstr = IDfield.getText();
				boolean isValidInput = false; // flag if input is valid
				
				// Check if input is not null
				if (IDstr != null) {
					isValidInput = true; // assume input is valid
					try { // try to convert ID to Integer
	                    readerID = (Integer.parseInt(IDstr));
	                    if (readerID < 1) { // If integer is negative, show Error
	                    	isValidInput = false;
	                    	new popupMsg("Error", "Invalid Card Number '" + IDstr + "' entered.");
	                    }
	                } catch (NumberFormatException nfe) { // If input is not a number, show Error
	                	isValidInput = false;
	                	new popupMsg("Error", "Invalid Card Number '" + IDstr + "' entered.");
	                }
					
					// if no errors occurred, open Admin Functions
					if (isValidInput) {
						if (isValidInput) {
							if (Server.readerExists(readerID)) {
								dispose();
								new ReaderFunctions(readerID);
							}
						}
					}
	        	} else { // Call default Admin Functions window in default case
	        		new ReaderFunctions();
	        	}
			}
			
		}
	
}
