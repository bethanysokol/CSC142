import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

public class PsychedelicCabbage extends Cabbage {

	private Oval middleHead;
	private Oval centerHead;

	public PsychedelicCabbage(GWindow window, Point center) {
		super(window, center);

		super.makeHead(Color.WHITE);
	}

	@Override
	protected void draw() {
		super.draw();
		int headSize = CABBAGE_RADIUS / 2;
		middleHead = new Oval(this.center.x - headSize / 2, this.center.y
				- headSize / 2, headSize, headSize, Color.YELLOW, true);
		headSize = CABBAGE_RADIUS / 4;
		centerHead = new Oval(this.center.x - headSize / 2, this.center.y
				- headSize/2, headSize, headSize, Color.BLUE, true);
		window.add(middleHead);
		window.add(centerHead);
	}

	@Override
	public void isEatenBy(Caterpillar cp) {
		window.remove(centerHead);
		window.remove(middleHead);
		window.remove(head);
		cp.psych();
	}

}
