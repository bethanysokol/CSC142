import java.awt.Color; // access the Color class 

import uwcse.graphics.*; // UW graphics library

/**
 * A sailboat in a graphics window
 */
public class SailBoat {

	// CLASS CONSTANTS

	/**
	 * constant for boat movement by number of units
	 */
	private static final int MOVE_UNITS = 3;

	/**
	 * constant for the boat moving up
	 */
	public static final int UP_MOVING = -1;

	/**
	 * constant for the boat moving down
	 */
	public static final int DOWN_MOVING = 1;

	/**
	 * constant for boat right moving direction
	 */
	public static final int RIGHT_MOVING = 1;

	/**
	 * constant for boat left moving direction
	 */
	public static final int LEFT_MOVING = -1;

	/**
	 * Default total height of the ship
	 */
	private static final int DEFAULT_HEIGHT = 70;

	/**
	 * Default total width of the ship
	 */
	private static final int DEFAULT_WIDTH = 70;

	/**
	 * Relative gap between the two sails
	 */
	private static final double SAIL_GAP_PCT = .05;

	/**
	 * Relative height of a single sail
	 */
	private static final double SAIL_HEIGHT_PCT = .75;

	/**
	 * Relative width of a single sail
	 */
	private static final double SAIL_WIDTH_PCT = .375;

	/**
	 * Space between hull and sail relative to the total height
	 */
	private static final double HULL_SAIL_GAP_PCT = SAIL_GAP_PCT / 2;

	/**
	 * Total hull height relative to the total height
	 */
	private static final double HULL_HEIGHT_PCT = 1 - SAIL_HEIGHT_PCT
			- HULL_SAIL_GAP_PCT;

	/**
	 * slowing down the default animation speed -higher numbers are slower
	 */
	public static final int ANIMATION_DELAY_RATE = 5;

	// OBJECT FIELDS

	/**
	 * The graphics window the boat belongs to
	 */
	private GWindow window;

	/**
	 * X component of the center of the boat
	 */
	private int x;

	/**
	 * Y component of the center of the boat
	 */
	private int y;

	/**
	 * Scale relative to the default dimensions
	 */
	private double scale;

	/**
	 * animationState used for controlling the bobbing animation
	 */
	private int animationState;

	/**
	 * The direction the sailboat is moving. More accurately, the direction of
	 * the bobbing motion. (vertical movement)
	 */
	private int verticalDirection;

	/**
	 * The direction the sailboat is moving. More accurately, velocity.
	 * (horizontal movement)
	 */
	private int horizontalDirection;

	/**
	 * sailboat's hull
	 */
	private Polygon hull;

	/**
	 * sailboat's secondary sail
	 */
	private Triangle jib;

	/**
	 * sailboat's main sail
	 */
	private Triangle mainsail;

	/**
	 * Draws a sailboat in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the boat
	 * @param y
	 *            the y coordinate of the location of the boat
	 * @param scale
	 *            the scale of the drawing of the boat
	 * @param verticalDirection
	 *            the movement of the boat vertically
	 * @param horizontalDirection
	 *            the movement of the boat horizontally
	 * @param oceanHeight
	 *            the height of the ocean
	 * @param window
	 *            the graphics window the boat belongs to
	 */
	public SailBoat(int x, int y, double scale, int verticalDirection,
			int horizontalDirection, int oceanHeight, GWindow window) {

		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		this.scale = scale;
		this.verticalDirection = verticalDirection;
		this.animationState = 0;
		this.horizontalDirection = horizontalDirection;

		// Draw the boat
		this.draw();
	}

	/**
	 * Draws this sailboat
	 */
	private void draw() {

		int height = (int) (DEFAULT_HEIGHT * scale);
		int width = (int) (DEFAULT_WIDTH * scale);

		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		// How to generate a random integer between >= 0 and < 256?
		// 0 <= (int)(256 * Math.random()) < 256

		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());

		// Modified the blue to show up better on the water
		int blue = (int) (128 * Math.random());
		Color color = new Color(red, green, blue);

		// rounding up, declare size and location of main sail
		int sailGap = (int) Math.ceil(SAIL_GAP_PCT * width);

