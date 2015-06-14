import uwcse.graphics.*;

import java.awt.Point;
import java.util.*;
import java.awt.Color;

/**
 * The representation and the display of a caterpillar
 */

public class Caterpillar extends Deadly implements CaterpillarGameConstants {

	/**
	 * Store the graphical elements of the caterpillar body
	 */
	private ArrayList<Shape> body = new ArrayList<Shape>();

	/**
	 * The window the caterpillar belongs to
	 */
	private GWindow window;

	/**
	 * Direction of motion of the caterpillar (NORTH initially)
	 */
	private int dir = NORTH;

	/**
	 * obstacles that the caterpillar is aware of
	 */
	private List<Collidable> obstacles;

	/**
	 * The thickness of the caterpillar
	 */
	private static final int thickness = 10;

	/**
	 * List of directions used in determining a bad direction
	 */
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
		// message to display if caterpillar crawls over itself
		this.collideMessage = "Don't crawl over yourself!";
		// Display the caterpillar
		update();
	}

	/**
	 * Displays the caterpillar
	 */
	private void update() {
		// remove caterpillar from window
		for (Shape s : body) {
			window.remove(s);
		}
		// add caterpillar to window
		for (Shape s : body) {
			window.add(s);
		}
	}

	/**
	 * Moves the caterpillar in the current direction
	 */
	public void move() {
		move(dir);
	}

	/**
	 * Move the caterpillar in the direction newDir. If the new direction is
	 * illegal, select randomly a legal direction of motion and move in that
	 * direction.
	 * 
	 * @param newDir
	 *            the new direction.
	 */
	public void move(int newDir) {

		growNewHead(newDir);

		Shape tail = body.remove(0);
		window.remove(tail);
	}

	/**
	 * Add the new body segment to the caterpillar
	 * 
	 * @param newDir
	 *            the new direction
	 */
	public void growNewHead(int newDir) {
		Shape oldHead = getHead();
		Shape newHead = null;
		/*
		 * if the caterpillar is moved in an illegal direction, automatically
		 * moves it in the opposite direction
		 */
		HashSet<Integer> knownBadDirs = new HashSet<Integer>();
		knownBadDirs.add(getOppositeDirection());
		do {
			// x, y coordinates of the body as well as the width and height
			int x = oldHead.getX();
			int y = oldHead.getY();
			int w = oldHead.getWidth();
			int h = oldHead.getHeight();
			if (newHead != null) {
				knownBadDirs.add(newDir);
				newDir = selectNewRandomDir(knownBadDirs);
			}
			// caterpillar movement for each direction
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
			// adds a new segment to the caterpillar
			newHead = new Rectangle(x, y, w, h, Color.ORANGE, true);
		} while (!isSafeDirection(newHead, newDir));
		dir = newDir;

		body.add(newHead);
		window.add(newHead);
	}

	/**
	 * Determines if there is an obstacle free path
	 * 
	 * @param newHead
	 *            the new head of the caterpillar
	 * @param newDir
	 *            the new direction of movement
	 * @return if it is a safe direction to move in
	 */
	public boolean isSafeDirection(Shape newHead, int newDir) {
		/*
		 * if the x or y coordinate is less than 0, the caterpillar is out of
		 * the window.
		 */
		boolean offEdge = newHead.getX() < 0 || newHead.getY() < 0
				|| newHead.getY() > window.getWindowHeight() - STEP
				|| newHead.getX() > window.getWindowWidth() - STEP;
		/*
		 * check if the caterpillar tries to double back on itself
		 */
		boolean doubledBack = ((newDir == NORTH && dir == SOUTH)
				|| (newDir == SOUTH && dir == NORTH)
				|| (newDir == EAST && dir == WEST) || (newDir == WEST && dir == EAST));
		// check to see if the caterpillar has hit an obstacle
		boolean hitObstacle = false;
		obstacleCheck: for (Collidable c : obstacles) {
			if (c.isCollision(newHead)) {
				hitObstacle = true;
				break obstacleCheck;
			}
		}
		/*
		 * if the caterpillar is in the window, not hitting an obstacle and not
		 * doubling back on itself, then it can move safely
		 */
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
	 * Are all of the points of the caterpillar outside the garden?
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
	 * Return the location of the head of the caterpillar.
	 * 
	 * @return the location of the head of the caterpillar.
	 */
	public Shape getHead() {

		return body.get(body.size() - 1);
	}

	/**
	 * Return the location of the head of the caterpillar
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
	 * Increase the length of the caterpillar. Add the elements at the tail of
	 * the caterpillar.
	 */
	public void grow() {
		growNewHead(dir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(Caterpillar)
	 */
	@Override
	public boolean isCollision(Caterpillar cat) {
		return isCrawlingOverItself();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(uwcse.graphics.Shape)
	 */
	@Override
	public boolean isCollision(Shape bufferedHead) {
		// Is the head point equal to any other point of the caterpillar?
		Shape head = getHead();

		for (Shape s : body) {
			if (s != head && bufferedHead.intersects(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * If the caterpillar has eaten a psychadellic cabbage, randomly change the
	 * colors of the caterpillar
	 */
	public void psych() {
		for (Shape s : body) {
			s.setColor(generateRandomColor());
		}
	}

	/**
	 * Generates a random color to use on the caterpillar.
	 * 
	 * @return the random color generated
	 */
	public Color generateRandomColor() {
		// Generates the color randomly
		int red = (int) (256 * Math.random());
		// cut the green by half to make it stand out against the grass better
		int green = (int) (128 * Math.random());
		int blue = (int) (256 * Math.random());
		return new Color(red, green, blue);
	}

	/**
	 * Selects a new direction for the caterpillar to move in if it tries to
	 * move to a bad location.
	 * 
	 * @param knownBadDirs
	 *            if a direction is known to be illegal
	 * @return the new direction to move in
	 */
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

	/**
	 * 
	 * @return
	 */
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

	/**
	 * Selects a new direction if the current direction is illegal
	 */
	public void selectNewRandomDir() {
		HashSet<Integer> knownBadDir = new HashSet<Integer>();
		knownBadDir.add(dir);
		dir = selectNewRandomDir(knownBadDir);
	}

}
