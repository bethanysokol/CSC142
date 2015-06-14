import uwcse.graphics.*;

import java.util.*;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JOptionPane;

public class Fence implements Collidable{

	private ArrayList<Shape> fenceRails;
	private GWindow window;
	private int lawnWidth;
	private int fenceOpening;
	private int gardenL;
	private int gardenT;
	private int gardenR;
	private int gardenB;
	private int thickness;

	public Fence(GWindow window, int fenceOpening, int lawnWidth) {
		this.window = window;
		fenceRails = new ArrayList<Shape>(5);
		this.lawnWidth = lawnWidth;
		this.fenceOpening = fenceOpening;
		thickness = 10;
		gardenL = lawnWidth+thickness;
		gardenT = thickness;
		gardenR = window.getWindowWidth()-thickness;
		gardenB = window.getWindowHeight()-thickness;
	}

	public void draw() {
		int height = window.getWindowHeight();
		int width = window.getWindowWidth() - lawnWidth;
		

		// top
		fenceRails.add(new Rectangle(lawnWidth, 0, width, thickness,
				Color.BLACK, true));

		// right
		fenceRails.add(new Rectangle(window.getWindowWidth() - thickness, 0,
				thickness, height, Color.BLACK, true));

		// bottom
		fenceRails.add(new Rectangle(lawnWidth, window.getWindowHeight()
				- thickness, width, thickness, Color.BLACK, true));

		// left with opening
		fenceRails.add(new Rectangle(lawnWidth, 0, thickness,
				((height - this.fenceOpening) / 2), Color.BLACK, true));
		
		// right with opening
		fenceRails.add(new Rectangle(lawnWidth,
				((height + this.fenceOpening) / 2), thickness,
				((height - this.fenceOpening) / 2), Color.BLACK, true));
		for (Shape line : fenceRails) {
			window.add(line);
		}
	}
	@Override
	public boolean isCollision(Caterpillar cat) {
		Shape head = cat.getBufferedHead();
		boolean collision = false;
		for (Shape line : fenceRails) {
			collision = collision || line.intersects(head);
		}
		return collision;
	}

	public Point randomPoint(int buffer) {
		int x = ((int) (Math.random()*(gardenR-gardenL-2*buffer)))+gardenL+buffer;
		int y = ((int) (Math.random()*(gardenB-gardenT-2*buffer)))+gardenT+buffer;
		return new Point(x, y);
	}

	public Rectangle getGardenBox() {
		return new Rectangle(gardenL+1, gardenT+1, gardenR-gardenL-2, gardenB-gardenT-2);
	}

	@Override
	public void doCollideAction(Caterpillar cat) {
		cat.selectNewRandomDir();
	}

}
