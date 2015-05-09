import uwcse.graphics.*; // UW graphics library

import java.awt.Color; // access the Color class        

/**
 * CSC 142 homework 2
 * 
 * An OceanScene displays animated fish, sailboats, stars, and squid in a
 * graphics window.
 * 
 * @author Bethany Dubeck
 */

public class OceanScene extends GWindowEventAdapter {

	/**
	 * The graphics window that displays the picture
	 */
	private GWindow window;

	/*
	 * The elements in the picture
	 */

	// 4 sailboats that sail back and forth and bob up and down
	private SailBoat sailboat1, sailboat2, sailboat3, sailboat4;
	// stars that twinkle
	private Star star1, star2, star3, star4, star5, star6, star7, star8, star9,
			star10, star11, star12, star13, star14, star15, star16, star17;
	// 7 fish that swim back and forth
	private Fish fish1, fish2, fish3, fish4, fish5, fish6, fish7;
	// 3 squid that swim up and down and blink
	private MyFourthElement myElement1, myElement2, myElement3;

	// To keep track of the duration of the animation
	private int animationCounter;

	/**
	 * Create a picture
	 */
	public OceanScene() {
		// The graphics window
		this.window = new GWindow("Ocean scene");
		this.window.setExitOnClose();

		// The ocean and the sky
		Rectangle ocean = new Rectangle(0, 0, window.getWindowWidth(),
				window.getWindowHeight(), Color.blue, true);
		window.add(ocean);
		/*
		 * Had to change the method for the sky. The sky covers the upper
		 * quarter of the window, tweaked it to create skyHeight, which will
		 * keep sea creatures from swimming out of the water and into the sky,
		 * because of the way the background is drawn, with the sky drawn on top
		 * of the ocean.
		 */
		int skyHeight = window.getWindowHeight() / 4;
		Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), skyHeight,
				Color.black, true);
		window.add(sky);

		// Add the graphics elements
		this.addGraphicsElements(skyHeight);

		// Code to do the animation
		this.window.addEventHandler(this);
		this.window.startTimerEvents(150);
	}

	/**
	 * Execute the animation
	 * 
	 * @see uwcse.graphics.GWindowEventAdapter#timerExpired(uwcse.graphics.GWindowEvent)
	 */
	public void timerExpired(GWindowEvent we) {
		this.window.suspendRepaints();

		// stars twinkling animation
		this.star1.twinkle();
		this.star2.twinkle();
		this.star3.twinkle();
		this.star4.twinkle();
		this.star5.twinkle();
		this.star6.twinkle();
		this.star7.twinkle();
		this.star8.twinkle();
		this.star9.twinkle();
		this.star10.twinkle();
		this.star11.twinkle();
		this.star12.twinkle();
		this.star13.twinkle();
		this.star14.twinkle();
		this.star15.twinkle();
		this.star16.twinkle();
		this.star17.twinkle();

		// fish swimming animation
		this.fish1.swim();
		this.fish2.swim();
		this.fish3.swim();
		this.fish4.swim();
		this.fish5.swim();
		this.fish6.swim();
		this.fish7.swim();

		// sailboats moving back and forth animation
		this.sailboat1.moveBackAndForth();
		this.sailboat2.moveBackAndForth();
		this.sailboat3.moveBackAndForth();
		this.sailboat4.moveBackAndForth();

		// sailboats bobbing up and down animation
		this.sailboat1.moveUpOrDown();
		this.sailboat2.moveUpOrDown();
		this.sailboat3.moveUpOrDown();
		this.sailboat4.moveUpOrDown();

		// squids up and down and eye blinking animation
		this.myElement1.doAction();
		this.myElement2.doAction();
		this.myElement3.doAction();

		this.window.resumeRepaints();

		// Run the animation 100 times (about 15 s)
		this.animationCounter++;
		if (this.animationCounter >= 100)
			this.window.stopTimerEvents();
	}

	/**
	 * Populate window with graphics
	 * 
	 * @param skyHeight
	 *            boundary from the top of the window to the ocean edge
	 */
	private void addGraphicsElements(int skyHeight) {
		// creating stars
		this.star1 = new Star(475, 20, 0.8, this.window);
		this.star2 = new Star(250, 50, 2.0, this.window);
		this.star3 = new Star(20, 35, 1.0, this.window);
		this.star4 = new Star(80, 15, .75, this.window);
		this.star5 = new Star(200, 60, .5, this.window);
		this.star6 = new Star(140, 60, 1.5, this.window);
		this.star7 = new Star(170, 25, 1.0, this.window);
		this.star8 = new Star(350, 35, .5, this.window);
		this.star9 = new Star(75, 65, 1.0, this.window);
		this.star10 = new Star(118, 45, .5, this.window);
		this.star11 = new Star(40, 70, .75, this.window);
		this.star12 = new Star(300, 15, .5, this.window);
		this.star13 = new Star(390, 70, 1.5, this.window);
		this.star14 = new Star(420, 30, 1.0, this.window);
		this.star15 = new Star(480, 70, 1.25, this.window);
		this.star16 = new Star(305, 70, 1.5, this.window);
		this.star17 = new Star(365, 50, .75, this.window);

		// creating fish
		this.fish1 = new Fish(50, 350, .75, Fish.LEFT_MOVING, this.window);
		this.fish2 = new Fish(200, 225, 1.0, Fish.RIGHT_MOVING, this.window);
		this.fish3 = new Fish(250, 350, 1.0, Fish.LEFT_MOVING, this.window);
		this.fish4 = new Fish(300, 175, .75, Fish.RIGHT_MOVING, this.window);
		this.fish5 = new Fish(375, 110, 0.5, Fish.LEFT_MOVING, this.window);
		this.fish6 = new Fish(80, 200, .25, Fish.RIGHT_MOVING, this.window);
		this.fish7 = new Fish(110, 150, .25, Fish.LEFT_MOVING, this.window);

		// creating sailboats
		this.sailboat3 = new SailBoat(50, 115, .5, SailBoat.DOWN_MOVING,
				SailBoat.LEFT_MOVING, skyHeight, this.window);
		this.sailboat4 = new SailBoat(200, 100, 1.0, SailBoat.UP_MOVING,
				SailBoat.RIGHT_MOVING, skyHeight, this.window);
		this.sailboat1 = new SailBoat(300, 150, 1.25, SailBoat.DOWN_MOVING,
				SailBoat.LEFT_MOVING, skyHeight, this.window);
		this.sailboat2 = new SailBoat(300, 300, 1.5, SailBoat.UP_MOVING,
				SailBoat.RIGHT_MOVING, skyHeight, this.window);

		// creating squid
		this.myElement1 = new MyFourthElement(125, 275, 1.0,
				MyFourthElement.DOWN_MOVING, skyHeight, this.window);
		this.myElement2 = new MyFourthElement(25, 175, 0.5,
				MyFourthElement.UP_MOVING, skyHeight, this.window);
		this.myElement3 = new MyFourthElement(450, 275, 1.2,
				MyFourthElement.UP_MOVING, skyHeight, this.window);
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new OceanScene();
	}
}