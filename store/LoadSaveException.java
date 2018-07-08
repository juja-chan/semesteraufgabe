package store;

public class LoadSaveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadSaveException(String message, Exception cause){
		super(message, cause);
	}
	
	public LoadSaveException(String message){
		super(message);
	}
}
