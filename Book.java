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
	private Optional<Integer> rating;
	private Optional<Boolean> isRead; // false if not read, true if already read

	/*
	 * constructor
	 */
	public Book(String title, String author, Optional<Integer> rating, Optional<Boolean> isRead) {
		this.title = title;
		this.author = author;

		this.rating = rating.isPresent() ? rating : Optional.empty();
		this.isRead = isRead.isPresent() ? isRead : Optional.empty();
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
