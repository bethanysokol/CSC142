/**
 * A class that creates an exception message for collisions. 
 *
 */
public class DeadlyCollisionException extends RuntimeException{

	public DeadlyCollisionException(String msg) {
		super(msg);
	}

	/**
	 * ID tag for serialized version
	 */
	private static final long serialVersionUID = -1525856855195862597L;

}
