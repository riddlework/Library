/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: MyLibrary.java
 * Course: CSC 335
 * Description: Implements the View portion of the MVC Design pattern
 */
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

		// *add parameters for certain methods*
		switch (command) {
			case "search":
				break;
			case "setToRead":
				break;
			case "rate":
				break;
			case "getBooks":
				break;
			case "suggestRead":
				break;
			case "addBooks":
				break;
			case "help":
				break;
			case "exit":
				return false;
			default:
				// if we get here, the command was incorrect
				// do something valid
				System.out.println("Please enter a valid command. Type help to show valid commands.");
				break;
		} return true;

	}

	private static void search() {

		// initialize input scanner
		Scanner inputScanner = new Scanner(System.in);

		// retrieve search method
		System.out.println("Would you like to search by title, author, or rating?");
		String searchMethod = inputScanner.nextLine();

		// input validation on searchMethod
		switch (searchMethod) {
			case "rating":
				System.out.print("Input the rating you're looking for: ");
				int searchArg = inputScanner.nextInt();
				controller.search(searchMethod,searchArg);
			case "title":
				System.out.print("Input the title of the book you're looking for: ");
				break;
			case "author":
				System.out.print("Input the author you're looking for: ");
				break;
			case default:
				System.out.println("Your argument is invalid. Type help for a list of commands.");
				search();
				break;
		}
	}

	private static boolean validateInput(String function) {
		System.out.println("Please input a valid command. Type help to display a list of valid commands.");
		switch (function) {
			case "handleCommand":
				handleCommand();
				break;
			case

		}
	}

	
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


