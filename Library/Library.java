package Library;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Library {
	private static final double INTEREST = 5;// percent %
	
	private String name;
	private ArrayList<Book> books;
	private ConcurrentHashMap<String, Double> taxes;// book name -> book tax
	private ConcurrentHashMap<String, Thread> takenBooks;// book name -> book checker
	
	public Library(String name) {
		this.name = name;
		this.books = new ArrayList<Book>();
		this.taxes = new ConcurrentHashMap<>();
		this.takenBooks = new ConcurrentHashMap<>();
		books.add(new Book("Harry Potter"));
		books.add(new Book("Java for everyone"));
		books.add(new Book("Golden book of talent"));
		books.add(new Book("I love New York"));
		books.add(new Book("Pyramids"));
		
		for (Book book : books) {
			taxes.put(book.getName(), Book.TAX);
		}
	}

	public synchronized Book giveBook() {
		Book book = books.get(new Random().nextInt(books.size()));
		BookCheker bookCheker = new BookCheker(this, book.getName());
		bookCheker.start();
		this.books.remove(book);
		this.takenBooks.put(book.getName(), bookCheker);
		return book;
	}

	public void receiveBook(Book takenBook) {
		this.books.add(takenBook);
		this.takenBooks.get(takenBook.getName()).interrupt();
		System.out.printf("%.2f \n", taxes.get(takenBook.getName()));
		// resumption of the fee for the next reader
		this.taxes.put(takenBook.getName(), Book.TAX);
	}

	public void increaseTax(String bookName) {
		// interest by 1%, if the book isn't returned on time
		double tax = taxes.get(bookName);
		tax += (tax / 100) * INTEREST;
		this.taxes.put(bookName, tax);
	}

}
