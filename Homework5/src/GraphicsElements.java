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
		ArrayList graphicsList = new ArrayList();

		List<Arc> wedges;
		if (wedges == null) {
			wedges = new ArrayList<Arc>(3);
		} else {
			wedges.clear();
		}
		wedges.add(createLegPair(color, height, height));
		for (Arc w : wedges) {
			this.frame.add(w);
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
		ArrayList graphicsList = new ArrayList();
		// Add your code here

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
		// Add your code here

		return graphicsList;
	}

	/**
	 * Rotate the colors in the pie, in a clockwise direction. Precondition:
	 * graphicsList describes a pie Return the updated ArrayList
	 */
	public ArrayList rotateColorsInPie(ArrayList graphicsList) {
		// Add your code here

		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the pattern of stripes Precondition: graphicsList
	 * describes a pattern of stripes Return the updated ArrayList
	 */
	public ArrayList flipColorsInStripes(ArrayList graphicsList) {
		// Add your code here

		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake (select a new random color) Use
	 * the Random class (in java.util) to select the new random color. The color
	 * that you create and return in this method will automatically be assigned
	 * to the snow flake.
	 */
	public Color changeColorOfSnowFlake() {
		// Generates the color randomly
		// Math.random() returns a random double between 0 and 1
		// 0 <= Math.random() < 1
		int red = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		Color color = new Color(red, green, blue);
		return Color.red; // CHANGE THIS
	}
}
