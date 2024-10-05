/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: Book.java
 * Course: CSC 335
 * Description: This class implements a book object.j
 */
import java.util.Comparator;

/**
 * Book represents a book -- title, author, read status, and rating
 *
 * Encapsulation is maintained by providing public getters and setters rather than allowing
 * client code to manipulate instance variables in an uncontrolled manner
 */
public class Book {

	// instance variables
	private String title;
	private String author;
	private boolean isRead; // false if not read, true if already read
	private int rating;


	/**
	 * create a new book object
	 * @param title a String -- the title of the book
	 * @param author a String -- the author of the book
	 * @param isRead a boolean -- whether or not the book has been read
	 * @param rating an int -- from 1-5 (inclusive), the rating of the book
	 */
	public Book(String title, String author, boolean isRead, int rating) {
		this.title = title;
		this.author = author;
		this.isRead = isRead;
		this.rating = rating;
	}


	/**
	 * create a new book object
	 * @param title a String -- the title of the book
	 * @param author a String -- the author of the book
	 */
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.isRead = false;
		this.rating = 0;
	}

	/**
	 * a copy constructor
	 * @param book the object to copy
	 */
	public Book(Book book) {
		this.title = book.title;
		this.author = book.author;
		this.isRead = book.isRead;
		this.rating = book.rating;
	}


	/**
	 * return the title of the book
	 * @return a string -- the title of the book
	 */
	public String getTitle() { return this.title; }


	/**
	 * return the author of the book
	 * @return a string -- the author of the book
	 */
	public String getAuthor() { return this.author; }


	/**
	 * return the read status of the book
	 * @return a string -- the read status of the book
	 */
	public boolean isRead() {return this.isRead; }


	/**
	 * return the rating of the book
	 * @return an int from 1-5 inclusive -- the rating of the book
	 */
	public int getRating() { return this.rating; }


	/**
	 * updates the book rating based off of user input
	 * @param rating an int -- the rating to set
	 */
	public void updateRating(int rating) { this.rating = rating; }


	/**
	 * updates the book read status
	 */
	public void updateRead() { this.isRead = true; }


	/**
	 * Create comparator by author
	 * @return A comparator that sorts by author
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
	 * @return a comparator that sorts by title
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
