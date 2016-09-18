
package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Presenter;
/**
 * <h2>View interface<h2>
 * <p> Holds ten methods to be override
 * <p>Implemented by MyVIew class
 * <p>The view generates new output to the user based on changes in the model.
 * 
 * 
 * @author Tal Oyar& Tomer Cohen
 * @version 1.0
 * @since 09-15-2016
 * 
 * 
 * @see MyVIew
 */
public interface View {
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void setCommands(HashMap<String, Command> commands);
	void displayCrossSection(int [][]maze2d);
	void displayFolders(File[] path);
	void notifySolutionIsReady(String name);
	void displayMazeSolution(Solution<Position> solution);
	void setPresenter(Presenter presenter);
	void start();
	void displayMessage(String name);
}
