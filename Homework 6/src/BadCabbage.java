import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;

/**
 * Bad cabbages that make the caterpillar die upon being eaten and end the game.
 *
 */
public class BadCabbage extends Cabbage {

	/**
	 * @param window
	 *            the window the cabbages are drawn in
	 * @param center
	 *            the center point of the cabbage
	 */
	public BadCabbage(GWindow window, Point center) {
		super(window, center);
		super.makeHead(Color.RED);
	}

	/* (non-Javadoc)
	 * @see Cabbage#isEatenBy(Caterpillar)
	 */
	@Override
	public void isEatenBy(Caterpillar cp) {
		// when eaten, open a pop up window and end the game
		throw new DeadlyCollisionException("Don't eat the poisionous cabbages!");

	}

}
