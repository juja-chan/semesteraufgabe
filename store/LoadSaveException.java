package store;

public class LoadSaveException extends Exception{
	Exception cause;
	public LoadSaveException(String message, Exception cause){
		super(message);
		this.cause = cause;
	}
	
	public Exception getCause(){
		return cause;
	}
}
