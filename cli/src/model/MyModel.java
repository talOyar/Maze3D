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

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

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

	@Override
	public File[] getDirectory(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		return listOfFiles;
	}
	
	@Override
	public void saveCompressMaze(String name, String file) {
		
		try (OutputStream out = new FileOutputStream(file)) {

				byte[] maze = getMaze(name).toByteArray();
				MyCompressorOutputStream output =new MyCompressorOutputStream(out);
				output.setComprresByte(maze);	
				output.close();
			}
			catch (IOException e) {e.printStackTrace();}	
	
		}
	
	@Override
	public void loadMaze(String name, String file) {
		
		try {
			InputStream in = new FileInputStream(file);
			MyDecompressorInputStream input = new MyDecompressorInputStream(in);
			byte[] arr =new byte [(int)file.length()];
			try {
				input.read(arr);
				Maze3d maze = new Maze3d(arr);
				mazes.put(name, maze);
				input.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}


	
}
	

