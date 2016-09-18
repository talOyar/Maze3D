
package model;

import java.io.File;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Presenter;
/**
 * <h2> Model interface<h2>
 * <p>The model stores data that is retrieved according to commands from the controller and displayed in the view.
 * 
 */
public interface Model {
	void generateMaze(String name,int levels , int rows, int cols);
	Maze3d getMaze(String name);
	int[][] getCrossSection(String crossby,int index ,String name );
	File[] getDirectory(String path);
	void saveCompressMaze(String name, String file);
	void loadMaze(String name, String file);
	void solveMaze3d(String name, String algorithm);
	Solution<Position> getMazeSolution(String name);
	void setPresenter(Presenter presenter);
	void exit();
	
}
