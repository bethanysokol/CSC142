import uwcse.graphics.*;

import java.util.*;
import java.awt.Color;
import java.awt.Point;

/**
 * The fence surrounding the playable game area
 *
 */
public class Fence implements Collidable {

	/**
	 * An array of the individual sections of the fence
	 */
	private ArrayList<Shape> fenceRails;

	/**
	 * The window the fence is drawn in
	 */
	private GWindow window;

	/**
	 * The width of the grass outside of the fenced area
	 */
	private int lawnWidth;

	/**
	 * The size of the opening in the fence
	 */
	private int fenceOpening;

	/**
	 * The left side of the garden
	 */
	private int gardenL;

	/**
	 * The top side of the garden
	 */
	private int gardenT;

	/**
	 * The right side of the garden
	 */
	private int gardenR;

	/**
	 * The bottom side of the garden
	 */
	private int gardenB;

	/**
	 * The thickness of a fence section
	 */
	private int thickness;

	/**
	 * Make a fence.
	 * 
	 * @param window
	 *            the window the fence is drawn in
	 * @param fenceOpening
	 *            the opening of the fence
	 * @param lawnWidth
	 *            the width of the grassy area outside of the fence
	 */
	public Fence(GWindow window, int fenceOpening, int lawnWidth) {
		this.window = window;
		// five sides to the fence
		fenceRails = new ArrayList<Shape>(5);
		this.lawnWidth = lawnWidth;
		this.fenceOpening = fenceOpening;
		thickness = 10;
		// defining the size of the garden
		gardenL = lawnWidth + thickness;
		gardenT = thickness;
		gardenR = window.getWindowWidth() - thickness;
		gardenB = window.getWindowHeight() - thickness;
	}

	/**
	 * Draws a fence.
	 */
	public void draw() {
		int height = window.getWindowHeight();
		int width = window.getWindowWidth() - lawnWidth;

		// fence top
		fenceRails.add(new Rectangle(lawnWidth, 0, width, thickness,
				Color.BLACK, true));

		// fence right
		fenceRails.add(new Rectangle(window.getWindowWidth() - thickness, 0,
				thickness, height, Color.BLACK, true));

		// fence bottom
		fenceRails.add(new Rectangle(lawnWidth, window.getWindowHeight()
				- thickness, width, thickness, Color.BLACK, true));

		// fence left top with opening
		fenceRails.add(new Rectangle(lawnWidth, 0, thickness,
				((height - this.fenceOpening) / 2), Color.BLACK, true));

		// frence left bottom with opening
		fenceRails.add(new Rectangle(lawnWidth,
				((height + this.fenceOpening) / 2), thickness,
				((height - this.fenceOpening) / 2), Color.BLACK, true));
		for (Shape line : fenceRails) {
			window.add(line);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(Caterpillar)
	 */
	@Override
	public boolean isCollision(Caterpillar cat) {
		return isCollision(cat.getBufferedHead());
	}

	/**
	 * The points to place the fence rails
	 * 
	 * @param buffer
	 *            buffer space around objects
	 * @returns a valid place to put a fence
	 */
	public Point randomPoint(int buffer) {
		int x = ((int) (Math.random() * (gardenR - gardenL - 2 * buffer)))
				+ gardenL + buffer;
		int y = ((int) (Math.random() * (gardenB - gardenT - 2 * buffer)))
				+ gardenT + buffer;
		return new Point(x, y);
	}

	/**
	 * Creates the garden area
	 * 
	 * @returns the size of the garden
	 */
	public Rectangle getGardenBox() {
		// adding/subtracting for area based on the fence
		return new Rectangle(gardenL + 1, gardenT + 1, gardenR - gardenL - 2,
				gardenB - gardenT - 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#doCollideAction(Caterpillar)
	 */
	@Override
	public void doCollideAction(Caterpillar cat) {
		cat.selectNewRandomDir();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collidable#isCollision(uwcse.graphics.Shape)
	 */
	@Override
	public boolean isCollision(Shape head) {
		boolean collision = false;
		for (Shape line : fenceRails) {
			/*
			 * If already a collision, don't check for another collision. When
			 * there is a collision, or the caterpillar head would intersect the
			 * fence
			 */
			collision = collision || line.intersects(head);
		}
		return collision;
	}

}
