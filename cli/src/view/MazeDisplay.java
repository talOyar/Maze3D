package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends Canvas {
	
	Maze3d maze;
	Character character;
	int [][] mazeData;
	Position startPosition ;
	Position goalPosition ;
	Position curentPosition ;
	Position characterPosition;

	
	
//	int[][] mazeData={
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
//			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
//			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
//			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
//			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
//			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
//			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
//			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
//			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
//		};
	//mazeDisplay.setMazeData(mazeData);	

	
	public MazeDisplay(Shell parent, int style) {
		// canvas const
		super(parent, style);
		
		// Initialize character
		character=new Character();
		character.setPos(curentPosition);
		
		mazeData=maze.getCrossSectionByX(curentPosition.x);
		
		//set window background
		setBackground(new Color(null,255,255,255));
		
		// add a paint handler
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
				              e.gc.fillRectangle(x,y,w,h);
				          else{
				          }
				      }
				  character.draw(w, h, e.gc);
				   
			}
		});
		
		
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {	
				getDisplay().syncExec(new Runnable() {					

					@Override
					public void run() {
						
						character.moveRight();
						redraw();
					}
				});
				
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 500);
	}

	
	
	
	
	
	
	//**********************************setters and getters********************************
	
	
	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
		this.redraw();
	}
	
	public void setGoalPosition(Position goalPos) {
		goalPosition=goalPos;
	}

	public void setCharacterPosition(Position startPos) {
		characterPosition=startPos;
	}
	
}
