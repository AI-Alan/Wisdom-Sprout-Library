package library;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


//for time and data
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit; // for calculating differnce in dates

class Member 
{
    String name;
    String  aadhar;
    int age;
    String  sex;

    public String  getName()
    {
        return name;
    }

    public void setName( String name)
    {
        this.name  = name;
    }


    public String  getAadhar()
    {
        return aadhar;
    }

    public void setAadhar( String aadhar)
    {
        this.aadhar  = aadhar;
    }


    public String  getSex()
    {
        return sex;
    }

    public void setSex( String sex)
    {
        this.sex  = sex;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }


}

class membersTableModel extends AbstractTableModel {

     private String[] columnNames = {"Name", "Aadhar","Age", "Sex"};
     private List<Member> members ;

     public membersTableModel(List<Member> members) {
        this.members = members;
    }

    @Override
    public int getRowCount() {
        return members.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member member = members.get(rowIndex);
        switch (columnIndex) {
            case 0: return member.getName();
            case 1: return  member.getAadhar();
            case 2: return member.getAge();
            case 3: return member.getSex();
            
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Member member = members.get(rowIndex);
        switch (columnIndex) {
            case 0: member.setName(aValue.toString()); break;
            case 1: member.setAadhar(aValue.toString()); break;
            case 2: member.setAge(Integer.parseInt(aValue.toString())); break;
            case 3: member.setSex(aValue.toString()); break;
            
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Allow editing of all cells
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Add a new book
    public void addMember(Member member) {
        members.add(member);
        fireTableRowsInserted(members.size() - 1, members.size() - 1);
    }

    // Remove a book by row index
    public void removeMember(int rowIndex) {
        members.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    // Get the current list of books
    public List<Member> getMembers() {
        return members;
    }


    


    
}


class Book {
    String title;
    String author;
    String genre;
    String id;
    int quantity;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
 




class BookTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Title", "Author", "Genre", "ID", "Quantity"};
    private final List<Book> books;

    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0: return book.getTitle();
            case 1: return book.getAuthor();
            case 2: return book.getGenre();
            case 3: return book.getId();
            case 4: return book.getQuantity();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0: book.setTitle(aValue.toString()); break;
            case 1: book.setAuthor(aValue.toString()); break;
            case 2: book.setGenre(aValue.toString()); break;
            case 3: book.setId(aValue.toString()); break;
            case 4: book.setQuantity(Integer.parseInt(aValue.toString())); break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Allow editing of all cells
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        fireTableRowsInserted(books.size() - 1, books.size() - 1);
    }

    // Remove a book by row index
    public void removeBook(int rowIndex) {
        books.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    // Get the current list of books
    public List<Book> getBooks() {
        return books;
    }
}


class Ledger {

    private String memberId;
    private String bookId;
    private String borrowDate;
    private String tillDate;
    private String returnDate;
    private int dues ;
    private boolean paid ;
    private boolean returned;

    Ledger(){}

    
    Ledger(String memberIdInput, String bookIdInput, String  currentDate, String oneMonthLater)
    {
        this.memberId = memberIdInput;
        this.bookId = bookIdInput;
        this.borrowDate = currentDate;
        this.tillDate = oneMonthLater;
        this.dues = 0;
        this.paid = true ;
        this.returned = false;

    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setTillDate(String tillDate) {
        this.tillDate = tillDate;
    }

    public String getTillDate() {
        return tillDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setDues(int dues) {
        this.dues = dues;
    }

    public int getDues() {
        return dues;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setReturned(boolean retuned) {
        this.returned = retuned;
    }

    public boolean isReturned() {
        return returned;
    }

    

}

class LedgerTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Member ID", "Book Id", "Borrow Date", "Borrowed Till Date", "Return Date", "Due", "Paid", "Returned"};
    private final List<Ledger> entries;

    public LedgerTableModel(List<Ledger> entries) {
        this.entries = entries;
    }

    @Override
    public int getRowCount() {
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ledger entry = entries.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.getMemberId();
            case 1: return entry.getBookId();
            case 2: return entry.getBorrowDate();
            case 3: return entry.getTillDate();
            case 4: return entry.getReturnDate();
            case 5: return entry.getDues();
            case 6: return entry.isPaid() ? "Yes" : "No";
            case 7: return entry.isReturned() ? "Yes" : "No";

            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
    


class LibraryApp extends JFrame {

    
    CardLayout cardLayout = new CardLayout();

    JPanel root = new JPanel(cardLayout);

    public LibraryApp() {
        setTitle("Wisdom Sprout Library");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define pages
        JPanel homePage = createHomePage();
        JPanel borrowPage = createBorrowPage();
        JPanel returnPage = createReturnPage();

        JPanel booksPage = createBooksPage();
        JPanel membersPage = createMembersPage();

        JPanel historyPage = createHistoryPage();

        // Add pages to the main panel
        root.add(homePage, "Home"); // Home page

        root.add(borrowPage, "Borrow"); // Borrow page
        root.add(returnPage, "Return"); // Return page

        root.add(booksPage, "Books"); // Return page
        root.add(membersPage, "Members");

        root.add(historyPage, "History");

        // Add mainPanel to frame
        add(root);
        setVisible(true);
    }

    private JPanel createHomePage() {
        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel libTitle = new JLabel("Wisdom Sprout Library: Where Curiosity Blossoms into Understanding",
                SwingConstants.CENTER);
        libTitle.setBounds(40, 50, 400, 60);
        panel.add(libTitle);

        JButton borrow = new JButton("Borrow");
        JButton rtrn = new JButton("Return");
        JButton members = new JButton("Members");
        JButton books = new JButton("Books");
        JButton history = new JButton("History");
        // JLabel test = new JLabel( "Test hlkhhoihohoihohouhouih");

        root.addComponentListener(new ComponentAdapter() {
            @Override
            
            public void componentResized(ComponentEvent e) {
                int rootWidth = root.getWidth();
                int rootHeight = root.getHeight();

                // test.setBounds((int)(rootWidth * 0.35), (int)(rootHeight * 0.20),
                // (int)(rootWidth * 0.95), (int)(rootHeight * 0.10));

                // Set bounds relative to container size
                libTitle.setFont(new Font("Serif", Font.BOLD, 20)); 
                libTitle.setBounds((int) (rootWidth * 0.05), (int) (rootHeight * 0.10), (int) (rootWidth * 0.95),
                        (int) (rootHeight * 0.20));

                borrow.setBounds((int) (rootWidth * 0.15), (int) (rootHeight * 0.40), (int) (rootWidth * 0.20),
                        (int) (rootHeight * 0.10));

                rtrn.setBounds((int) (rootWidth * 0.60), (int) (rootHeight * 0.40), (int) (rootWidth * 0.20),
                        (int) (rootHeight * 0.10));


                members.setBounds((int) (rootWidth * 0.15), (int) (rootHeight * 0.70), (int) (rootWidth * 0.20),
                        (int) (rootHeight * 0.10));
                books.setBounds((int) (rootWidth * 0.60), (int) (rootHeight * 0.70), (int) (rootWidth * 0.20),
                        (int) (rootHeight * 0.10));


                history.setBounds((int) (rootWidth * 0.38), (int) (rootHeight * 0.55), (int) (rootWidth * 0.20),
                        (int) (rootHeight * 0.10));

            }
        });

        panel.add(borrow);
        panel.add(rtrn);
        panel.add(books);
        panel.add(members);
        panel.add(history);
        // panel.add(test);

        ActionListener listener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == borrow) {
                    cardLayout.show(root, "Borrow");
                } else if (e.getSource() == rtrn) {
                    cardLayout.show(root, "Return");

                } else if (e.getSource() == books) {
                    cardLayout.show(root, "Books");
                } else if (e.getSource() == members) {
                    cardLayout.show(root, "Members");
                } else if (e.getSource() == history) {
                    cardLayout.show(root, "History");
                }
            }
        };

        borrow.addActionListener(listener);

        rtrn.addActionListener(listener);

        books.addActionListener(listener);
        members.addActionListener(listener);

        history.addActionListener(listener);

        return panel;
    }

    
    private JPanel createBorrowPage() {
        JPanel panel = new JPanel(null); // Using null layout

        String   currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String  oneMonthLater = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd-MM-yy"));
    
        // Labels
        JLabel label = new JLabel("Borrow Books", SwingConstants.CENTER);
        JLabel memberId = new JLabel("Enter Member ID:");
        JLabel bookId = new JLabel("Enter Book ID:");
        JLabel borrowDate = new JLabel("Borrow Date:");
        JLabel borrowTillDate = new JLabel("Borrow Till Date:");
        JLabel notice = new JLabel("Return Book on exact Date, otherwise Rs. 10/day fine.");
    
        // Text Fields
        JTextField memberIdField = new JTextField(20);
        JTextField bookIdField = new JTextField(20);
        JTextField borrowDateField = new JTextField(currentDate);
        JTextField borrowTillDateField = new JTextField(oneMonthLater);
    
        // Buttons
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back to Home");
    
        // Adding components to the panel
        panel.add(label);
        panel.add(memberId);
        panel.add(memberIdField);
        panel.add(bookId);
        panel.add(bookIdField);
        panel.add(borrowDate);
        panel.add(borrowDateField);
        panel.add(borrowTillDate);
        panel.add(borrowTillDateField);
        panel.add(notice);
        panel.add(submitButton);
        panel.add(backButton);
    
        // Add ComponentListener to adjust component bounds dynamically
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();
    
                // Adjusting bounds dynamically based on panel size
                int padding = 20;
                int labelHeight = 30;
    
                label.setBounds(padding, padding, width - 2 * padding, labelHeight);
    
                int fieldWidth = width / 2 - 2 * padding;
                int yOffset = labelHeight + 2 * padding;
    
                memberId.setBounds(padding, yOffset, fieldWidth, labelHeight);
                memberIdField.setBounds(fieldWidth + 2 * padding, yOffset, fieldWidth, labelHeight);
    
                bookId.setBounds(padding, yOffset + 40, fieldWidth, labelHeight);
                bookIdField.setBounds(fieldWidth + 2 * padding, yOffset + 40, fieldWidth, labelHeight);
    
                borrowDate.setBounds(padding, yOffset + 80, fieldWidth, labelHeight);
                borrowDateField.setBounds(fieldWidth + 2 * padding, yOffset + 80, fieldWidth, labelHeight);
    
                borrowTillDate.setBounds(padding, yOffset + 120, fieldWidth, labelHeight);
                borrowTillDateField.setBounds(fieldWidth + 2 * padding, yOffset + 120, fieldWidth, labelHeight);
    
                notice.setBounds((int)(width*0.20), yOffset + 160, (int)(width*0.50), labelHeight);
    
                submitButton.setBounds(width / 4 - 50, height - 60, 100, 30);
                backButton.setBounds((int)(width*0.80), height-60,(int)(width*0.20) , labelHeight);
            }
        });
    
        // Action listeners for buttons
        submitButton.addActionListener(e -> {
            String memberIdInput = memberIdField.getText();
            String bookIdInput = bookIdField.getText();
            // String borrowDateInput = borrowDateField.getText();
            // String borrowTillDateInput = borrowTillDateField.getText();

            File ledgerFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/ledger_data.json");
            File booksFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/books_data.json");
            File membersFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/members_data.json");
            
            ObjectMapper mapper =  new ObjectMapper();

            List<Book>  booksList  = new ArrayList<Book>();
            List<Ledger> entries =  new ArrayList<Ledger>();
            List<Member>  membersList = new ArrayList<Member>();
              
            Boolean memberExists = false;
            Boolean  bookExists = false;

            
            try {
                booksList = mapper.readValue(booksFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Book.class));
                membersList = mapper.readValue(membersFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Member.class));
                entries = mapper.readValue(ledgerFile,mapper.getTypeFactory().constructCollectionType(ArrayList.class, Ledger.class) );
                            // Check if member exists
            for (Member member : membersList) {
                if (member.getAadhar().equals(memberIdInput)) {
                    memberExists = true;
                    break;
                }
            }

            // Check if book exists
            for (Book book : booksList) {
                if (book.getId().equals(bookIdInput) && book.getQuantity() > 0) {
                    bookExists = true;
                    break;
                }
            }

            if (!memberExists || !bookExists) {
                JOptionPane.showMessageDialog(panel, "Invalid Member ID or Book ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                memberIdField.setText(null);
                bookIdField.setText((null));
            } else {
                // Create a new entry for the ledger
                Ledger newEntry = new Ledger(memberIdInput, bookIdInput, currentDate, oneMonthLater);
                entries.add(newEntry);

                // Update the book quantity
                for (Book book : booksList) {
                    if (book.getId().equals(bookIdInput)) {
                        book.setQuantity(book.getQuantity() - 1);
                        break;
                    }
                }

                // Save updated data to files
                mapper.writeValue(ledgerFile, entries);
                mapper.writeValue(booksFile, booksList);

                JOptionPane.showMessageDialog(panel, "Book borrowed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                memberIdField.setText(null);
                bookIdField.setText((null));
                borrowDateField.setText(null);
                borrowTillDateField.setText(null);
            }
        } 
                
         catch (Exception He) {
                He.printStackTrace();
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(root, "Home"));
    
        return panel;
    }

    // Return page with a back button
    private JPanel createReturnPage() {
        JPanel panel = new JPanel(null); // Using null layout

        // Labels
        JLabel label = new JLabel("Return Books", SwingConstants.CENTER);
        JLabel memberId = new JLabel("Enter Member ID:");
        JLabel bookId = new JLabel("Enter Book ID:");
        JLabel borrowDate = new JLabel("Borrow Date:");
        JLabel borrowTillDate = new JLabel("Borrow Till Date:");
        JLabel returnDate = new JLabel("Return Date:");
        JLabel due = new JLabel("Due Amount:");
        JLabel paid = new JLabel("Amount Paid:");

        // Initially hide fields
        borrowDate.setVisible(false);
        borrowTillDate.setVisible(false);
        returnDate.setVisible(false);
        due.setVisible(false);
        paid.setVisible(false);

        // Text Fields
        JTextField memberIdField = new JTextField(20);
        JTextField bookIdField = new JTextField(20);
        JTextField borrowDateField = new JTextField(20);
        JTextField borrowTillDateField = new JTextField(20);
        JTextField returnDateField = new JTextField(20);
        JTextField dueField = new JTextField(20);
        JTextField paidField = new JTextField(20);

        // Initially hide text fields
        borrowDateField.setVisible(false);
        borrowTillDateField.setVisible(false);
        returnDateField.setVisible(false);
        dueField.setVisible(false);
        paidField.setVisible(false);

        // Buttons
        JButton submitButton = new JButton("Check");
        JButton backButton = new JButton("Back to Home");

        // Add components to the panel
        panel.add(label);
        panel.add(memberId);
        panel.add(memberIdField);
        panel.add(bookId);
        panel.add(bookIdField);
        panel.add(borrowDate);
        panel.add(borrowDateField);
        panel.add(borrowTillDate);
        panel.add(borrowTillDateField);
        panel.add(returnDate);
        panel.add(returnDateField);
        panel.add(due);
        panel.add(dueField);
        panel.add(paid);
        panel.add(paidField);
        panel.add(submitButton);
        panel.add(backButton);

        // Add ComponentListener to adjust component bounds dynamically
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int padding = 20;
                int labelHeight = 30;

                label.setBounds(padding, padding, width - 2 * padding, labelHeight);

                int fieldWidth = width / 2 - 2 * padding;
                int yOffset = labelHeight + 2 * padding;

                memberId.setBounds(padding, yOffset, fieldWidth, labelHeight);
                memberIdField.setBounds(fieldWidth + 2 * padding, yOffset, fieldWidth, labelHeight);

                bookId.setBounds(padding, yOffset + 40, fieldWidth, labelHeight);
                bookIdField.setBounds(fieldWidth + 2 * padding, yOffset + 40, fieldWidth, labelHeight);

                borrowDate.setBounds(padding, yOffset + 80, fieldWidth, labelHeight);
                borrowDateField.setBounds(fieldWidth + 2 * padding, yOffset + 80, fieldWidth, labelHeight);

                borrowTillDate.setBounds(padding, yOffset + 120, fieldWidth, labelHeight);
                borrowTillDateField.setBounds(fieldWidth + 2 * padding, yOffset + 120, fieldWidth, labelHeight);

                returnDate.setBounds(padding, yOffset + 160, fieldWidth, labelHeight);
                returnDateField.setBounds(fieldWidth + 2 * padding, yOffset + 160, fieldWidth, labelHeight);

                due.setBounds(padding, yOffset + 200, fieldWidth, labelHeight);
                dueField.setBounds(fieldWidth + 2 * padding, yOffset + 200, fieldWidth, labelHeight);

                paid.setBounds(padding, yOffset + 240, fieldWidth, labelHeight);
                paidField.setBounds(fieldWidth + 2 * padding, yOffset + 240, fieldWidth, labelHeight);

                submitButton.setBounds(width / 4 - 50, height - 60, 100, 30);
                backButton.setBounds((int) (width * 0.80), height - 60, (int) (width * 0.20), labelHeight);
            }
        });

        submitButton.addActionListener(e -> {
            String memberIdInput = memberIdField.getText();
            String bookIdInput = bookIdField.getText();
        
            File ledgerFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/ledger_data.json");
            File booksFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/books_data.json");
        
            ObjectMapper mapper = new ObjectMapper();
        
            List<Book> booksList = new ArrayList<>();
            List<Ledger> entries = new ArrayList<>();
            
            Ledger matchedEntry = null;
    
            Boolean memberExists = false;
            Boolean bookExists = false;
        
           
               

        try{
            booksList = mapper.readValue(booksFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Book.class));
            entries = mapper.readValue(ledgerFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Ledger.class));
        
                // Check if member and book exist
            for (Ledger entry : entries) 
            {
                    if (entry.getMemberId().equals(memberIdInput) && entry.getBookId().equals(bookIdInput) && !(entry.isReturned()))
                     {
                        memberExists = true;
                        bookExists = true;
                        matchedEntry = entry;
                        
        
                        // Making UI elements visible
                        borrowDate.setVisible(true);
                        borrowTillDate.setVisible(true);
                        returnDate.setVisible(true);
                        due.setVisible(true);
                        paid.setVisible(true);
        
                        borrowDateField.setVisible(true);
                        borrowTillDateField.setVisible(true);
                        returnDateField.setVisible(true);
                        



                         borrowDateField.setText(matchedEntry.getBorrowDate());
                         borrowTillDateField.setText(matchedEntry.getTillDate());
                         returnDateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        
                         dueField.setVisible(true);
                         paidField.setVisible(true);
        
                        break;
                    }
            }

            if( submitButton.getText().equals("Check")) 
            {
                    if (!memberExists || !bookExists) 
                    {
                    JOptionPane.showMessageDialog(panel, "Invalid Member ID or Book ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    memberIdField.setText(null);
                    bookIdField.setText(null);
                    } 

                    else
                    {
    
                        submitButton.setText("Submit");
                        // Calculate the due amount
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate tillDate = LocalDate.parse(matchedEntry.getTillDate(), formatter);
                        LocalDate rtrnDate = LocalDate.parse(returnDateField.getText(), formatter);
                        // If the return date is before the borrow till date
                        
                        if (rtrnDate.isBefore(tillDate) || rtrnDate.isEqual(tillDate)) {
                            matchedEntry.setDues(0);
                            matchedEntry.setPaid(true);
                        } 
                        else {
                            long daysBetween = ChronoUnit.DAYS.between(rtrnDate, tillDate);
                            matchedEntry.setDues((int)(daysBetween * 10)); // 10 is the daily late fee
                            
                            
                        }
                        
                        dueField.setText(Integer.toString(matchedEntry.getDues()));
                       
                       
                       
                        

                        
                    }
                }

                else
                {
                    submitButton.setText("Check");

                    matchedEntry.setReturned(true);
                   
                    matchedEntry.setReturnDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        
                       for (Book book : booksList) {
                          if (book.getId().equals(bookIdInput)) {
                              book.setQuantity(book.getQuantity() + 1); // Increase quantity as the book is returned
                              break;
                          }
                        //matchedEntry.setReturnDate();
                         mapper.writeValue(booksFile, booksList);
                         mapper.writeValue(ledgerFile, entries);
                          
          
                         JOptionPane.showMessageDialog(panel, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                              
                           
         
                            borrowDate.setVisible(false);
                            borrowTillDate.setVisible(false);
                            returnDate.setVisible(false);
                            due.setVisible(false);
                            paid.setVisible(false);
      
      
                            borrowDateField.setVisible(false);
                            borrowTillDateField.setVisible(false);
                            returnDateField.setVisible(false);
                            dueField.setVisible(false);
                            paidField.setVisible(false);

                            memberIdField.setText(null);
                            bookIdField.setText(null);
      
      

                              
                }

            }}
         catch( Exception Re)
        {
              Re.printStackTrace();
        }
            
            
        })
        ;

        
        backButton.addActionListener(e -> cardLayout.show(root, "Home"));
    
        return panel;
    }

    private JPanel createBooksPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout());
    
