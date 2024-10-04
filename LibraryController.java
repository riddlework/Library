/**
 * Authors: Ben Yurek and Fay Garcia
 * File: LibraryController.java
 * Course: CSC 335
 * Description: This file implements the Controller portion of the MVC design pattern
 */
import java.util.*;

public class LibraryController {
	private LibraryModel model;


	public LibraryController(LibraryModel model) { this.model = model; }

	
	public ArrayList<Book> searchRating(int rating) throws NoSuchBookException {
		return model.searchRating(rating);
	}

	
	public void search(String searchMethod, String searchArg) {
		model.search(searchMethod, searchArg);
	}
	public void search(String searchMethod, String searchAarg) {

	}
	
	

	public void addBook() {
		model.addBook();
	}

	public void setToRead() {
		model.setToRead();	
	}

	// do input validation here
	public void rate() {
		model.rate();
	}

	public void getBooks() {
		model.getBooks();
	}

	public void suggestRead() {
		model.suggestRead();
	}

	public void addBooks() {
		model.addBooks();
	}




	
	
	
	
	
	
	
	
	
	
	
	
}
