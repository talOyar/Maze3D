package model;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import controller.Controller;

public class MyModel implements Model {
	
	private Controller controller;
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public MyModel(Controller c){
		this.controller = c;
	}
		
	//Create a new maze with thread 
	@Override
	public void generateMaze(String namemaze, int levels, int rows, int cols) {
		Thread thread = new Thread(new Runnable(){
			
			public void run(){
				GrowingTreeGenerator Generator =new GrowingTreeGenerator();
				Maze3d maze = Generator.generate(levels, rows, cols);
				mazes.put(namemaze, maze);
				//controller send the request to view 
				controller.notifyMazeIsReady(namemaze);
			}
		});
		thread.start();
		// input all the threads into list
		threads.add(thread);
	}

	//get a name & return maze 
	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}	
	
	
	
	public int[][] displayCrossSection(String crossby , int index , String name) {
		Maze3d maze =  mazes.get(name);

		switch (crossby) {
		case "x":
			return  maze.getCrossSectionByX(index);
		case "y":
			return  maze.getCrossSectionByY(index);
		case "z":
			return  maze.getCrossSectionByZ(index);
		default:
			break;
		}
		
		
		return null;
	}
	
	public static void main(String[] args) {
	}

	@Override
	public File[] getDirectory(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
//		   for (int i = 0; i < listOfFiles.length; i++) {
//			      if (listOfFiles[i].isFile()) {
//			        System.out.println("File " + listOfFiles[i].getName());
//			      } else if (listOfFiles[i].isDirectory()) {
//			        System.out.println("Directory " + listOfFiles[i].getName());
//			      }
//			    }
		return listOfFiles;
	}
}
