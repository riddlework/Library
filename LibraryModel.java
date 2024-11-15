/**
 * Author(s): Ben Yurek and Fay Garcia
 * File: LibraryModel.java
 * Course: CSC 335
 * Description: Implements the Model portion of the MVC design pattern
 */
import java.util.*;
import java.util.Collections;
import java.io.*;


/**
 * LibraryModel implements all functionality and acts as the model portion in the MVC design pattern
 *
 * Encapsulation is maintained by returning copies of all ArrayLists and the Books they contain rather than
 * references to the original objects.
 */
public class LibraryModel {

	// the list of books in the library
	private ArrayList<Book> books;


	/**
	 * A constructor -- initializes the list of books
	 */
	public LibraryModel() { books = new ArrayList<>(); }


	/**
	 * search for a book by its title
	 * @param title a string -- the title of the book to search for
	 * @return a book object -- the book with the given title
	 * @throws NoSuchBookException thrown if no book with the given title exists
	 */
	public Book searchTitle(String title) throws NoSuchBookException {
		for (Book book : this.books)
			if (book.getTitle().equals(title)) return book;
		throw new NoSuchBookException("| No such title exists.");
	}


	/**
	 * search for a group of books by author
	 * @param author a string -- the author to search for
	 * @return an arraylist of books whose authors match that which is given
	 * @throws NoSuchBookException thrown if no books with the given author exist
	 */
	public ArrayList<Book> searchAuthor(String author) throws NoSuchBookException {
		ArrayList<Book> retList = new ArrayList<>();

		// loop over the books to search for those by the given author
		for (Book book : this.books)
			if (book.getAuthor().equals(author)) retList.add(new Book(book));

		// throw an exception if no such books exist
		if (retList.isEmpty()) throw new NoSuchBookException("| No such author exists.");
		else return retList;
	}


	/**
	 * search for a group of books by rating
	 * @param rating an int -- the rating to search for
	 * @return an arraylist of books whose ratings match that which is given
	 * @throws NoSuchBookException thrown if no books with the given rating exist
	 */
	public ArrayList<Book> searchRating(int rating) throws NoSuchBookException {
		ArrayList<Book> retList = new ArrayList<>();

		// loop over the books to search for those with the given rating
		for (Book book : this.books)
			if (book.getRating() == rating) retList.add(new Book(book));

		// throw an exception if no such book exists
		if (retList.isEmpty()) throw new NoSuchBookException("| No such rating exists.");
		else return retList;
	} /**
	 * add a new book to the list of books
	 * @param title a string -- the title of the book to add
	 * @param author a string -- the author of the book to add
	 */
	public void addBook(String title, String author) {
		Book book = new Book(title, author);
		this.books.add(book);
	}


	/**
	 * update the read status of a given book
	 * @param title a string -- the title of the book to be updated
	 * @throws NoSuchBookException thrown if no book with the given title exists
	 */
	public void setToRead(String title) throws NoSuchBookException {
		Book bookToUpdate = searchTitle(title);
		bookToUpdate.updateRead();
	}


	/**
	 * rate a book 1-5 (inclusive)
	 * @param title a string -- the title of the book to be rated
	 * @param rating an integer 1-5 (inclusive) -- the rating to assign to the book
	 * @throws NoSuchBookException thrown if no such book exists within the library
	 */
	public void rate(String title, int rating) throws NoSuchBookException {
		Book bookToUpdate = searchTitle(title);
		bookToUpdate.updateRating(rating);
	}


	/**
	 * return a collection of books given a specific return criterion
	 * @param option a string
	 *               rating -- return all books sorted by rating
	 *               author -- return all books sorted by author
	 *               read   -- return all books that have been read
	 *               unread -- return all books that have not been read
	 * @return an arraylist of books matching the return criterion
	 * @throws NoSuchBookException thrown if no such collection of books exists within the library
	 */
	public ArrayList<Book> getBooks(String option) throws NoSuchBookException {
		ArrayList<Book> booksToReturn = new ArrayList<>();
		switch (option) {
			case "title":
				sortTitle();
				booksToReturn = new ArrayList<>(this.books);
				break;
			case "author":
				sortAuthor();
				booksToReturn = new ArrayList<>(this.books);
				break;
			case "read":
				booksToReturn = getReadBooks(true);
				break;
			case "unread":
				booksToReturn = getReadBooks(false);
				break;
		}

		if (booksToReturn.isEmpty()) throw new NoSuchBookException("| No such books exist!");

		// make a copy to avoid escaping references and return
		ArrayList<Book> booksToReturnCopy = new ArrayList<>();
		for (Book book: booksToReturn) {
			booksToReturnCopy.add(new Book(book));
		}
		return booksToReturnCopy;
	}


	/**
	 * sort the library by title
	 */
	private void sortTitle() { Collections.sort(books, Book.createByTitleComparator()); }


	/**
	 * sort the library by author
	 */
	private void sortAuthor() { Collections.sort(books, Book.createByAuthorComparator()); }


	/**
	 * return a list of books that meet the return criterion
	 * @param wantsReadBooks a boolean -- the return criterion
	 *                       true  -- return all books that have been read
	 *                       false -- return all books that have not been read
	 * @return an arraylist of books matching the return criterion
	 */
	private ArrayList<Book> getReadBooks(boolean wantsReadBooks) {
		ArrayList<Book> readBooks = new ArrayList<>();
		ArrayList<Book> unreadBooks = new ArrayList<>();

		for (Book book: this.books) {
			if (book.isRead()) readBooks.add(book);
			else unreadBooks.add(book);
		}

		if (wantsReadBooks) return readBooks;
		else return unreadBooks;
	}


	/**
	 * choose a random unread book from the library and return it to the controller
	 * @return a book object that is unread
	 * @throws NoSuchBookException thrown if no unread books exist within the library
	 */
	public Book suggestRead() throws NoSuchBookException {
		ArrayList<Book> booksToReturn = getReadBooks(false);
		if (booksToReturn.isEmpty()) throw new NoSuchBookException("| There are no unread books!");
		Collections.shuffle(booksToReturn);
		return booksToReturn.get(0);
	}

	
	/**
	 * add a list of books to the library from a given file
	 * @param filename a string -- the name of the file containing the list of books
	 */
	public void addBooks(String filename) throws FileNotFoundException {
		File inputFile = new File(filename);

		//create scanner
		Scanner scan = new Scanner(inputFile);

		//instantiate necessary reading variables
		String curLine = scan.nextLine();
		String[] split;
		String title;
		String author;

		//read each line, split into title and author, create a book, add new book to collection
		while (scan.hasNext()) {
			curLine = scan.nextLine();
			split = curLine.split(";");
			title = split[0];
			author = split[1];
			books.add(new Book(title, author));
		}

		//close scanner
		scan.close();
		

	}
}



