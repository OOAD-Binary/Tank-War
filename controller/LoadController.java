/**
 *
 * @author Low Pau Ek
 */

package controller;

import model.*;
import view.*;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

// Controller of Load MVC.
// Handles Load View and Load Model.
public class LoadController{
	private LoadScreen loadScreen;
	private Load load;
	private String filename;
	private int isLoad;
	
	
	public LoadController(LoadScreen loadScreen, Load load)
	{
		this.loadScreen = loadScreen;
		isLoad = loadScreen.getLoadConfirm();
		this.load = load;
	}
	
	public Boolean confirmLoad(){	
		return isLoad ==JFileChooser.APPROVE_OPTION;
	}
	
	public void loadGame(){
			filename = "save/" + loadScreen.getFileName();
			filename = FileUtil.checkExtension(filename);
			load.readFile(filename);
			
			// CHANGE TO GAME SCREEN
			Tank greenTank = new Tank();
			Tank blueTank = new Tank();
			Board board = new Board(greenTank, blueTank);
			Command command = new Command();
			AI ai = new AI(load.getAiMoves());		//load the saved AI moves 
			Game game = new Game();
					
			// VIEW
			GameScreen gameScreen = new GameScreen();
			// CONTROLLER
			GameController gameController = new GameController(greenTank, blueTank, board, command, ai, game, gameScreen);
			gameController.loadGame(load.getNumOfAttempt(), load.getUserMoves());
						
			// RUN
			gameScreen.render();				
	}	
}