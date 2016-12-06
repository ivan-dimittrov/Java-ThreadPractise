package Library;

public class Book {
	static final double TAX = 2;
	static final int LENDING_TIME = 5;// seconds
	
	private String name;

	public Book(String name) {
		this.name = name; 
	}
	
	public String getName(){
		return this.name;
	}
	
}