        JButton backButton = new JButton("Back to Home");
        JButton deleteRow = new JButton("Delete Row");
        JButton add = new JButton("Add");
    
        buttonsPanel.add(backButton);
        buttonsPanel.add(deleteRow);
        buttonsPanel.add(add);
       
    
        JLabel label = new JLabel("Available Books", SwingConstants.CENTER);
        northPanel.add(label, BorderLayout.NORTH);
        northPanel.add(buttonsPanel, BorderLayout.SOUTH);
    
        panel.add(northPanel, BorderLayout.NORTH);
    
        ObjectMapper mapper = new ObjectMapper();
        File booksFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/books_data.json");
        List<Book> books = new ArrayList<>();
        try {
            books = mapper.readValue(booksFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        BookTableModel tableModel = new BookTableModel(books);
        JTable table = new JTable(tableModel);
        JScrollPane jsp = new JScrollPane(table);
        panel.add(jsp, BorderLayout.CENTER);
    
        // Add book action
        add.addActionListener(e -> {
            Book newBook = new Book(); // Create a blank book
            newBook.setTitle("New Book");
            newBook.setAuthor("Author");
            newBook.setGenre("Genre");
            newBook.setQuantity(0);
            tableModel.addBook(newBook);
        });
    
        // Delete row action
        deleteRow.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeBook(selectedRow);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e ->{
            cardLayout.show(root, "Home");
            try {
               mapper.writeValue(booksFile, tableModel.getBooks());
           } catch (IOException E) {
               E.printStackTrace();
           }});


        return panel;
    }
    

