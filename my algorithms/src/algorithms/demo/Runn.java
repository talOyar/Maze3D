/*package algorithms.demo;


	import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.ChoseRandomCell;
import algorithms.mazeGenerators.Directions;


	import algorithms.mazeGenerators.GrowingTreeGenerator;
	import algorithms.mazeGenerators.Maze3d;
	import algorithms.mazeGenerators.Maze3dGenerator;
	import algorithms.mazeGenerators.SimpleMaze3dGenerator;
	import algorithms.mazeGenerators.Position;
	import java.util.ArrayList;

	public class Runn {

			private static void testMazeGenerator(Maze3dGenerator mg ){
				// prints the time it takes the algorithm to run
				System.out.println(mg.measureAlgorithmTime(11,11,11));
				// generate another 3d maze
			
				Maze3d maze=mg.generate(11,11,11);
				// get the maze entrance
				Position p=maze.getStartPosition();
				 //print the position
				System.out.print("Start position: ");
				System.
				out.println(p); // format "{x,y,z}"
				// get all the possible moves from a position
				ArrayList<Directions> moves = maze.getPossibleMoves(p);
				// print the moves
				for(Directions move: moves)
					System.out.println(move);
				if(moves.size()==0)
					System.out.println("no moves from this position!");
				// prints the maze exit position
				System.out.print("Goal position: ");
				System.out.println(maze.getGoalPosition());

			try{
				// get 2d cross sections of the 3d maze
				int[][] maze2dx=maze.getCrossSectionByX(2);
				for(int i=0;i<maze.getLevels();i++){
				for(int j=0;j<maze.getRows();j++)
				System.out.print(maze2dx[j][i]+" ");
				System.out.println();}
				System.out.println();
				
				int[][] maze2dy=maze.getCrossSectionByY(5);
				for(int i=0;i<maze.getLevels();i++){
				for(int j=0;j<maze.getCols();j++)
				System.out.print(maze2dy[j][i]+" ");
				System.out.println();}
				System.out.println();
				
				int[][] maze2dz=maze.getCrossSectionByZ(0);	
				for(int i=0;i<maze.getCols();i++){
				for(int j=0;j<maze.getRows();j++)
				System.out.print(maze2dz[i][j]+" ");
				System.out.println();}
				System.out.println();
					
				// this should throw an exception!
				maze.getCrossSectionByX(-1);
				} catch (IndexOutOfBoundsException e){
				System.out.println("good!");
				}
			
			
					}
			
				public static void main(String[] args) {
				testMazeGenerator(new SimpleMaze3dGenerator());
				testMazeGenerator(new GrowingTreeGenerator(new ChoseRandomCell()));
				testMazeGenerator(new GrowingTreeGenerator(new ChoseLastCell() ));

				
				}
				
				
		}


*/