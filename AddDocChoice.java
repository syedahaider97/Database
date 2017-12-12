import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddDocChoice extends JFrame {

	private int choice = 0; // 0 = add, 1 = update
	
    public AddDocChoice(int decision) {
    	this.choice = decision;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Document Types");
        title.setFont(new Font("Helvetica",Font.BOLD,18));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel("Select a Document Type:");
        label.setFont(new Font("Helvetica",Font.BOLD,18));
        label.setAlignmentX(CENTER_ALIGNMENT);

        JButton addBook = new JButton("Add Book");
        addBook.setAlignmentX(CENTER_ALIGNMENT);
        addBook.addActionListener(new addBook());

        JButton addJournal = new JButton("Add Journal");
        addJournal.setAlignmentX(CENTER_ALIGNMENT);
        addJournal.addActionListener(new addJournal());

        JButton addProceedings = new JButton("Add Proceeding");
        addProceedings.setAlignmentX(CENTER_ALIGNMENT);
        addProceedings.addActionListener(new addProceeding());
        
        JPanel buttonPanel = new JPanel();
		JButton back = new JButton("Back");
		back.addActionListener(new Back());
		back.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(back);

        panel.add(new JLabel(" "));
        panel.add(title);
        panel.add(label);
        panel.add(new JLabel(" "));
        panel.add(new JLabel(" "));
        panel.add(addBook);
        panel.add(new JLabel(" "));
        panel.add(addJournal);
        panel.add(new JLabel(" "));
        panel.add(addProceedings);
        panel.add(new JLabel(" "));
		panel.add(buttonPanel);


        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    class addBook implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddBook(choice,"Add a Book"); // 0 is add, 1 is update
        }
    }

    class addJournal implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddJournal(choice,"Add a Journal"); // 0 is add, 1 is update
        }
    }

    class addProceeding implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddProceeding(choice,"Add a Conference Proceeding"); // 0 is add, 1 is update
        }
    }
    
    class Back implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}