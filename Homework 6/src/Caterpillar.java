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

	// obstacles that the caterpillar is aware of
	private List<Collidable> obstacles;

	private static final int thickness = 10;

	private static final int[] directions = { NORTH, EAST, SOUTH, WEST };

	/**
	 * Constructs a caterpillar
	 * 
	 * @param window
	 *            the graphics where to draw the caterpillar.
	 */
	public Caterpillar(GWindow window, List<Collidable> obstacles) {
		// Initialize the graphics window for this Caterpillar
		this.window = window;

		// Create the caterpillar (10 points initially)
		// First point
		Point p = new Point();
		p.x = 5;
		p.y = WINDOW_HEIGHT / 2;

		this.obstacles = obstacles;

		// Other points
		for (int i = 0; i < 9; i++) {
			Point q = new Point(p);
			q.translate(STEP, 0);
			body.add(new Rectangle(p.x, p.y, Math.abs(p.x - q.x), thickness,
					Color.ORANGE, true));
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
		Shape newHead = null;
		HashSet<Integer> knownBadDirs = new HashSet<Integer>();
		knownBadDirs.add(getOppositeDirection());
		do {
			int x = oldHead.getX();
			int y = oldHead.getY();
			int w = oldHead.getWidth();
			int h = oldHead.getHeight();
			if (newHead != null) {
				knownBadDirs.add(newDir);
				newDir = selectNewRandomDir(knownBadDirs);
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
			newHead = new Rectangle(x, y, w, h, Color.ORANGE, true);
		} while (!isSafeDirection(newHead, newDir));
		dir = newDir;

		body.add(newHead);
		window.add(newHead);
	}

	public boolean isSafeDirection(Shape newHead, int newDir) {
		boolean offEdge = newHead.getX() < 0 || newHead.getY() < 0
				|| newHead.getY() > window.getWindowHeight()-STEP
				|| newHead.getX() > window.getWindowWidth()-STEP;
		boolean doubledBack = ((newDir == NORTH && dir == SOUTH)
				|| (newDir == SOUTH && dir == NORTH)
				|| (newDir == EAST && dir == WEST) || (newDir == WEST && dir == EAST));
		boolean hitObstacle = false;
		obstacleCheck: for (Collidable c : obstacles) {
			if (c.isCollision(newHead)) {
				hitObstacle = true;
				break obstacleCheck;
			}
		}
		return !doubledBack && !offEdge && !hitObstacle;
	}

	/**
	 * Is the caterpillar crawling over itself?
	 * 
	 * @return true if the caterpillar is crawling over itself and false
	 *         otherwise.
	 */
	public boolean isCrawlingOverItself() {

		return isCollision(getBufferedHead());
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

	@Override
	public boolean isCollision(Shape bufferedHead) {
		// Is the head point equal to any other point of the caterpillar?
		Shape head = getHead();

		for (Shape s : body) {
			if (s != head && bufferedHead.intersects(s)) {
				return true;
			}
		}
		return false; // CHANGE THIS!
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

	public int selectNewRandomDir(HashSet<Integer> knownBadDirs) {
		int index = (int) (Math.random() * directions.length);
		int newDir = (int) knownBadDirs.toArray()[0];
		while (knownBadDirs.contains(newDir)) {
			if (knownBadDirs.size() == directions.length) {
				throw new DeadlyCollisionException(
						"You ran into a corner, there's no escape!");
			}
			index = (int) (Math.random() * directions.length);
			newDir = directions[index];
		}
		return newDir;

	}

	private int getOppositeDirection() {
		int retVal = -1;
		if (dir == SOUTH) {
			retVal = NORTH;
		} else if (dir == NORTH) {
			retVal = SOUTH;
		} else if (dir == EAST) {
			retVal = WEST;
		} else if (dir == WEST) {
			retVal = EAST;
		}
		return retVal;
	}

	public void selectNewRandomDir() {
		HashSet<Integer> knownBadDir = new HashSet<Integer>();
		knownBadDir.add(dir);
		dir = selectNewRandomDir(knownBadDir);
	}

}
