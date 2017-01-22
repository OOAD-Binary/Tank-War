/**
 *
 * @author Khor Kia Kin, Ng Chin Ann
 */

package view;

import java.util.HashMap;
import javax.swing.ImageIcon;

// View of MVC.
// Singleton class - To ensure only one instance of assets is created.
// Manage image assets.
public class AssetManager {
	private static AssetManager instance = null;
	private HashMap<String, ImageIcon> sprites;
	private HashMap<String, ImageIcon> buttons;
	private HashMap<String, ImageIcon> mainScreenGif;
	private HashMap<String, ImageIcon> help;
	
	private AssetManager() {
		sprites = new HashMap<String, ImageIcon>();
		sprites.put("empty", new ImageIcon("assets/sprite/empty.png"));
		sprites.put("bullet", new ImageIcon("assets/sprite/bullet.png"));
		sprites.put("green_up", new ImageIcon("assets/sprite/green_tank_up.png"));
		sprites.put("green_down", new ImageIcon("assets/sprite/green_tank_down.png"));
		sprites.put("green_left", new ImageIcon("assets/sprite/green_tank_left.png"));
		sprites.put("green_right", new ImageIcon("assets/sprite/green_tank_right.png"));
		sprites.put("blue_up", new ImageIcon("assets/sprite/blue_tank_up.png"));
		sprites.put("blue_down", new ImageIcon("assets/sprite/blue_tank_down.png"));
		sprites.put("blue_left", new ImageIcon("assets/sprite/blue_tank_left.png"));
		sprites.put("blue_right", new ImageIcon("assets/sprite/blue_tank_right.png"));
		
		buttons = new HashMap<String, ImageIcon>();
		buttons.put("blank", new ImageIcon("assets/button/blank.png"));
		buttons.put("next_move", new ImageIcon("assets/button/next_move.png"));
		buttons.put("up", new ImageIcon("assets/button/up.png"));
		buttons.put("down", new ImageIcon("assets/button/down.png"));
		buttons.put("left", new ImageIcon("assets/button/left.png"));
		buttons.put("right", new ImageIcon("assets/button/right.png"));
		buttons.put("move_up_clicked", new ImageIcon("assets/button/up_clicked.png"));
		buttons.put("move_down_clicked", new ImageIcon("assets/button/down_clicked.png"));
		buttons.put("move_left_clicked", new ImageIcon("assets/button/left_clicked.png"));
		buttons.put("move_right_clicked", new ImageIcon("assets/button/right_clicked.png"));
		buttons.put("shoot_up", new ImageIcon("assets/button/shoot_up.png"));
		buttons.put("shoot_down", new ImageIcon("assets/button/shoot_down.png"));
		buttons.put("shoot_left", new ImageIcon("assets/button/shoot_left.png"));
		buttons.put("shoot_right", new ImageIcon("assets/button/shoot_right.png"));
		buttons.put("shoot_up_clicked", new ImageIcon("assets/button/shoot_up_clicked.png"));
		buttons.put("shoot_down_clicked", new ImageIcon("assets/button/shoot_down_clicked.png"));
		buttons.put("shoot_left_clicked", new ImageIcon("assets/button/shoot_left_clicked.png"));
		buttons.put("shoot_right_clicked", new ImageIcon("assets/button/shoot_right_clicked.png"));	
		buttons.put("start_game", new ImageIcon("assets/button/start.png"));
		buttons.put("home", new ImageIcon("assets/button/home.png"));
		buttons.put("main_button", new ImageIcon("assets/button/main_button.png"));
		
		mainScreenGif = new HashMap<String, ImageIcon>();
		mainScreenGif.put("human_win", new ImageIcon("assets/main/human_win.gif"));
		mainScreenGif.put("enemy_win", new ImageIcon("assets/main/ai_win.gif"));
		mainScreenGif.put("double_kill", new ImageIcon("assets/main/double_kill.gif"));
		
		help = new HashMap<String, ImageIcon>();
		help.put("next", new ImageIcon("assets/help/next.png"));
		help.put("back", new ImageIcon("assets/help/back.png"));
		help.put("rules", new ImageIcon("assets/help/rules.png"));
		help.put("instructions", new ImageIcon("assets/help/instructions.png"));
	}
	
	public static AssetManager getInstance() {
		if (instance == null)
			instance = new AssetManager();
		return instance;
	}
	
	public ImageIcon getSprite(String name) { return sprites.get(name); }
	public ImageIcon getButton(String name) { return buttons.get(name); }
	public ImageIcon getMainScreenGif(String name) { return mainScreenGif.get(name); }
	public ImageIcon getHelp(String name) { return help.get(name); }
}