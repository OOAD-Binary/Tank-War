/**
 *
 * @author Ng Chin Ann
 */

package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

// View of Help MVC.
// Display how to play and game rules.
public class HelpScreen implements Screen{
	private Window window;
	private JPanel helpPanel;
	private AssetManager assets;
	
	private JPanel btnPanel;
	private JLabel helpLbl;
	private JLabel instructionLbl;
	private JButton nextBtn;
	private JButton homeBtn;
	
	public HelpScreen() {
		window = Window.getInstance();
		assets = AssetManager.getInstance();
	
		nextBtn = new JButton();
		nextBtn.setIcon(assets.getHelp("next"));
		nextBtn.setPreferredSize(new Dimension(124, 47));
		
		homeBtn = new JButton();
		homeBtn.setIcon(assets.getButton("home"));
		homeBtn.setPreferredSize(new Dimension(75, 75));
		
		GridBagConstraints gbc = new GridBagConstraints();
		btnPanel = new JPanel(new GridBagLayout());
		gbc.gridx = 1; gbc.gridy = 0;
		btnPanel.add(homeBtn, gbc);
		gbc.gridx = 2; gbc.gridy = 0;
		btnPanel.add(Box.createRigidArea(new Dimension(400,0)), gbc);
		gbc.gridx = 3; gbc.gridy = 0;
		btnPanel.add(nextBtn, gbc);
		
		helpLbl = new JLabel("How to Play");
		helpLbl.setFont(new Font("Roboto", Font.BOLD, 60));
		
		instructionLbl = new JLabel();
		instructionLbl.setFont(new Font("Roboto", Font.BOLD, 60));
		instructionLbl.setIcon(assets.getHelp("instructions"));
		
		helpPanel = new JPanel(new GridBagLayout());
		gbc.gridx = 1; gbc.gridy = 0;
		helpPanel.add(helpLbl, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		helpPanel.add(Box.createRigidArea(new Dimension(0,80)), gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		helpPanel.add(instructionLbl, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		helpPanel.add(Box.createRigidArea(new Dimension(0,100)), gbc);
		gbc.gridx = 1; gbc.gridy = 4;
		helpPanel.add(btnPanel, gbc);
	}
	
	@Override
	public void render() {
		window.addPanel(helpPanel);
	}
	
	@Override
	public void close() {
		if (helpPanel != null)
			window.removePanel(helpPanel);
	}
	
	public void setPanelVisible() {
		if (nextBtn.getIcon().toString().equals("assets/help/back.png")){
			helpLbl.setText("How to Play");
			nextBtn.setIcon(assets.getHelp("next"));
			instructionLbl.setIcon(assets.getHelp("instructions"));
		}
		else if (nextBtn.getIcon().toString().equals("assets/help/next.png")){
			helpLbl.setText("Game Rules");
			nextBtn.setIcon(assets.getHelp("back"));
			instructionLbl.setIcon(assets.getHelp("rules"));
		}
	}
	
	public void addListener(ActionListener listenForPageChange, ActionListener listenForHome) {
		nextBtn.addActionListener(listenForPageChange);
		homeBtn.addActionListener(listenForHome);
	}	
}