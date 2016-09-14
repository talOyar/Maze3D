package model;

import java.io.File;


import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	void generateMaze(String name,int levels , int rows, int cols);
	Maze3d getMaze(String name);
	int[][] displayCrossSection(String crossby,int index ,String name );
	File[] getDirectory(String path);
	void saveCompressMaze(String name, String file);
	void loadMaze(String name, String file);
	void solveMaze3d(String name, String algorithm);
	Solution<Position> getMazeSolution(String name);
	void setController(Controller controller);
	
}
