import uwcse.graphics.Shape;

/**
 * Interface that lists the collision actions for the game.
 *
 */
public interface Collidable {

	/**
	 * The action to perform upon a collision with an object
	 * 
	 * @param cat
	 *            the caterpillar that moves on the screen
	 */
	public void doCollideAction(Caterpillar cat);

	/**
	 * Checks whether or not a collision has occurred with the caterpillar.
	 * 
	 * @param cat
	 *            the caterpillar that moves on the screen
	 * @returns whether there has been a collision or not
	 */
	public boolean isCollision(Caterpillar cat);

	/**
	 * Checks whether or not a collision has occurred with the caterpillar's
	 * head.
	 * 
	 * @param head
	 *            the head of the caterpillar
	 * @returns whether there has been a collision or not
	 */
	public boolean isCollision(Shape head);
}
