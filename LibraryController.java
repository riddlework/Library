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

	public Book searchTitle(String title) throws NoSuchBookException { return model.searchTitle(title); }

	public ArrayList<Book> searchAuthor(String author) throws NoSuchBookException { return model.searchAuthor(author); }

	public ArrayList<Book> searchRating(int rating) throws NoSuchBookException { return model.searchRating(rating); }

	public void addBook(String title, String author) { model.addBook(author, title); }

	public void setToRead(String title) throws NoSuchBookException { model.setToRead(title); }

	// do input validation here
	public void rate(String title, int rating) throws NoSuchBookException { model.rate(title, rating); }

	public ArrayList<Book> getBooks(String option) throws NoSuchBookException { return model.getBooks(option); }

	public void suggestRead() throws NoSuchBookException { model.suggestRead(); }

	public void addBooks(String filename) { model.addBooks(filename); }

}