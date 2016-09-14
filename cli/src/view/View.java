package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

public interface View {
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void setCommands(HashMap<String, Command> commands);
	void displayCrossSection(int [][]maze2d);
	void displayfolders(File[] path);
	void notifySolutionIsReady();
	void displayMazeSolution(Solution<Position> solution);
	void setController(Controller controller);
	void start();

}
