import java.awt.Color;
import java.util.*;
import java.awt.Point;

import uwcse.io.*;
import uwcse.graphics.*;

import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 * 
 * @author Bethany Dubeck
 */

public class GraphicsElements {

	/** Maximum number of wedges in a pie */
	public static final int MAXIMUM_NUMBER_OF_PIE_WEDGES = 100;

	/** Maximum number of stripes of one color in the pattern of stripes */
	public static final int MAXIMUM_NUMBER_OF_STRIPES = 15;

	/** Maximum number of divisions in a Koch snow flake */
	public static final int MAXIMUM_NUMBER_OF_DIVISIONS = 5;

	public static final int CENTER_X = 200;

	public static final int CENTER_Y = 150;

	// The window is 400 pixels wide and 300 pixels high

	/**
	 * Create the view of a pie Use filled arcs. The color of each arc is
	 * random. The pie should fill the window. Store the arcs in an ArrayList
	 * and return that ArrayList. The number of pie wedges (= arcs) is given by
	 * the user (use a dialog box). If that number is less than or equal to 0 or
	 * greater than MAXIMUM_NUMBER_OF_PIE_WEDGES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again. Make sure that no gap
	 * appears in the pie when drawing it.
	 */
	public ArrayList createAPie() {
		Input in = new Input();
		int inputVal = in.readIntDialog("Number of wedges (between 1 and 100)");

		ArrayList graphicsList = new ArrayList<Arc>(inputVal);

		int wedgeSize = (int) Math.ceil(360.0 / inputVal);
		int currentAngle = 0;
		for (int i = 0; i < inputVal; i++) {
			Color c = generateRandomColor();
			graphicsList.add(new Arc(CENTER_X, CENTER_Y, 100, 100,
					currentAngle, wedgeSize, c, false));
			currentAngle += wedgeSize;

		}

		return graphicsList;
	}

	/**
	 * Create a pattern of stripes as shown in the homework instructions. Store
	 * the triangles in an ArrayList and return that ArrayList. Use two colors
	 * only to paint the triangles. The pattern should cover most of the window.
	 * The number of stripes (of one color) is given by the user (use a dialog
	 * box). If that number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_STRIPES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList createStripes() {
		Input in = new Input();
		int inputVal = in.readIntDialog("Number of stripes (between 1 and 15)");
		ArrayList graphicsList = new ArrayList(inputVal * inputVal * 2);
		Color[] colors = { generateRandomColor(), generateRandomColor() };
		int squareWidth = 400 / inputVal;
		int squareHeight = 300 / inputVal;
		for (int row = 0; row < inputVal; row++) {
			int top = row * squareHeight;
			int bottom = (row + 1) * squareHeight;
			for (int column = 0; column < inputVal; column++) {
				int left = column * squareWidth;
				int right = (column + 1) * squareWidth;
				graphicsList.add(new Triangle(left, top, right, top, right,
						bottom, colors[(row + column) % 2], true));
				graphicsList.add(new Triangle(left, top, left, bottom, right,
						bottom, colors[((row + column) + 1) % 2], true));
			}
		}

		return graphicsList;
	}

	/**
	 * Create a Koch snow flake. Use the class java.awt.Point. Store the Points
	 * in an ArrayList and return that ArrayList. Note that you can't set the
	 * color of a point. The initial color of the lines making up the snow flake
	 * is black. But, you can change the color in the method
	 * changeColorOfSnowFlake below. The snow flake should cover most of the
	 * window, and be always visible. The number of divisions of the initial
	 * triangle is given by the user (use a dialog box). If that number is less
	 * than 0 or greater than MAXIMUM_NUMBER_OF_DIVISIONS, display an error
	 * message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList createASnowFlake() {
		ArrayList graphicsList = new ArrayList();
		Input in = new Input();
		int inputVal = in
				.readIntDialog("Number of divisions (between 1 and 5)");

		int sideLength = 200;
		int x = 100;
		int y = 75;
		graphicsList.add(new Point(x, y));
		graphicsList.add(new Point((sideLength + x), y));
		graphicsList.add(new Point(((sideLength / 2) + x),
				(int) (y + sideLength * Math.sin(Math.PI / 3))));
		for (int division = 1; division <= inputVal; division++) {

			ArrayList original = (ArrayList) graphicsList.clone();
			for (int i = 0; i < original.size(); i++) {
				Point p = (Point) original.get(i);
				int nextIndex = i + 1;
				if (i == original.size() - 1) {
					nextIndex = 0;
				}
				Point q = (Point) original.get(nextIndex);
				Point a = new Point((int) (p.x + (q.x - p.x) / 3.0),
						(int) (p.y + (q.y - p.y) / 3.0));
				Point c = new Point((int) (p.x + 2 * (q.x - p.x) / 3.0),
						(int) (p.y + 2 * (q.y - p.y) / 3.0));
				Point b = new Point();
				b.x = (int) (a.x + (c.x - a.x) * Math.cos(Math.PI / 3.0) + (c.y - a.y)
						* Math.sin(Math.PI / 3.0));
				b.y = (int) (a.y - (c.x - a.x) * Math.sin(Math.PI / 3.0) + (c.y - a.y)
						* Math.cos(Math.PI / 3.0));
				graphicsList.add(4 * i + 1, c);
				graphicsList.add(4 * i + 1, b);
				graphicsList.add(4 * i + 1, a);

			}
		}
		return graphicsList;
	}

	/**
	 * Rotate the colors in the pie, in a clockwise direction. Precondition:
	 * graphicsList describes a pie Return the updated ArrayList
	 */
	public ArrayList rotateColorsInPie(ArrayList graphicsList) {
		ArrayList<Arc> arcList = graphicsList;

		Color previous = null;
		for (int i = arcList.size() - 1; i >= 0; i--) {
			Color next = arcList.get(i).getColor();
			if (previous == null) {

				previous = next;
				continue;
			}
			arcList.get(i).setColor(previous);
			previous = next;
		}
		arcList.get(arcList.size() - 1).setColor(previous);

		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the pattern of stripes Precondition: graphicsList
	 * describes a pattern of stripes Return the updated ArrayList
	 */
	public ArrayList flipColorsInStripes(ArrayList graphicsList) {
		ArrayList<Triangle> triList = graphicsList;

		for (int i = 0; i < triList.size() - 1; i += 2) {
			Color top = triList.get(i).getColor();
			Color bottom = triList.get(i + 1).getColor();
			triList.get(i).setColor(bottom);
			triList.get(i + 1).setColor(top);

		}

		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake (select a new random color) Use
	 * the Random class (in java.util) to select the new random color. The color
	 * that you create and return in this method will automatically be assigned
	 * to the snow flake.
	 */
	public Color changeColorOfSnowFlake() {
		return generateRandomColor();
	}

	public Color generateRandomColor() {
		// Generates the color randomly
		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		return new Color(red, green, blue);
	}
}
