package store;

public class LoadSaveException extends Exception {
	public LoadSaveException(String message, Exception cause){
		super(message, cause);
	}
	
	public LoadSaveException(String message){
		super(message);
	}
}
