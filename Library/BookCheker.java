package Library;

public class BookCheker extends Thread{
	
	private Library library;
	private String bookName;
	
	
	public BookCheker(Library library, String bookName) {
		this.library = library;
		this.bookName = bookName;
	}
	

	@Override
	public void run() {
		try {
			Thread.sleep(Book.LENDING_TIME * 1000);
		} catch (InterruptedException e) {
			return;
		}
		while (true) {
			try {
				Thread.sleep(1000);
				library.increaseTax(this.bookName);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
