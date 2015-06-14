import java.awt.Color;

import java.awt.Point;

import uwcse.graphics.GWindow;

/**
 * Good cabbages that make the caterpillar grow upon being eaten.
 *
 * @param window
 *            the window the cabbages are drawn in
 * @param center
 *            the center point of the cabbage
 */
public class GoodCabbage extends Cabbage {

	public GoodCabbage(GWindow window, Point center) {
		super(window, center);

		super.makeHead(Color.WHITE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Cabbage#isEatenBy(Caterpillar)
	 */
	@Override
	public void isEatenBy(Caterpillar cp) {
		// remove the heads from the window
		window.remove(head);
		// make the caterpillar grow
		cp.grow();
	}

}
