package view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Directions;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

public class MazeDisplay extends Canvas {
	
	Maze3d maze;
	Character character;
	int [][] mazeData;
	Position startPosition ;
	Position goalPosition ;
	Position currentPosition ;
	Position characterPos;
	int currentLevel;
	Image wall;
	Image path;
	Solution<Position> solution;
	Character goal;
	boolean wantHint;
	Character hint;
	Image arrowUp;
	Image arrowDown;
	Image arrowUpDown;
	Image winner;
	//**********************************setters and getters********************************

	public void setMazeData(Maze3d maze) {
		wantHint=false;
		this.maze = maze;
		startPosition=maze.getStartPosition();
		goalPosition=maze.getGoalPosition();
		characterPos=maze.getStartPosition();
		currentLevel=startPosition.x;
		mazeData=maze.getCrossSectionByX(currentLevel);
		currentPosition=maze.getStartPosition();
		character.setPos(startPosition);
		character.setImage("images/leonardo2.png");
		goal.setImage("images/pizzaslice.jpg");
		goal.setPos(maze.getGoalPosition());}
		
	
//*****************************Contractor*********************************
	

	public MazeDisplay(Shell parent, int style) {
		
		// canvas const
		super(parent, style);
		
		// Initialize character
		goal=new Character();
		hint=new Character();
		character=new Character();

		// Initialize images
		wall= new Image(null, "images/sewer-wall.png" );
		arrowUp=new Image(null, "images/uparrow.png" );
		arrowDown=new Image(null, "images/down.png" );
		arrowUpDown = new Image(null, "images/updown.png" );
		winner=new Image(null, "images/winner.jpg");
		//set window background
		setBackground(new Color(null,255,255,255));

		
		// add a paint handler- drawing the maze and the character
		this.addPaintListener(new PaintListener() {
			
			
			
			@Override
			public void paintControl(PaintEvent e) {
				if (mazeData == null)
					return;
				
				   e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));

				   int width=getSize().x;  
				   int height=getSize().y;

				   int w=width/mazeData[0].length;
				   int h=height/mazeData.length;

				   for(int i=0;i<mazeData.length;i++)
				      for(int j=0;j<mazeData[i].length;j++){
				          int x=j*w;
				          int y=i*h;
				          if(mazeData[i][j]!=0)
								e.gc.drawImage(wall, 0, 0, wall.getBounds().width, wall.getBounds().height, x, y, w, h);
				          
				          if(maze.getCellVal(currentLevel+1, i, j)==0)
								e.gc.drawImage(arrowUp, 0, 0, arrowUp.getBounds().width, arrowUp.getBounds().height, x, y, w, h);

		
				          if(maze.getCellVal(currentLevel-1, i, j)==0)
								e.gc.drawImage(arrowDown, 0, 0, arrowDown.getBounds().width, arrowDown.getBounds().height, x, y, w, h);
				          
				          if(maze.getCellVal(currentLevel+1, i, j)==0 && maze.getCellVal(currentLevel-1, i, j)==0 )
								e.gc.drawImage(arrowUpDown, 0, 0, arrowUpDown.getBounds().width, arrowUpDown.getBounds().height, x, y, w, h);

				      }
				     
				   character.draw(w, h, e.gc);
				   
				   
				   
				  if(goal.getPos().x==currentLevel){
					  goal.draw(w, h, e.gc);}
				  
				  if(wantHint){
						 hint.draw(w, h, e.gc);
						 wantHint=false;  }
				  
				  if(character.getPos().equals(goal.getPos()))
				  {
						e.gc.drawImage(winner, 0, 0, winner.getBounds().width, winner.getBounds().height, 0,0, getSize().x, getSize().y);

				  }	  
				          
			}
		}); 
		
	}
	
	public void setSolution(Solution<Position> solution) {
		this.solution=solution;		
	}


	public void setWantHint(boolean b) {
		wantHint=b;		
	}

	public void checkForUpDown(){
	
	}
}
		




