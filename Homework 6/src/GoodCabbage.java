import java.awt.Color;
import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

public class GoodCabbage extends Cabbage{


	public GoodCabbage(GWindow window, Point center) {
		super(window, center);

		super.makeHead(Color.WHITE);
	}


	@Override
	public void isEatenBy(Caterpillar cp) {
		window.remove(head);
		cp.grow();
	}


}
