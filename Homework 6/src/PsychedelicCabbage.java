import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

/**
 * Psychedelic cabbages that make the caterpillar change colors and grow upon
 * being eaten.
 *
 */
public class PsychedelicCabbage extends Cabbage {

	/**
	 * The second (middle) tier of the cabbage head
	 */
	private Oval middleHead;
	/**
	 * The third (center) tier of the cabbage head
	 */
	private Oval centerHead;

	/**
	 * @param window
	 *            the window the cabbages are drawn in
	 * @param center
	 *            the center point of the cabbage
	 */
	public PsychedelicCabbage(GWindow window, Point center) {
		super(window, center);
		// the first (outer) oval of the cabbage head
		super.makeHead(Color.MAGENTA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Cabbage#draw()
	 */
	@Override
	protected void draw() {
		super.draw();
		// decreasing the size of the next oval
		int headSize = CABBAGE_RADIUS / 2;
		middleHead = new Oval(this.center.x - headSize / 2, this.center.y
				- headSize / 2, headSize, headSize, Color.WHITE, true);
		// further decreasing the size of the next oval
		headSize /= 2;
		centerHead = new Oval(this.center.x - headSize / 2, this.center.y
				- headSize / 2, headSize, headSize, Color.BLACK, true);
		// add the ovals to the window
		window.add(middleHead);
		window.add(centerHead);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Cabbage#isEatenBy(Caterpillar)
	 */
	@Override
	public void isEatenBy(Caterpillar cp) {
		// remove the heads from the window
		window.remove(centerHead);
		window.remove(middleHead);
		window.remove(head);
		// make the caterpillar change colors
		cp.psych();
		// make the caterpillar grow
		cp.grow();
	}

}
