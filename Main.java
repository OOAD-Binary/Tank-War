/**
 *
 * @author Khor Kia Kin
 */

import view.HomeScreen;
import controller.HomeController;

// Entry point to run the game.
public class Main {
	public static void main(String args[]) {
		// VIEW
		HomeScreen homeScreen = new HomeScreen();
		// CONTROLLER
		HomeController homeController = new HomeController(homeScreen);
		// RUN
		homeScreen.render();
	}
}