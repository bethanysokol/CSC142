import uwcse.graphics.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * An abstract class that contains all of the basic features of a cabbage. It
 * should be derived by any class that represents a cabbage. (complete, but you
 * can add other methods if you want)
 */

public abstract class Cabbage implements Collidable, CaterpillarGameConstants {
	// The graphics window this Cabbage belongs to
	protected GWindow window;

	// The location of the center of this Cabbage in the graphics window
	protected Point center;

	protected Oval head;
	/**
	 * Creates a cabbage in the graphics window
	 * 
	 * @param window
	 *            the GWindow this Cabbage belongs to
	 * @param center
	 *            the center Point of this Cabbage
	 */
	public Cabbage(GWindow window, Point center) {
		this.window = window;
		this.center = new Point(center);
	}

	/**
	 * Displays this Cabbage in the graphics window
	 */
	protected void draw() {
		window.add(head);
	}

	/**
	 * This cabbage is eaten by a caterpillar
	 * 
	 * @param cp
	 *            the caterpillar that is eating this cabbage
	 */
	public abstract void isEatenBy(Caterpillar cp);

	/**
	 * Is this Point in this Cabbage?
	 * 
	 * @param p
	 *            the Point to check
	 * @return true if p in within the cabbage and false otherwise.
	 */
	public boolean isPointInCabbage(Point p) {
		return (p.distance(center) <= CABBAGE_RADIUS);
	}

	/**
	 * Returns the location of this Cabbage
	 * 
	 * @return the location of this Cabbage.
	 */
	public Point getLocation() {
		return new Point(center);
	}

	@Override
	public boolean isCollision(Caterpillar cat) {
		return cat.getHead().intersects(head);

	}

	@Override
	public void doCollideAction(Caterpillar cat) {
		isEatenBy(cat);
	}
	
	public static Cabbage spawnGoodCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Point p = fence.randomPoint(CABBAGE_RADIUS);
		Cabbage candidate = new GoodCabbage(window, p);
		if (!candidate.isValidPlacement(cabbages)) {
			candidate = spawnGoodCabbage(window, fence, cabbages);
		}
		candidate.draw();
		return candidate;
	}

	private boolean isValidPlacement(ArrayList<Cabbage> cabbages) {
		for(Cabbage other:cabbages){
			if(this.head.intersects(other.head)){
				return false;
			}
		}
		return true;
	}

	public static Cabbage spawnBadCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Point p = fence.randomPoint(CABBAGE_RADIUS);
		Cabbage candidate = new BadCabbage(window, p);
		if (!candidate.isValidPlacement(cabbages)) {
			candidate = spawnBadCabbage(window, fence, cabbages);
		}
		candidate.draw();
		return candidate;
	}

	public void makeHead(Color color) {
		head = new Oval(this.center.x - CABBAGE_RADIUS / 2, this.center.y
				- CABBAGE_RADIUS / 2, CABBAGE_RADIUS, CABBAGE_RADIUS,
				color, true);
		
	}

	public static Cabbage spawnPsychedelicCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Point p = fence.randomPoint(CABBAGE_RADIUS);
		Cabbage candidate = new PsychedelicCabbage(window, p);
		if (!candidate.isValidPlacement(cabbages)) {
			candidate = spawnPsychedelicCabbage(window, fence, cabbages);
		}
		candidate.draw();
		return candidate;
	}

}
