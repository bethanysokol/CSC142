import uwcse.graphics.Shape;


public interface Collidable {

public void doCollideAction(Caterpillar cat);

public  boolean isCollision(Caterpillar cat);

public boolean isCollision(Shape head);
}
