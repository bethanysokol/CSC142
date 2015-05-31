import uwcse.graphics.*;
import uwcse.io.*;
import java.awt.Font; // To select a font for the display of the calendar 
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

	// Constants
	/**
	 * Maximum month value that can be displayed or input
	 */
	public static final int MAX_MONTH_NUMBER = 12;
	/**
	 * Height of the button
	 */
	private static final int BUTTON_DISPLAY_WIDTH = 90;
	/**
	 * Width of the button
	 */
	private static final int BUTTON_DISPLAY_HEIGHT = 25;
	/**
	 * The minimum value allowed for a year input
	 */
	private static final int MIN_YEAR = 1;

	// Instance fields
	/**
	 * Button that will display a new calendar
	 */
	private Rectangle newClndrBttn;

	/**
	 * Font used for the button label
	 */
	private Font font;
	/**
	 * The calendar being displayed
	 */
	private Calendar calendar;
	/**
	 * Graphics display
	 */
	private GWindow window;

	/**
	 * Create the display
	 */
	public CalendarDisplay() {
		// Create the window
		window = new GWindow("Calendar", 300, 300);
		window.setExitOnClose();

		// Listen for any click on the window
		window.addEventHandler(this);
		// displayHeight used to create a specified area to draw the button in
		int displayHeight = (3 * window.getWindowHeight()) / 4;

		// calls method to draw the button
		drawButton(displayHeight);
	}

	/**
	 * Method to draw the button and button label
	 * 
	 * @param calBttnDsplyHght
	 *            Reserved space for calendar in which the button cannot be
	 *            drawn.
	 */
	private void drawButton(int calBttnDsplyHght) {
		int y = calBttnDsplyHght;
		// height of the button
		int height = BUTTON_DISPLAY_HEIGHT;
		// width of the button
		int width = BUTTON_DISPLAY_WIDTH;

		// New Calendar Button
		this.newClndrBttn = new Rectangle(110, y, width, height, Color.BLACK,
				true);
		// Label for new calendar button
		TextShape newClndrLbl = new TextShape("New Calendar", 115, y,
				Color.WHITE, font);
		// Add button and label to the window
		window.add(newClndrBttn);
		window.add(newClndrLbl);
	}

	/**
	 * Click on the window. If the click is on the button, ask for input for a
	 * new calendar
	 */
	public void mousePressed(GWindowEvent e) {
		/*
		 * Created a box that finds the mouse click. If the button intersects
		 * with the box in which the mouse click occurred, then it will call the
		 * appropriate method.
		 */
		Rectangle mouseClick = new Rectangle(e.getX(), e.getY(), 0, 0);
		if (newClndrBttn.intersects(mouseClick)) {
			this.inputNewCalendar();
		}
	}

	/**
	 * the new value given interactively by the user. If the value given by the
	 * user is invalid, print an error message and ask for input again
	 */
	public void inputNewCalendar() {
		/*
		 * Pops open a window when the user clicks "new calendar", and prompts
		 * for input.
		 */
		Input in = new Input();
		int inputValMonth = in.readIntDialog("Input a month");
		if (inputValMonth >= 1 && inputValMonth <= MAX_MONTH_NUMBER) {

			int inputValYear = in.readIntDialog("Input a year");
			if (inputValYear >= MIN_YEAR) {
				// if there is already a calendar drawn from previous input,
				// clear it.
				if (this.calendar != null) {
					this.calendar.clear();
				}
				// draw the new calendar
				this.calendar = new Calendar(inputValMonth, inputValYear);
				this.calendar.draw(window);
			} else {
				/*
				 * If user input is not within the specified range, display an
				 * error message in the console, and ask for input again.
				 */
				System.err
						.println("Not a valid year, please enter a positive integer");
				inputNewCalendar();

			}
		} else {
			/*
			 * If user input is not within the specified range, display an
			 * error message in the console, and ask for input again.
			 */
			System.err
					.println("Not a valid month, please enter a number between 1-12");
			inputNewCalendar();
		}

	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CalendarDisplay();
	}

}
