// Some useful libraries
import uwcse.graphics.*;
import uwcse.io.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * CSC 142 - Homework 3 A RomanNumber is the representation in decimal and Roman
 * numerals of an integer between 1 and 3000. The number is displayed in a
 * graphics window. A RomanNumber can be incremented, decremented or changed via
 * interactive input.
 * 
 * @author Bethany Dubeck
 */

public class RomanNumber {

	/** 
	 * Maximum number that can be displayed or input 
	 */
	public static final int MAX_NUMBER = 3000;
	// instance fields
	/**
	 * Font used to display the number
	 */
	private Font font;
	/**
	 * the window this RomanNumber belongs to
	 */
	private GWindow window;
	/**
	 * The text to write in the Arabic text display (e.g. 18 = XVIII)
	 */
	private String sNumber;
	/**
	 * the user generated number to display
	 */
	private int dNumber;
	/**
	 * displays <Arabic number> = <Roman numeral equivalent>
	 */
	private TextShape numeralDisplay;
	/**
	 * specified height of the display
	 */
	private int displayHeight;

	/**
	 * Creates the display of a number in Roman numerals in a given graphics
	 * window. The number is given via interactive input by the user.
	 * 
	 * @param window
	 *            the graphics window that displays the Roman number.
	 * @param displayHeight
	 *            creates the height of the Arabic text box
	 */
	public RomanNumber(GWindow window, int displayHeight) {
		// This RomanNumber is displayed in the GWindow window
		this.window = window;

		// Create the font for the Arabic number
		font = new Font("Times New Roman", Font.BOLD, 50);
		numeralDisplay = new TextShape("", 400, 80);
		this.displayHeight = displayHeight;
		window.add(numeralDisplay);

		// Ask for the number and display it
		this.inputNewNumber();
	}

	/**
	 * Displays user number and Roman numeral value and displays it as
	 * "18 = XVIII"
	 */
	public void updateDisplay() {
		sNumber = dNumber + "=" + translateNumber();
		numeralDisplay.setText(sNumber);
		numeralDisplay.setColor(Color.BLACK);
		numeralDisplay.setFont(font);
		/*
		 * Created a bounding box used in centering the Arabic and Roman
		 * numerals in the window.
		 */
		Rectangle2D bounding = font.getStringBounds(sNumber,
				new FontRenderContext(null, true, true));
		double textWidth = bounding.getMaxX();
		/*
		 * Box starts at 0, -y in the window.
		 * Multiplied y by -1 to get height as a positive value.
		 */
		double textHeight = bounding.getY() * -1;
		int x = (int) ((window.getWindowWidth() / 2) - (textWidth / 2));
		int y = (int) ((displayHeight / 2) - (textHeight / 2));
		numeralDisplay.moveTo(x, y);
	}

	/**
	 * Converting dNumber from its Arabic form and returning the Roman numeral.
	 * 
	 * @return the value of the input number as a Roman numeral string
	 */
	private String translateNumber() {
		int remainder = dNumber;
		/*
		 * Check the value of each Arabic number starting with 1,000 and
		 * preceding down to 1.
		 */
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

	/**
	 * Factors out a given Roman symbol from a given Arabic number, appending
	 * the extracted Roman value to a provided string builder and returns the
	 * remaining value. i.e.: addRomanNumSec(578, [string builder containing
	 * "MM"], 500, "D") results in a return of 78 and the string builder now
	 * contains "MMD"
	 * 
	 * NOTE: This relies on the caller having previously executed for any
	 * greater symbols. i.e.: addRomanNumSec(2578, [string builder containing
	 * ""], 500, "D") results in a return of 78 and the string builder now
	 * contains "DDDDD"
	 * 
	 * @param arabicInput
	 *            the number the Roman symbols are being extracted from
	 * @param romanOutput
	 *            a string builder containing any previously extracted symbols
	 *            to append newly extracted symbols to
	 * @param arabicValue
	 *            the Arabic number equivalent of each Roman symbols being
	 *            applied
	 * @param romanValue
	 *            the Roman symbols being applied currently
	 * @return the value remaining after factoring out the Roman symbols
	 */
	private int addRomanNumSec(int arabicInput, StringBuilder romanOutput,
			int arabicValue, String romanValue) {
		/*
		 * Check to avoid wasting time (by appending things 0 times, multiplying
		 * by zero, or subtracting by zero)
		 */
		if (arabicValue <= arabicInput) {

			// Determines number of symbols to extract
			int numSymbols = arabicInput / arabicValue;

			// Append the Roman numeral characters
			romanOutput.append(repeat(romanValue, numSymbols));

			// Decrementing by the amount equivalent to the Roman numeral string
			arabicInput -= numSymbols * arabicValue;
		}
		return arabicInput;
	}

	/**
	 * The number of times a symbol repeats in a Roman numeral from 1-3 times,
	 * such as III or XX.
	 * 
	 * @param symbol
	 *            the Roman numerals such as "V"
	 * @param times
	 *            the number of times a symbol repeats itself
	 * @return value of repeated (how many times the symbol should be drawn)
	 */
	public String repeat(String symbol, int times) {
		// Did not use a loop as the requirements said not to use loops.
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
		// Roman numerals never repeat more than three times
		return repeated;
	}

	/**
	 * Changes this RomanNumber to the new value given interactively by the
	 * user. If the value given by the user is invalid, display an error message
	 * and doesn't change the display.
	 */
	public void inputNewNumber() {
		/*
		 * Pops open a window when the user clicks "new number", and prompts for
		 * input.
		 */
		Input in = new Input();
		int inputVal = in.readIntDialog("Input a number");
		/*
		 * If user input is less than one or more than max number, display an
		 * error message in the console. Display does not change per the
		 * requirements.
		 */
		if (inputVal < 1 || inputVal > MAX_NUMBER) {

			System.err
					.println("Not a valid number, please enter a number between 1-3000");
		} else {
			dNumber = inputVal;
			updateDisplay();
		}
	}

	/**
	 * Adds one to this RomanNumber (if < MAX_NUMBER). Does NOT wrap from 3,000
	 * to 1.
	 */
	public void plusOne() {
		if (dNumber < MAX_NUMBER) {

			dNumber++;
			updateDisplay();
		}
	}

	/**
	 * Subtracts one from this RomanNumber (if > 1). Does NOT wrap from 1 to
	 * 3,000
	 */
	public void minusOne() {
		if (dNumber > 1) {
			dNumber--;
			updateDisplay();
		}
	}

}
