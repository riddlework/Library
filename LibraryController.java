/**
 * Authors: Ben Yurek and Fay Garcia
 * File: LibraryController.java
 * Course: CSC 335
 * Description: This file implements the Controller portion of the MVC design pattern
 */
import java.util.*;

/**
 * LibraryController implements the controller portion of the MVC design pattern -- it acts as the
 * intermediary between MyLibrary and LibraryModel, passing data and commands between the two.
 */
public class LibraryController {

	// the model
	private LibraryModel model;


	/**
	 * A constructor
	 * @param model a LibraryModel -- the model portion of the MVC design pattern
	 */
	public LibraryController(LibraryModel model) { this.model = model; }


	/**
	 * return the book with the given title
	 * @param title a string -- the title to be searched for
	 * @return the book with the matching title
	 * @throws NoSuchBookException throw this exception if no book with the given title is found
	 */
	public Book searchTitle(String title) throws NoSuchBookException { return model.searchTitle(title); }


	/**
	 * return an arraylist of books with the given author
	 * @param author a string -- the author to be searched for
	 * @return an arraylist of books written by the given author
	 * @throws NoSuchBookException throw this exception if no books with the given author are found
	 */
	public ArrayList<Book> searchAuthor(String author) throws NoSuchBookException { return model.searchAuthor(author); }

	/**
	 * return an arraylist of books with the given rating
	 * @param rating an int -- the rating to be searched for
	 * @return an arraylist of books with the given rating
	 * @throws NoSuchBookException throw this exception if no books with the given rating are found
	 */
	public ArrayList<Book> searchRating(int rating) throws NoSuchBookException { return model.searchRating(rating); }


	/**
	 * add a book to the library
	 * @param title a string -- the title of the book to be added
	 * @param author a string -- the author of the book to be added
	 */
	public void addBook(String title, String author) { model.addBook(title, author); }


	/**
	 * update the read status of a book based on its title
	 * @param title a string -- the title of the book to be updated
	 * @throws NoSuchBookException thrown if no book with the given title exists
	 */
	public void setToRead(String title) throws NoSuchBookException { model.setToRead(title); }


	/**
	 * update the rating of a book with the given title
	 * @param title a string -- the book whose rating is to be updated
	 * @param rating an int -- the rating to assign to the given book
	 * @throws NoSuchBookException thrown if no book with the given title exists
	 */
	public void rate(String title, int rating) throws NoSuchBookException { model.rate(title, rating); }


	/**
	 * returns a list of books based on the option that is passed
	 * @param option a string -- either sorted by title, author, or  read, unread
	 * @return an arraylist of books matching the given option
	 * @throws NoSuchBookException thrown if no such books exist which match the given option
	 */
	public ArrayList<Book> getBooks(String option) throws NoSuchBookException { return model.getBooks(option); }


	/**
	 * suggest a random unread book to the user
	 * @return a book object -- the book to be suggested to the user
	 * @throws NoSuchBookException thrown if no unread books exist
	 */
	public Book suggestRead() throws NoSuchBookException { return model.suggestRead(); }


	/**
	 * add a file of books to the library
	 * @param filename a string -- the name of the file containing the books to be added
	 */
	public void addBooks(String filename) { model.addBooks(filename); }

}