		int sailHeight = (int) Math.ceil(SAIL_HEIGHT_PCT * height);
		int sailWidth = (int) Math.ceil(SAIL_WIDTH_PCT * width);
		int mainSailX1 = x - sailGap / 2;
		int mainSailX2 = mainSailX1 - sailWidth;
		int mainSailX3 = mainSailX1;
		int y1 = y;
		int y2 = y1;
		int y3 = y1 - sailHeight;

		// create mainsail
		mainsail = new Triangle(mainSailX1, y1, mainSailX2, y2, mainSailX3, y3,
				color, true);
		window.add(mainsail);

		red = (int) (256 * Math.random());
		green = (int) (256 * Math.random());
		// Modified the blue so the sail show up better on the water
		blue = (int) (128 * Math.random());
		color = new Color(red, green, blue);

		// create jib
		int jibX1 = x + sailGap / 2;
		int jibX2 = jibX1 + sailWidth;
		int jibX3 = jibX1;
		jib = new Triangle(jibX1, y1, jibX2, y2, jibX3, y3, color, true);
		window.add(jib);
		red = (int) (256 * Math.random());
		green = (int) (256 * Math.random());

		// Modified the blue so the hull show up better on the water
		blue = (int) (128 * Math.random());
		color = new Color(red, green, blue);

		// create hull
		hull = new Polygon(color, true);

		// rounding up to declare size and location of hull
		int hullWidth = width;
		int hullHeight = (int) Math.ceil(HULL_HEIGHT_PCT * height);
		int x1 = mainSailX2;
		int x2 = jibX2;
		int holdWidth = x2 - x1;
		int bowWidth = (hullWidth - holdWidth) / 2;
		int sternWidth = bowWidth; // symmetrical

		int x3 = x2 + bowWidth;
		int x4 = x1 - sternWidth;
		int y4 = y + (int) Math.ceil(HULL_SAIL_GAP_PCT * height);
		y3 = y4;
		y2 = y3 + hullHeight;
		y1 = y2;
		hull.addPoint(x4, y4);
		hull.addPoint(x3, y3);
		hull.addPoint(x2, y2);
		hull.addPoint(x1, y1);
		window.add(hull);
	}

	/**
	 * Move the boat back and forth across the water
	 */
	public void moveBackAndForth() {
		int moveXShift = SailBoat.MOVE_UNITS * horizontalDirection;
		this.x += moveXShift;
		/*
		 * Gets window height so that the sailboat doesn't sail too far out of
		 * the window. Multiply the direction the sailboat is going by -1 to
		 * make sure it will always reverse direction when it hits the boundary.
		 */
		if (this.x < 0 || this.x > window.getWindowWidth()) {
			this.horizontalDirection *= -1;
		}
		this.hull.moveBy(moveXShift, 0);
		this.jib.moveBy(moveXShift, 0);
		this.mainsail.moveBy(moveXShift, 0);
	}

	/**
	 * Make it look like it is bobbing up and down in the water
	 */
	public void moveUpOrDown() {
		/*
		 * increments the animationState so that when it becomes greater than or
		 * equal to the animation speed set, it will resize the hull to look
		 * like it is bobbing. Then it becomes false and resets to 0 to restart
		 * the process as long as the animation timer is running. Animation
		 * delay rate slows down the default animation so it isn't spasmodically
		 * fast.
		 */
		this.animationState++;
		if (this.animationState >= ANIMATION_DELAY_RATE) {
			this.animationState = 0;
			this.verticalDirection *= -1;
			
			//Check if direction matches the down moving direction sign
			if (this.verticalDirection * SailBoat.DOWN_MOVING > 0) {
				this.hull.resize(this.hull.getWidth(), this.hull.getHeight()
						- SailBoat.MOVE_UNITS);
			} else {

				this.hull.resize(this.hull.getWidth(), this.hull.getHeight()
						+ SailBoat.MOVE_UNITS);
			}
			int moveYShift = SailBoat.MOVE_UNITS * verticalDirection;
			this.y += moveYShift;
			this.hull.moveBy(0, moveYShift);
			this.jib.moveBy(0, moveYShift);
			this.mainsail.moveBy(0, moveYShift);
		}
	}
}