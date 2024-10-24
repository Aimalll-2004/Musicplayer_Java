package studiplayer.audio;


public class NotPlayableException extends Exception {

	private static final long serialVersionUID = 1L;
	String pathname= ""; 
	public NotPlayableException() {

	}

	public NotPlayableException(String pathname ,String msg) {
		super(msg);
		this.pathname = pathname;
	}

	public NotPlayableException(String pathname,Throwable t) {
		super(t);
		this.pathname = pathname;
	}

	public NotPlayableException(String pathname, String msg, Throwable t) {
		super(msg, t);
		this.pathname = pathname;
		
	}

	public String toString() {
		return pathname + super.toString();
	}
}
