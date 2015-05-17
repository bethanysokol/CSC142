// Some useful libraries
import uwcse.graphics.*;
import java.awt.Color; // avoid writing java.awt.* since the package has a
// class called Rectangle, which is a name also used
// in uwcse.graphics
import java.awt.Font;

/**
 * CSC 142 - Homework 3 A RomanNumberDisplay has a window that can display a
 * RomanNumber. Buttons allow the user to enter a new number to display, or to
 * add 1 to or subtract 1 from the current number.
 * 
 * @author Bethany Dubeck
 */

public class RomanNumberDisplay extends GWindowEventAdapter {
	/*
	 * By extending GWindowEventAdapter, a RomanNumberDisplay can respond to
	 * mouse clicks on a graphics window.
	 */

	// Instance fields
	/**
	 * Height of the buttons
	 */
	private static final int BUTTON_DISPLAY_WIDTH = 90;
	/**
	 * Width of the buttons
	 */
	private static final int BUTTON_DISPLAY_HEIGHT = 25;
	/**
	 * Button that increments Roman numeral by one
	 */
	private Rectangle plusOneButton;
	/**
	 * Button that decrements Roman numeral by one
	 */
	private Rectangle minusOneButton;
	/**
	 * Button that prompts user for a new number
	 */
	private Rectangle newNumberButton;
	/**
	 * Graphics window
	 */
	private GWindow window;
	/**
	 * The RomanNumber being displayed
	 */
	private RomanNumber rNumber;
	/**
	 * Font used for the buttons
	 */
	private Font font;

	/**
	 * Create the display
	 */
	public RomanNumberDisplay() {
		// Create the window
		window = new GWindow("Numbers in roman numerals", 690, 200);
		window.setExitOnClose();
		// This RomanNumberDisplay handles the mouse clicks
		window.addEventHandler(this);
		// set button label font
		font = new Font("SANS_SERIF", Font.BOLD, 15);
		// displayHeight used to create a specified area to draw the buttons in
		int displayHeight = (3 * window.getWindowHeight()) / 4;
		this.rNumber = new RomanNumber(window, displayHeight);
		// calls method to draw the buttons
		drawButtons(displayHeight);
	}

	/**
	 * Method to draw the buttons and button labels
	 * 
	 * @param romanNumeralDisplayHeight
	 *            Reserved space for Roman numerals in which the buttons cannot
	 *            be drawn.
	 */
	private void drawButtons(int romanNumeralDisplayHeight) {
		int y = romanNumeralDisplayHeight;
		// height of a button
		int height = BUTTON_DISPLAY_HEIGHT;
		// width of a button
		int width = BUTTON_DISPLAY_WIDTH;

		// Buttons
		this.plusOneButton = new Rectangle(200, y, width, height, Color.BLACK,
				true);
		// Label for incrementing button
		TextShape plusOneButtonLbl = new TextShape("Plus One", 213, y,
				Color.WHITE, font);
		this.minusOneButton = new Rectangle(300, y, width, height, Color.BLACK,
				true);
		// Label for decrementing button
		TextShape minusOneButtonLbl = new TextShape("Minus One", 307, y,
				Color.WHITE, font);
		this.newNumberButton = new Rectangle(400, y, width, height,
				Color.BLACK, true);
		// Label for new number button
		TextShape newNumberButtonLbl = new TextShape("New Number", 400, y,
				Color.WHITE, font);
		// Add buttons and labels to the window
		window.add(plusOneButton);
		window.add(minusOneButton);
		window.add(newNumberButton);
		window.add(plusOneButtonLbl);
		window.add(minusOneButtonLbl);
		window.add(newNumberButtonLbl);
	}

	/**
	 * A mouse button has been pressed on the window. Detects the location of
	 * the click and take proper action (do nothing or increment or decrement
	 * the number, or input and display a new number).
	 * 
	 * @param e
	 *            the GWindowEvent triggered by the mouse click
	 */
	public void mousePressed(GWindowEvent e) {
		/*
		 * Created a box that finds the mouse click. If any of the buttons
		 * intersect with the box in which the mouse click occurred, then it
		 * will call the appropriate method. If two or more buttons overlap, it
		 * is possible that both functions could trigger. The last function to
		 * execute has the highest priority, the +/- buttons will execute before
		 * prompting for a new number for the user's sake.
		 */
		Rectangle mouseClick = new Rectangle(e.getX(), e.getY(), 0, 0);
		if (plusOneButton.intersects(mouseClick)) {
			rNumber.plusOne();
		}
		if (minusOneButton.intersects(mouseClick)) {
			rNumber.minusOne();
		}
		if (newNumberButton.intersects(mouseClick)) {
			rNumber.inputNewNumber();
		}
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new RomanNumberDisplay();
	}

}
