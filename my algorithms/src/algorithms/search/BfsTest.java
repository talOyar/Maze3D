package algorithms.search;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.ChoseRandomCell;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class BfsTest {


	Position startPosition;
	Position goalPosition;
	Maze3d maze1;
	Maze3d maze2;

	Searcher<Position> bfs;
	SearchableMaze3d searchable;
	Solution<Position> sol1;
	Solution<Position>  sol2;
	

	public  BfsTest() {
		
		
		
		GrowingTreeGenerator generatorByLast=new GrowingTreeGenerator(new ChoseLastCell());
		GrowingTreeGenerator generatorByRandum=new GrowingTreeGenerator(new ChoseRandomCell());

	
		maze1 = generatorByLast.generate(10, 10, 10);
		maze2 = generatorByRandum.generate(20, 20, 20);
		
		maze1.setGoalPosition(maze1.setRandomGoal());
		maze1.setStartPosition(maze1.choosRandomPosition());
		maze1.setGoalPosition(maze2.setRandomGoal());
		maze1.setStartPosition(maze2.choosRandomPosition());
		Searchable<Position> searchableMaze1 = new SearchableMaze3d(maze1);
		Searchable<Position> searchableMaze2 = new SearchableMaze3d(maze2);

		bfs = new Bfs<Position>();
		sol1 = bfs.search(searchableMaze1);
		sol2=bfs.search(searchableMaze2);
	}
	
	
	@Test
	public void checkmaze() {
		
		assertNotNull(sol1);
		assertNotNull(sol2);

	}
	
	@Test
	public void checkSizeOfSolution() {
		
		assertEquals(4, sol1.getStates().size());
		assertEquals(4, sol2.getStates().size());

	}
	@Test
	public void checkStartIsFirst() {
		assertEquals(maze1.getStartPosition(), sol1.getStates().get(0).getValue());
		assertEquals(maze2.getStartPosition(), sol2.getStates().get(0).getValue());

	}
	@Test
	public void checkGoalStateIsLast() {
		assertEquals(maze1.getGoalPosition(), sol1.getStates().get(sol1.getStates().size()-1).getValue());
		assertEquals(maze2.getGoalPosition(), sol2.getStates().get(sol2.getStates().size()-1).getValue());

	}
	@Test
	public void testEvaluted() {
		assertEquals(true, bfs.getNumberOfNodesEvaluated() >= sol1.getStates().size());
		assertEquals(true, bfs.getNumberOfNodesEvaluated() >= sol2.getStates().size());

	}


}
