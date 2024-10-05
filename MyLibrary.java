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
			switch (input[0]) {
				case "addBook":
					if (input.length == 3) {
						controller.addBook(input[1], input[2]);
					} break;
				case "addBooks":
					if (input.length == 2) {
						controller.addBooks(input[1]);
					} break;
				case "getBooks":
					if (input.length == 2) {
						controller.getBooks(input[1]);
					} break;
				case "rate":
					if (input.length == 3) {
						controller.rate(input[1], Integer.parseInt(input[2]));
					} break;
				case "search":
					if (input.length == 3) {
						if (input[1].equals("rating")
								&& (0 < Integer.parseInt(input[2]))
								&& (6 > Integer.parseInt(input[2]))) {
							controller.searchRating(Integer.parseInt(input[2]));
						} else if (input[1].equals("title")) {
							controller.searchTitle(input[2]);
						} else if (input[1].equals("author")) {
							controller.searchTitle(input[2]);
						}
					} break;
				case "setToRead":
					if (input.length == 2) {
						controller.setToRead(input[1]);
					} break;
				case "suggestRead":
					if (input.length == 1) {
						controller.suggestRead();
					} break;
				case "help":
					help();
					break;
			}
		}
		catch (NoSuchBookException e) {
			e.printStackTrace();
		}

		System.out.println("Invalid argument input for given command. Type 'help' for a list of valid command arguments.");
	}


	public static void help() {

		System.out.println('+' + "-".repeat(100));
		System.out.println("| Valid Commands ");
		System.out.println('+' + "-".repeat(100));
		System.out.println("| Below is a list of valid commands. If the command \n"
				+ "| name is preceded by a star (*), then it requires \n"
				+ "| extra input arguments that can be found in the \n"
				+ "| bottom half of the table. \n|\n"
				+ "| (Each command is case sensitive and must be typed exactly as it appears.)\n|");
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

	void dumpBooks(ArrayList<Book> books) {

	}


// sepate arguments by comma
	// dumpBooks method
	// more detailed command thingy
	// fix thingy thats printed out by exception catching

	/*
	 * The purpose of this method is to print a message to the user when they provide a keyboard input.
	 * this way they know the status of their command before the next input prompt.
	 *
	 * parameters
	 * command: users last input command string
	 *
	 * returns: void
	 */
	private static void printCommand(String command) {

	}

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