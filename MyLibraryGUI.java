import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyLibraryGUI {
    private static LibraryController controller;
    private static JTextField inputField;
    private static JTextArea textArea;

    private enum Operation {
        SEARCH_TITLE,
        SEARCH_AUTHOR,
        SEARCH_RATING;
    }

    private static Operation op;

    // TODO: method to reset gui elements

    public static void makeMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // create search commands
        JMenu searchMenu = new JMenu("Search");

        JMenuItem byTitle, byAuthor, byRating;
        byTitle = new JMenuItem("By Title");
        byAuthor = new JMenuItem("By Author");
        byRating = new JMenuItem("By Rating");

        // add action listeners to menu items
        byTitle.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               textArea.setText("");
                inputField.setText("Input a title to search for here...");
                op = Operation.SEARCH_TITLE;
           }
        });
        byAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input an author to search for here...");
                op = Operation.SEARCH_AUTHOR;
            }
        });
        byRating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input a rating to search for here...");
                op = Operation.SEARCH_RATING;
            }
        });

        searchMenu.add(byTitle);
        searchMenu.add(byAuthor);
        searchMenu.add(byRating);
        menuBar.add(searchMenu);


        frame.setJMenuBar(menuBar);

        // TODO: other commands
    }

    public static void initTextField(JSplitPane splitPane) throws IllegalArgumentException {
        inputField = new JTextField("Input text here");
        inputField.setColumns(20);

        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // retrieve input
                String input = inputField.getText();

                // TODO: perform input validation
                switch(op) {
                    case SEARCH_TITLE:
                        try {
                            textArea.setText(dumpBook(controller.searchTitle(input)));
                        } catch (NoSuchBookException f) {
                            textArea.setText("No such book exists!");
                        } break;
                    case SEARCH_AUTHOR:
                        try {
                            textArea.setText(dumpBooks(controller.searchAuthor(input)));
                        } catch (NoSuchBookException f) {
                            textArea.setText("No such book exists!");
                        } break;
                    case SEARCH_RATING:
                        try {
                            textArea.setText(dumpBooks(controller.searchRating(Integer.valueOf(input))));
                        } catch (NoSuchBookException f) {
                            textArea.setText("No such book exists!");
                        } break;
                    default:
                        System.out.println("Please click on an option");
                        break;
                }
            }
        });
        splitPane.add(inputField);


    }

    /**
     * dumpBooks prints out a list of book titles, authors, read statuses, and ratings in a readable format.
     * @param books the arraylist of books to be dumped.
     */
    private static String dumpBooks(ArrayList<Book> books) {
        String output = "";
        for (Book book: books) output += dumpBook(book);
        return output;
    }

    /**
     * print out the title, author, read status, and rating of one book in a readable format
     * @param book the book object whose information will be printed
     */
    private static String dumpBook(Book book) {
        String output = "";
        output += "| Title               : " + book.getTitle();
        output += "\n";
        output += "| Author          : " + book.getAuthor();
        output += "\n";

        String isReadString = book.isRead() ? "Read" : "Unread";
        output += "| Read/Unread : " + isReadString;
        output += "\n";


        // print the rating if it exists
        int rating = book.getRating();
        if (rating > 0) output += "| Rating       : " + String.valueOf(rating);
        output += "+" + "-".repeat(59);
        output += "\n";

        return output;
    }

    public static void main(String[] args) {
        // initialize objects
        LibraryModel model = new LibraryModel();
        controller = new LibraryController(model);

        controller.addBooks("books.txt");

        // initialize jframe
        JFrame frame = new JFrame("My Library");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());

        // make the menu
        makeMenu(frame);

        // create a horizonatl split
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(50);

        // initialize the text field
        initTextField(splitPane);

        // initialize the text area
        textArea = new JTextArea();
        textArea.setText("hello world");
        splitPane.add(textArea);

        frame.add(splitPane);


        frame.setVisible(true);
    }
}