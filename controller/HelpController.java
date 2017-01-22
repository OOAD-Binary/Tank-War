/**
 *
 * @author Ng Chin Ann
 */

package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Controller of Help MVC.
// Handles Help View and Help Model.
public class HelpController {
	private HelpScreen helpScreen;
	
	public HelpController(HelpScreen helpScreen) {
		this.helpScreen = helpScreen;
		this.helpScreen.addListener(new NavigationListener(), new HomeListener());
	}
	
	private class HomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			helpScreen.close();
			// VIEW
			HomeScreen homeScreen = new HomeScreen();
			// CONTROLLER
			HomeController homeController = new HomeController(homeScreen);
			// RUN
			homeScreen.render();	
		}
	}
	
	private class NavigationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			helpScreen.setPanelVisible();
		}
	}
}