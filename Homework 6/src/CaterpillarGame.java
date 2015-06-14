import uwcse.graphics.*;

import java.util.*;
import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * A CaterpillarGame displays a garden that contains good and bad cabbages and a
 * constantly moving caterpillar. The player directs the moves of the
 * caterpillar. Every time the caterpillar eats a cabbage, the caterpillar
 * grows. The player wins when all of the good cabbages are eaten and the
 * caterpillar has left the garden. The player loses if the caterpillar eats a
 * bad cabbage or crawls over itself.
 */

public class CaterpillarGame extends GWindowEventAdapter implements
		CaterpillarGameConstants
// The class inherits from GWindowEventAdapter so that it can handle key events
// (in the method keyPressed), and timer events.
// All of the code to make this class able to handle key events and perform
// some animation is already written.
{
	// Game window
	private GWindow window;

	// The caterpillar
	private Caterpillar cp;

	// Direction of motion given by the player
	private int dirFromKeyboard;

	// Do we have a keyboard event
	private boolean isKeyboardEventNew = false;

	// The list of all the cabbages
	private ArrayList<Cabbage> cabbages;

	// is the current game over?
	private boolean gameOver;

	private String messageGameOver;

	private Fence fence;

	private LinkedList<Collidable> obstacles;

	private LinkedList<Collidable> objectives;

	/**
	 * Constructs a CaterpillarGame
	 */
	public CaterpillarGame() {
		// Create the graphics window
		window = new GWindow("Caterpillar game", WINDOW_WIDTH, WINDOW_HEIGHT);

		// Any key or timer event while the window is active is sent to this
		// CaterpillarGame
		window.addEventHandler(this);

		// Set up the game (fence, cabbages, caterpillar)
		initializeGame();

		// Display the game rules
		// ...
		JOptionPane.showMessageDialog(null,
				"Eat all of the non red cabbage heads, \n"
						+ "and exit the garden. \n"
						+ "Don't leave the window. \n" 
						+ "Don't eat the red cabbage heads. \n"
						+ "Don't crawl over yourself. \n"
						+ "To move left, press 'J' or 'A'. \n"
						+ "To move right, press 'K' or 'D'. \n"
						+ "To move up, press 'I' or 'W'. \n"
						+ "To move down, press 'M' or 'S'.",
				"Caterpillar game",

				JOptionPane.INFORMATION_MESSAGE);

		// start timer events (to do the animation)
		this.window.startTimerEvents(ANIMATION_PERIOD);
	}

	/**
	 * Initializes the game (draw the garden, garden fence, cabbages,
	 * caterpillar)
	 */
	private void initializeGame() {
		// Clear the window
		window.erase();

		this.obstacles = new LinkedList<Collidable>();
		this.objectives = new LinkedList<Collidable>();
		// New game
		gameOver = false;

		// No keyboard event yet
		isKeyboardEventNew = false;

		// Background (the garden)
		window.add(new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,
				Color.green, true));

		// make the fence
		fence = new Fence(window, 50, 100);
		fence.draw();
		obstacles.add(fence);
		// Cabbages
		// ...
		cabbages = new ArrayList<Cabbage>(N_GOOD_CABBAGES + N_BAD_CABBAGES
				+ N_PSYCHEDELIC_CABBAGES);
		for (int i = 0; i < N_GOOD_CABBAGES; i++) {
			Cabbage c = Cabbage.spawnGoodCabbage(window, fence, cabbages);
			cabbages.add(c);
			objectives.add(c);
		}
		for (int i = 0; i < N_BAD_CABBAGES; i++) {
			cabbages.add(Cabbage.spawnBadCabbage(window, fence, cabbages));
		}
		for (int i = 0; i < N_PSYCHEDELIC_CABBAGES; i++) {
			Cabbage c = Cabbage
					.spawnPsychedelicCabbage(window, fence, cabbages);
			cabbages.add(c);
			objectives.add(c);
		}
		obstacles.addAll(cabbages);
		// Initialize the elements of the ArrayList = cabbages
		// (they should not overlap and be in the garden) ....

		// Create the caterpillar
		cp = new Caterpillar(window);
		obstacles.add(cp);
	}

	/**
	 * Moves the caterpillar within the graphics window every ANIMATION_PERIOD
	 * milliseconds.
	 * 
	 * @param e
	 *            the timer event
	 */
	public void timerExpired(GWindowEvent e) {
		// Did we get a new direction from the user?
		// Use isKeyboardEventNew to take the event into account
		// only once
		if (isKeyboardEventNew) {
			isKeyboardEventNew = false;
			cp.move(dirFromKeyboard);
		} else
			cp.move();

		// Is the caterpillar eating a cabbage? Is it crawling over itself?
		// Is the game over? etc...
		// (do all of these checks in a private method)...
		checkCollisions();
		// Is the game over?
		// if (???) {
		// endTheGame();
		// }
	}

	private void checkCollisions() {
		List<Collidable> eaten = new ArrayList<Collidable>();
		for (Collidable c : obstacles) {
			try {
				if (c.isCollision(cp)) {
					c.doCollideAction(cp);
					eaten.add(c);

				}
			} catch (DeadlyCollisionException e) {
				this.messageGameOver = e.getMessage();
				endTheGame();
			}

		}
		obstacles.removeAll(eaten);
		objectives.removeAll(eaten);
		if (objectives.isEmpty() && cp.isOutsideGarden(fence)) {
			this.messageGameOver = "Congratulations, you win!";
			endTheGame();
		}

	}

	/**
	 * Moves the caterpillar according to the selection of the user i: NORTH, j:
	 * WEST, k: EAST, m: SOUTH
	 * 
	 * @param e
	 *            the keyboard event
	 */
	public void keyPressed(GWindowEvent e) {

		switch (Character.toLowerCase(e.getKey())) {
		case 'i':
		case 'w':
			dirFromKeyboard = NORTH;
			break;
		case 'j':
		case 'a':
			dirFromKeyboard = WEST;
			break;
		case 'k':
		case 'd':
			dirFromKeyboard = EAST;
			break;
		case 'm':
		case 's':
			dirFromKeyboard = SOUTH;
			break;

		default:
			return;
		}

		// new keyboard event
		isKeyboardEventNew = true;
	}

	/**
	 * The game is over. Starts a new game or ends the application
	 */
	private void endTheGame() {
		window.stopTimerEvents();
		// messageGameOver is an instance String that
		// describes the outcome of the game that just ended
		// (e.g. congratulations! you win)
		boolean again = anotherGame(messageGameOver);
		if (again) {
			initializeGame();
		} else {
			System.exit(0);
		}
	}

	/**
	 * Does the player want to play again?
	 */
	private boolean anotherGame(String s) {
		int choice = JOptionPane.showConfirmDialog(null, s
				+ "\nDo you want to play again?", "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new CaterpillarGame();
	}
}
