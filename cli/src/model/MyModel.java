package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.ChooseMethod;
import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Bfs;
import algorithms.search.CommonSearcher;
import algorithms.search.Dfs;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {
	
	private Controller controller;
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String,Solution<Position>>();
	private ExecutorService threadPool;

		
	public MyModel() {
		
		threadPool = Executors.newFixedThreadPool(20);
	}

	//Create a new maze with thread 
	@Override
	public void generateMaze(String namemaze, int levels, int rows, int cols) {
		//Thread thread = new Thread
		threadPool.execute(new Runnable(){
			
			public void run(){
				
				GrowingTreeGenerator Generator =new GrowingTreeGenerator(new ChoseLastCell());
				Maze3d maze = Generator.generate(levels, rows, cols);
				mazes.put(namemaze, maze);
				//controller send the request to view
				controller.notifyMazeIsReady(namemaze);
			}
		});
		
		//thread.start();
		// input all the threads into list
		//threads.add(thread);
	}

	@Override
	public void setController(Controller controller) {
		this.controller=controller;		
	}

	//get a name & return maze 
	@Override
	public Maze3d getMaze(String name) {
		
		if(!mazes.containsKey(name)){
			controller.displayMessage("Maze does not exist!");
			
		}
		else
		return mazes.get(name);
		
		
		return null;
		
	}	
	
	
	
	public int[][] displayCrossSection(String crossby , int index , String name) {
		if(!mazes.containsKey(name)){
			controller.displayMessage("Maze does not exist!");
		}
		else{
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
		
		}
		
		return null;
	}

	@Override
	public File[] getDirectory(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		return listOfFiles;
	}
	
	
	@Override
	public void saveCompressMaze(String name, String fileName) {
		
		try {

				byte[] maze = getMaze(name).toByteArray();
				OutputStream out =new MyCompressorOutputStream(new FileOutputStream(fileName));
				out.write(maze);
				out.flush();
				out.close();
				controller.displayMessage("maze saved to "+"fileName");
			}
			catch (IOException e) {
				controller.displayMessage("cant save maze into " + fileName);
			}	
	
		}
	
	@Override
	public void loadMaze(String name, String fileName) {
		
		try {
			InputStream in = new FileInputStream(fileName);
			MyDecompressorInputStream input = new MyDecompressorInputStream(in);
			byte[] arr =new byte [(int)fileName.length()];
			try {
				input.read(arr);
				input.close();
				Maze3d maze = new Maze3d(arr);
				mazes.put(name, maze);
				
				
			} catch (IOException e) {
				controller.displayMessage("cant read maze from "+fileName+"!");
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			controller.displayMessage("file "+fileName+" not found!");
			e.printStackTrace();
		}
		

		
	}

	@Override
	public void solveMaze3d(String name, String algorithm) {
		
		//Thread thread=new Thread
		
		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				
				if(!mazes.containsKey(name)){
					controller.displayMessage("Maze does not exist!");
					return;
				}
				
				else{
				Maze3d maze=mazes.get(name);
				
				SearchableMaze3d searchableMaze= new SearchableMaze3d(maze);

				if(algorithm.equals("bfs")|| algorithm.equals("BFS")){
					CommonSearcher<Position> searcher = new Bfs<Position>();
					Solution<Position> solution=searcher.search(searchableMaze);
					solutions.put(name, solution);
					controller.notifySolutionIsReady(name);
					
				}
				else if(algorithm.equals("dfs")|| algorithm.equals("DFS")){
					CommonSearcher<Position> searcher = new Dfs<Position>();
					Solution<Position> solution=searcher.search(searchableMaze);
					solutions.put(name, solution);
					controller.notifySolutionIsReady(name);
					
				}
				else{
					controller.displayMessage("Wrong algorithm choice!");
						}
				}
			}
		});
		
		//thread.start();
		
		//threads.add(thread);
	}

	@Override
	public Solution<Position> getMazeSolution(String name) {
		
		if(!solutions.containsKey(name)){
			
			controller.displayMessage("No solution for this maze yet!");
			return null;
				}
		
		else{
			
			Solution<Position> solution=solutions.get(name);
			return solution;	}
		
	}
	
	public void exit(){
		
		threadPool.shutdown();
		boolean terminated=false;
		
		while(!terminated)
		{
			try {
				terminated=(threadPool.awaitTermination(10, TimeUnit.SECONDS));
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		controller.displayMessage("Program terminated!");

	}
}
	