    private JPanel createMembersPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout());
    
        JButton backButton = new JButton("Back to Home");
        JButton deleteRow = new JButton("Delete Row");
        JButton add = new JButton("Add");
    
        buttonsPanel.add(backButton);
        buttonsPanel.add(deleteRow);
        buttonsPanel.add(add);
       
    
        JLabel label = new JLabel("Members", SwingConstants.CENTER);
        northPanel.add(label, BorderLayout.NORTH);
        northPanel.add(buttonsPanel, BorderLayout.SOUTH);
    
        panel.add(northPanel, BorderLayout.NORTH);
    
        ObjectMapper mapper = new ObjectMapper();
        File membersFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/members_data.json");
        List<Member> members = new ArrayList<>();
        try {
            members = mapper.readValue(membersFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Member.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        membersTableModel tableModel = new membersTableModel(members);
        JTable table = new JTable(tableModel);
        JScrollPane jsp = new JScrollPane(table);
        panel.add(jsp, BorderLayout.CENTER);
    
        // Add book action
        add.addActionListener(e -> {
            Member newMember = new Member() ;// Create a blank book
            newMember.setName("New Name");
            newMember.setAadhar("XXXX YYYY ZZZZ");
            newMember.setAge(10);
            newMember.setSex("sex");

            tableModel.addMember(newMember);
        });
    
        // Delete row action
        deleteRow.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeMember(selectedRow);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e ->{
            cardLayout.show(root, "Home");
            try {
               mapper.writeValue(membersFile, tableModel.getMembers());
           } catch (IOException Ee) {
               Ee.printStackTrace();
           }});


        return panel;

    }

    private JPanel createHistoryPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout());
    
        JButton backButton = new JButton("Back to Home");
        buttonsPanel.add(backButton);
        
       
    
        JLabel label = new JLabel("History", SwingConstants.CENTER);
        northPanel.add(label, BorderLayout.NORTH);
        northPanel.add(buttonsPanel, BorderLayout.SOUTH);
    
        panel.add(northPanel, BorderLayout.NORTH);

        backButton.addActionListener(e -> cardLayout.show(root, "Home"));

        File ledgerFile = new File("/Users/amankumaryadav/Desktop/graddle_jv_p/library/app/src/main/resources/ledger_data.json");

        ObjectMapper mapper  = new ObjectMapper();
        List<Ledger> entries = new ArrayList<Ledger>();

       try{
         entries =  mapper.readValue(ledgerFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Ledger.class));
       }
       catch (IOException Le){
        Le.printStackTrace();

       }
       LedgerTableModel tableModel = new LedgerTableModel(entries);
       JTable table = new JTable(tableModel);
       JScrollPane jsp = new JScrollPane(table);
    

       panel.add(jsp, BorderLayout.CENTER);



        

        return panel;

    }

};

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        UIManager.put("Button.font", new Font("Serif", Font.BOLD, 15));
       UIManager.put("Button.border", new LineBorder(Color.BLACK, 1));
        UIManager.put("Label.font", new Font("Serif", Font.BOLD, 20));

        UIManager.put("Table.font", new Font("SansSerif", Font.PLAIN, 15));
        UIManager.put("TableHeader.font", new Font("SansSerif", Font.BOLD, 16));

        System.out.println(new App().getGreeting());

        // Create the frame on the event dispatching thread.
    SwingUtilities.invokeLater(
    new Runnable()
     {public void run() {
       new LibraryApp();
        }
    }  );

}

}
