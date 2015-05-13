// Some useful libraries
import uwcse.graphics.*;
import java.awt.Color; // avoid writing java.awt.* since the package has a
// class called Rectangle, which is a name also used
// in uwcse.graphics
import java.awt.Font;

/**
 * CSC 142 - Homework 3
 * A RomanNumberDisplay has a window that can display a RomanNumber.
 * Buttons allow the user to enter a new number to display, or to add 1 to or
 * subtract 1 from the current number.
 * 
 * @author Bethany Dubeck
 */

public class RomanNumberDisplay extends GWindowEventAdapter {
	// By extending GWindowEventAdapter, a RomanNumberDisplay can
	// respond to mouse clicks on a graphics window.

	// Instance fields
	// Graphics window
	private GWindow window;

	// The RomanNumber being displayed
	private RomanNumber rNumber;

	// Add here other instance fields as needed

	/**
	 * Create the display
	 */
	public RomanNumberDisplay() {
		// Create the window (change the size if you wish)
		window = new GWindow("Numbers in roman numerals", 690, 200);
		window.setExitOnClose();
		// This RomanNumberDisplay handles the mouse clicks
		window.addEventHandler(this);

		// Buttons...
		// If you find yourself writing lots of line of code in the constructor,
		// move the code into a private method that you call from here.

		// Add the RomanNumber rNumber...
	}

	/**
	 * A mouse button has been pressed on the window.<br>
	 * Detects the location of the click and take proper action (do nothing or
	 * increment or decrement the number, or input and display a new number).
	 * 
	 * @param e
	 *            the GWindowEvent triggered by the mouse click
	 */
	public void mousePressed(GWindowEvent e) {
		// Locate the click
		int x = e.getX();
		int y = e.getY();

		// Click on the left button?
		// (if the left button is "plus one", tell rNumber
		// to increment itself by one: rNumber.addOne();)
		// etc...

		// If you need to update the display, use the method doRepaint() as in
		// window.doRepaint();
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new RomanNumberDisplay();
	}

	// Add your private methods here
}
