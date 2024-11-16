import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MyLibraryGUI {
    private static LibraryController controller;
    private static JTextField inputField;
    private static JTextArea textArea;

    private enum Operation {
        SEARCH_TITLE,
        SEARCH_AUTHOR,
        SEARCH_RATING,
        ADD_BOOK,
        ADD_BOOKS,
        SET_TO_READ,
        RATE,
        GET_BOOKS_BY_READ_STATUS,
    }

    private static Operation op;

    public static void makeMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // create search commands
        JMenu searchMenu, addMenu, setMenu, rateMenu, printMenu, suggestMenu, helpMenu;
        searchMenu = new JMenu("Search");
        addMenu = new JMenu("Add");
        setMenu = new JMenu("Set");
        rateMenu = new JMenu("Rate");
        printMenu = new JMenu("Get Books");
        suggestMenu = new JMenu("Suggest");
        helpMenu = new JMenu("Help");

        JMenuItem searchByTitle, searchByAuthor, searchByRating, addBook, addBooks, setToRead, rate, getBooksByTitle, getBooksByAuthor, getBooksByReadStatus, suggestRead, help;
        searchByTitle = new JMenuItem("By Title");
        searchByAuthor = new JMenuItem("By Author");
        searchByRating = new JMenuItem("By Rating");
        addBook = new JMenuItem("Add Book");
        addBooks = new JMenuItem("Add Books");
        setToRead = new JMenuItem("Set Book to Read");
        rate = new JMenuItem("Rate a Book");
        getBooksByTitle = new JMenuItem("Sort Books by Title");
        getBooksByAuthor = new JMenuItem("Sort Books by Author");
        getBooksByReadStatus = new JMenuItem("Print Books by Read Status");
        suggestRead = new JMenuItem("I'm feeling lucky ;-)");
        help = new JMenuItem("Help");


        // add action listeners to menu items
        searchByTitle.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               textArea.setText("");
                inputField.setText("Input a title to search for here...");
                op = Operation.SEARCH_TITLE;
           }
        });
        searchByAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input an author to search for here...");
                op = Operation.SEARCH_AUTHOR;
            }
        });
        searchByRating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input a rating to search for here...");
                op = Operation.SEARCH_RATING;
            }
        });
        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input title, author here...");
                op = Operation.ADD_BOOK;
            }
        });
        addBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input the name of the file here...");
                op = Operation.ADD_BOOKS;
            }
        });
        setToRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input the name of the book here...");
                op = Operation.SET_TO_READ;
            }
        });
        rate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input 'name, rating' here...");
                op = Operation.RATE;
            }
        });
        getBooksByTitle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.setText(dumpBooks(controller.getBooks("title")));
                } catch (NoSuchBookException f) {
                    textArea.setText("No such books exist!");
                }
            }
        });
        getBooksByAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.setText(dumpBooks(controller.getBooks("author")));
                } catch (NoSuchBookException f) {
                    textArea.setText("No such books exist!");
                }
            }
        });
        getBooksByReadStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                inputField.setText("Input desired read status (read/unread)...");
                op = Operation.GET_BOOKS_BY_READ_STATUS;
            }
        });
        suggestRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("...");
                try {
                    textArea.setText(dumpBook(controller.suggestRead()));
                } catch (NoSuchBookException f) {
                    textArea.setText("No unread books");
                }
            }
        });
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("...");
                String output = '+' + "-".repeat(100);
                output += "\n";
                output += "If a command requires multiple arguments, please provide them in the following form:\n" +
                        "argument1, argument2.\n";
                output += "Please note that book names and authors are case-sensitive.\n";
                output += '+' + "-".repeat(100);
                textArea.setText(output);
            }
        });


        searchMenu.add(searchByTitle);
        searchMenu.add(searchByAuthor);
        searchMenu.add(searchByRating);

        addMenu.add(addBook);
        addMenu.add(addBooks);

        setMenu.add(setToRead);
        rateMenu.add(rate);

        printMenu.add(getBooksByReadStatus);
        printMenu.add(getBooksByAuthor);
        printMenu.add(getBooksByTitle);

        suggestMenu.add(suggestRead);
        helpMenu.add(help);

        menuBar.add(searchMenu);
        menuBar.add(addMenu);
        menuBar.add(setMenu);
        menuBar.add(rateMenu);
        menuBar.add(printMenu);
        menuBar.add(suggestMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
    }

    public static void initTextField(JSplitPane splitPane) throws IllegalArgumentException {
        inputField = new JTextField("Input text here");
        inputField.setColumns(20);

        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // retrieve input
                String input = inputField.getText();
                String[] inputList;

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
                            int rating = Integer.parseInt(input);
                            if (rating < 1) rating = 1;
                            if (rating > 5) rating = 5;
                            textArea.setText(dumpBooks(controller.searchRating(rating)));
                        } catch (NoSuchBookException f) {
                            textArea.setText("No such book exists!");
                        } break;
                    case ADD_BOOK:
                        inputList = inputField.getText().split(", ");
                        // input validation
                        if (inputList.length == 2) {
                            controller.addBook(inputList[0],inputList[1]);
                        } else {
                            textArea.setText("Invalid argument input for given command.\n");
                        }
                        break;
                    case ADD_BOOKS:
                        try {
                            controller.addBooks(input);
                            textArea.setText("File added successfully.");
                        } catch (FileNotFoundException f) {
                            textArea.setText("File not found. Try again!");
                        } break;
                    case SET_TO_READ:
                        try {
                            controller.setToRead(input);
                            textArea.setText("Book successfully updated");
                        } catch (NoSuchBookException f) {
                            textArea.setText("No such book exists!");
                        } break;
                    case RATE:
                        inputList = inputField.getText().split(", ");
                        // input validation
                        if (inputList.length == 2) {
                            int rating = Integer.parseInt(inputList[1]);
                            if (rating < 1) rating = 1;
                            if (rating > 5) rating = 5;
                            try {
                                controller.rate(inputList[0], rating);
                                textArea.setText("Book successfully rated.");
                            } catch (NoSuchBookException f) {
                                textArea.setText("No such book exists!");
                            }
                        } else {
                            textArea.setText("Invalid argument input for given command.\n");
                        } break;
                    case GET_BOOKS_BY_READ_STATUS:
                        if (!input.equals("unread") && !input.equals("read")) {
                            textArea.setText("Invalid argument input for given command. Please input 'read' or 'unread'");
                        } else {
                            try {
                                textArea.setText(dumpBooks(controller.getBooks(input)));
                            } catch (NoSuchBookException f) {
                                textArea.setText("No such book exists!");
                            }
                        } break;
                    default:
                        System.out.println("Please click on an option");
                        break;
                }

                inputField.setText("");
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
        if (rating > 0) {
            output += "| Rating       : " + String.valueOf(rating);
            output += "\n";
        }
        output += "+" + "-".repeat(59);
        output += "\n";

        return output;
    }

    public static void main(String[] args) {
        // initialize objects
        LibraryModel model = new LibraryModel();
        controller = new LibraryController(model);

        // initialize jframe
        JFrame frame = new JFrame("My Library");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new BorderLayout());

        // make the menu
        makeMenu(frame);

        // create a vertical split
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(50);

        // initialize the text field
        initTextField(splitPane);

        // initialize the text area
        textArea = new JTextArea();
        textArea.setText("Books will be displayed here...");
        JScrollPane scrollPane = new JScrollPane(textArea);

        splitPane.add(scrollPane);

        frame.add(splitPane);


        frame.setVisible(true);
    }
}