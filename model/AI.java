/**
 *
 * @author Khor Kia Kin
 */

package model;

import java.util.Random;

// Model of Game MVC.
// Generate moves for computer.
public class AI {
	
	private String[] moves;
	
	public AI() { moves = new String[Command.MAX_MOVES]; }
	
	public AI(String[] moves) { this.moves = moves; }
	
	public String[] getMoves() { return moves; }
	
	public void generateMoves(){
		final String[] possibleMoves = {"move_up", "move_down", "move_left", "move_right", "shoot_up", "shoot_down", "shoot_left", "shoot_right"};
		Random rand = new Random();
		for (int i = 0, luck; i < Command.MAX_MOVES; i++){
			luck = rand.nextInt(possibleMoves.length);
			moves[i] = possibleMoves[luck];
		}
	}
}