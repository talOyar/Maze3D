package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
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
		out.println("the maze" +name+ "is ready");
		out.flush();
	}
	//print the maze (get the maze from controller)
	@Override
	public void displayMaze(Maze3d maze) {
		maze.toString();

	}

	@Override
	public void displayCrossSection(int[][] maze2d) {
		
		out.println(Arrays.deepToString(maze2d));
		
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
	public void notifySolutionIsReady() {
		out.println("The maze is solved!");
		out.flush();
		
	}

	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		out.println(solution);
		out.flush();
	}
	
	public void Start(){
		Cli.start();
	}

}
