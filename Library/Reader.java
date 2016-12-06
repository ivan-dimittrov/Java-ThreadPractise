package Library;

import my.exceptions.ReadingTimeException;
import my.exceptions.WrongNameException;

public class Reader extends Thread{

	private Book takenBook;
	private static Library library = new Library("Sofia Library");	
	private int readingTime;// in seconds
	
	public Reader(String name, int readingTime) throws WrongNameException, ReadingTimeException {
		if (isNameCorrect(name)) {
			setName(name);
		}else {
			throw new WrongNameException();
		}
		if (readingTime > 0) {
			this.readingTime = readingTime;
		} else {
			throw new ReadingTimeException();
		}
		
	}
	
	@Override
	public void run() {
		this.takeBook();
		try {
			Thread.sleep(readingTime * 1000);
		} catch (InterruptedException e) {
			System.err.println(getName() + " was interrupted");
		}
		this.returnBook();
	}
	

	public void takeBook() {
		Book book = library.giveBook();
		System.out.println(getName() + " takes " + book.getName());
		this.takenBook = book;
		
	}

	public void returnBook() {
		System.out.println(getName() + " returns " + takenBook.getName());
		System.out.print(getName() + " owe to the library: ");
		library.receiveBook(this.takenBook);
		this.takenBook = null;
	}
	
	private boolean isNameCorrect(String name){
		String regex = "[a-zA-Z\\s]+";
		return name.matches(regex);
	}
}
