package com.csc.elements;

import java.awt.Color;

import uwcse.graphics.*;

/**
 * Creates a fish in a graphics window
 */
public class Fish {

	// OBJECT FIELDS

	/**
	 * The graphics window the fish belongs to
	 */
	private GWindow window;

	// The location of the center of the fish
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
	 * Draws a Fish in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the fish
	 * @param y
	 *            the y coordinate of the location of the fish
	 * @param scale
	 *            the scale of the drawing of the fish
	 * @param window
	 *            the graphics window the fish belongs to
	 */
	public Fish(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;

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
		Color color = new Color(red, green, blue);

		// Create the body and tail
		Oval body = new Oval(x - width / 2, y - height / 2, width, height,
				color, true);
		window.add(body);

		// Declaring the size of the fish's eye
		int eyeSize = width / 8;

		Triangle tail;
		Oval eye;
		// randomly select the direction of the fish (and its eye to match)
		if (Math.random() < 0.5) { // move left
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

}
