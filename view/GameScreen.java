/**
 *
 * @author Khor Kia Kin, Ng Chin Ann
 */

package view;

import model.Board;
import model.Command;
import model.Tile;
import model.GameState;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

// View of Game MVC.
// Display game - board, tank, command, etc.
public class GameScreen implements Screen {
	private Window window;
	private AssetManager assets;
	private JPanel gamePanel;

	// START: BOARD PANEL
	private JPanel boardPanel;
	private JLabel[][] boardLbl;
	// END: BOARD PANEL
	
	// START: COMMAND PANEL
	private JPanel commandPanel;
	private JLabel controlsLbl;
	private JLabel triesLbl;
	private JLabel remainLbl;
	private JPanel movesPanel; 
	private JButton[] movesBtn;
	// END: COMMAND PANEL
	
	// START: BUTTON PANEL
	private JPanel buttonPanel;
	private JButton homeBtn;
	//private JButton saveBtn;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveItem;
	private JButton playBtn;
	// END: BUTTON PANEL
	
	public GameScreen() {
		window = Window.getInstance();
		assets = AssetManager.getInstance();

		// START: BOARD PANEL
		boardPanel = new JPanel(new GridLayout(10, 10));
		
		boardLbl = new JLabel[Board.BOARD_SIZE][Board.BOARD_SIZE];
		for (int row = 0; row < Board.BOARD_SIZE; row++){
			for (int col = 0; col < Board.BOARD_SIZE; col++){
				boardLbl[row][col] = new JLabel();
				boardPanel.add(boardLbl[row][col]);
			}
		}
		// END: BOARD PANEL
		
		// START: COMMAND PANEL
		controlsLbl = new JLabel("CONTROLS:   - MOVE: ARROW KEYS   - SHOOT: W A S D   - UNDO: SPACEBAR");
		triesLbl = new JLabel();
		remainLbl = new JLabel();
		
		movesPanel = new JPanel(new GridLayout(1,18));
		movesBtn = new JButton[18];
		
		movesBtn[0] = new JButton();
		movesBtn[0].setPreferredSize(new Dimension(37, 37));
		movesPanel.add(movesBtn[0]);
		for (int i = 1; i < Command.MAX_MOVES; i++) {
			movesBtn[i] = new JButton();
			movesBtn[i].setPreferredSize(new Dimension(37, 37));	
			movesPanel.add(movesBtn[i]);
		}
		
		commandPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0;
		commandPanel.add(controlsLbl, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		commandPanel.add(triesLbl, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		commandPanel.add(remainLbl, gbc);
		gbc.gridx = 0; gbc.gridy = 3;
		commandPanel.add(movesPanel, gbc);
		// END: COMMAND PANEL
		
		// START: BUTTON PANEL
		homeBtn = new JButton();
		homeBtn.setIcon(assets.getButton("home"));
		homeBtn.setPreferredSize(new Dimension(75, 75));	
		playBtn = new JButton();
		playBtn.setIcon(assets.getButton("start_game"));
		playBtn.setPreferredSize(new Dimension(150, 35));			
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(homeBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(400, 0)));
		buttonPanel.add(playBtn);
		// END: BUTTON PANEL
		
		//START: MenuBar
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);	
		//END: MenuBar
		
		// START: GAME PANEL
		gamePanel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0;
		gamePanel.add(menuBar, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		gamePanel.add(boardPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		gamePanel.add(commandPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		gamePanel.add(buttonPanel, gbc);
		// END: GAME PANEL
	}
		
	public void focusCommandPanel() {
		// Focus on command panel so key listener could work
		commandPanel.requestFocus();
	}
	
	public void focusBoardPanel() {
		// Focus on command panel so key listener could work
		boardPanel.requestFocus();
	}
	
	@Override
	public void render() {
		window.addPanel(gamePanel);
		focusCommandPanel();
	}
	
	@Override
	public void close() {
		if (gamePanel != null) 
			window.removePanel(gamePanel);
			gamePanel = null;
	}
	
	public Boolean isClose() {
		return gamePanel == null;
	}
	
	public void addListener(KeyListener listenForCommand, ActionListener listenForHome, ActionListener listenForSave, ActionListener listenForPlay) {
		commandPanel.addKeyListener(listenForCommand);
		homeBtn.addActionListener(listenForHome);
		saveItem.addActionListener(listenForSave);		
		playBtn.addActionListener(listenForPlay);
	}
	
	public void setButtonState(Boolean state) {
		for (int i = 0; i < Command.MAX_MOVES; i++)
			movesBtn[i].setVisible(state);
		playBtn.setEnabled(state);
		saveItem.setEnabled(state);
	}
	
	public void renderBoard(Tile[][] board, String greenTankDirection, String blueTankDirection) {
		for (int row = 0; row < Board.BOARD_SIZE; row++) 
			for (int col = 0; col < Board.BOARD_SIZE; col++) {
				if (board[row][col] == Tile.EMPTY)
					boardLbl[row][col].setIcon(assets.getSprite("empty"));
				else if (board[row][col] == Tile.BULLET) 
					boardLbl[row][col].setIcon(assets.getSprite("bullet"));
				else if (board[row][col] == Tile.GREEN) 
					boardLbl[row][col].setIcon(assets.getSprite("green_" + greenTankDirection));
				else if (board[row][col] == Tile.BLUE) 
					boardLbl[row][col].setIcon(assets.getSprite("blue_" + blueTankDirection));
			}
	}
	
	public void renderTries(int tries) {
		triesLbl.setText("Number of tries: " + tries);
	}
	
	public void renderMovesRemain(int remain) {
		remainLbl.setText("Moves remaining: " + remain);
	}
	
	public void renderCommand(int counter, String name) {
		movesBtn[counter].setIcon(assets.getButton(name));
	}
	
	public void renderNewGame() {
		renderTries(0);
		renderMovesRemain(Command.MAX_MOVES);
		renderCommand(0, "next_move");
		for (int i = 1; i < Command.MAX_MOVES; i++) 
			renderCommand(i, "blank");
	}
	
	public void renderLoadGame(int numOfTries, String[] playerMoves) {
		renderTries(numOfTries);
		int counter = 0;
		for (int i = 0; i < Command.MAX_MOVES; i++) {			
			counter++;
			if (playerMoves[i].equals("move_up"))
				renderCommand(i, "up"); 
			else if (playerMoves[i].equals( "move_down"))
				renderCommand(i, "down"); 
			else if (playerMoves[i].equals( "move_left"))
				renderCommand(i, "left"); 
			else if (playerMoves[i].equals("move_right"))
				renderCommand(i, "right"); 
			else if (playerMoves[i].equals("shoot_up"))
				renderCommand(i, "shoot_up");
			else if (playerMoves[i].equals("shoot_down"))
				renderCommand(i, "shoot_down"); 
			else if (playerMoves[i].equals("shoot_left"))
				renderCommand(i, "shoot_left"); 
			else if (playerMoves[i].equals("shoot_right"))
				renderCommand(i, "shoot_right"); 
			else if (playerMoves[i].equals("null")) {
				renderCommand(i, "blank");
				counter--;
			}
		}
		if (playerMoves[17].equals("null"))
			renderMovesRemain(Command.MAX_MOVES - counter);
		else
			renderMovesRemain(0);
	}

	public void renderGameEnd(GameState state, int numOfTries) {
		if (state == GameState.DRAW)
			JOptionPane.showMessageDialog(null, "It's a draw!");	
		else if (state == GameState.WIN)
			JOptionPane.showMessageDialog(null, "Congrats! You have won after " + numOfTries + " tries!");	
		else if (state == GameState.LOSE)
			JOptionPane.showMessageDialog(null, "You have lost to the AI!");	
		else if (state == GameState.DOUBLEKILL)
			JOptionPane.showMessageDialog(null, "Both of you collide with each other!");	
	}
}