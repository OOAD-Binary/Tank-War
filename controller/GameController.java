/**
 *
 * @author Khor Kia Kin, Ng Chin Ann, Low Pau Ek
 */

package controller;

import model.*;
import view.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Stack;
import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.io.IOException;
import javax.swing.Action;

// Controller of Game MVC.
// Handles Game View and Game Model.
public class GameController {
	// MODEL
	private Tank greenTank;
	private Tank blueTank;
	private Board board;
	private Command command;
	private AI ai;
	private Game game;
	// VIEW
	private GameScreen gameScreen;
	
	// FOR UNDO - move stacks
	private Stack<Tile[][]> prevBoard; 
	private Stack<String> prevDirec;  
	private Stack<Point> prevPosit; 
	// FOR COMMAND
	private Boolean canEnter;
	
	public GameController(Tank greenTank, Tank blueTank, Board board, Command command, AI ai, Game game, GameScreen gameScreen) {
		this.greenTank = greenTank;
		this.blueTank = blueTank;
		this.board = board;
		this.command = command;
		this.ai = ai;
		this.game = game;
		this.gameScreen = gameScreen;
		this.gameScreen.addListener(new CommandListener(), new HomeListener(), new SaveListener(), new PlayListener());
		
	}
	
	private void updateMoveStacks() {
		Tile[][] tempBoard = new Tile[10][10];
		for (int row = 0; row < Board.BOARD_SIZE; row++)
			for (int col = 0; col < Board.BOARD_SIZE; col++)
				tempBoard[row][col] = board.getBoardGrid(row, col);
				
		prevBoard.push(tempBoard);
		prevDirec.push(greenTank.getDirection());
		prevPosit.push(new Point(greenTank.getPosition()));
	}
	
	private void undoMoveStacks() {
		greenTank.setDirection(prevDirec.peek());
		greenTank.setPosition(prevPosit.peek().x, prevPosit.peek().y);
		board.setBoard(prevBoard.peek());
		prevBoard.pop();
		prevDirec.pop();
		prevPosit.pop();
	}
	
