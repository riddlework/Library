/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: MyLibrary.java
 * Course: CSC 335
 * Description: Implements the View portion of the MVC Design pattern
 */
import java.util.ArrayList;
import java.util.Scanner;


/**
 * View -- User interface
 */
public class MyLibrary {

	private static LibraryController controller;


	private static void execute() {
		boolean status = true;
		while (status) status = handleCommand();
	}

	

	/*
	 * This method is called with each repetition of the main() loop to pass the users next command
	 * to the controller.
	 *
	 * parameters
	 * command: users input command string
	 *
	 * returns: void
	 */
	private static boolean handleCommand() {

		// decipher command here and have different controller methods for each?
		// or, pass the string to the controller and decipher there?  (i think this one so we can delete this method)

		Scanner inputScanner = new Scanner(System.in);

		System.out.print("Input a command, or type help for a list of valid commands: ");

		String command = inputScanner.nextLine();

		String[] splitCommand = command.split(", ");
		return validateCommand(splitCommand);
	}



	private static boolean validateCommand(String[] input) {

		switch (input[0]) {
			case "addBook":
			case "addBooks":
			case "getBooks":
			case "rate":
			case "search":
			case "setToRead":
			case "suggestRead":
			case "help":
				validateArgs(input);
				return true;
			case "exit":
				return false;
			default:
				System.out.println("Invalid command input. Type 'help' for a list of valid commands.");
				return true;
		}
	}


	private static void validateArgs(String[] input) {
		// handle exception
		try {
			int len = input.length;
			if (input[0].equals("addBook") && len == 3)  {
				controller.addBook(input[1], input[2]);
			} else if (input[0].equals("addBooks") && len == 2) {
				controller.addBooks(input[1]);
			} else if (input[0].equals("getBooks") && len == 2) {
				dumpBooks(controller.getBooks(input[1]));
			} else if (input[0].equals("rate") && len == 3) {
				controller.rate(input[1], Integer.parseInt(input[2]));
			} else if (input[0].equals("search") && len == 3) {
				if (input[1].equals("rating")
						&& (0 < Integer.parseInt(input[2]))
						&& (6 > Integer.parseInt(input[2]))) {
					dumpBooks(controller.searchRating(Integer.parseInt(input[2])));
				} else if (input[1].equals("title")) {
					dumpBook(controller.searchTitle(input[2]));
				} else if (input[1].equals("author")) {
					dumpBooks(controller.searchAuthor(input[2]));
				}
			} else if (input[0].equals("setToRead") && len == 2) {
				controller.setToRead(input[1]);
			} else if (input[0].equals("suggestRead") && len == 1) {
				dumpBook(controller.suggestRead());
			} else if (input[0].equals("help")) {
				help();
			} else {
				System.out.println("Invalid argument input for given command. Type 'help' for a list of valid command arguments.");
			}
		}
		catch (NoSuchBookException e) {
			System.out.println(e.getMessage());
		}

	}


	public static void help() {

		System.out.println('+' + "-".repeat(100));
		System.out.println("| Valid Commands ");
		System.out.println('+' + "-".repeat(100));
		System.out.println("| Below is a list of valid commands. If the command \n"
				+ "| name is preceded by a star (*), then it requires \n"
				+ "| extra input arguments that can be found in the \n"
				+ "| bottom half of the table. \n|\n"
				+ "| -Each command is case sensitive and must be typed exactly as it appears.\n"
				+ "| -Commands and arguments should be separated by commas ',' \n"
				+ "|  	For example: search, title, Lord of the Rings\n|");
		System.out.println("| * addBook   :		Add a new book to your collection.");
		System.out.println("| * addBooks  :		Submit a text file of books to add to your collection.");
		System.out.println("| * getBooks  : 	Display all books in your collection that fall under a specified category.");
		System.out.println("| * rate      : 	Change the rating of a book after it has been read.");
		System.out.println("| * search    : 	Search for books via book's title, author, rating.");
		System.out.println("| * setToRead : 	Change the status of a book to read from unread.");
		System.out.println("| suggestRead : 	Suggest a random unread book from your collection.");
		System.out.println('+' + "-".repeat(100));
		System.out.println("| Extra Required Arguments");
		System.out.println('+' + "-".repeat(100));
		System.out.println("| Below are the required arguments for each of the above commands.\n|");
		System.out.println("| addBook     :		The title and author of the book to add.");
		System.out.println("| addBooks    :		The name of the text file to read from (formatted to title;author on each line).");
		System.out.println("| getBooks    : 	The filter criteria (author, rating, read, unread).");
		System.out.println("| rate        : 	The title of the book to rate and the desired rating (1-5).");
		System.out.println("| search      : 	The desired book's title, author or rating.");
		System.out.println("| setToRead   : 	The title of the desired book to rate.");
		System.out.println("| suggestRead : 	N/A. ");
		System.out.println('+' + "-".repeat(100));

	}

	private static void dumpBooks(ArrayList<Book> books) {
		for (Book book: books) dumpBook(book);
	}

	private static void dumpBook(Book book) {
		System.out.println("| Title       : " + book.getTitle());
		System.out.println("| Author      : " + book.getAuthor());

		String isReadString = book.isRead() ? "Read" : "Unread";
		System.out.println("| Read/Unread : " + isReadString);

		int rating = book.getRating();
		if (rating > 0) System.out.println("| Rating       : " + String.valueOf(rating));
		System.out.println("+" + "-".repeat(59));
	}


// sepate arguments by comma
	// dumpBooks method
	// more detailed command thingy
	// fix thingy thats printed out by exception catching
	/*
	 * main method
	 */
	public static void main(String[] args) {

		// instantiate objects (controller? model? collection?)
		LibraryModel model = new LibraryModel();
		controller = new LibraryController(model);

		execute();

		// print exit message?
	}

}
