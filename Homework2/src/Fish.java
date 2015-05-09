import java.awt.Color; // access the Color class  

import uwcse.graphics.*; // UW graphics library

/**
 * a fish in a graphics window
 */
public class Fish {

	// CLASS CONSTANTS

	/**
	 * constant for fish left moving direction
	 */
	public static final int LEFT_MOVING = -1;

	/**
	 * constant for fish right moving direction
	 */
	public static final int RIGHT_MOVING = 1;

	/**
	 * constant for fish movement by number of units
	 */
	private static final int MOVE_UNITS = 3;

	// OBJECT FIELDS
	/**
	 * The graphics window the fish belongs to
	 */
	private GWindow window;

	/**
	 * X component of the center of the fish
	 */
	private int x;

	/**
	 * Y component of the center of the fish
	 */
	private int y;

	/**
	 * Scale relative to the default dimensions
	 */
	private double scale;

	/**
	 * The direction the fish is moving. More accurately, velocity.
	 */
	private int direction;

	/**
	 * fish body
	 */
	private Oval body;

	/**
	 * fish tail
	 */
	private Triangle tail;

	/**
	 * fish eye
	 */
	private Oval eye;

	/**
	 * color of fish
	 */
	private Color color;

	/**
	 * Draws a Fish in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the fish
	 * @param y
	 *            the y coordinate of the location of the fish
	 * @param scale
	 *            the scale of the drawing of the fish
	 * @param direction
	 *            the movement of the fish
	 * @param window
	 *            the graphics window the fish belongs to
	 */
	public Fish(int x, int y, double scale, int direction, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		this.direction = direction;

		// Draw the fish
		this.draw();
	}

	/**
	 * Draws this fish
	 */
	private void draw() {

		int height = (int) (30 * scale);
		int width = (int) (70 * scale);

		// Generates the color of the fish randomly
		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		// How to generate a random integer between >= 0 and < 256?
		// 0 <= (int)(256 * Math.random()) < 256
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());

		// Modified the blue so the fish show up better on the water
		int blue = (int) (128 * Math.random());
		if (color == null) {
			color = new Color(red, green, blue);
		}

		// Create the body and tail
		body = new Oval(x - width / 2, y - height / 2, width, height, color,
				true);
		window.add(body);

		// Declaring the size of the fish's eye
		int eyeSize = width / 8;

		// select the direction of the fish (and its eye to match)

		// Check if direction matches the left moving direction sign
		if (this.direction * Fish.LEFT_MOVING > 0) { // move left
			tail = new Triangle(x + width / 2, y, x + width / 2 + width / 3, y
					- height / 2, x + width / 2 + width / 3, y + height / 2,
					color, true);
			eye = new Oval(x - width / 4, y - (height / 4), eyeSize, eyeSize,
					Color.WHITE, true);
		} else { // move right
			tail = new Triangle(x - width / 2, y, x - width / 2 - width / 3, y
					- height / 2, x - width / 2 - width / 3, y + height / 2,
					color, true);
			eye = new Oval(x + width / 4, y - (height / 4), eyeSize, eyeSize,
					Color.WHITE, true);
		}
		window.add(eye);
		window.add(tail);

	}

	/**
	 * Getting the window width to tell the fish to turn around if it were to
	 * swim too far left or right out of the window.
	 */
	public void swim() {

		/*
		 * moveXShift moves the fish on the x axis by multiplying the move units
		 * by the direction
		 */
		int moveXShift = Fish.MOVE_UNITS * direction;
		this.x += moveXShift;
		/*
		 * By multiplying by negative 1, it will always change to the opposite
		 * direction the fish was going.
		 */
		if (this.x < 0 || this.x > window.getWindowWidth()) {
			this.direction *= -1;
			/*
			 * Remove and redraw fish when they get to the edge of the screen so
			 * that the tail will be facing the correct direction.
			 */
			this.eye.removeFromWindow();
			this.tail.removeFromWindow();
			this.body.removeFromWindow();
			this.draw();
		}

		this.tail.moveBy(moveXShift, 0);
		this.body.moveBy(moveXShift, 0);
		this.eye.moveBy(moveXShift, 0);

	}

}
