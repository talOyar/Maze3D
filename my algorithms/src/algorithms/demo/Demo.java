package algorithms.demo;



import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Bfs;
import algorithms.search.CommonSearcher;
import algorithms.search.Dfs;
import algorithms.search.Searcher;
import algorithms.search.Solution;

/**
 * <h2>Demo class<h2> 
 * <p> The Demo class generate a 3d maze then test the BFS and DFS search algorithm
 * and return the  solution
 * @author Tal Oyar & Tomer Cohen
 * @since 2016-08-30
 * @version 1.0
 * 
 * @see Maze3d
 * @see GrowingTreeGenerator
 * @see CommonSearcher
 * @see Searcher
 * @see SearchableMaze3d
 * @see Solution
 */

public class Demo {
	

	public static void Run(){
		
		GrowingTreeGenerator mg= new GrowingTreeGenerator(new ChoseLastCell());
		Maze3d maze=(mg.generate(4,4,4));
		System.out.println(maze);
		SearchableMaze3d searchableMaze= new SearchableMaze3d(maze);
		CommonSearcher<Position> searcherBfs = new Bfs<Position>();	
		CommonSearcher<Position> searcherDfs = new Dfs<Position>();	

		Solution<Position> solutionBfs = searcherBfs.search(searchableMaze);
		System.out.println("BFS solution:");
		System.out.println(solutionBfs);
		System.out.println("Nodes evaluated:");
		System.out.println(searcherBfs.getNumberOfNodesEvaluated());
		
		System.out.println("DFS solution:");
		Solution<Position> solutionDfs= searcherDfs.search(searchableMaze);
		System.out.println(solutionDfs);
		System.out.println("Nodes evaluated:");
		System.out.println(searcherDfs.getNumberOfNodesEvaluated());
	
	}
}