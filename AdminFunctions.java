import javax.swing.*;
import java.awt.*;

public class AdminFunctions extends JFrame {

    private static void Admin() {

        JFrame frame = new JFrame("Administrator Functions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);

        JLabel adminLabel = new JLabel("Administrator Functions:");
        panel.add(adminLabel);
        Dimension sizeAdmin = adminLabel.getPreferredSize();
        adminLabel.setBounds(25, 25, sizeAdmin.width, sizeAdmin.height);

        JLabel branchLabel = new JLabel("<Branch Name>");
        panel.add(branchLabel);
        Dimension sizeBranch = branchLabel.getPreferredSize();
        branchLabel.setBounds(55, 55, sizeBranch.width, sizeBranch.height);

        JLabel branchLocLabel = new JLabel("<Branch Location>");
        panel.add(branchLocLabel);
        Dimension sizeBranchLoc = branchLocLabel.getPreferredSize();
        branchLocLabel.setBounds(165, 55, sizeBranchLoc.width, sizeBranchLoc.height);

        JLabel addCopyLabel = new JLabel("Add Document Copy:");
        panel.add(addCopyLabel);
        Dimension sizeCopy = addCopyLabel.getPreferredSize();
        addCopyLabel.setBounds(25, 85, sizeCopy.width, sizeCopy.height);

        JLabel addReaderLabel = new JLabel("Add New Reader:");
        panel.add(addReaderLabel);
        Dimension sizeReader = addReaderLabel.getPreferredSize();
        addReaderLabel.setBounds(25, 135, sizeReader.width, sizeReader.height);

        JLabel searchCopyLabel = new JLabel("Search Document Copy:");
        panel.add(searchCopyLabel);
        Dimension searchSize = searchCopyLabel.getPreferredSize();
        searchCopyLabel.setBounds(25, 185, searchSize.width, searchSize.height);

        JButton addCopyBtn = new JButton("Go!");
        addCopyBtn.setBounds(230, 85, 55, 30);
        panel.add(addCopyBtn);

        JButton addReaderBtn = new JButton("Go!");
        addReaderBtn.setBounds(230, 135, 55, 30);
        panel.add(addReaderBtn);

        JButton searchBtn = new JButton("Go!");
        searchBtn.setBounds(230, 185, 55, 30);
        panel.add(searchBtn);

        JTextField addCopyField = new JTextField(10);
        addCopyField.setBounds(20, 100, 200, 20);
        panel.add(addCopyField);

        JTextField addReaderField = new JTextField(10);
        addReaderField.setBounds(20, 150, 200, 20);
        panel.add(addReaderField);

        JTextField searchField = new JTextField(10);
        searchField.setBounds(20, 200, 200, 20);
        panel.add(searchField);

        JLabel fineLabel = new JLabel("Avg. Fine for All:");
        panel.add(fineLabel);
        Dimension sizeFine = fineLabel.getPreferredSize();
        fineLabel.setBounds(25, 455, sizeFine.width, sizeFine.height);

        JButton logOutBtn = new JButton("Logout!");
        logOutBtn.setBounds(100, 485, 80, 30);
        panel.add(logOutBtn);

        JButton modifyBtn = new JButton("Modify!");
        modifyBtn.setBounds(200, 485, 80, 30);
        panel.add(modifyBtn);

        JLabel topTenBorrowLabel = new JLabel("Top 10 Borrowers:");
        panel.add(topTenBorrowLabel);
        Dimension sizeTenBorrow = topTenBorrowLabel.getPreferredSize();
        topTenBorrowLabel.setBounds(25, 240, sizeTenBorrow.width, sizeTenBorrow.height);

        JLabel topTenBooksLabel = new JLabel("Top 10 Books:");
        panel.add(topTenBooksLabel);
        Dimension sizeTenBooks = topTenBooksLabel.getPreferredSize();
        topTenBooksLabel.setBounds(155, 240, sizeTenBooks.width, sizeTenBooks.height);

        JLabel topTenYr = new JLabel("Top 10 Books:");
        panel.add(topTenYr);
        Dimension sizeTenYr = topTenYr.getPreferredSize();
        topTenYr.setBounds(285, 240, sizeTenYr.width, sizeTenYr.height);

        frame.setSize(450, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Admin();
            }
        });
    }
}
