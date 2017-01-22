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

// Controller of Save MVC.
// Handles Save View and Save Model.
public class SaveController{
	private SaveScreen saveScreen;
	private Save save;

	public SaveController(Save save, SaveScreen saveScreen) {
		this.saveScreen = saveScreen;
		this.save = save;
	}
	
	public void saveGame() {
		// User want to save
		if(saveScreen.isConfirmSave()){
			// Ensure file is in correct directory and has correct extension
			String filename = "save/" + saveScreen.getFilename();
			filename = FileUtil.checkExtension(filename);
			
			// The file already exists
			if(FileUtil.isFileExist(filename)) {
				// Ask whether to overwrite the file
				saveScreen.renderDialog(filename);
				if(saveScreen.isConfirmReplace()) 	
					save.writeFile(filename);
			}
			else save.writeFile(filename);
		}		
	}
}