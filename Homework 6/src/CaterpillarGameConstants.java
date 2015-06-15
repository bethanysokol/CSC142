/**
 * Interface that lists useful constants for the caterpillar game
 */
public interface CaterpillarGameConstants {

	/**
	 * Northern movement of the caterpillar
	 */
	public static final int NORTH = 1;

	/**
	 * Eastward movement of the caterpillar
	 */
	public static final int EAST = 2;

	/**
	 * Westward movement of the caterpillar
	 */
	public static final int WEST = 3;

	/**
	 * Southern movement of the caterpillar
	 */
	public static final int SOUTH = 4;

	/**
	 * Distance covered by the caterpillar in one move
	 */
	public static final int STEP = 10;

	/**
	 * Number of body elements added to the caterpillar when it grows after
	 * eating a good or psychadelic cabbage
	 */
	public static final int GROWTH_SPURT = 5;

	/**
	 * Thickness of the caterpillar
	 */
	public static final int CATERPILLAR_WIDTH = 6;

	/**
	 * Number of good cabbages
	 */
	public static final int N_GOOD_CABBAGES = 10;

	/**
	 * Number of bad cabbages
	 */
	public static final int N_BAD_CABBAGES = 10;

	/**
	 * Number of psychedelic cabbages
	 */
	public static final int N_PSYCHEDELIC_CABBAGES = 4;

	/**
	 * Radius of a cabbage head
	 */
	public static final int CABBAGE_RADIUS = 20;

	/**
	 * Size of the graphics window (height)
	 */
	public static final int WINDOW_HEIGHT = 500;

	/**
	 * Size of the graphics window (width)
	 */
	public static final int WINDOW_WIDTH = 500;

	/**
	 * Period of the animation (in ms)
	 */
	public static final int ANIMATION_PERIOD = 100;
}