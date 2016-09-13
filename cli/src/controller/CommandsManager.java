package controller;

import java.io.File;

import java.util.HashMap;


import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class CommandsManager {
	
	private Model model;
	private View view;
	
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	 
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("Directory", new getPath());
		commands.put("display_cross_section",new displayCrossSection());
		commands.put("save_maze",new saveMaze());
		return commands;
	}
	
	//model.creat maze from the user input
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int levels = Integer.parseInt(args[1]);
			int rows = Integer.parseInt(args[2]);
			int cols = Integer.parseInt(args[3]);
			//model.creat maze 
			model.generateMaze(name,levels, rows, cols);
		}		
	}
	//get a name from CLI(user input) & return maze
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			 //do it!!--> if the name throw exception
			view.displayMaze(maze);
		}
		
	}
	
	public class displayCrossSection implements Command {
		
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int index =Integer.parseInt(args[1]) ;
			String crossby =args[2];
			int [][] maze2d = model.displayCrossSection(crossby, index, name);
			view.displayCrossSection(maze2d);
		}
		
	}
	public class getPath implements Command {
		
		@Override
		public void doCommand(String[] args) {
			String path = args[0];
			File[] file =  model.getDirectory(path);
			view.displayfolders(file);
		}
		
	}
	public class saveMaze implements Command {
		
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String file = args[1];
			 model.saveCompressMaze(name,file);			
		}
	}
		
		public class loadMaze implements Command {
			
			@Override
			public void doCommand(String[] args) {
				String name = args[0];
				String file = args[1];
				 model.saveCompressMaze(name,file);			
			}
		
	
		
	}
	
	
}
