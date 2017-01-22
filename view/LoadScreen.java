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

// View of Load MVC.
// Display load screen.
public class LoadScreen implements Screen {

	private JFileChooser loadChooser;
	private File defaultDir;
	private int isLoad;	//loadDialog
	
	public LoadScreen(){		
		defaultDir = new File("save");
		FileSystemView fsv = new SingleRootFileSystemView(defaultDir);
		loadChooser = new JFileChooser(fsv);
		
		//disable buttons
		disableButton(loadChooser, "FileChooser.upFolderIcon");
		disableButton(loadChooser, "FileChooser.homeFolderIcon");
		disableButton(loadChooser, "FileChooser.newFolderIcon");
		
		//Only allow to choose text file 
		loadChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		loadChooser.setFileFilter(filter);
		loadChooser.setAcceptAllFileFilterUsed(false);		//only allow to save in text format
	}
	
	@Override
	public void render() {
		isLoad = loadChooser.showOpenDialog(null);
	}
	
	@Override
	public void close() {};

	
	public String getFileName()
	{
		return loadChooser.getSelectedFile().getName();
	}
	
	public int getLoadConfirm()
	{
		return isLoad;
	}
	
	private void disableButton(Container c, String btn) 
	{
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) 
		{
			Component comp = c.getComponent(i);
			if (comp instanceof JButton) 
			{
				JButton b = (JButton) comp;
				Icon icon = b.getIcon();           
				if (icon != null  && icon == UIManager.getIcon(btn) )
				{
					b.setEnabled(false);
				} 
			} 
			else if (comp instanceof Container) 
			{
				disableButton((Container) comp, btn);
			}
        }        
    }

}