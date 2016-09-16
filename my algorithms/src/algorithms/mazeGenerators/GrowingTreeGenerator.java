package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class GrowingTreeGenerator extends Generator implements Maze3dGenerator {

	public GrowingTreeGenerator(ChooseMethod desiredMethod) {
		this.desiredMethod = desiredMethod;
	}


	Random rand =new Random();
	Maze3d maze;
	ArrayList<Position> list= new ArrayList<Position>();
	ChooseMethod desiredMethod;
	public GrowingTreeGenerator() {
		super();

	}
	@Override
	
	public Maze3d generate(int levels, int rows, int cols) {
	//create a new maze
	maze=new Maze3d(levels, rows, cols);
	//set all maze as walls(1)
	maze.setMazeAsWalls();

	//choose random start position then set it free.
		Position start=maze.choosRandomPosition();
		maze.setStartPosition(start);
		maze.setFree(start.x, start.y, start.z);

		//add start point to the list
		list.add(start);
	
	
	while(!list.isEmpty()){
	Position position=desiredMethod.chose(list);
	//create array of directions than check for possible directions(unvisited neighbors)
	ArrayList<Directions> directions=maze.getUnvisitedNeighbors(position);
	
	//check if the cell have any unvisited neighbors(which directions we can go next from position)
	if(!directions.isEmpty()){
	// choose random direction from directions.
		int d= rand.nextInt(directions.size());
		cutChoosenDirection(directions.get(d),position);
	}
	
	else
		list.remove(position);
	}
	
	//choose random goal position then set it free.
	maze.setRandomGoal();
	
	return maze;
	}
	
	
	private void cutChoosenDirection(Directions direction,Position position){
		
		//using 'switch-case' to set free chosen direction
		switch (direction) {
		
		case RIGHT:
			maze.setFree(position.x,position.y,position.z+1);
			maze.setFree(position.x,position.y,position.z+2);
			Position posRight= new Position(position.x,position.y,position.z+2);
			list.add(posRight);
			break;
		case LEFT:
			maze.setFree(position.x,position.y,position.z-1);
			maze.setFree(position.x,position.y,position.z-2);
			Position posLeft= new Position(position.x,position.y,position.z-2);
			list.add(posLeft);
			break;
		case FORWARD:
			maze.setFree(position.x,position.y+1,position.z);
			maze.setFree(position.x,position.y+2,position.z);
			Position posForward= new Position(position.x,position.y+2,position.z);
			list.add(posForward);
			break;
		case BACKWARD:		
		maze.setFree(position.x,position.y-1,position.z);
		maze.setFree(position.x,position.y-2,position.z);
		Position posBackward= new Position(position.x,position.y-2,position.z);
			list.add(posBackward);
			break;
		case UP:
			maze.setFree(position.x+1,position.y,position.z);
			maze.setFree(position.x+2,position.y,position.z);
			Position posUp= new Position(position.x+2,position.y,position.z);
			list.add(posUp);
			break;
		case DOWN:
			maze.setFree(position.x-1,position.y,position.z);
			maze.setFree(position.x-2,position.y,position.z);
			Position posDown= new Position(position.x-2,position.y,position.z);
			list.add(posDown);
			break;
		                                                                                                                                                                                                                 default:
		
			}
		}

	}
	