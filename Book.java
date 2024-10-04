/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: Book.java
 * Course: CSC 335
 * Description: This class implements a book object.j
 */
import java.util.Comparator;

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

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.isRead = false;
		this.rating = 0;
	}

	public Book(Book book) {
		this.title = book.title;
		this.author = book.author;
		this.isRead = book.isRead;
		this.rating = book.rating;
	}


	// getters
	public String getTitle() { return this.title; }

	public String getAuthor() { return this.author; }

	public boolean isRead() {return this.isRead; }

	public int getRating() { return this.rating; }


	/**
	 * updates the book rating based off user input
	 */
	public void updateRating(int rating) { this.rating = rating; }
	
	/**
	 * updates the book read status  
	 */
	public void updateRead() { this.isRead = true; }


	/**
	 * Create comparator by author
	 */
	public static Comparator<Book> createByAuthorComparator() {
		return new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				String b1a = b1.getAuthor();
				String b2a = b2.getAuthor();

				return b1a.compareTo(b2a);
			}
		};
	}

	/**
	 * create comparator by title
	 */
	public static Comparator<Book> createByTitleComparator() {
		return new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				String b1t = b1.getTitle();
				String b2t = b2.getTitle();
				return b1t.compareTo(b2t);
			}
		};	
	}
	
	

}
