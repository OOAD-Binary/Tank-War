/**
 *
 * @author Low Pau Ek
 */

package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Model of Load MVC.
// Load number of tries, AI's moves and player's moves from a file.
public class Load{

	private File fileToOpen;
	private int numOfAttempt;
	private String[] ai_Moves;
	private String[] user_Moves;
	
	public void readFile(String filename) {
		fileToOpen = new File(filename);
		
		try {
			Scanner reader = new Scanner(fileToOpen);
			
			numOfAttempt = reader.nextInt();
						
			ai_Moves = new String[18];
			for(int i = 0; i < 18; i++)
				ai_Moves[i] = reader.next();
						
			user_Moves = new String[18];
			for(int i = 0; i < 18; i++)
				user_Moves[i] = reader.next();
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	public int getNumOfAttempt() {	return numOfAttempt; }
	public String[] getAiMoves() { return ai_Moves; }
	public String[] getUserMoves() { return user_Moves; }
}


