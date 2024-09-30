/*
 * Author(s):
 * File:
 * Course:
 * Description:
 */


public class LibraryController {

	private LibraryModel model;



	
	public LibraryController(LibraryModel model) { this.model = model; }


	
	public void search(int rating) {
		model.search(rating);		
	} 

	
	public void search(String searchMethod, String searchArg) {
		model.search(searchMethod, searchArg);
	}
	
	

	public void addBook() {
		model.addBook();
	}

	public void setToRead() {
		model.setToRead();	
	}

	// do input validation here
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
