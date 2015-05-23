/**
 * Given a month and a year, construct the corresponding calendar for that month
 * and year.
 */

public class Calendar {
	// Instance fields to compute the calendar
	private int month;

	private int year;

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

	// Add your own private methods below
}
