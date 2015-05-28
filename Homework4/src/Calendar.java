import uwcse.io.Input;

/**
 * Given a month and a year, construct the corresponding calendar for that month
 * and year.
 */

public class Calendar {
	/** 
	 * Maximum month that can be displayed or input 
	 */
	public static final int MAX_MONTH_NUMBER = 12;

	// Instance fields to compute the calendar
	private int month;

	private int year;
	/**
	 * the user generated number to display
	 */
	private int dNumber;
	// Reference point
	// 1/1/1 is a Monday
	private final int REF_YEAR = 1;

	private final int REF_MONTH = 1;

	private final int REF_DAY = 2; // if you choose Sunday=1, etc...

	/**
	 * Define a calendar for a month and a year (assume that the input is valid)
	 * 
	 * @param year
	 *            the year of the calendar (>= 1)
	 * @param month
	 *            the month of the calendar (>= 1 AND &lt;= 12)
	 */
	public Calendar(int month, int year) {

	}

	/**
	 * postcondition: result==the calendar nicely formatted in a String.<br>
	 * Example:<br>
	 * 
	 * <pre>
	 * 
	 *  August  2003
	 *  Su Mo Tu We Th Fr Sa
	 *                 1  2
	 *  3  4  5  6  7  8  9
	 *  10 11 12 13 14 15 16
	 *  17 18 19 20 21 22 23
	 *  24 25 26 27 28 29 30
	 *  31
	 * 
	 * </pre>
	 */
	public String toString() {
		String output = "";

		return output;
	}

	/**
	 * Changes this RomanNumber to the new value given interactively by the
	 * user. If the value given by the user is invalid, display an error message
	 * and doesn't change the display.
	 */
	public void inputNewCalendar() {
		/*
		 * Pops open a window when the user clicks "new calendar", and prompts for
		 * input.
		 */
		Input in = new Input();
		int inputVal = in.readIntDialog("Input a month");
		/*
		 * If user input is less than one or more than max number, display an
		 * error message in the console. Display does not change per the
		 * requirements.
		 */
		if (inputVal < 1 || inputVal > MAX_MONTH_NUMBER) {

			System.err
					.println("Not a valid number, please enter a number between 1-12");
		} else {
			dNumber = inputVal;
			//updateDisplay();
		}
}
}