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
 * 
 * @author Bethany Dubeck
 */

public class CaterpillarGame extends GWindowEventAdapter implements
		CaterpillarGameConstants
/*
 * The class inherits from GWindowEventAdapter so that it can handle key events
 * (in the method keyPressed), and timer events.
 */
{
	/**
	 * The window the game is in
	 */
	private GWindow window;

	/**
	 * The caterpillar that moves around eating cabbages
	 */
	private Caterpillar cp;

	/**
	 * Direction of motion given by the player
	 */
	private int dirFromKeyboard;

	/**
	 * Is there a keyboard event?
	 */
	private boolean isKeyboardEventNew = false;

	/**
	 * The list of all the cabbages
	 */
	private ArrayList<Cabbage> cabbages;

	/**
	 * Is the current game over?
	 */
	private boolean gameOver;

	/**
	 * A message declaring the game is over
	 */
	private String messageGameOver;

	/**
	 * The fence around the garden play area
	 */
	private Fence fence;

	/**
	 * A list of various obstacles
	 */
	private LinkedList<Collidable> obstacles;

	/**
	 * A list of objects needed to win (cabbages to be eaten)
	 */
	private LinkedList<Collidable> objectives;

	/**
	 * Constructs a CaterpillarGame
	 */
	public CaterpillarGame() {
		// Create the graphics window
		window = new GWindow("Caterpillar game", WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setExitOnClose();
		// Any key or timer event while the window is active is sent to this
		window.addEventHandler(this);

		// Set up the game (fence, cabbages, caterpillar)
		initializeGame();

		// Display the game rules
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

		beginPlaying();
	}

	/**
	 * Starts the game
	 */
	public void beginPlaying() {
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
		List<Collidable> caterpillarAwareObstacles = (List<Collidable>) obstacles
				.clone();

		// Create the caterpillar
		cp = new Caterpillar(window, caterpillarAwareObstacles);
		obstacles.add(cp);

		// Cabbages
		cabbages = new ArrayList<Cabbage>(N_GOOD_CABBAGES + N_BAD_CABBAGES
				+ N_PSYCHEDELIC_CABBAGES);
		for (int i = 0; i < N_GOOD_CABBAGES; i++) {
			Cabbage c = Cabbage.spawnGoodCabbage(window, fence, cabbages);
			cabbages.add(c);
			objectives.add(c);
		}
		for (int i = 0; i < N_BAD_CABBAGES; i++) {
			Cabbage c = Cabbage.spawnBadCabbage(window, fence, cabbages);
			cabbages.add(c);
		}
		for (int i = 0; i < N_PSYCHEDELIC_CABBAGES; i++) {
			Cabbage c = Cabbage
					.spawnPsychedelicCabbage(window, fence, cabbages);
			cabbages.add(c);
			objectives.add(c);
		}
		obstacles.addAll(cabbages);

	}

	/**
	 * Moves the caterpillar within the graphics window every ANIMATION_PERIOD
	 * milliseconds.
	 * 
	 * @param e
	 *            the timer event
	 */
	public void timerExpired(GWindowEvent e) {
		/*
		 * Did we get a new direction from the user? Use isKeyboardEventNew to
		 * take the event into account only once
		 */
		if (isKeyboardEventNew) {
			isKeyboardEventNew = false;
			cp.move(dirFromKeyboard);
		} else
			cp.move();
		// check to see if any collisions have occurred
		checkCollisions();
	}

	/**
	 * Checks for collisions that could end the game
	 */
	private void checkCollisions() {
		// Array of cabbages that have been eaten
		List<Collidable> eaten = new ArrayList<Collidable>();
		for (Collidable c : obstacles) {
			try {
				/*
				 * if the caterpillar collides with an object, take the
				 * appropriate action
				 */
				if (c.isCollision(cp)) {
					c.doCollideAction(cp);
					// add eaten cabbages to the list
					eaten.add(c);

				}
				// if the caterpillar eats a poisonous cabbage, end the game
			} catch (DeadlyCollisionException e) {
				this.messageGameOver = e.getMessage();
				endTheGame();
			}

		}
		// reset the game
		obstacles.removeAll(eaten);
		objectives.removeAll(eaten);
		/*
		 * if all the good cabbages are gone and the caterpillar is completely
		 * out of the garden, the game ends in a win
		 */
		if (objectives.isEmpty() && cp.isOutsideGarden(fence)) {
			this.messageGameOver = "Congratulations, you win!";
			endTheGame();
		}

	}

	/**
	 * Moves the caterpillar according to the selection of the user i or w:
	 * NORTH, j or a: WEST, k or d: EAST, m or s: SOUTH (because PC gamers are
	 * more used to these keys)
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
		/*
		 * messageGameOver is an instance String that describes the outcome of
		 * the game that just ended
		 */
		boolean again = anotherGame(messageGameOver);
		if (again) {
			initializeGame();
			beginPlaying();
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
