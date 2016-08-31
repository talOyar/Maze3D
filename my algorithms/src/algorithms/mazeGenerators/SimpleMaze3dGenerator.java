package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends Generator {

	
	Random rand=new Random();
	
	private static final float WALLRATIO=0.56f;
	

	@Override
		
	public Maze3d generate(int levels, int rows, int cols) {
		//Construct the maze
		Maze3d maze=new Maze3d(levels,rows,cols);
		
		levels=levels*2+1;
		rows=rows*2+1;
		cols=cols*2+1;
				
		//set maze as walls- to make edges of the maze as 1
		maze.setMazeAsWalls();
		//set all the maze free(0) except edges
		maze.setMazeAsPath();
		
		//set desired wall ratio in the maze
		int wallCount=(int)(rows*cols*levels*WALLRATIO);
				
		for(int i=0;i<wallCount;i++){
			
			int x=rand.nextInt(levels);
			int y=rand.nextInt(rows);
			int z=rand.nextInt(cols);
			maze.setCellVal(x, y, z, maze.WALL); }

		//choose random start and goal positions then set them in the maze
		Position startPos=maze.choosRandomPosition();
		Position goalPos=maze.choosRandomPosition();
		maze.setStartPosition(startPos);
		maze.setGoalPosition(goalPos);
		//set free(0) start and goal positions
		maze.setFree(startPos.x, startPos.y, startPos.z);
		maze.setFree(goalPos.x, goalPos.y, goalPos.z);

		// make sure there is a path from start to goal(solution to the maze)
		Position currentpos= new Position(startPos.x,startPos.y,startPos.z);
		

		while(!currentpos.equals(goalPos))
		{
			// go right
			if(currentpos.z <goalPos.z)  
			{
				currentpos.setZ(currentpos.z+1);
				maze.setFree(currentpos.x,currentpos.y,currentpos.z);}
			//go left
			 if(currentpos.z>goalPos.z){
				currentpos.setZ(currentpos.z-1);
			maze.setFree(currentpos.x,currentpos.y,currentpos.z);
			}
			 //go forward
			if(currentpos.y <goalPos.y)
			{
			currentpos.setY(currentpos.y+1);
			maze.setFree(currentpos.x,currentpos.y,currentpos.z);

			}
			//go backward
			if(currentpos.y>goalPos.y){
				currentpos.setY(currentpos.y-1);
			maze.setFree(currentpos.x,currentpos.y,currentpos.z);
			}
			//go up
			if(currentpos.x <goalPos.x)
			{
			currentpos.setX(currentpos.x+1);
			maze.setFree(currentpos.x,currentpos.y,currentpos.z);

			}
			//go down
			if(currentpos.x>goalPos.x){
				currentpos.setX(currentpos.x-1);
			maze.setFree(currentpos.x,currentpos.y,currentpos.z);

		}

		}
		
		return maze;

	}

	}

