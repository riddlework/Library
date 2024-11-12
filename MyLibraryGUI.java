import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLibraryGUI {
    private static JTextField inputField;

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
                inputField.setText("Input a title here...");
           }
        });
        byAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("Input an author here...");
            }
        });
        byRating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("Input a rating here...");
            }
        });

        searchMenu.add(byTitle);
        searchMenu.add(byAuthor);
        searchMenu.add(byRating);
        menuBar.add(searchMenu);


        frame.setJMenuBar(menuBar);

        // TODO: other commands
    }

    public static void main(String[] args) {
        // initialize jframe
        JFrame frame = new JFrame("My Library");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new FlowLayout());

        // make the menu
        makeMenu(frame);

        // initialize the text field
        inputField = new JTextField("Input text here");
        inputField.setColumns(20);
        frame.add(inputField);

        frame.setVisible(true);
    }
}
