package my.exceptions;

public class ReadingTimeException extends Exception{

	@Override
	public String getMessage() {
		return "Reading time exception!";
	}
}
