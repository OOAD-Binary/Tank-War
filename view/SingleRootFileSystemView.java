/**
 *
 * @reference https://tips4java.wordpress.com/2009/01/28/single-root-file-chooser/
 */

package view;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;

// View of Save/Load MVC.
// Ensure user cannot navigate to other folders.
public class SingleRootFileSystemView extends FileSystemView {
	private static File[] roots;
	
	public SingleRootFileSystemView(File root) { this.roots = new File[] {root}; }

	@Override
	public File createNewFolder(File containingDir) { return null; }	   
	@Override
	public File getDefaultDirectory() { return roots[0]; }
	@Override
	public File[] getRoots() { return roots; }
}