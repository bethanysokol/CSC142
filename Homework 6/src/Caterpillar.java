import uwcse.graphics.*;

import java.awt.Point;
import java.util.*;
import java.awt.Color;

/**
 * A Caterpillar is the representation and the display of a caterpillar
 */

public class Caterpillar extends Deadly implements CaterpillarGameConstants {

	// Store the graphical elements of the caterpillar body
	// Useful to erase the body of the caterpillar on the screen
	private ArrayList<Shape> body = new ArrayList<Shape>();

	// The window the caterpillar belongs to
	private GWindow window;

	// Direction of motion of the caterpillar (NORTH initially)
	private int dir = NORTH;

	private static final int thickness = 10;

	private static final int[] directions = { NORTH, EAST, SOUTH, WEST };

	/**
	 * Constructs a caterpillar
	 * 
	 * @param window
	 *            the graphics where to draw the caterpillar.
	 */
	public Caterpillar(GWindow window) {
		// Initialize the graphics window for this Caterpillar
		this.window = window;

		// Create the caterpillar (10 points initially)
		// First point
		Point p = new Point();
		p.x = 5;
		p.y = WINDOW_HEIGHT / 2;

		// Other points
		for (int i = 0; i < 9; i++) {
			Point q = new Point(p);
			q.translate(STEP, 0);
			body.add(new Rectangle(p.x, p.y, Math.abs(p.x - q.x), thickness,
					Color.RED, true));
			p = q;
		}

		// Other initializations (if you have more instance fields)

		this.collideMessage = "Don't crawl over yourself!";
		// Display the caterpillar (call a private method)
		update();
	}

	private void update() {
		for (Shape s : body) {
			window.remove(s);
		}
		for (Shape s : body) {
			window.add(s);
		}
	}

	/**
	 * Moves the caterpillar in the current direction (complete)
	 */
	public void move() {
		move(dir);
	}

	/**
	 * Move the caterpillar in the direction newDir. <br>
	 * If the new direction is illegal, select randomly a legal direction of
	 * motion and move in that direction.<br>
	 * 
	 * @param newDir
	 *            the new direction.
	 */
	public void move(int newDir) {

		growNewHead(newDir);

		Shape tail = body.remove(0);
		window.remove(tail);
	}

	public void growNewHead(int newDir) {
		Shape oldHead = getHead();
		int x = oldHead.getX();
		int y = oldHead.getY();
		int w = oldHead.getWidth();
		int h = oldHead.getHeight();

		if ((newDir == NORTH && dir == SOUTH)
				|| (newDir == SOUTH && dir == NORTH)
				|| (newDir == EAST && dir == WEST)
				|| (newDir == WEST && dir == EAST)) {
			newDir = selectNewRandomDir(newDir);
		}

		if (newDir == NORTH) {

			if (dir == EAST) {
				x += oldHead.getWidth() - thickness;
			}
			y -= STEP;
			w = thickness;
			h = STEP;
		} else if (newDir == EAST) {
			if (dir == SOUTH) {
				y += oldHead.getHeight() - thickness;
			}
			x += oldHead.getWidth();
			w = STEP;
			h = thickness;
		} else if (newDir == SOUTH) {
			if (dir == EAST) {

				x += oldHead.getWidth() - thickness;
			}
			y += oldHead.getHeight();
			w = thickness;
			h = STEP;
		} else if (newDir == WEST) {
			if (dir == SOUTH) {
				y += oldHead.getHeight() - thickness;
			}
			x -= STEP;
			w = STEP;
			h = thickness;

		}
		if (x < 0 || y < 0 || y > window.getWindowHeight()
				|| x > window.getWindowWidth()) {
			growNewHead(selectNewRandomDir(newDir));
		} else {

			dir = newDir;
			Rectangle newHead = new Rectangle(x, y, w, h, Color.RED, true);

			body.add(newHead);
			window.add(newHead);
		}
	}

	/**
	 * Is the caterpillar crawling over itself?
	 * 
	 * @return true if the caterpillar is crawling over itself and false
	 *         otherwise.
	 */
	public boolean isCrawlingOverItself() {
		// Is the head point equal to any other point of the caterpillar?
		Shape head = getHead();
		Shape bufferedHead = getBufferedHead();

		for (Shape s : body) {
			if (s != head && bufferedHead.intersects(s)) {
				return true;
			}
		}
		return false; // CHANGE THIS!
	}

	/**
	 * Are all of the points of the caterpillar outside the garden
	 * 
	 * @return true if the caterpillar is outside the garden and false
	 *         otherwise.
	 */
	public boolean isOutsideGarden(Fence fence) {
		Rectangle garden = fence.getGardenBox();
		for (Shape s : body) {
			if (s.intersects(garden)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return the location of the head of the caterpillar (complete)
	 * 
	 * @return the location of the head of the caterpillar.
	 */
	public Shape getHead() {

		return body.get(body.size() - 1);
	}

	/**
	 * Return the location of the head of the caterpillar (complete)
	 * 
	 * @return the location of the head of the caterpillar.
	 */
	public Shape getBufferedHead() {
		Shape head = getHead();
		Shape bufferedHead = new Rectangle(head.getX() + 1, head.getY() + 1,
				head.getWidth() - 2, head.getHeight() - 2);
		return bufferedHead;
	}

	/**
	 * Increase the length of the caterpillar (by GROWTH_SPURT elements) Add the
	 * elements at the tail of the caterpillar.
	 */
	public void grow() {
		growNewHead(dir);
	}

	@Override
	public boolean isCollision(Caterpillar cat) {
		return isCrawlingOverItself();
	}

	public void psych() {
		for (Shape s : body) {
			s.setColor(generateRandomColor());
		}
	}

	/**
	 * Generates a random color to use in the pie, stripes and snow flake.
	 * 
	 * @return the random color generated
	 */
	public Color generateRandomColor() {
		// Generates the color randomly
		int red = (int) (256 * Math.random());
		int green = (int) (128 * Math.random());// ************* don't forget
		int blue = (int) (256 * Math.random());
		return new Color(red, green, blue);
	}

	public int selectNewRandomDir(int invalidDir) {
		int newDir = directions[(int) (Math.random() * (directions.length - 1))];
		if (newDir != invalidDir && !isOppositeDirection(newDir)) {
			return newDir;
		} else {
			return selectNewRandomDir(invalidDir);
		}
	}

	private boolean isOppositeDirection(int newDir) {
		return ((newDir == NORTH && dir == SOUTH)
				|| (newDir == SOUTH && dir == NORTH)
				|| (newDir == EAST && dir == WEST) || (newDir == WEST && dir == EAST));
	}

	public void selectNewRandomDir() {
		move(selectNewRandomDir(dir));
	}
}
