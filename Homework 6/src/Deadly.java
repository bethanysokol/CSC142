
public abstract class Deadly implements Collidable{

	protected String collideMessage;

	@Override
	public void doCollideAction(Caterpillar cat) {
		// deadly collision occured
		throw new DeadlyCollisionException(collideMessage);
	}
	

}
