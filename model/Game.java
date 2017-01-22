/**
 *
 * @author Khor Kia Kin
 */

package model;

// Model of Game MVC.
// Check game condition and updates number of tries.
public class Game {
	private GameState state;
	public static final int MAX_TRIES = 5;
	private int tries;
	
	public Game() {}
	
	public void initialise() {
		state = GameState.RUNNING;
		tries = 0; 
	}
		
	public GameState getState() { return state; }
	public void checkGame(Boolean greenAlive, Boolean blueAlive, int moveCounter) {
		if (greenAlive && !blueAlive)
			state = GameState.WIN;
		else if (!greenAlive && blueAlive)
			state = GameState.LOSE;
		else if (!greenAlive && !blueAlive)
			state = GameState.DOUBLEKILL;
		else if (greenAlive && blueAlive && moveCounter == Command.MAX_MOVES)
			state = GameState.DRAW;
		else
			state = GameState.RUNNING;
	}
	
	public int getTries() { return tries; }
	public void setTries(int tries) { this.tries = tries ; } 	//for load 
	public Boolean reachMaxTries() { return tries == MAX_TRIES; }
	public void incrementTries() { if (tries < MAX_TRIES) tries++; }
}