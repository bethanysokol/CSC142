import uwcse.graphics.*;
import uwcse.io.*;

import java.util.StringTokenizer; // To parse a String
import java.awt.Font; // To select a font for the display of the calendar

import javax.swing.JOptionPane; // To use dialog boxes 

import java.awt.Color; // To select colors

/**
 * CSC 142 - Homework
 * 
 * A CalendarDisplay is a graphics window that displays a calendar for a given
 * year and month. A button on the window allows the user to select the year and
 * month of the calendar.
 * 
 * @Bethany Dubeck
 */

public class CalendarDisplay extends GWindowEventAdapter {
	
	// Instance fields
		/**
		 * Height of the button
		 */
		private static final int BUTTON_DISPLAY_WIDTH = 90;
		/**
		 * Width of the button
		 */
		private static final int BUTTON_DISPLAY_HEIGHT = 25;
		/**
		 * Button that will display a new calendar
		 */
		private Rectangle newClndrBttn;
		
		/**
		 * Font used for the button
		 */
		private Font font;
		/**
		 * The calendar being displayed
		 */
		private Calendar cal;
		
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
		// displayHeight used to create a specified area to draw the buttons in
		int displayHeight = (3 * window.getWindowHeight()) / 4;
		// calls method to draw the buttons
		drawButtons(displayHeight);

		// Input a month and a year and show the calendar
		// (call some private methods)
	}
	
	/**
	 * Method to draw the buttons and button labels
	 * 
	 * @param calBttnDsplyHght
	 * Reserved space for calendar in which the button cannot
	 *            be drawn.
	 */
	private void drawButtons(int calBttnDsplyHght) {
		int y = calBttnDsplyHght;
		// height of a button
		int height = BUTTON_DISPLAY_HEIGHT;
		// width of a button
		int width = BUTTON_DISPLAY_WIDTH;

		// Buttons
		this.newClndrBttn = new Rectangle(110, y, width, height, Color.BLACK,
				true);
		// Label for new calendar button
		TextShape newClndrLbl = new TextShape("New Calendar", 115, y,
				Color.WHITE, font);
		// Add buttons and labels to the window
				window.add(newClndrBttn);
				window.add(newClndrLbl);
	}

	/**
	 * Click on the window. If it is on the button, input a new calendar
	 */
	public void mousePressed(GWindowEvent e) {
		// Location of the click
		int x = e.getX();
		int y = e.getY();

		/*
		 * Created a box that finds the mouse click. If any of the buttons
		 * intersect with the box in which the mouse click occurred, then it
		 * will call the appropriate method. If two or more buttons overlap, it
		 * is possible that both functions could trigger. The last function to
		 * execute has the highest priority, the +/- buttons will execute before
		 * prompting for a new number for the user's sake.
		 */
		Rectangle mouseClick = new Rectangle(e.getX(), e.getY(), 0, 0);
		if (newClndrBttn.intersects(mouseClick)) {
			cal.inputNewCalendar();
		}
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CalendarDisplay();
	}

	

}
