/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: MyLibrary.java
 * Course: CSC 335
 * Description: Implements the View portion of the MVC Design pattern
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * MyLibrary implements the user interface -- prompting the user for commands, validating input,
 * catching exceptions, and passing the appropriate commands onto the Controller.
 */
public class MyLibrary {

	// instance variable -- the controller
	private static LibraryController controller;


	/**
	 * execute contains the main execution loop, calling the appropriate functions for execution to occur
	 */
	private static void execute() {
		boolean status = true;
		while (status) status = handleCommand();
	}


	/**
	 * handleCommand reads a command from the user and passes the input to validateCommand
	 * @return a boolean -- true if execution should continue and false if the program should terminate.
	 */
	private static boolean handleCommand() {

		// read input from the user
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("| Input a command, or type help for a list of valid commands: ");
		String command = inputScanner.nextLine();

		// split the input into the command and its arguments
		String[] splitCommand = command.split(", ");
		return validateCommand(splitCommand);
	}


	/**
	 * validateCommand parses the beginning of the input and checks if it is a valid command. If the command
	 * 		  it passes the rest of the input to validateArg
	 * @param input the input string given by the user
	 * @return a boolean -- true if program execution should continue and false if the program should terminate
	 */
	private static boolean validateCommand(String[] input) {

		// check for a valid command
		switch (input[0]) {
			case "addBook":
			case "addBooks":
			case "getBooks":
			case "rate":
			case "search":
			case "setToRead":
			case "suggestRead":
			case "help":
				// validate the arguments
				validateArgs(input);
				return true;
			case "exit":
				return false;
			default:
				// if the command is invalid, prompt the user for input
				System.out.println("| Invalid command input. Type 'help' for a list of valid commands.");
				return true;
		}
	}


	/**
	 * validateArgs validates the arguments and passes the appropriate commands to the controller
	 * @param input A String, the arguments to be validated
	 */
	private static void validateArgs(String[] input) {
		try {
			int len = input.length;

			/*
			check the input string
			validate the number of arguments
			pass the appropriate commands to the controller
			 */
			if (input[0].equals("addBook") && len == 3)  {
				controller.addBook(input[1], input[2]);
			} else if (input[0].equals("addBooks") && len == 2) {
				try {
					controller.addBooks(input[1]);
				}
				// throw this if the file is not found
				catch (FileNotFoundException e) {
					System.out.println("| File not found. Try again!");
				}

			} else if (input[0].equals("getBooks") && len == 2) {
				dumpBooks(controller.getBooks(input[1]));
			} else if (input[0].equals("rate") && len == 3) {
				int rating = Integer.parseInt(input[2]);
				if (rating < 1) rating = 1;
				if (rating > 5) rating = 5;
				controller.rate(input[1], rating);
			} else if (input[0].equals("search") && len == 3) {
				if (input[1].equals("rating")) {
					int rating = Integer.parseInt(input[2]);
					if (rating < 1) rating = 1;
					if (rating > 5) rating = 5;
					dumpBooks(controller.searchRating(rating));
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
				// if invalid input is passed, prompt the user accordingly
				System.out.println("| Invalid argument input for given command. Type 'help' for a list of valid command arguments.");
			}
		}
		catch (NoSuchBookException e) {
			// if no such book exists, pass the message along to the user
			System.out.println(e.getMessage());
		}

	}


	/**
	 * print out a help page of commands and formats for the user
	 */
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


	/**
	 * dumpBooks prints out a list of book titles, authors, read statuses, and ratings in a readable format.
	 * @param books the arraylist of books to be dumped.
	 */
	private static void dumpBooks(ArrayList<Book> books) {
		for (Book book: books) dumpBook(book);
	}

	/**
	 * print out the title, author, read status, and rating of one book in a readable format
	 * @param book the book object whose information will be printed
	 */
	private static void dumpBook(Book book) {
		System.out.println("| Title       : " + book.getTitle());
		System.out.println("| Author      : " + book.getAuthor());

		String isReadString = book.isRead() ? "Read" : "Unread";
		System.out.println("| Read/Unread : " + isReadString);

		// print the rating if it exists
		int rating = book.getRating();
		if (rating > 0) System.out.println("| Rating       : " + String.valueOf(rating));
		System.out.println("+" + "-".repeat(59));
	}


	/**
	 * the main method -- creates the model and controller and calls the execution loop
	 * @param args N/A
	 */
	public static void main(String[] args) {

		// instantiate objects
		LibraryModel model = new LibraryModel();
		controller = new LibraryController(model);

		execute();
	}

}
