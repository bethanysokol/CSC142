// Some useful libraries
import uwcse.graphics.*;
import uwcse.io.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * CSC 142 - Homework<br>
 * A RomanNumber is the representation in decimal and Roman numerals of an
 * integer between 1 and 3000.<br>
 * The number is displayed in a graphics window.<br>
 * A RomanNumber can be incremented, decremented or changed via interactive
 * input.
 * 
 * @author <your name here>
 */

public class RomanNumber {

	/** Maximum number that can be displayed */
	public static final int MAX_NUMBER = 3000;

	// instance fields
	private TextShape text; // The label to display the number (center it in the

	// window)

	private Font font; // Font used to display the number

	private GWindow window; // the window this RomanNumber belongs to

	private String sNumber; // The text to write in the TextShape (e.g. 18 =

	// XVIII)

	private int dNumber; // the number to display

	/**
	 * Creates the display of a number in Roman numerals in a given graphics
	 * window.<br>
	 * The number is given via interactive input by the user
	 * 
	 * @param window
	 *            the graphics window that displays the roman number.
	 */
	public RomanNumber(GWindow window) {
		// This RomanNumber is displayed in the GWindow window
		this.window = window;

		// Create the font for the TextShape
		// e.g. font = new Font("Courier",Font.BOLD,50);

		// Ask for the number and display it
		// (to do so, you might want to call other methods in the class)
	}

	/**
	 * Changes this RomanNumber to the new value given interactively by the
	 * user.<br>
	 * If the value given by the user is invalid, display an error message and
	 * don't change the display.
	 */
	public void inputNewNumber() {
	}

	/**
	 * Adds one to this RomanNumber (if < MAX_NUMBER).
	 */
	public void plusOne() {
	}

	/**
	 * Subtracts one from this RomanNumber (if > 1)
	 */
	public void minusOne() {
	}

}
