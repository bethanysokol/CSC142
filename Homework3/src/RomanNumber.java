// Some useful libraries
import uwcse.graphics.*;
import uwcse.io.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

/**
 * CSC 142 - Homework 3 A RomanNumber is the representation in decimal and Roman
 * numerals of an integer between 1 and 3000. The number is displayed in a
 * graphics window. A RomanNumber can be incremented, decremented or changed via
 * interactive input.
 * 
 * @author Bethany Dubeck
 */

public class RomanNumber {

	/** Maximum number that can be displayed */
	public static final int MAX_NUMBER = 3000;

	private static final int[] ARABIC_NUMBER_ARRAY = { 1, 4, 5, 9, 10, 40, 50,
			90, 100, 400, 500, 900, 1000 };
	private static final String[] ROMAN_NUMERALS_ARRAY = { "I", "IV", "V",
			"IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };

	// instance fields
	private TextShape text; // The label to display the number (center it in the

	// window)

	private Font font; // Font used to display the number

	private GWindow window; // the window this RomanNumber belongs to

	private String sNumber; // The text to write in the TextShape (e.g. 18 =

	// XVIII)

	private int dNumber; // the number to display

	private TextShape numeralDisplay;

	private int displayHeight;

	/**
	 * Creates the display of a number in Roman numerals in a given graphics
	 * window.<br>
	 * The number is given via interactive input by the user
	 * 
	 * @param window
	 *            the graphics window that displays the roman number.
	 * @param displayHeight 
	 */
	public RomanNumber(GWindow window, int displayHeight) {
		// This RomanNumber is displayed in the GWindow window
		this.window = window;

		// Create the font for the TextShape
		font = new Font("Times New Roman", Font.BOLD, 50);
		numeralDisplay = new TextShape("", 400, 80);
		this.displayHeight = displayHeight;
		window.add(numeralDisplay);

		// Ask for the number and display it
		// (to do so, you might want to call other methods in the class)
		this.inputNewNumber();
	}

	public void updateDisplay() {
		String val = translateNumber();
		numeralDisplay.setText(val);
		numeralDisplay.setColor(Color.BLACK);
		numeralDisplay.setFont(font);
		Rectangle2D bounding = font.getStringBounds(val, new FontRenderContext(
				null, true, true));
		double textWidth = bounding.getMaxX();
		double textHeight = bounding.getY()*-1;
		int x = (int) ((window.getWindowWidth()/2) -(textWidth/2));
		int y = (int) ((displayHeight/2) - (textHeight/2));
		numeralDisplay.moveTo(x, y);
	}

	private String translateNumber() {
		int remainder = dNumber;
		StringBuilder romanNumeral = new StringBuilder();
		remainder = addRomanNumSec(remainder, romanNumeral, 1000, "M");
		remainder = addRomanNumSec(remainder, romanNumeral, 900, "CM");
		remainder = addRomanNumSec(remainder, romanNumeral, 500, "D");
		remainder = addRomanNumSec(remainder, romanNumeral, 400, "CD");
		remainder = addRomanNumSec(remainder, romanNumeral, 100, "C");
		remainder = addRomanNumSec(remainder, romanNumeral, 90, "XC");
		remainder = addRomanNumSec(remainder, romanNumeral, 50, "L");
		remainder = addRomanNumSec(remainder, romanNumeral, 40, "XL");
		remainder = addRomanNumSec(remainder, romanNumeral, 10, "X");
		remainder = addRomanNumSec(remainder, romanNumeral, 9, "IX");
		remainder = addRomanNumSec(remainder, romanNumeral, 5, "V");
		remainder = addRomanNumSec(remainder, romanNumeral, 4, "IV");
		remainder = addRomanNumSec(remainder, romanNumeral, 1, "I");
		return romanNumeral.toString();
	}

	private int addRomanNumSec(int remainder, StringBuilder romanNumeral,
			int arabicValue, String romanValue) {
		if (arabicValue <= remainder) {
			int quotient = remainder / arabicValue;
			romanNumeral.append(repeat(romanValue, quotient));
			remainder -= quotient * arabicValue;
		}
		return remainder;
	}

	public String repeat(String symbol, int times) {
		String repeated = "";
		if (times > 0) {
			repeated += symbol;
		}
		if (times > 1) {
			repeated += symbol;
		}
		if (times > 2) {
			repeated += symbol;
		}
		// roman numerals never repeat more than three times
		return repeated;
	}

	/**
	 * Changes this RomanNumber to the new value given interactively by the
	 * user.<br>
	 * If the value given by the user is invalid, display an error message and
	 * don't change the display.
	 */
	public void inputNewNumber() {
		Input in = new Input();
		int inputVal = in.readIntDialog("Text");
		if (inputVal < 1 || inputVal > MAX_NUMBER) {

			System.err.println("Bad input");
		} else {
			dNumber = inputVal;
			updateDisplay();
		}
	}

	/**
	 * Adds one to this RomanNumber (if < MAX_NUMBER).
	 */
	public void plusOne() {
		if (dNumber < MAX_NUMBER) {

			dNumber++;
			updateDisplay();
		}
	}

	/**
	 * Subtracts one from this RomanNumber (if > 1)
	 */
	public void minusOne() {
		if (dNumber > 1) {
			dNumber--;
			updateDisplay();
		}
	}

}
