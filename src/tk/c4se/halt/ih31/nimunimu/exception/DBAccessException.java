/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.exception;

/**
 * Database access exception.
 * 
 * @author ne_Sachirou
 */
public class DBAccessException extends Exception {
	private static final long serialVersionUID = 1L;

	public DBAccessException() {
	}

	public DBAccessException(String message) {
		super(message);
	}
}