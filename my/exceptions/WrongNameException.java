package my.exceptions;

public class WrongNameException extends Exception{

	@Override
	public String getMessage() {
		return "Wrong reader name!";
	}
}
