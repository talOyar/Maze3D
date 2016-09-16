
package controller;

import java.io.File;

import java.util.HashMap;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * <h2>Class CommandManager<h2>
 * <p>holds all the command classes that implements the Command interface
 * <p>holds an hashMap with a string key(name of the command) and a command value.
 * <p>accepts input from the view and converts it to commands for the model to perform  or for the view to display
 * 
 * @author Tal Oyar & Tomer cohen
 * @version 1.0
 * @since 2016-08-30
 *
 *
 * @param model- the specific model that the commandManager uses to perform an actionõ
 * @param view- the specific view that the commandMAnager uses to display output messages.
 *
 *
 * @see Command
 * @see model
 * @see view
 *
 */

public class CommandsManager {
	
	private Model model;
	private View view;
	
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	 /**
	  * <p>GetCommandMap method<p>
	  * <p>puts all the commands in a list(hashMap) and returns it.
	  * 
	  * @return the commands list.
	  */
	public HashMap<String, Command> getCommandsMap() {
		
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display_maze", new DisplayMazeCommand());
		commands.put("solve_maze",new solveMaze3dCommand());
		commands.put("display_solution",new displayMazeSolutionCommand());
		commands.put("display_cross_section",new displayCrossSectionCommand());
		commands.put("save_maze",new saveMazeCommand());
		commands.put("load_maze", new loadMazeCommand());
		commands.put("directory", new getPathCommand());
		commands.put("exit", new exitCommand());

		return commands;
	}
	/**
	 * <h2>GenerateMazeCommand class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p>generate a maze while given a name and the desired size of levels,column and rows of the maze.
	 * <p>uses the model generatMaze method to generate the maze.
	 * 
	 * @author Tal oyar& tomer cohen
	 *
	 *@see MyModel
	 */
	// create a maze.
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			try {
				String name = args[0];
				int levels = Integer.parseInt(args[1]);
				int rows = Integer.parseInt(args[2]);
				int cols = Integer.parseInt(args[3]);
				//model.creat maze 
				model.generateMaze(name,levels, rows, cols);
				
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}

		}		
	}
	/**
	 * <h2>DisplayMazeCommand<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> get the name from the Cli(user input) and display the maze to the output.
	 * <p> uses the displayMaze method of MyView(implements view interface)
	 * 
	 * @author Tal Oyar& Tomer cohen
	 *
	 *
	 *@see MyVIew
	 */
	//get a name from the user & return the maze
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			try {
				String name = args[0];
				Maze3d maze = model.getMaze(name);
				view.displayMaze(maze);
				
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}
;
		}
		
	}
	
	/**
	 * <h2> displayCrossSectionCommand<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> gets from the CLI(user input) the name of the maze the section(x,y,z) and an index.
	 * 	then display to the output the 2dmaze in the index of the section.
	 * <p> uses the MyModel(implements Model) method- getCrossSection to get the 2dmaze
	 * <p> uses the MyVIew(implements View) method - displayCrossSection to display the 2dmaze to the output.
	 * 
	 *  
	 * @author Tal Oyar& Tomer Cohen
	 *
	 *
	 *@see MyView
	 *@see MyModel
	 */
	public class displayCrossSectionCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			try {
				String name = args[0];
				String crossby =args[1];
				int index =Integer.parseInt(args[2]) ;
				int [][] maze2d = model.getCrossSection(crossby, index, name);
				view.displayCrossSection(maze2d);
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}

		}
		
	}
	/**
	 * <h2> getPathCommand Class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> displays all the files and folders in a specific path in the computer.
	 * <p> uses MyModel  getDirectory method to get all the files and folders in the path.
	 * <p> uses MyView displayFolders method to display all the files and folders to the output
	 * 
	 *  
	 * @author Tal Oyar& Tomer Cohen
	 * 
	 *
	 * @see MyModel
	 * @see MyView
	 *
	 *
	 */
	public class getPathCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			try {
				String path = args[0];
				File[] file =  model.getDirectory(path);
				view.displayFolders(file);
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}
		}
		
	}
	
	/**
	 * <h2> saveMazeCommand<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p>save a maze into a file
	 * <p> uses MyModel saveCompressMaze method to save the maze into the  file
	 *  
	 * @author Tal Oyar & Tomer Cohen
	 *
	 *
	 * @param name- the name of the maze 
	 * @param file- the name of the file to save to.
	 * 
	 *@see MyModel
	 */
	public class saveMazeCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			try {
				String name = args[0];
				String file = args[1];
				 model.saveCompressMaze(name,file);	
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}
		
		}
	}
	
	/**
	 * <h2> loadMazeCommand Class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> loads a maze from a file
	 * <p> using MyModel method loadMaze
	 * 
	 * @author Tal Oyar & Tomer Cohen
	 *
	 *@param name- this is the name of the maze to load
	 *@param file- this is the name of the file to load from
	 *
	 *@see MyModel
	 */
	public class loadMazeCommand implements Command {
			
			@Override
			public void doCommand(String[] args) {
				try {
					String name = args[0];
					String file = args[1];
					 model.loadMaze(name,file);
				} catch (Exception e) {
					view.displayMessage("Wrong input,please try again!");
				}

				 
			}
		
	}

	/**
	 * <h2> solveMazeCommand Class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> solves the  maze using the desired search algorithm('BFS' or 'DFS')
	 * <p> using MyModel method solveMaze3d to solve the maze
	 * 
	 * @author Tal Oyar & Tomer Cohen
	 *
	 *@param name- this is the name of the maze to solve
	 *@param algorithm- this is the name of the algorithm to use in order to solve the maze
	 *
	 *@see MyModel
	 */
	public class solveMaze3dCommand implements Command{

		@Override
		public void doCommand(String[] args) {
			try {
				String name=args[0];
				String algorithm=args[1];
				model.solveMaze3d(name,algorithm);	
			} catch (Exception e) {
				view.displayMessage("Wrong input,please try again!");
			}
	
		}
		
	}
	
	/**
	 * <h2> displayMazeCommand Class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> loads a maze from a file
	 * <p> using MyModel method getMazeSolution to get the solution for the maze
	 * <p> using MyView method displayMazeSolution to display the solution to the output
	 * 
	 * @author Tal Oyar & Tomer Cohen
	 *
	 *@param name- this is the name of the maze to load
	 *@param solution- this is the solution of the maze
	 *
	 *@see MyModel
	 *@see MyView
	 */
	public class displayMazeSolutionCommand implements Command{

		@Override
		public void doCommand(String[] args) {
			
			String name=args[0];
			Solution<Position> solution=model.getMazeSolution(name);
			view.displayMazeSolution(solution);
		}
		
	} 
	
	/**
	 * <h2> exitCommand Class<h2>
	 * <p> implement Command interface and override the doCommand method
	 * <p> terminated the program and all threads
	 * <p> using MyModel method exit to terminat all threads
	 * 
	 * @author Tal Oyar & Tomer Cohen
	 *
	 *
	 *@see MyModel
	 */
	public class exitCommand implements Command{

		@Override
		public void doCommand(String[] args) {
			//terminate and shut down all thread and files securely
			model.exit();
			
		}
		
	}
	
}
