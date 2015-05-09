package com.csc;

import java.awt.Color; //graphics library

import com.csc.elements.Fish;
import com.csc.elements.SailBoat;
import com.csc.elements.MyFourthElement;
import com.csc.elements.Star;

import uwcse.graphics.*; // uw graphics library

/**
 * CSC 142 homework 1
 * 
 * Create a landscape that features 4 different types of elements (3 of the
 * types must be a sailboat, a fish, and a star)
 * 
 * @author (Bethany Dubeck)
 */
public class OceanScene {
	/**
	 * Creates an ocean scene
	 */
	public OceanScene() {
		// The graphics window
		// the dimensions of the window is 500 by 400 pixels.
		GWindow window = new GWindow("Coastal landscape");
		window.setExitOnClose();

		// The ocean and the sky
		Rectangle ocean = new Rectangle(0, 0, window.getWindowWidth(),
				window.getWindowHeight(), Color.blue, true);
		window.add(ocean);

		// the sky covers the upper quarter of the window
		Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(),
				window.getWindowHeight() / 4, Color.black, true);
		window.add(sky);

		// Draw the elements in the window

		// draw a bunch of stars
		new Star(475, 20, 0.8, window);
		new Star(250, 50, 2.0, window);
		new Star(20, 35, 1.0, window);
		new Star(80, 15, .75, window);
		new Star(200, 60, .5, window);
		new Star(140, 60, 1.5, window);
		new Star(170, 25, 1.0, window);
		new Star(350, 35, .5, window);
		new Star(75, 65, 1.0, window);
		new Star(118, 45, .5, window);
		new Star(40, 70, .75, window);
		new Star(300, 15, .5, window);
		new Star(390, 70, 1.5, window);
		new Star(420, 30, 1.0, window);
		new Star(480, 70, 1.25, window);
		new Star(305, 70, 1.5, window);
		new Star(365, 50, .75, window);

		// draw several fish
		new Fish(50, 350, .75, window);
		new Fish(200, 225, 1.0, window);
		new Fish(250, 350, 1.0, window);
		new Fish(300, 175, .75, window);
		new Fish(375, 110, .5, window);
		new Fish(80, 200, .25, window);
		new Fish(110, 150, .25, window);

		// draw a few squid
		new MyFourthElement(125, 275, 1, window);
		new MyFourthElement(25, 175, .5, window);
		new MyFourthElement(450, 275, 1.2, window);

		// draw a few boats
		new SailBoat(425, 150, 1.25, window);
		new SailBoat(350, 300, 1.5, window);
		new SailBoat(50, 115, .5, window);
		new SailBoat(200, 100, 1.0, window);

		// Refresh the window
		window.doRepaint();
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new OceanScene();
	}
}
