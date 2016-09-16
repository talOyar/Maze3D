
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;


/**
 * <h2>MyView class<h2>
 * <p>Implements the VIew interface
 * <p>The view generates new output to the user based on changes in the model.
 * 
 * 
 * @author Tal Oyar& Tomer Cohen
 * @version 1.0
 * @since 09-15-2016
 * 
 * 
 * @see View
 * @see Controller
 */

public class MyView implements View {
	@SuppressWarnings("unused")
	private Controller controller;
	private CLI Cli;
	@SuppressWarnings("unused")
	private BufferedReader in;
	private PrintWriter out;
	 

	public MyView(BufferedReader in, PrintWriter out) {
		
		Cli =new CLI(in,out);
		this.in = in;
		this.out = out;	
	}
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
/**
 * <p>notifyMazeIsReady method
 * <p> display a message to the user when the maze is generated.
 * 
 */
	@Override
	public void notifyMazeIsReady(String name) {
		out.println("The maze '"+name+"' is ready!");
		out.flush();
	}
	
	//print the maze (get the maze from controller)
	/**
	 * <p>displayMaze method
	 * <p> displays the desired 3dmaze to the user
	 */
	@Override
	public void displayMaze(Maze3d maze) {
		if(maze!=null)
		out.println(maze);

	}
	
/**
 * <p>displayCrossSection method
 * <p> displays the 2dmaze that was generate in the model
 */
	@Override
	public void displayCrossSection(int[][] maze2d) {
		if(maze2d!=null){
		int length = maze2d.length;
		int width = maze2d[0].length;
		
		for(int i=0;i<length;i++){
			out.println("");
			for(int j=0;j<width;j++){
				out.print(" " + maze2d[i][j] + " ");
			}
			out.println();
			out.println();
		}
		
		}
		
	}
	/**
	 * <p>setCommands method
	 * <p> sets all the command to the user command line interface
	 */
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		Cli.setCommands(commands);
	}
	
	/**
	 * <p> displayFolders method
	 * <p> displays all the files and folders in the given directory
	 *  
	 */
	@Override
	public void displayFolders(File[] path) {
		try {
			for(File f: path){
				out.println(f.getName()+" ");
				out.flush();}
		} catch (Exception e) {
			out.println("Error while trying to display files and folders in this directory!");
		}
	}
/**
 * <p>notifySolutionIsReady method
 * <p> displays a message to the user when the solution to the maze is ready
 */
	@Override
	public void notifySolutionIsReady(String name) {
		out.println("The solution for the maze '"+name+"' is ready!");
		out.flush();
		
	}

	/**
	 * <p>displayMazeSolution method
	 * <p> displays the soulution of the maze to the user.
	 */
	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		if(solution!=null){
		out.println(solution);
		out.flush();
		}
	}
	
/**
 * <p>start method
 * <p> activated the CLI's start method which starts the program
 */
	@Override
	public void start() {
		Cli.start();
		
	}

/**
 * <p>displayMessage method
 * <p>displays a message to the user
 */
	@Override
	public void displayMessage(String name) {
		out.println(name);
		out.flush();
	}




}
