package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import algorithms.mazeGenerators.Position;

public class Character {
	
	private Position pos;
	private Image img;
	
	
	//*************Contractor***************//
	
	public Character() {
		img = new Image(null, "images/leonardo2.png");
	}

	
	//*****draw the character*********
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.z, cellHeight * pos.y, cellWidth, cellHeight);
	}
	

	//****************methods for moving the character*******************//
	
	
	public void moveUp(){
		pos.x+=2;		

	}

	public void moveDown(){
		pos.x-=2;
		}
	
	public void moveLeft(){
		pos.z--;	

		
	}

	public void moveRight(){
		pos.z++;		

	}
	public void moveForward() {
		pos.y--;
		
	}
	
	public void moveBackward() {
		pos.y++;
	}
	
	
	
// setter & getter for character position
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}


	public void setImage(String string) {
		img = new Image(null, string);
		
	}
}
