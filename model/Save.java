/**
 *
 * @author Low Pau Ek
 */

package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Model of Save MVC.
// Save number of tries, AI's moves and player's moves to a file.
public class Save{
	private int numOfTries;
	private String[] aiMoves, playerMoves;
	
	public Save(int numOfTries, String[] aiMoves, String[] playerMoves) {
		this.numOfTries = numOfTries;
		this.aiMoves = aiMoves;
		this.playerMoves = playerMoves;
	}
		
	public void writeFile(String filename) {
		try {
			File fileToSave = new File(filename);
			FileWriter fileWriter = new FileWriter(fileToSave);
			
			fileWriter.write(numOfTries + "\n");	
							
			for (int i = 0; i < 18; i++) 
				fileWriter.write(aiMoves[i]+ " ");
			fileWriter.write("\n");
							
			for(int i = 0; i < 18; i++) 
				fileWriter.write(playerMoves[i]+ " ");
			fileWriter.write("\n");
		
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e) { e.printStackTrace(); }
	}
}