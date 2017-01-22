/**
 *
 * @author Khor Kia Kin, Ng Chin Ann
 */

package model;

// Model of Game MVC.
// User's moves.
public class Command {
	public static final int MAX_MOVES = 18;
	private int counter;
	private String[] moves;
	
	public Command() {}
	
	public void initialise() {
		counter = 0;
		moves = new String[MAX_MOVES];
	}
	
	public int getCounter() { return counter; }
	public Boolean reachMaxMoves() { return counter == MAX_MOVES - 1; }
	public String getMove(int counter){ return moves[counter]; }
	public String[] getMoves() { return moves; }
	
	public void setMove(int counter, String move) { moves[counter] = move; }
	public void incrementCounter() { if (counter < MAX_MOVES) counter++; }
	public void decrementCounter() { if (counter > 0) counter--; }
}