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
	


	private static void execute(LibraryController controller) {

		String status = "start";
		while (!status.equals("exit")) {
			
			status = scanCommand(); 
			executeCommand(status);
			
		}		
	}
	
	
	
	
	
	

	
	/*
	 * This method is called with each repetition of the main() loop to get the next command. 
	 * We use a scanner to get the keyboard input and pass it to the proper command method call.
	 * 
	 * parameters: none
	 * returns: users keyboard input string
	 */
	private static String scanCommand() {
		
		return "str";		// just so I'm not getting an error
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
	public static void executeCommand(LibraryController controller, String command) {
		
		// decipher command here and have different controller methods for each?
		// or, pass the string to the controller and decipher there?  (i think this one so we can delete this method)
	
		
		// *add parameters for certain methods*
		switch (command) {
		case "search":
			controller.search();
		break;
		case "addBook":
			controller.addBook();
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
	
	
	
	private static void exit() {

	}
	
	
	
	
	
	
	
	
	
	
	
}
