package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Directions;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

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
	
	//**********************************setters and getters********************************

	public void setMaze(Maze3d maze) {
		this.maze = maze;
		startPosition=maze.getStartPosition();
		goalPosition=maze.getGoalPosition();
		characterPos=maze.getStartPosition();
		currentLevel=startPosition.x;
		mazeData=maze.getCrossSectionByX(currentLevel);
		currentPosition=maze.getStartPosition();
		character.setPos(startPosition);

	}

	
//*****************************Contractor*********************************
	

	public MazeDisplay(Shell parent, int style) {
		
		// canvas const
		super(parent, style);
		
		// Initialize character
		character=new Character();

		// Initialize images
		wall= new Image(null, "images/wall.jpg" );
		path= new Image(null, "images/path.jpg" );

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
				          else
								e.gc.drawImage(path, 0, 0, path.getBounds().width, path.getBounds().height,x, y, w, h);
				      }
				   
				  character.draw(w, h, e.gc);
				  
			}
		}); 
		
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				Position pos = character.getPos();
				
				switch (e.keyCode) {
				
				case SWT.ARROW_RIGHT:
				
					character.moveRight();
					redraw();
					
					break;
				
				case SWT.ARROW_LEFT:	
					
					character.moveLeft();
					redraw();

					break;
					
				case SWT.ARROW_UP:
					
					character.moveForward();
					redraw();
					
					break;
					
				case SWT.ARROW_DOWN:
					
					character.moveBackward();
					redraw();
					
					break;
					
				case SWT.PAGE_DOWN:	
					
					character.moveDown();
					currentLevel--;
					mazeData=maze.getCrossSectionByX(currentLevel);
					redraw();
					
					break;
					
				case SWT.PAGE_UP:
					
					character.moveUp();
					currentLevel++;
					mazeData=maze.getCrossSectionByX(currentLevel);
					redraw();
					
					break;
			}
				
			}
		});
	}
	
	
	
	
	private void redrawMe() {
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				setEnabled(true);
				redraw();
			}
			
		});
	}
}



