package com.csc.elements;

import java.awt.Color;

import uwcse.graphics.Arc;
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

/**
 * This fourth element represents a squid and was required by scope
 * 
 * @author bdubeck
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

	// OBJECT FIELDS

	/**
	 * The graphics window the squid belongs to
	 */
	private GWindow window;

	// The location of the center of the squid body
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
	 * Draws a squid in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the squid
	 * @param y
	 *            the y coordinate of the location of the squid
	 * @param scale
	 *            the scale of the drawing of the squid
	 * @param window
	 *            the graphics window the squid belongs to
	 */
	public MyFourthElement(int x, int y, double scale, GWindow window) {

		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;

		// Draw the squid
		this.draw();
	}

	/**
	 * Draws this squid of random color in the window.
	 */
	private void draw() {

		int height = (int) (DEFAULT_HEIGHT * scale);
		int width = (int) (DEFAULT_WIDTH * scale);

		// Generates the color of the squid randomly
		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		// How to generate a random integer between >= 0 and < 256?
		// 0 <= (int)(256 * Math.random()) < 256
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());

		// Modified the blue so the squid show up better on the water
		int blue = (int) (128 * Math.random());
		Color color = new Color(red, green, blue);

		// Create the body
		Oval body = new Oval(x - width / 2, y - height / 2, width, height,
				color, true);
		window.add(body);

		// Declaring the size and spacing of the squid's eyes
		int eyeGap = (width / 4) / 3;
		int eyeSize = width / 4;

		// creating the eyes
		Oval eye1 = new Oval(x + eyeGap, y, eyeSize, eyeSize, Color.WHITE, true);
		window.add(eye1);
		Oval eye2 = new Oval(x - eyeGap - eyeSize, y, eyeSize, eyeSize,
				Color.WHITE, true);
		window.add(eye2);

		// creating the legs
		drawLegPair(color, height, height);
		drawLegPair(color, height, height / 2);
		drawLegPair(color, height, height * 2);

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
	 */
	private void drawLegPair(Color color, int bodyHeight, int tentacleLength) {

		Arc tentacle = new Arc(x - tentacleLength / 4, y
				+ (int) ((2.0 / 5) * bodyHeight), tentacleLength / 2,
				tentacleLength * 2, 0, 180, color, false);

		window.add(tentacle);
	}

}
