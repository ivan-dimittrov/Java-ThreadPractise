package Library;

import my.exceptions.ReadingTimeException;
import my.exceptions.WrongNameException;

public class Demo {

	public static void main(String[] args) {
		
		try {
			Reader bella = new Reader("Bella", 7);
			bella.start();
		} catch (WrongNameException | ReadingTimeException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Reader mike = new Reader("Mike",5);
			mike.start();
		} catch (WrongNameException | ReadingTimeException e) {
			System.out.println(e.getMessage());
		}

	}

}
