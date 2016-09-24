package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	
	private Position pos;
	private Image img;
	
	
	
	//*************Contractor***************//
	
	public Character() {
		img = new Image(null, "images/leonardo.jpg");
	}

	
	//*****draw the character*********
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.x, cellHeight * pos.y, cellWidth, cellHeight);
	}
	

	//****************methods for moving the character*******************//
	
	public void moveUp(){
		pos.x++;		
	}

	public void moveDown(){
		pos.x--;	
		}
	
	public void moveLeft(){
		pos.y++;	}

	public void moveRight(){
		pos.y--;		
	}
	public void moveForward() {
		pos.z++;
	}
	
	public void moveBackward() {
		pos.z--;
	}
	
	
	
// setter & getter for character position
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
}
