import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddDocChoice extends JFrame {

    public AddDocChoice() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Which type of Document");
        title.setFont(new Font("Helvetica",Font.BOLD,18));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel("would you like to add?");
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
        setSize(300,400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    class addBook implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddBook("add","Add a Book");
        }
    }

    class addJournal implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddJournal("add","Add a Journal");
        }
    }

    class addProceeding implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new AddProceeding("add","Add a Conference Proceeding");
        }
    }
    
    class Back implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

}