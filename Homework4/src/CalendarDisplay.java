import uwcse.graphics.*;
import uwcse.io.*;

import java.util.StringTokenizer; // To parse a String
import java.awt.Font; // To select a font for the display of the calendar
import javax.swing.JOptionPane; // To use dialog boxes 
import java.awt.Color; // To select colors

/**
 * CSC 142 - Homework<br>
 * 
 * A CalendarDisplay is a graphics window that displays a calendar for a given
 * year and month. A button on the window allows the user to select the year and
 * month of the calendar.
 * 
 * @author <Your name here>
 */

public class CalendarDisplay extends GWindowEventAdapter {
	// Graphics display
	private GWindow window;

	// To compute the calendar
	private Calendar calendar;

	/**
	 * Create the display
	 */
	public CalendarDisplay() {
		// Create the window
		window = new GWindow("Calendar", 300, 300);
		window.setExitOnClose();

		// Listen for any click on the window
		window.addEventHandler(this);

		// Input a month and a year and show the calendar
		// (call some private methods)
	}

	/**
	 * Click on the window. If it is on the button, input a new calendar
	 */
	public void mousePressed(GWindowEvent e) {
		// Location of the click
		int x = e.getX();
		int y = e.getY();

		// Click on the button?
		// ...
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CalendarDisplay();
	}

	// Add your own private methods below

}
