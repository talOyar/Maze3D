package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Directions;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;
/**
 * <h2>class SearchableMaze3d<h2>
 * <p> This class using object adapter to connect  the Searchable interface to a maze3d problem.
 * <p> Implements Searchable interface
 * <p>	overrides methodes- getStartState, getGoalState, getAllPossibleStates, getMoveCost
 * 
 * @author Tal Oyar
 * @since 2016-08-30
 * @version 1.0
 *
 *@param maze- this is the problem to be solve. represented by the Maze3d class
 *@see Searchable
 *@see Maze3d
 *@see State
 */
public class SearchableMaze3d implements Searchable<Position> {
	Maze3d maze;
	/**
	 * <p>SearchableMaze3d constructor
	 * <p>gets a 3d maze. and initializing the maze parameter 
	 * @param maze
  */
	public SearchableMaze3d(Maze3d maze) {
		super();
		this.maze = maze;
	}
/**
 * <p> getStartState method
 * <p> gets the start position from the 3d maze and initialize the start state with it.
 */
	@Override
	public State<Position> getStartState() {
		 State<Position> start=new State<Position>(maze.getStartPosition());
		return start;
	}
	/**
	 * <p> getGoaltState method
	 * <p> gets the goal position from the 3d maze and initialize the goal state with it.
	 */
	@Override
	public State<Position> getGoalState() {
		 State<Position> goal=new State<Position>(maze.getGoalPosition());
		return goal;	
	}
	/**
	 * <p> getAllPossibleStates method
	 * <p> gets the all the possible moves from a specific position in the maze
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> currentState) {
		Position s= currentState.getValue();
		ArrayList<Directions> dir=maze.getPossibleMoves(s);
		ArrayList<Position> positions=maze.getDirectionsReturnPositions(dir, s);
		ArrayList<State<Position>> list=new ArrayList<State<Position>>();
		for(Position p: positions){
		State<Position>	state= new State<Position>(p);
		list.add(state);
		}
		
		return list;
	}
	/**
	 * <p> getMoveCost method
	 * <p> setting the cost of each move to be 1
	 */
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {

		return 1;
	}
	
}
