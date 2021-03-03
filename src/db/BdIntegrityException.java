package db;

public class BdIntegrityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BdIntegrityException(String msg) {
		super(msg);
	}
}
