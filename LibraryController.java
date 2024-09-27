/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */


public class LibraryController {

	private LibraryModel model;



	
	public LibraryController(LibraryModel model) { this.model = model; }




	public void search() {
		model.search();		
	}

	public void addBook() {
		model.addBook();
	}

	public void setToRead() {
		model.setToRead();	
	}

	public void rate() {
		model.rate();
	}

	public void getBooks() {
		model.getBooks();
	}

	public void suggestRead() {
		model.suggestRead();
	}

	public void addBooks() {
		model.addBooks();
	}




	
	
	
	
	
	
	
	
	
	
	
	
}
