package view;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public class MyView implements View {
	Controller controller;
	
	 
	public MyView(Controller controller){
		this.controller = controller;
	}
	
	@Override
	public void notifyMazeIsReady(String name) {
		System.out.println("the maze" +name+ "is ready");

	}
	//print the maze (get the maze from controller)
	@Override
	public void displayMaze(Maze3d maze) {
		maze.toString();

	}

	@Override
	public void displayCrossSection(int[][] maze2d) {
		
		System.out.println(Arrays.deepToString(maze2d));

		
	}
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub

	}
	@Override
	public void displayfolders(File[] path) {
		for(File f: path){
			System.out.println(f.getName());
		}
		
	}

}
