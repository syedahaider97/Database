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
        branchLocLabel.setBounds(210, 55, sizeBranchLoc.width, sizeBranchLoc.height);

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
        addCopyBtn.setBounds(450, 85, 55, 30);
        addCopyBtn.setBorder(null);
        panel.add(addCopyBtn);

        JButton addReaderBtn = new JButton("Go!");
        addReaderBtn.setBounds(450, 135, 55, 30);
        addReaderBtn.setBorder(null);
        panel.add(addReaderBtn);

        JButton searchBtn = new JButton("Go!");
        searchBtn.setBounds(450, 185, 55, 30);
        searchBtn.setBorder(null);
        panel.add(searchBtn);

        JTextField addCopyField = new JTextField(10);
        addCopyField.setBounds(20, 100, 400, 20);
        panel.add(addCopyField);

        JTextField addReaderField = new JTextField(10);
        addReaderField.setBounds(20, 150, 400, 20);
        panel.add(addReaderField);

        JTextField searchField = new JTextField(10);
        searchField.setBounds(20, 200, 400, 20);
        panel.add(searchField);

        JLabel fineLabel = new JLabel("Avg. Fine for All:");
        panel.add(fineLabel);
        Dimension sizeFine = fineLabel.getPreferredSize();
        fineLabel.setBounds(25, 465, sizeFine.width, sizeFine.height);

        JButton logOutBtn = new JButton("Logout!");
        logOutBtn.setBounds(170, 500, 80, 30);
        logOutBtn.setBorder(null);
        panel.add(logOutBtn);

        JButton modifyBtn = new JButton("Modify!");
        modifyBtn.setBounds(270, 500, 80, 30);
        modifyBtn.setBorder(null);
        panel.add(modifyBtn);

        JLabel topTenBorrowLabel = new JLabel("Top 10 Borrowers:");
        panel.add(topTenBorrowLabel);
        Dimension sizeTenBorrow = topTenBorrowLabel.getPreferredSize();
        topTenBorrowLabel.setBounds(25, 240, sizeTenBorrow.width, sizeTenBorrow.height);

        JLabel topTenBooksLabel = new JLabel("Top 10 Books:");
        panel.add(topTenBooksLabel);
        Dimension sizeTenBooks = topTenBooksLabel.getPreferredSize();
        topTenBooksLabel.setBounds(200, 240, sizeTenBooks.width, sizeTenBooks.height);

        JLabel topTenYr = new JLabel("Top 10 Books (Year):");
        panel.add(topTenYr);
        Dimension sizeTenYr = topTenYr.getPreferredSize();
        topTenYr.setBounds(380, 240, sizeTenYr.width, sizeTenYr.height);

        Object[][] topTenBorrowersData =
                {{"RName 1", "13"},
                        {"RName 2", "12"},
                        {"RName 3", "35"},
                        {"RName 4", "33"},
                        {"RName 5", "13"},
                        {"RName 6", "32"},
                        {"RName 7", "100"},
                        {"RName 8", "1"},
                        {"RName 9", "0"},
                        {"RName 10", "2"}};

        String[] topTenBorrowersColumns = {"Name", "# Borrowed"};

        JTable borrowsTable = new JTable(topTenBorrowersData, topTenBorrowersColumns);
        JScrollPane scrollBorrows = new JScrollPane(borrowsTable);
        scrollBorrows.setBounds(10, 260, 175, 150);
        panel.add(scrollBorrows);

        String[] topTenBooksColumns = {"Title"};
        Object[][] topTenBooksData =
                {{"Book Name 1", "13"},
                        {"Book Name 2", ""},
                        {"Book Name 3", ""},
                        {"Book Name 4", ""},
                        {"Book Name 5", ""},
                        {"Book Name 6", ""},
                        {"Book Name 7", ""},
                        {"Book Name 8", ""},
                        {"Book Name 9", ""},
                        {"Book Name 10", ""}};

        JTable topTenBooksTable = new JTable(topTenBooksData, topTenBooksColumns);
        JScrollPane scrollTopTenBooks = new JScrollPane(topTenBooksTable);
        scrollTopTenBooks.setBounds(200, 260, 160, 150);
        panel.add(scrollTopTenBooks);

        String[] topTenBooksYrColumns = {"Title"};
        Object[][] topTenBooksYrData =
                {{"Book Name 1", "13"},
                        {"Book Name 2", ""},
                        {"Book Name 3", ""},
                        {"Book Name 4", ""},
                        {"Book Name 5", ""},
                        {"Book Name 6", ""},
                        {"Book Name 7", ""},
                        {"Book Name 8", ""},
                        {"Book Name 9", ""},
                        {"Book Name 10", ""}};

        JTable topTenBooksYrTable = new JTable(topTenBooksYrData, topTenBooksYrColumns);
        JScrollPane scrollTopTenBooksYr = new JScrollPane(topTenBooksYrTable);
        scrollTopTenBooksYr.setBounds(380, 260, 160, 150);
        panel.add(scrollTopTenBooksYr);

        frame.setSize(570, 600);
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
