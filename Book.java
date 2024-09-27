/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */


public class Book {

	// instance variables
	private String title;
	private String author;
	private int rating;
	private boolean read;		// false if not read, true if already read
	
	
	
	/*
	 * constructor
	 */
	public Book(String title, String author, int rating, boolean read) {
		
		this.title = title;
		this.author = author;
		this.rating = rating;
		this.read = read;
		
	}
	
	
	
	
	
	/*
	 * updates the book rating based off user input
	 */
	public void updateRating(int rating) {
		
		this.rating = rating;
	}
	
	
	
	
	
	/*
	 * updates the book read status  
	 */
	public void updateRead() {
		
		read = true;
	}
	
	
	
}
