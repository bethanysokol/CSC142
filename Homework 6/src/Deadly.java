/**
 * An abstract class that contains error handling messages upon collision with
 * objects.
 *
 */
public abstract class Deadly implements Collidable {

	/**
	 * Throws a message if a game ending collision were to occur (ie, eating a
	 * bad cabbage)
	 */
	protected String collideMessage;

	@Override
	public void doCollideAction(Caterpillar cat) {
		// deadly collision occurred
		throw new DeadlyCollisionException(collideMessage);
	}

}
