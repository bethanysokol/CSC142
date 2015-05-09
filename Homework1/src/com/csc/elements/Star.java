package com.csc.elements;

import java.awt.Color;

import uwcse.graphics.*;

/**
 * A star in a graphics window
 */
public class Star {

	// OBJECT FIELDS

	/**
	 * The graphics window the star belongs to
	 */
	private GWindow window;

	// The location of the center of the star
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

		// color of the star
		Color starColor = Color.WHITE;

		// Size of the star
		int starSize = (int) (this.scale * 10);

		// Draw the lines making up the star
		// create line 1 & 2
		Line line = new Line(this.x, this.y - starSize / 2, this.x, this.y
				+ starSize / 2, starColor);
		this.window.add(line);
		line = new Line(this.x - starSize / 2, this.y, this.x + starSize / 2,
				this.y, starColor);
		this.window.add(line);
		line = new Line(this.x - starSize / 2, this.y - starSize / 2, this.x
				+ starSize / 2, this.y + starSize / 2, starColor);
		this.window.add(line);
		line = new Line(this.x + starSize / 2, this.y - starSize / 2, this.x
				- starSize / 2, this.y + starSize / 2, starColor);
		this.window.add(line);

	}
}