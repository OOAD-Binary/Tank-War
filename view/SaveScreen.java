/**
 *
 * @author Low Pau Ek
 */

package view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Container;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Icon;

// View of Save MVC.
// Display save screen.
public class SaveScreen implements Screen {
	
	private JFileChooser saveChooser;
	private int isSave;	//saveDialog
	private int isReplace;		//confirmDialog
	
	public SaveScreen(){		
		File defaultDir = new File("save");
		FileSystemView fsv = new SingleRootFileSystemView( defaultDir );
		saveChooser = new JFileChooser(fsv);
		
		//disable buttons
		disableButton(saveChooser, "FileChooser.upFolderIcon");
		disableButton(saveChooser, "FileChooser.homeFolderIcon");
		disableButton(saveChooser, "FileChooser.newFolderIcon");
		
		//Only allow to choose text file 
		saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		saveChooser.setFileFilter(filter);
		saveChooser.setAcceptAllFileFilterUsed(false);		//only allow to save in text format
	}
	
	@Override
	public void render() {
		isSave = saveChooser.showSaveDialog(null);
	}
	
	@Override
	public void close() {};

	
	public String getFilename() { return saveChooser.getSelectedFile().getName(); }
	
	public Boolean isConfirmSave() { return isSave == JFileChooser.APPROVE_OPTION; }
	
	public void renderDialog(String filename) {
		isReplace = JOptionPane.showConfirmDialog(null, filename.substring(5, filename.length())  
		+ " already exists \nDo you want to replace the file? ", "File exists", JOptionPane.YES_NO_OPTION);
	}
	
	public Boolean isConfirmReplace() { return isReplace == JOptionPane.YES_OPTION; }
	
	private void disableButton(Container c, String btn) {
        for (int i = 0, len = c.getComponentCount(); i < len; i++) {
			Component comp = c.getComponent(i);
			if (comp instanceof JButton) {
				JButton b = (JButton) comp;
				Icon icon = b.getIcon();           
				if (icon != null  && icon == UIManager.getIcon(btn))
					b.setEnabled(false);
			} 
			else if (comp instanceof Container)
				disableButton((Container) comp, btn);
        }        
    }
}