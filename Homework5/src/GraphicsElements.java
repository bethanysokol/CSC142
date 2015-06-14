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

	/**
	 * The height of the display area
	 */
	public static final int WINDOW_HEIGHT = 400;

	/**
	 * The width of the display area
	 */
	public static final int WINDOW_WIDTH = 500;

	/**
	 * Maximum number of wedges in a pie
	 */
	public static final int MAXIMUM_NUMBER_OF_PIE_WEDGES = 100;

	/**
	 * Maximum number of stripes of one color in the pattern of stripes
	 */
	public static final int MAXIMUM_NUMBER_OF_STRIPES = 15;

	/**
	 * Maximum number of divisions in a Koch snow flake
	 */
	public static final int MAXIMUM_NUMBER_OF_DIVISIONS = 5;

	/**
	 * Center x coordinate point for the pie
	 */
	public static final int CENTER_X = 50;

	/**
	 * Center y coordinate point for the pie
	 */
	public static final int CENTER_Y = 5;

	/**
	 * Creates a pie chart of arcs between 1 and 100 in random colors.
	 * 
	 * @return the list of arcs that make up the pie
	 */
	public ArrayList<Arc> createAPie() {
		// user input dialog box
		Input in = new Input();
		int inputVal = in.readIntDialog("Number of wedges (between 1 and 100)");
		/*
		 * display an error window if the input is invalid and request for input
		 * again
		 */
		if (inputVal < 1 || inputVal > 100) {
			JOptionPane.showMessageDialog(null, "Invalid input.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			return createAPie();
		}
		ArrayList<Arc> graphicsList = new ArrayList<Arc>(inputVal);
		/*
		 * sizing the arcs by dividing the input into 360 degrees of a circle to
		 * ensure even sizing. (rounding up to ensure a full circle) this can
		 * result in slightly unevenly sized wedges.
		 */
		int wedgeSize = (int) Math.ceil(360.0 / inputVal);
		int currentAngle = 0;
		for (int i = 0; i < inputVal; i++) {
			Color c = generateRandomColor();
			graphicsList.add(new Arc(CENTER_X, CENTER_Y, 300, 300,
					currentAngle, wedgeSize, c, false));
			currentAngle += wedgeSize;

		}

		return graphicsList;
	}

	/**
	 * Creates diagonal stripes of two different, random colors between 1 and 15
	 * stripes.
	 * 
	 * @return the list of triangles used to create the stripes
	 */
	public ArrayList<Triangle> createStripes() {
		// user input dialog box
		Input in = new Input();
		int inputVal = in.readIntDialog("Number of stripes (between 1 and 15)");
		/*
		 * display an error window if the input is invalid and request for input
		 * again
		 */
		if (inputVal < 1 || inputVal > 15) {
			JOptionPane.showMessageDialog(null, "Invalid input.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			return createStripes();

		}

		/*
		 * input value squared to create enough squares in the grid, and doubled
		 * for the triangles.
		 */
		ArrayList<Triangle> graphicsList = new ArrayList<Triangle>(inputVal
				* inputVal * 2);
		Color[] colors = { generateRandomColor(), generateRandomColor() };
		// total width of the stripe image
		int squareWidth = WINDOW_WIDTH / inputVal;
		// total height of the stripe image
		int squareHeight = WINDOW_HEIGHT / inputVal;

		// iterate over every column in every row (add 2 triangles to each)
		for (int row = 0; row < inputVal; row++) {
			// top and bottom are Y values
			int top = row * squareHeight;
			int bottom = (row + 1) * squareHeight;
			for (int column = 0; column < inputVal; column++) {
				// right and left are X values
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
	 * Creates a Koch snow flake between 1 and 5 divisions, of varying colors.
	 * 
	 * @return the list of points used to create the snow flake
	 */
	public ArrayList<Point> createASnowFlake() {
		// user input dialog box
		ArrayList<Point> graphicsList = new ArrayList<Point>();
		Input in = new Input();
		int inputVal = in
				.readIntDialog("Number of divisions (between 1 and 5)");
		/*
		 * display an error window if the input is invalid and request for input
		 * again
		 */
		if (inputVal < 1 || inputVal > 5) {
			JOptionPane.showMessageDialog(null, "Invalid input.", "ERROR",
					JOptionPane.WARNING_MESSAGE);
			return createASnowFlake();
		}
		// length of the original triangle's sides
		int sideLength = 260;
		// starting x coordinate
		int x = 75;
		// starting y coordinate
		int y = 80;
		// original triangle points
		graphicsList.add(new Point(x, y));
		graphicsList.add(new Point((sideLength + x), y));
		graphicsList.add(new Point(((sideLength / 2) + x),
				(int) (y + sideLength * Math.sin(Math.PI / 3))));

		for (int division = 1; division <= inputVal; division++) {

			@SuppressWarnings("unchecked")
			ArrayList<Point> original = (ArrayList<Point>) graphicsList.clone();
			for (int i = 0; i < original.size(); i++) {
				Point p = (Point) original.get(i);
				int nextIndex = i + 1;
				if (i == original.size() - 1) {
					nextIndex = 0;
				}
				// copied and pasted from instructor's directions
				// points of the triangle after creating the divisions and
				// breaks
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
	 * Rotate the colors in the pie, in a clockwise direction.
	 * 
	 * @param graphicsList
	 *            is the list of arcs that make up the pie.
	 * @return the list of arcs used to rotate the colors
	 */
	public ArrayList<Arc> rotateColorsInPie(ArrayList<Arc> graphicsList) {
		ArrayList<Arc> arcList = graphicsList;

		Color previous = null;
		// going backwards from the list of arcs in order to go clockwise
		for (int i = arcList.size() - 1; i >= 0; i--) {
			Color next = arcList.get(i).getColor();
			// skip first because no previous color to set
			if (previous == null) {

				previous = next;
				continue;
			}

			// set current color to the previous (clockwise) color
			arcList.get(i).setColor(previous);
			previous = next;
		}
		arcList.get(arcList.size() - 1).setColor(previous);

		return graphicsList;
	}

	/**
	 * Flips the 2 colors of the pattern of stripes
	 * 
	 * @return the list of triangles used to flip the colors
	 */
	public ArrayList<Triangle> flipColorsInStripes(
			ArrayList<Triangle> graphicsList) {
		ArrayList<Triangle> triList = graphicsList;
		// Iterate by two because a square is made up of two triangles
		for (int i = 0; i < triList.size() - 1; i += 2) {
			Color top = triList.get(i).getColor();
			Color bottom = triList.get(i + 1).getColor();
			// alternate by flipping each square and moving on to the next
			// square
			triList.get(i).setColor(bottom);
			triList.get(i + 1).setColor(top);

		}

		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake.
	 * 
	 * @return the random color generated
	 */
	public Color changeColorOfSnowFlake() {
		return generateRandomColor();
	}

	/**
	 * Generates a random color to use in the pie, stripes and snow flake.
	 * 
	 * @return the random color generated
	 */
	public Color generateRandomColor() {
		// Generates the color randomly
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		return new Color(red, green, blue);
	}
}
