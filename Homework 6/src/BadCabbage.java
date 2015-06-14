import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;


public class BadCabbage extends Cabbage {

	public BadCabbage(GWindow window, Point center) {
		super(window, center);
		super.makeHead(Color.RED);
	}


	@Override
	public void isEatenBy(Caterpillar cp) {
		throw new DeadlyCollisionException("Don't eat the poisionous cabbages!");

	}

}
