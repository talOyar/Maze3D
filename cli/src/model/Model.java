package model;

import java.io.File;

import algorithms.mazeGenerators.Maze3d;

public interface Model {
	void generateMaze(String name,int levels , int rows, int cols);
	Maze3d getMaze(String name);
	int[][] displayCrossSection(String crossby,int index ,String name );
	File[] getDirectory(String path);
}
