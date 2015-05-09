import java.awt.Color; // access the Color class  
import java.util.ArrayList; // access lists for star animation
import java.util.List;

import uwcse.graphics.*; // UW graphics library

/**
 * A star in a graphics window
 */
public class Star {

	// OBJECT FIELDS

	/**
	 * The graphics window the star belongs to
	 */
	private GWindow window;

	/**
	 * X component of the center of the star
	 */
	private int x;

	/**
	 * Y component of the center of the star
	 */
	private int y;

	/**
	 * Scale relative to the default dimensions
	 */
	private double scale;

	/**
	 * list used for animating star, using a list so that the objects can be
	 * consistently modified without having to change it in multiple areas. Thus
	 * reducing duplicate code and copy/paste errors.
	 */
	private List<Line> lines;

	/**
	 * color of the star
	 */
	private Color color;

	/**
	 * Draws a star in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the center of the star
	 * @param y
	 *            the y coordinate of the center of the star
	 * @param scale
	 *            the scale of the drawing of the star
	 * @param window
	 *            the graphics window the star belongs to
	 */
	public Star(int x, int y, double scale, GWindow window) {

		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		this.scale = scale;

		// Draw the star
		this.draw();
	}

	/**
	 * Draws this star
	 */
	private void draw() {

		// starting color of the star
		color = Color.WHITE;

		// Size of the star
		int starSize = (int) (this.scale * 10);

		// Draw the lines making up the star
		// If list is null, make it
		if (lines == null) {
			lines = new ArrayList<Line>(4);
		}
		lines.add(new Line(this.x, this.y - starSize / 2, this.x, this.y
				+ starSize / 2, color));
		lines.add(new Line(this.x - starSize / 2, this.y,
				this.x + starSize / 2, this.y, color));
		lines.add(new Line(this.x - starSize / 2, this.y - starSize / 2, this.x
				+ starSize / 2, this.y + starSize / 2, color));
		lines.add(new Line(this.x + starSize / 2, this.y - starSize / 2, this.x
				- starSize / 2, this.y + starSize / 2, color));
		// Add each line to the window
		for (Line l : lines) {
			this.window.add(l);
		}
	}

	/**
	 * twinkle animation method For each line, set the color to be the same but
	 * randomized
	 */
	public void twinkle() {
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		color = new Color(red, green, blue);
		for (Line l : lines) {
			l.setColor(color);
		}

	}
}