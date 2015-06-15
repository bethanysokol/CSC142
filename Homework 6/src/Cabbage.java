import uwcse.graphics.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 * An abstract class that contains all of the basic features of a cabbage.
 */
public abstract class Cabbage implements Collidable, CaterpillarGameConstants {
	
	/**
	 * The graphics window this Cabbage belongs to
	 */
	protected GWindow window;

	/**
	 * The location of the center of this Cabbage in the graphics window
	 */
	protected Point center;

	/**
	 * The head of the cabbage
	 */
	protected Oval head;

	/**
	 * Creates a cabbage that can latrer be drawn in the graphics window
	 * 
	 * @param window
	 *            the window this Cabbage belongs to
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
	 * Checks if this Point in this Cabbage
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(Caterpillar)
	 */
	@Override
	public boolean isCollision(Caterpillar cat) {
		return isCollision(cat.getHead());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(uwcse.graphics.Shape)
	 */
	@Override
	public boolean isCollision(Shape head) {
		return head.intersects(this.head);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#doCollideAction(Caterpillar)
	 */
	@Override
	public void doCollideAction(Caterpillar cat) {
		isEatenBy(cat);
	}

	/**
	 * Checks to make sure the cabbages don't overlap
	 * 
	 * @param cabbages
	 *            the cabbages in the array
	 * @returns whether or not a space is occupied by another cabbage
	 */
	private boolean isValidPlacement(ArrayList<Cabbage> cabbages) {
		for (Cabbage other : cabbages) {
			if (this.head.intersects(other.head)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Spawns a good cabbage within the fenced area without overlapping any of the existing cabbages
	 * 
	 * @param window
	 *            the window the cabbages are drawn in
	 * @param fence
	 *            the area within the fence in which cabbages can be spawned
	 * @param cabbages
	 *            the cabbages to be drawn
	 * @returns if the location is valid, spawn a cabbage
	 */
	public static Cabbage spawnGoodCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Cabbage candidate = null;
		do {
			Point p = fence.randomPoint(CABBAGE_RADIUS);
			candidate = new GoodCabbage(window, p);
		} while (!candidate.isValidPlacement(cabbages));
		candidate.draw();
		return candidate;
	}

	/**
	 * Spawns a deadly cabbage within the fenced area without overlapping any of the existing cabbages
	 * 
	 * @param window
	 *            the window the cabbages are drawn in
	 * @param fence
	 *            the area within the fence in which cabbages can be spawned
	 * @param cabbages
	 *            the cabbages to be drawn
	 * @returns if the location is valid, spawn a cabbage
	 */
	public static Cabbage spawnBadCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Cabbage candidate = null;
		do {
			Point p = fence.randomPoint(CABBAGE_RADIUS);
			candidate = new BadCabbage(window, p);
		} while (!candidate.isValidPlacement(cabbages));
		candidate.draw();
		return candidate;
	}

	/**
	 * Spawns a psychadelic cabbage within the fenced area without overlapping any of the existing cabbages
	 * 
	 * @param window
	 *            the window the cabbages are drawn in
	 * @param fence
	 *            the area within the fence in which cabbages can be spawned
	 * @param cabbages
	 *            the cabbages to be drawn
	 * @returns if the location is valid, spawn a cabbage
	 */
	public static Cabbage spawnPsychedelicCabbage(GWindow window, Fence fence,
			ArrayList<Cabbage> cabbages) {
		Cabbage candidate = null;
		do {
			Point p = fence.randomPoint(CABBAGE_RADIUS);
			candidate = new PsychedelicCabbage(window, p);
		} while (!candidate.isValidPlacement(cabbages));
		candidate.draw();
		return candidate;
	}

	/**
	 * Creates a new cabbage head
	 * 
	 * @param color
	 *            the color of the cabbage heads
	 */
	public void makeHead(Color color) {
		head = new Oval(this.center.x - CABBAGE_RADIUS / 2, this.center.y
				- CABBAGE_RADIUS / 2, CABBAGE_RADIUS, CABBAGE_RADIUS, color,
				true);

	}

}
