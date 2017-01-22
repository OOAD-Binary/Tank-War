/**
 *
 * @author Khor Kia Kin, Ng Chin Ann
 */

package model;

import java.awt.Point;

// Model of Game MVC.
// Update tank moves on board.
public class Board {	
	public static final int BOARD_SIZE = 10;
	private Tile board[][];
	private Tank greenTank;
	private Tank blueTank;
	private Boolean simulation;
	
	public Board(Tank greenTank, Tank blueTank) {
		board = new Tile[BOARD_SIZE][BOARD_SIZE];
		this.greenTank = greenTank;
		this.blueTank = blueTank;
	}
	
	public Tile[][] getBoard() { return board; }
	public Tile getBoardGrid(int row, int col){ return board[row][col]; }
	public Boolean isSimulation() { return simulation; }
	
	public void setBoard(Tile[][] board){ this.board = board; }
	public void setSimulation(Boolean simulation) { this.simulation = simulation; }

	public void resetBoard() {
		for (int row = 0; row < BOARD_SIZE; row++)
			for (int col = 0; col < BOARD_SIZE; col++)
				board[row][col] = Tile.EMPTY;
		
		board[9][9] = Tile.GREEN;
		greenTank.setAlive(true);
		greenTank.setPosition(9, 9);
		greenTank.setDirection("up");
		
		if (!isSimulation()){
			board[0][0] = Tile.BLUE;
			blueTank.setAlive(true);
			blueTank.setPosition(0, 0);
			blueTank.setDirection("right");
		}
	}
	
	private Boolean inBounds(int row, int col) {
		return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
	}
	
	public void eraseBullet() {
		for (int row = 0; row < BOARD_SIZE; row++)
			for (int col = 0; col < BOARD_SIZE; col++)
				if (board[row][col] == Tile.BULLET)
					board[row][col] = Tile.EMPTY;
	}
	
	private void move(int turn,  Point tankPosition, int stepRow, int stepCol) {
		// Move to blank cell
		if (board[tankPosition.x + stepRow][tankPosition.y + stepCol] == Tile.EMPTY) {
			board[tankPosition.x][tankPosition.y] = Tile.EMPTY;
			board[tankPosition.x + stepRow][tankPosition.y + stepCol] = (turn == 0) ? Tile.GREEN : Tile.BLUE;
			tankPosition.setLocation(tankPosition.x + stepRow, tankPosition.y + stepCol);
		}
		// Clash
		else if (board[tankPosition.x + stepRow][tankPosition.y + stepCol] == Tile.GREEN || 
					board[tankPosition.x + stepRow][tankPosition.y + stepCol] == Tile.BLUE) {
			board[tankPosition.x][tankPosition.y] = Tile.BULLET;
			board[tankPosition.x + stepRow][tankPosition.y + stepCol] = Tile.BULLET;
			greenTank.setAlive(false);
			blueTank.setAlive(false);
		}		
	}
	
	private void shoot(int turn,  Point tankPosition, int stepRow, int stepCol) {
		if (board[tankPosition.x + stepRow][tankPosition.y + stepCol] == Tile.GREEN || 
			board[tankPosition.x + stepRow][tankPosition.y + stepCol] == Tile.BLUE) {
			if (turn == 0) blueTank.setAlive(false); 
			else if (turn == 1) greenTank.setAlive(false);
		}
		board[tankPosition.x + stepRow][tankPosition.y + stepCol] = Tile.BULLET;
	}
	
	public void updateBoard(int turn, String command) {
		// Tokenise command
		String[] parts = command.split("_");
		Boolean isMove = parts[0].equals("move") ? true : false;
		String direction = parts[1];
		
		// 
		int stepRow = 0, stepCol = 0;
		if (direction.equals("up")) stepRow--;
		else if (direction.equals("down")) stepRow++;
		else if (direction.equals("left")) stepCol--;
		else if (direction.equals("right")) stepCol++;
	
		Point tankPosition = (turn == 0) ? greenTank.getPosition() : blueTank.getPosition();
		
		if (turn == 0) greenTank.setDirection(direction);
		else if (turn == 1) blueTank.setDirection(direction);

		// Turn bullet tile in previous round to empty tile
		if (isSimulation()) eraseBullet();
		
		if(inBounds(tankPosition.x + stepRow, tankPosition.y + stepCol)) {
			if (isMove) move(turn,  tankPosition, stepRow, stepCol);
			else shoot(turn,  tankPosition, stepRow, stepCol);
		}
	}
}