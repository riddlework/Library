/**
 * View -- User interface
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */



public class MyLibrary {


	private static void execute(LibraryController controller) {
		boolean status = true;
		Scanner inputScanner = new Scanner(System.in);
		while (status) status = handleCommand(controller, inputScanner);
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
	private static boolean handleCommand(LibraryController controller, Scanner inputScanner) {
		
		// decipher command here and have different controller methods for each?
		// or, pass the string to the controller and decipher there?  (i think this one so we can delete this method)

		String command = inputScanner.nextLine();

		// *add parameters for certain methods*
		switch (command) {
			case "search":
				search();
				break;
			case "setToRead":
				controller.setToRead();
				break;
			case "rate":
				controller.rate();
				break;
			case "getBooks":
				controller.getBooks();
				break;
			case "suggestRead":
				controller.suggestRead();
				break;
			case "addBooks":
				controller.addBooks();
				break;
			case "help":
				break;
			case "exit":
				return false;
		} return true;
	}

	private static void search(LibraryController controller, Scanner inputScanner) {
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
		LibraryController controller = new LibraryController(model);

		execute(controller);

		// print exit message?
	}

	
}


