package za.co.setvin.exception;

public class CurrencyNotFoundException extends RuntimeException {
	
	public CurrencyNotFoundException() {
		super();
	}
	
	public CurrencyNotFoundException(String msg) {
		super(msg);
	}
	

}
