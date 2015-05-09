package com.csc.elements;

import java.awt.Color;

import uwcse.graphics.*;

/**
 * A sailboat in a graphics window
 */
public class SailBoat {

	// CLASS CONSTANTS

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

	// OBJECT FIELDS

	/**
	 * The graphics window the boat belongs to
	 */
	private GWindow window;

	// The location of the center of the boat
	/**
	 * X component of the center
	 */
	private int x;

	/**
	 * Y component of the center
	 */
	private int y;

	/**
	 * Scale relative to the default dimensions
	 */
	private double scale;

	/**
	 * Draws a sailboat in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the boat
	 * @param y
	 *            the y coordinate of the location of the boat
	 * @param window
	 *            the graphics window where the boat is displayed
	 * @param window
	 *            the graphics window the boat belongs to
	 */
	public SailBoat(int x, int y, double scale, GWindow window) {

		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		this.scale = scale;

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

		// Modified the blue so the squid show up better on the water
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
		Triangle mainsail = new Triangle(mainSailX1, y1, mainSailX2, y2,
				mainSailX3, y3, color, true);
		window.add(mainsail);

		red = (int) (256 * Math.random());
		green = (int) (256 * Math.random());
		// Modified the blue so the squid show up better on the water
		blue = (int) (128 * Math.random());
		color = new Color(red, green, blue);

		// create jib
		int jibX1 = x + sailGap / 2;
		int jibX2 = jibX1 + sailWidth;
		int jibX3 = jibX1;
		Triangle jib = new Triangle(jibX1, y1, jibX2, y2, jibX3, y3, color,
				true);
		window.add(jib);
		red = (int) (256 * Math.random());
		green = (int) (256 * Math.random());

		// Modified the blue so the squid show up better on the water
		blue = (int) (128 * Math.random());
		color = new Color(red, green, blue);

		// create hull
		Polygon hull = new Polygon(color, true);

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
}
