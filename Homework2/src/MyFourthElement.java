import java.awt.Color; // access the Color class 
import java.util.ArrayList; // access lists for legs
import java.util.List;
//UW graphics library
import uwcse.graphics.Arc;
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Shape;

/**
 * This fourth element represents a squid and was required by scope
 */
public class MyFourthElement {

	// CLASS CONSTANTS

	/**
	 * Default height of the body of the squid.
	 */
	private static final int DEFAULT_HEIGHT = 70;

	/**
	 * Default width of the body of the squid.
	 */
	private static final int DEFAULT_WIDTH = 30;

	/**
	 * constant for squid movement by number of units
	 */
	private static final int MOVE_UNITS = 3;

	/**
	 * constant for the squid moving up
	 */
	public static final int UP_MOVING = -1;

	/**
	 * constant for the squid moving down
	 */
	public static final int DOWN_MOVING = 1;

	/**
	 * slowing down the default animation speed -higher numbers are slower
	 */
	public static final int ANIMATION_DELAY_RATE = 5;

	// OBJECT FIELDS

	/**
	 * The graphics window the squid belongs to
	 */
	private GWindow window;

	/**
	 * X component of the center of the squid body
	 */
	private int x;

	/**
	 * Y component of the center of the squid body
	 */
	private int y;

	/**
	 * Scale relative to the default dimensions
	 */
	private double scale;

	/**
	 * The direction the squid is moving. More accurately, velocity.
	 */
	private int direction;

	/**
	 * first eye of the squid
	 */
	private Oval eye2;

	/**
	 * second eye of the squid
	 */
	private Oval eye1;

	/**
	 * boolean value for the eye blinking animation. determines whether the eyes
	 * are open (visible) or closed (not visible).
	 */
	private boolean eyesOpen;

	/**
	 * squid's body
	 */
	private Oval body;

	/**
	 * list for the squids legs, using a list so that the objects can be
	 * consistently modified without having to change it in multiple areas. Thus
	 * reducing duplicate code and copy/paste errors.
	 */
	private List<Arc> legs;

	/**
	 * color of squid
	 */
	private Color color;

	/**
	 * oceanHeight will keep squid from swimming out of the water and into the
	 * sky
	 */
	private int oceanHeight;

	/**
	 * squid body width used for spacing the eyes based on the squid's body
	 * scaled value.
	 */
	private int width;

	/**
	 * used for controlling the blink animation on the squid
	 */
	private int animationState;

	/**
	 * Draws a squid in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the squid
	 * @param y
	 *            the y coordinate of the location of the squid
	 * @param scale
	 *            the scale of the drawing of the squid
	 * @param direction
	 *            the movement of the squid
	 * @param oceanHeight
	 *            the height of the ocean
	 * @param window
	 *            the graphics window the squid belongs to
	 */
	public MyFourthElement(int x, int y, double scale, int direction,
			int oceanHeight, GWindow window) {

		// Initialize the instance fields
		this.eyesOpen = false;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		this.direction = direction;
		this.oceanHeight = oceanHeight;
		this.animationState = 0;

		// Draw the squid
		this.draw();
	}

	/**
	 * Draws this squid of random color in the window.
	 */
	private void draw() {

		int height = (int) (DEFAULT_HEIGHT * scale);
		width = (int) (DEFAULT_WIDTH * scale);

		// Generates the color of the squid randomly
		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		// How to generate a random integer between >= 0 and < 256?
		// 0 <= (int)(256 * Math.random()) < 256
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());

		// Modified the blue so the squid show up better on the water
		int blue = (int) (128 * Math.random());
		if (color == null) {
			color = new Color(red, green, blue);
		}

		// Create the body
		body = new Oval(x - width / 2, y - height / 2, width, height, color,
				true);
		window.add(body);

		drawEyes(width);

		/*
		 * creating the legs If legs are not drawn, create legs, else if legs
		 * are drawn, clear the list. For each line in the list, draw legs.
		 */
		if (legs == null) {
			legs = new ArrayList<Arc>(3);
		} else {
			legs.clear();
		}
		legs.add(createLegPair(color, height, height));
		legs.add(createLegPair(color, height, height / 2));
		legs.add(createLegPair(color, height, height * 2));
		for (Arc l : legs) {
			this.window.add(l);
		}
	}

	/**
	 * Draws the eyes on the squid. Sometimes they will be closed.
	 * 
	 * @param width
	 *            allows scaling of eyes relative to squids body
	 */
	private void drawEyes(int width) {
		/*
		 * making eyes vanish is easier than having to draw new shapes (eye
		 * lids) and undraw them.
		 */
		if (eye1 != null) {
			this.eye1.removeFromWindow();
			this.eye1 = null;
		}
		if (eye2 != null) {
			this.eye2.removeFromWindow();
			this.eye2 = null;
		}

		/*
		 * Drawing the squid's eyes for animation. If the eyes are "open", or
		 * visible, draw the eyes.
		 */
		if (eyesOpen) {
			// Declaring the size and spacing of the squid's eyes
			int eyeGap = (width / 4) / 3;
			int eyeSize = width / 4;
			// creating the eyes
			eye1 = new Oval(x + eyeGap, y, eyeSize, eyeSize, Color.WHITE, true);
			window.add(eye1);
			eye2 = new Oval(x - eyeGap - eyeSize, y, eyeSize, eyeSize,
					Color.WHITE, true);
			window.add(eye2);
		}
	}

	/**
	 * Draws a pair of legs anchored near the center of the body
	 * 
	 * @param color
	 *            body color to match leg color
	 * @param bodyHeight
	 *            used to position legs relative to the body
	 * @param tentacleLength
	 *            length of the tentacles
	 * @return tentacle arc representing the pair of legs
	 */
	private Arc createLegPair(Color color, int bodyHeight, int tentacleLength) {

		Arc tentacle = new Arc(x - tentacleLength / 4, y
				+ (int) ((2.0 / 5) * bodyHeight), tentacleLength / 2,
				tentacleLength * 2, 0, 180, color, false);
		return tentacle;
	}

	/**
	 * Animate the squid
	 */
	public void doAction() {
		/*
		 * increments the animationState so that when it becomes greater than or
		 * equal to the animation speed set, it will blink.
		 */
		this.animationState++;
		/*
		 * get window height so that the squid doesn't swim into the sky or too
		 * far out of the window. Multiply the direction the squid is going by
		 * -1 to make sure it will always reverse direction when it hits the
		 * boundary.
		 */
		if (this.body.getY() < oceanHeight || this.y > window.getWindowHeight()) {
			this.direction *= -1;
		}

		if (this.animationState >= ANIMATION_DELAY_RATE) {
			/*
			 * Then it becomes false and resets to 0 to restart the process as
			 * long as the animation timer is running.
			 */
			this.eyesOpen = !eyesOpen;
			this.animationState = 0;
		}
		// move the squid body, eyes and legs up and down on the Y axis
		int moveYShift = MyFourthElement.MOVE_UNITS * direction;
		this.y += moveYShift;
		this.body.moveBy(0, moveYShift);
		this.drawEyes(width);
		for (Shape l : legs) {
			l.moveBy(0, moveYShift);
		}
	}
}
