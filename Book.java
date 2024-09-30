/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */


import java.util.Optional;

public class Book {

	// instance variables
	private String title;
	private String author;
	private boolean isRead; // false if not read, true if already read
	private int rating;

	/*
	 * constructor
	 */
	public Book(String title, String author, boolean isRead, int rating) {
		this.title = title;
		this.author = author;
		this.isRead = isRead;
		this.rating = rating;
	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.isRead = false;
		this.rating = 0;
	}

	/*
	 * updates the book rating based off user input
	 */
	public void updateRating(int rating) { this.rating = rating; }
	
	/*
	 * updates the book read status  
	 */
	public void updateRead() { this.isRead = true; }
	
	
	
}
