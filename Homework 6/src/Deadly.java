/**
 * An abstract class that contains error handling messages upon collision with
 * objects.
 *
 */
public abstract class Deadly implements Collidable {

	/**
	 * message to present if a game ending collision were to occur (ie, wrapping
	 * in on yourself)
	 */
	protected String collideMessage;

	/* (non-Javadoc)
	 * @see Collidable#doCollideAction(Caterpillar)
	 */
	@Override
	public void doCollideAction(Caterpillar cat) {
		// deadly collision occurred
		throw new DeadlyCollisionException(collideMessage);
	}

}
