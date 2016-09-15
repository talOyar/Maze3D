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

public class MyView implements View {
	private Controller controller;
	private CLI Cli;
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


	@Override
	public void notifyMazeIsReady(String name) {
		out.println("The maze " +name+ " is ready!");
		out.flush();
	}
	//print the maze (get the maze from controller)
	
	@Override
	public void displayMaze(Maze3d maze) {
		if(maze!=null)
		out.println(maze);

	}

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
	
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		Cli.setCommands(commands);
	}
	
	@Override
	public void displayfolders(File[] path) {
		for(File f: path){
			out.println(f.getName());
			out.flush();
		}
		
	}

	@Override
	public void notifySolutionIsReady(String name) {
		out.println("The solution for "+name+ " is ready!");
		out.flush();
		
	}

	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		if(solution!=null){
		out.println(solution);
		out.flush();
		}
	}
	

	@Override
	public void start() {
		Cli.start();
		
	}


	@Override
	public void displayMessage(String name) {
		out.println(name);
		out.flush();
	}

}