	public void newGame() {
		// For undo
		prevBoard = new Stack<Tile[][]>();
		prevDirec = new Stack<String>();
		prevPosit = new Stack<Point>();
		
		// Setup model
		board.setSimulation(true);
		board.resetBoard();
		ai.generateMoves();
		game.initialise();
		command.initialise();
		canEnter = true;
		
		// Setup view
		gameScreen.renderNewGame();
		gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), null);
		gameScreen.focusCommandPanel();
	}
	
	public void loadGame(int numOfTries, String[] playerMoves) {
		// For undo
		prevBoard = new Stack<Tile[][]>();
		prevDirec = new Stack<String>();
		prevPosit = new Stack<Point>();
		
		// Setup model
		board.setSimulation(true);
		board.resetBoard();
		
		game.initialise();
		game.setTries(numOfTries);		//LOAD number of tries
		command.initialise();
		canEnter = true;
		Boolean notFull = false;
		int lastGrid = -1;
		for(int i = 0; i < Command.MAX_MOVES; i++) {
			if(!playerMoves[i].equals("null")) {
				command.setMove(i, playerMoves[i]);
				canEnter = false;
				updateMoveStacks();
				board.updateBoard(0, command.getMove(command.getCounter()));
				if (!command.reachMaxMoves()) {	
					command.incrementCounter();
					canEnter = true;
				}
			}
			else if(playerMoves[i].equals("null")&& notFull == false) {
				lastGrid = command.getCounter();
				notFull = true;
			}
		}
				
		// Setup view
		gameScreen.renderLoadGame(numOfTries, playerMoves);
		if (command.reachMaxMoves())
			gameScreen.renderCommand(command.getCounter(), command.getMove(command.getCounter())+"_clicked");
		if(notFull == true)
			gameScreen.renderCommand(lastGrid, "next_move");
		gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), null);
		gameScreen.focusCommandPanel();
	}
	
	private class CommandListener implements KeyListener {
		private int key;
		
		private Boolean isMove(int key){
			return key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || 
			key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			key = e.getKeyCode();

			// space key = undo
			if (key == KeyEvent.VK_SPACE){
				gameScreen.renderCommand(command.getCounter(), "blank");
				if (!(command.getCounter() == 17 && command.getMove(command.getCounter()) != null))
					command.decrementCounter();

				command.setMove(command.getCounter(), null);
				gameScreen.renderCommand(command.getCounter(), "next_move");
				
				canEnter = true;
				if (!prevBoard.empty()){
					undoMoveStacks();
					gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), null);	
				}
			}
			else if(canEnter){
				// MOVE
				if (key == KeyEvent.VK_UP){
					command.setMove(command.getCounter(), "move_up"); //update model
					gameScreen.renderCommand(command.getCounter(), "up"); //update view
				}
				else if (key == KeyEvent.VK_DOWN){
					command.setMove(command.getCounter(), "move_down");
					gameScreen.renderCommand(command.getCounter(), "down");
				}
				else if (key == KeyEvent.VK_LEFT){
					command.setMove(command.getCounter(), "move_left");
					gameScreen.renderCommand(command.getCounter(), "left");
				}
				else if (key == KeyEvent.VK_RIGHT){
					command.setMove(command.getCounter(), "move_right");
					gameScreen.renderCommand(command.getCounter(), "right");
				}
				
				// SHOOT
				if (key == KeyEvent.VK_W){
					command.setMove(command.getCounter(), "shoot_up");
					gameScreen.renderCommand(command.getCounter(), "shoot_up");
				}
				else if (key == KeyEvent.VK_S){
					command.setMove(command.getCounter(), "shoot_down");
					gameScreen.renderCommand(command.getCounter(), "shoot_down");
				}
				else if (key == KeyEvent.VK_A){
					command.setMove(command.getCounter(), "shoot_left");
					gameScreen.renderCommand(command.getCounter(), "shoot_left");
				}
				else if (key == KeyEvent.VK_D){
					command.setMove(command.getCounter(), "shoot_right");
					gameScreen.renderCommand(command.getCounter(), "shoot_right");
				}
				
				if (command.reachMaxMoves() && command.getMove(command.getCounter()) != null){
					gameScreen.renderCommand(command.getCounter(), command.getMove(command.getCounter())+"_clicked");
				}
				
				if (isMove(key)){
					canEnter = false;
						
					updateMoveStacks();
					
					board.updateBoard(0, command.getMove(command.getCounter()));
					gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), null);
					
					if (!command.reachMaxMoves()){	
						command.incrementCounter();	
						gameScreen.renderCommand(command.getCounter(), "next_move");
						canEnter = true;
					}
				}
			}
			
			if (canEnter) gameScreen.renderMovesRemain(Command.MAX_MOVES - command.getCounter());
			else gameScreen.renderMovesRemain(0);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	private class HomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// CLOSE
			gameScreen.close();
			// VIEW
			HomeScreen homeScreen = new HomeScreen();
			// CONTROLLER
			HomeController homeController = new HomeController(homeScreen);
			// RUN
			homeScreen.render();
		}
	}
	
	private class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// MODEL
			Save save = new Save(game.getTries(), ai.getMoves(), command.getMoves());
			// VIEW
			SaveScreen saveScreen = new SaveScreen();
			// CONTROLLER
			SaveController saveController = new SaveController(save, saveScreen);
			// RUN
			saveScreen.render();
			saveController.saveGame();
		}
	}
	
	private class PlayListener implements ActionListener {
		private Timer timer;
		private int moveCounter;
		private String[] playerMoves, aiMoves;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (command.getCounter() == Command.MAX_MOVES - 1 && command.getMove(command.getCounter()) != null) {
				gameScreen.focusBoardPanel();
				startGame();
				startTimer(); // will trigger runGame()		
			} else gameScreen.focusCommandPanel();
		}
		
		private void startTimer() {
			ActionListener renderListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					runGame();
				}		
			};
			int delay = 400; // in milliseconds
			timer = new Timer(delay, renderListener);
			timer.start();
		}
		
		private void stopTimer() { timer.stop(); }
		
		private void startGame() {
			// Make buttons hidden
			gameScreen.setButtonState(false);
			// Remember board of player's last move so that we can go back to it after game end
			updateMoveStacks();
			// The real deal - not a simulation
			board.setSimulation(false);
			// Render board initial state
			board.resetBoard();
			gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), blueTank.getDirection());
			// Get player moves and AI moves
			playerMoves = command.getMoves();
			aiMoves = ai.getMoves();
			// Initialise move counter
			moveCounter = 0;
		}
		
		private void runGame() {
			// Update board and move counter
			board.updateBoard(0, playerMoves[moveCounter]);
			if (blueTank.isAlive()) board.updateBoard(1, aiMoves[moveCounter]);
			moveCounter++;
			// Render board
			gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), blueTank.getDirection());
			// Erase bullet in logic board so that the bullet won't stay forever
			board.eraseBullet();
			// Check game status
			game.checkGame(greenTank.isAlive(), blueTank.isAlive(), moveCounter);
			// Game end
			if (game.getState() != GameState.RUNNING) {
				// Stop timer
				stopTimer();
				gameEnd();
			}	
		}
		
		private void gameEnd() {
			// Update and render tries
			game.incrementTries();
			gameScreen.renderTries(game.getTries());
			// Render game end message
			if (!gameScreen.isClose())
				gameScreen.renderGameEnd(game.getState(), game.getTries());
			// Set and render board of player's last move
			undoMoveStacks();
			gameScreen.renderBoard(board.getBoard(), greenTank.getDirection(), null);	
			// Focus on command panel so key listeners could work
			gameScreen.focusCommandPanel();
			// Make buttons visible
			gameScreen.setButtonState(true);
			// Back to simulation mode yo
			board.setSimulation(true);
			// Reach max number of tries or player won the game. Start new game.
			if (game.reachMaxTries() || game.getState() == GameState.WIN)
				newGame();
		}
	}
}