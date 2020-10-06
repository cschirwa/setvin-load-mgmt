package za.co.setvin.exception;

public class TruckNotFoundException extends Exception {

	public TruckNotFoundException() {
		super();
	}
	
	public TruckNotFoundException(String msg) {
		super(msg);
	}
}
