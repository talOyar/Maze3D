package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerators.Position;
/**
 * <h2>Character class<h2>
 * <p>  defines an image with a specific position and draws it
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 * @since 20-09-2016
 * 
 * 
 *
 */
public class Character {
	
	private Position pos;
	private Image img;
	
	
	//*************Contractor***************//
	
	public Character() {

	}
	
	/**
	 * <p>draw method
	 * <p> draws the image in the wanted position
	 */
	
	//*****draw the character*********

	public void draw(int cellWidth, int cellHeight, GC gc) {
		
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.z, cellHeight * pos.y, cellWidth, cellHeight);
	}
	

	//****************methods for moving the character*******************//
	
	/**
	 * <p>moveUp method
	 * <p>moves the image position one step up
	 */
	public void moveUp(){
		pos.x+=2;		

	}
	
	/**
	 * <p>moveDown method
	 * <p>moves the image position one step down
	 */
	public void moveDown(){
		pos.x-=2;
		}
	
	/**
	 * <p>moveLeft method
	 * <p>moves the image position one step left
	 */
	public void moveLeft(){
		pos.z--;	

		
	}
	
	/**
	 * <p>moveRight method
	 * <p>moves the image position one step right
	 */
	public void moveRight(){
		pos.z++;		

	}
	
	/**
	 * <p>moveForward method
	 * <p>moves the image position one step forward
	 */
	public void moveForward() {
		pos.y--;
		
	}
	
	/**
	 * <p>moveBackward method
	 * <p>moves the image position one stepbackward
	 */
	public void moveBackward() {
		pos.y++;
	}
	
	
	/**
	 * <p>getPos method
	 * <p>returns the character position
	 */
	
// setter & getter for character position
	
	public Position getPos() {
		return pos;
	}

	/**
	 * <p>setPos method
	 * <p>sets the character position
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}

	/**
	 * <p>setImage method
	 * <p>sets the character image
	 */
	public void setImage(String string) {
		img = new Image(null, string);
		
	}
}
