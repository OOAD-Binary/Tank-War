/**
 *
 * @author Khor Kia Kin
 */

package model;

import java.awt.Point;

// Model of Game MVC.
// Contain information about tank's life status, position and direction.
public class Tank {
	private Boolean alive;
	private Point position;
	private String direction;
	
	public Tank() { position = new Point(); }
	
	public Boolean isAlive() { return alive; }
	public Point getPosition() { return position; }
	public String getDirection() { 	return direction; }
	
	public void setAlive(Boolean alive) { this.alive = alive; }
	public void setPosition(int row, int col) { position.setLocation(row, col); }
	public void setDirection(String direction) { this.direction = direction; }
}