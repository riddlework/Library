import java.util.Random;
import java.util.*;


/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */


public class LibraryModel {
	// instance variables
	ArrayList<Book> books;



	
	/*
	 * constructor
	 */
	public LibraryModel() { books = new ArrayList<>(); }
	
	
	
	
	
	/*
	 * find all books with a given search criteria
	 * 
	 * *going to need parameters to find books that have common factors*
	 */
	public void chooseSearch() {
		
	}

	private Book searchTitle(String title) throws NoSuchBookException {
		// TODO: catch exception in the view
		for (Book book : this.books)
			if (book.getTitle().equals(title)) return book;
		throw new NoSuchBookException("No such book exists.");
	}
	
	
	
	
	/*
	 * create a book to add to the collection
	 * 
	 * *going to need parameters to create book*
	 */
	public void addBook(String title, String author, boolean isRead, int rating) {
		Book book = new Book(title, author, isRead, rating);
		this.books.add(book);
	}

	
	
	
	/*
	 * edit the read status of the book
	 * 
	 * *going to need parameters to find book*
	 */
	public void setToRead(String title) throws NoSuchBookException {
		Book bookToUpdate = searchTitle(title);
		bookToUpdate.updateRead();
	}

	

	/*
	 * edit the integer 1-5 rating of the book
	 * 
	 * * going to need parameters to find book*
	 */
	public void rate(String title, int rating) throws NoSuchBookException {
		Book bookToUpdate = searchTitle(title);
		bookToUpdate.updateRating(rating);
	}


	/*
	 * return entire collection of books given a specific return criteria
	 * 
	 * *probably going to need helper methods for different criteria*
	 * 
	 * *going to need to figure out return type*
	 * 
	 * *going to need parameter to decipher return format*
	 */
	public void getBooks() {

	}
	
	
	
	
	
	/*
	 * find a random book to return to the user
	 * (maybe use shuffle to mix collection and return index 0?)
	 * (maybe use random.nextInt to pick a random index?)
	 * 
	 * *going to need to return either data set containing info or Book object itself*
	 */
	public void suggestRead() {
		
	}
	
	
	
	
	
	/*
	 * read a file and add books from file info
	 * 
	 *  *going to need a parameter for input file name*
	 */
	public void addBooks() {
		
	}
	
	
}

class NoSuchBookException extends Exception {

	// constructor that accepts a message
	public NoSuchBookException(String message) {
		super(message);
	}
}
