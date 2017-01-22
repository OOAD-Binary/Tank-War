/**
 *
 * @author Low Pau Ek
 */

package model;

import java.io.File;

// Model of Save MVC.
// Utility functions for file.
public class FileUtil {
	public static String checkExtension(String filename){
		if(!filename.endsWith(".txt"))
			filename = filename + ".txt"; 
		return filename;
	}
	
	public static Boolean isFileExist (String filename) { return new File(filename).exists(); }
}