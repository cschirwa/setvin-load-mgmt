package za.co.setvin.exception;

public class TrailerNotFoundException extends RuntimeException {

	public TrailerNotFoundException() {
		super();
	}
	
	public TrailerNotFoundException(String msg) {
		super(msg);
	}
}
