/**
 *
 * @author Ng Chin Ann
 */

package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.Random;

// View of Home MVC.
// Display main screen.
public class HomeScreen implements Screen {
	private Window window;
	private JPanel homePanel;
	private AssetManager assets;
	
	// START: TITLE PANEL
	private JPanel titlePanel;
	private JLabel titleLbl;
	private JLabel simulateLbl;
	// END: TITLE PANEL
	
	// START: BUTTON PANEL
	private JPanel buttonPanel;
	private JButton newGameBtn;
	private JButton loadGameBtn;
	private JButton helpBtn;
	// END: BUTTON PANEL
	
	public HomeScreen() {
		window = Window.getInstance();
		assets = AssetManager.getInstance();
		
		// START: TITLE PANEL
		titleLbl = new JLabel("Tank Wars");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 90));
		
		Random rand = new Random();
		int luck = rand.nextInt(3);
		
		simulateLbl = new JLabel();
		if (luck == 0) simulateLbl.setIcon(assets.getMainScreenGif("human_win"));
		else if (luck == 1) simulateLbl.setIcon(assets.getMainScreenGif("enemy_win"));
		else if (luck == 2) simulateLbl.setIcon(assets.getMainScreenGif("double_kill"));
		
		titlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1; gbc.gridy = 0;
		titlePanel.add(titleLbl, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		titlePanel.add(simulateLbl, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		titlePanel.add(Box.createRigidArea(new Dimension(0,50)), gbc);
		// END: TITLE PANEL
		
		// START: BUTTON PANEL
		newGameBtn = new JButton("NEW GAME");
		newGameBtn.setIcon(assets.getButton("main_button"));
		newGameBtn.setPreferredSize(new Dimension(153, 53));
		newGameBtn.setHorizontalTextPosition(JButton.CENTER);
		newGameBtn.setVerticalTextPosition(JButton.CENTER);
		
		loadGameBtn = new JButton("LOAD GAME");
		loadGameBtn.setIcon(assets.getButton("main_button"));
		loadGameBtn.setPreferredSize(new Dimension(153, 53));
		loadGameBtn.setHorizontalTextPosition(JButton.CENTER);
		loadGameBtn.setVerticalTextPosition(JButton.CENTER);
		
		helpBtn = new JButton("HELP");
		helpBtn.setIcon(assets.getButton("main_button"));
		helpBtn.setPreferredSize(new Dimension(153, 53));
		helpBtn.setHorizontalTextPosition(JButton.CENTER);
		helpBtn.setVerticalTextPosition(JButton.CENTER);
		
		buttonPanel = new JPanel(new BorderLayout(10, 10));
		buttonPanel.add(newGameBtn, BorderLayout.NORTH);
		buttonPanel.add(loadGameBtn, BorderLayout.CENTER);
		buttonPanel.add(helpBtn, BorderLayout.SOUTH);
		// END: BUTTON PANEL
		
		// START: HOME PANEL
		homePanel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 1; gbc.gridy = 0;
		homePanel.add(titlePanel, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		homePanel.add(buttonPanel, gbc);
		// END: HOME PANEL
	}
	
	@Override
	public void render() {
		window.addPanel(homePanel);
	}
	
	@Override
	public void close() {
		if (homePanel != null)
			window.removePanel(homePanel);
	}

	public void addListener(ActionListener listenForNewGame, ActionListener listenForLoadGame, ActionListener listenForHelp) {
		newGameBtn.addActionListener(listenForNewGame);
		loadGameBtn.addActionListener(listenForLoadGame);
		helpBtn.addActionListener(listenForHelp);
	}
	
}