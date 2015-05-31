import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import uwcse.graphics.GWindow;
import uwcse.graphics.Shape;
import uwcse.graphics.TextShape;

/**
 * holds data and executes logic needed to display a calendar for a given month
 * and year. It should be noted that the accuracy of the first day of each month
 * was checked against joda DateTime, but given that leap was not used in a
 * modern fashion until a few hundred years ago accuracy with some historical
 * references may be faulty.
 */
public class Calendar {

	// Instance fields
	/**
	 * The month of the year
	 */
	private int month;

	/**
	 * The year
	 */
	private int year;
	/**
	 * reference date, 1/1/1. 0 is Sunday
	 */
	private final int REF_DAY_OF_WEEK = 1;
	/**
	 * the original starting Y position
	 */
	private final int INITIAL_Y = 10;
	/**
	 * the original starting X position
	 */
	private final int INITIAL_X = 10;

	/**
	 * helper constant to look up the index of start of week
	 */
	private final int SUNDAY = 0;

	/**
	 * helper constant to look up the index of end of week
	 */
	private final int SATURDAY = 6;

	/**
	 * Creating a custom font for the calendar
	 */
	private Font font;

	/**
	 * List of months to index user input against
	 */
	private String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	/**
	 * The days of the week label for the calendar
	 */
	private String weekdays = "Su Mo Tu We Th Fr Sa";

	/**
	 * List with the days in each month of a normal year.
	 */
	static int[] normalYear = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * List with the days in each month of a leap year.
	 */
	static int[] leapYear = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * List of shapes used for the multiple objects that make up the display of
	 * the calendar
	 */
	private List<Shape> shapes;

	/**
	 * Define a calendar for a month and a year (assume that the input is valid)
	 * 
	 * @param year
	 *            the year of the calendar (>= 1)
	 * @param month
	 *            the month of the calendar (>= 1 && <= 12)
	 */
	public Calendar(int month, int year) {
		this.month = month;
		this.year = year;
		shapes = new LinkedList<Shape>();
	}

	/**
	 * calculates the number of days since our reference date of 1/1/1
	 * 
	 * @return the number of days since our reference date
	 */
	private int calcDaysSinceRef() {
		// initialize days to 0
		int days = 0;
		// previous years
		for (int y = 1; y < year; y++) {
			// all years have at least 365 days
			days += 365;
			// leap years have one extra day
			if (isLeapYear(y)) {
				days++;
			}
		}
		// finding the amount of days since the year started
		int daysThisYear = 0;
		// current year
		for (int m = 0; m < month - 1; m++) {
			// find the matching value in the list
			int daysInMonth = findDaysInMonth(m);
			// add the number of days in the month to the number of days in the
			// year
			daysThisYear += daysInMonth;
		}
		days += daysThisYear;
		return days;
	}

	/**
	 * Finds the number of days in a month
	 * 
	 * @param m
	 *            Index position in list(s)
	 * @return the number of days in a given month
	 */
	private int findDaysInMonth(int m) {

		int daysInMonth = normalYear[m];
		if (isLeapYear(year)) {
			daysInMonth = leapYear[m];
		}
		return daysInMonth;
	}

	/**
	 * Determines if a year is a leap year
	 * 
	 * @param y
	 *            the year being checked
	 * @return whether or not it is a leap year
	 */
	public boolean isLeapYear(int y) {
		// If a year is divisible by 4 but not 100 OR is divisible by 400, then
		// it is a
		// leap year
		return y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);

	}

	/**
	 * Draws the calendar text shapes
	 * 
	 * @param window
	 *            the window the calendar is drawn in
	 * 
	 */
	public void draw(GWindow window) {
		// starting position of the x and y coordinates
		int x = INITIAL_X;
		int y = INITIAL_Y;
		// using a mono spaced font for better uniformity and spacing
		font = new Font("Courier", Font.BOLD, 15);
		// spacing between the lines of the calendar
		int lineSpc = 20;
		int monthIndex = month - 1;
		// Label in the top of the window with the month and year
		TextShape monthYearLbl = new TextShape(buildMonthYr(monthIndex), x, y,
				Color.black, font);
		y += lineSpc;
		// Label for each day of the week in 2 letter abbreviations
		TextShape weekDayLbl = new TextShape(weekdays, x, y, Color.black, font);
		y += lineSpc;
		shapes.add(monthYearLbl);
		shapes.add(weekDayLbl);

		int dayOfWeek = findStartDOW();
		int daysInMonth = findDaysInMonth(monthIndex);
		// using a string builder to make use of the append method
		StringBuilder weekString = new StringBuilder();

		/*
		 * because left justified align was used, an offset had to be created so
		 * that the first day would start in the correct location. Formatted
		 * width x number of days in week to offset by.
		 */
		int offSet = 3 * dayOfWeek;
		if (offSet > 0) {
			// left justified alignment
			weekString.append(String.format("%1$-" + offSet + "s", ""));
		}
		/*
		 * so long as day is less than or equal to the days in the month, keep
		 * printing lines of dates.
		 */
		for (int day = 1; day <= daysInMonth; day++) {
			// 3 width spacing
			weekString.append(String.format("%1$-3s", day));
			dayOfWeek++;
			/*
			 * Check to see if the end of the month or week has been reached.
			 */
			if (dayOfWeek > SATURDAY || day == daysInMonth) {
				TextShape line = new TextShape(weekString.toString(), x, y,
						Color.black, font);
				y += lineSpc;
				shapes.add(line);
				/*
				 * after each line, reset dayOfWeek to Sunday in order to start
				 * the next line
				 */
				dayOfWeek = SUNDAY;
				/*
				 * after each line, create a new string builder so that multiple
				 * weeks are not together in the same line
				 */
				weekString = new StringBuilder();
			}
		}
		// add each shape to the window
		for (Shape s : shapes) {
			window.add(s);
		}
	}

	/**
	 * Finds the day of the week of the first of this calendar's month given the
	 * number of days since the reference date 1/1/1
	 * 
	 * @return the day of the week this day falls on
	 */
	public int findStartDOW() {
		// counting the days since the reference day of 1/1/1
		int daysSinceRef = calcDaysSinceRef();
		int d = daysSinceRef + REF_DAY_OF_WEEK;

		return d % 7;
	}

	/**
	 * Building the month and year label for the top of the calendar
	 * 
	 * @param monthIndex
	 * @return the month and year input by the user as a string
	 */
	private String buildMonthYr(int monthIndex) {
		String m = months[monthIndex];
		return m + " " + this.year;
	}

	/**
	 * Clears the pre-exisiting calendar from the window
	 */
	public void clear() {
		// for every shape in the list of shapes, remove it from the window
		for (Shape s : shapes) {
			s.removeFromWindow();
		}
	}

}