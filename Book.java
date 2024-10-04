/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: Book.java
 * Course: CSC 335
 * Description: This class implements a book object.j
 */
import java.util.Comparator;
import java.util.Optional;

public class Book {

	// instance variables
	private String title;
	private String author;
	private boolean isRead; // false if not read, true if already read
	private int rating;

	/**
	 * constructor
	 */
	public Book(String title, String author, boolean isRead, int rating) {
		this.title = title;
		this.author = author;
		this.isRead = isRead;
		this.rating = rating;
	}

	// comparator nested classes in here?

	// getters
	public String getTitle() { return this.title; }

	public String getAuthor() { return this.author; }

	public int getRating() { return this.rating; }


	/**
	 * updates the book rating based off user input
	 */
	public void updateRating(int rating) { this.rating = rating; }
	
	/**
	 * updates the book read status  
	 */
	public void updateRead() { this.isRead = true; }


}
