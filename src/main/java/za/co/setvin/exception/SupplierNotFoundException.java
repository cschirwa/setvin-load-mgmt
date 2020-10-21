package za.co.setvin.exception;

public class SupplierNotFoundException extends RuntimeException {
	
	public SupplierNotFoundException() {
		super();
	}

	
	public SupplierNotFoundException(String msg) {
		super(msg);
	}
}
