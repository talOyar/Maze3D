package algorithms.demo;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Bfs;
import algorithms.search.CommonSearcher;
import algorithms.search.Dfs;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
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

	public static void main(String[] args){
			
		GrowingTreeGenerator mg= new GrowingTreeGenerator(new ChoseLastCell());
		Maze3d maze=(mg.generate(10,10,10));
		System.out.println(maze);
		SearchableMaze3d searchableMaze= new SearchableMaze3d(maze);
		CommonSearcher<Position> searcherBfs = new Bfs<Position>();	
		CommonSearcher<Position> searcherDfs = new Dfs<Position>();	

		Solution<Position> solutionBfs = searcherBfs.search(searchableMaze);
		System.out.println(solutionBfs);
		Solution<Position> solutionDfs= searcherDfs.search(searchableMaze);
		System.out.println(solutionDfs);
		System.out.println(searcherBfs.getNumberOfNodesEvaluated());
		System.out.println(searcherDfs.getNumberOfNodesEvaluated());
	
	
		MyCompressorOutputStream out;
		try {
			
		out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
				
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		MyDecompressorInputStream in;
		
		try {
			in=new MyDecompressorInputStream(
			new FileInputStream("1.maz"));

		byte b[]=new byte[maze.toByteArray().length];
		
			in.read(b);
			in.close();
			
		Maze3d loaded=new Maze3d(b);
		System.out.println(loaded);
		System.out.println(loaded.equals(maze));
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}