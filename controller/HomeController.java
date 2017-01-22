/**
 *
 * @author Ng Chin Ann
 */

package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Controller of Home MVC.
// Handles Home View and Home Model.
public class HomeController {
	private HomeScreen homeScreen;
	
	public HomeController(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
		this.homeScreen.addListener(new NewGameListener(), new LoadListener(), new HelpListener());
	}
	
	private class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// CLOSE
			homeScreen.close();
			
			// MODEL
			Tank greenTank = new Tank();
			Tank blueTank = new Tank();
			Board board = new Board(greenTank, blueTank);
			Command command = new Command();
			AI ai = new AI();
			Game game = new Game();
			// VIEW
			GameScreen gameScreen = new GameScreen();
			// CONTROLLER
			GameController gameController = new GameController(greenTank, blueTank, board, command, ai, game, gameScreen);
			gameController.newGame();
			// RUN
			gameScreen.render();	
		}
	}
	
	private class LoadListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			LoadScreen loadScreen = new LoadScreen();
			loadScreen.render();
			Load load = new Load();
			LoadController loadController = new LoadController(loadScreen, load);
			Boolean isLoad = loadController.confirmLoad();
			if(isLoad)
			{
				// CLOSE HOMESCREEN
				homeScreen.close();
				loadController.loadGame();
			}
		}
	}
	
	private class HelpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			homeScreen.close();
			
			HelpScreen helpScreen = new HelpScreen();
			HelpController helpController = new HelpController(helpScreen);
			helpScreen.render();
		}
	}
}