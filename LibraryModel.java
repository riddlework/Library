/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: LibraryModel.java
 * Course: CSC 335
 * Description: Implements the Model portion of the MVC design pattern
 */
import java.util.Random;
import java.util.*;


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
	public void chooseSearch(String searchMethod, String searchArg) throws NoSuchBookException {
		// TODO: catch exception in the view
		
		switch(searchMethod) {
			case "title":
				searchTitle(searchArg);
				break;
			case "author":
				searchAuthor(searchArg);
				break;
		}
	}
	

	private Book searchTitle(String title) throws NoSuchBookException {
		for (Book book : this.books)
			if (book.getTitle().equals(title)) return book;
		throw new NoSuchBookException("No such title exists.");
	}

	private ArrayList<Book> searchAuthor(String author) throws NoSuchBookException {
		ArrayList<Book> retList = new ArrayList<>();
		for (Book book : this.books)
			if (book.getAuthor().equals(author)) retList.add(book);

		if (retList.isEmpty()) throw new NoSuchBookException("No such author exists.");
		else return retList;
	}
	
	private ArrayList<Book> searchRating(int rating) throws NoSuchBookException {
		ArrayList<Book> retList = new ArrayList<>();
		for (Book book : this.books)
			if (book.getRating() == rating) retList.add(book);

		if (retList.isEmpty()) throw new NoSuchBookException("No such rating exists.");
		else return retList;
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
	public ArrayList<Book> getBooks(String option) {
		switch (option) {
			case "sortByTitle":
				return

		}

	}
	
	
	
	
	
	/*
	 * find a random book to return to the user
	 * (maybe use shuffle to mix collection and return index 0?)
	 * (maybe use random.nextInt to pick a random index?)
	 * 
	 * *going to need to return either data set containing info or Book object itself*
	 */
	public Book suggestRead() {
		Collections.shuffle(this.books);
		return this.books.get(0);
	}
	
	
	
	
	
	/*
	 * read a file and add books from file info
	 * 
	 *  *going to need a parameter for input file name*
	 */
	public void addBooks(String filename) {
		
	}


	/*
	 * Sort the instance variable 'books' by each books rating  
	 */
	private void sortRating() {
		ratingSortedBooks = Collections.sort(books, new createByRatingComparator());
	}

	/*
	 * Sort the instance variable 'books' by each books title  
	 */
	private void sortTitle() {
		titleSortedBooks = Collections.sort(books, new createByTitleComparator());
	}

	/*
	 * Sort the instance variable 'books' by each books author  
	 */
	private void sortAuthor() {
		AuthorSortedBooks = Collections.sort(books, new createByAuthorComparator());
	}
	
	
	
}

class NoSuchBookException extends Exception {
	// constructor that accepts a message
	public NoSuchBookException (String message) {
		super(message);
	}
}

