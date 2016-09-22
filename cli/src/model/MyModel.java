
package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.ChoseLastCell;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Bfs;
import algorithms.search.CommonSearcher;
import algorithms.search.Dfs;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.Presenter;
import presenter.Properties;
import presenter.PropertiesLoader;

/**
 * <h2> MyModel  <h2>
 * <p> implements Model interface
 * <p> The model stores data that is retrieved according to commands 
 * from the controller and displayed in the view.
 *
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 09-15-2016
 * @version 1.0
 * 
 *@see Presenter
 *@see View
 *@see Model
 *@see CommandManager 
 */
public class MyModel extends Observable implements Model {
	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String,Solution<Position>>();
	private ExecutorService threadPool;
	private Presenter presenter;
	private Properties properties;	
	
	public MyModel() {
		
		properties=PropertiesLoader.getInstance().getProperties();
		threadPool = Executors.newFixedThreadPool(properties.getNumOfThreads());
		loadSolutions();
		
	}
	
/**
 * <p> generateMaze method
 * <p> generate a maze in a thread according to the dimensions received from the user 
 *  and add it to an hashMap of mazes with the name entered by the user.
 *  
 *  
 */
	//Create a new maze with thread 
	
	@Override
	public void generateMaze(String namemaze, int levels, int rows, int cols) {
		threadPool.submit(new Callable<Maze3d>(){
			
			@Override
			public Maze3d call() throws Exception {
				GrowingTreeGenerator Generator =new GrowingTreeGenerator(new ChoseLastCell());
				Maze3d maze = Generator.generate(levels, rows, cols);
				mazes.put(namemaze, maze);
				setChanged();
				notifyObservers("maze_ready "+namemaze);
				
				return maze;
			}
		});

	}
	
/**
 * <p>setController method
 * <p>sets the controller of the model
 */
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
	}
	
/**
 * <p>getMaze method
 * <p> receives a string of the maze name and return the maze
 * @return maze3d
 */
	//get a name & return maze 
	@Override
	public Maze3d getMaze(String name) {
		
		if(!mazes.containsKey(name)){
			setChanged();
			notifyObservers("display_message "+"maze does not exist!");
			//presenter.displayMessage("Maze does not exist!");
		}
		else
	
		return mazes.get(name);
		
		return null;
	}
	public void  SaveSolutions(){
		ObjectOutputStream save=null;
		try {
			save=new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutions.dat")));
			save.writeObject(mazes);
			save.writeObject(solutions);			
			
		} catch (FileNotFoundException e) {
				setChanged();
				notifyObservers("display_message Error while trying to save the solution into the file!");
		} catch (IOException e) {
			setChanged();
			notifyObservers("display_message Error while trying to save the solution into the file!");
		}finally {
			try {
				save.close();
			} catch (IOException e) {
				setChanged();
				notifyObservers("display_message Error while trying to close the file!");
			}
		}
		
	}
	
	
@SuppressWarnings("unchecked")
public void loadSolutions(){
	File file = new File("solutions.dat");
	if (!file.exists()){
	setChanged();
	notifyObservers("display_message File do not exist!");
		return;
	}
	
	ObjectInputStream load = null;

	try {
		load = new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutions.dat")));
		mazes = (Map<String,Maze3d>)load.readObject();
		solutions = (Map<String, Solution<Position>>)load.readObject();	
		
	} catch (FileNotFoundException e) {
		setChanged();
		notifyObservers("display_message Error while trying to save the solution into the file!");
	}
	catch (IOException e) {
		setChanged();
		notifyObservers("display_message Error while trying to save the solution into the file!");
	}
	catch (ClassNotFoundException e) {
		setChanged();
		notifyObservers("display_message Error while trying to save the solution into the file!");
	} 
	finally{
		try {
			load.close();
		}
		catch (IOException e) {
			setChanged();
			notifyObservers("display_message Error while trying to close the file!");
		}
	}		
	
}	
	
	/**
	 * <p>getCrossSection method
	 * <p> receives from the controller the name of the maze, the section(x,y,z) and the index
	 * and returns the 2dmaze
	 * 
	 */
	public int[][] getCrossSection(String crossby , int index , String name) {
		if(!mazes.containsKey(name)){
			setChanged();
			notifyObservers("display_message "+"Maze does not exist!");
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
/**
 * <p>getDirectory method
 * <p> receives a directory path and returns a file array that contains all the folders and files in
 * the directory.
 */
	@Override
	public File[] getDirectory(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		return listOfFiles;
	}
	/**
	 * <p> saveCompressMaze method
	 * <p> receives the name of the maze to be saved and the name of the file to save into
	 * and saves the compressed maze into that file
	 */
	
	@Override
	public void saveCompressMaze(String name, String fileName) {
		
		try {
				if(!mazes.containsKey(name) || fileName==null){
					setChanged();
					notifyObservers("display_message Error while trying to save the maze");

					return;
				}
				
				byte[] maze = getMaze(name).toByteArray();
				
				OutputStream out =new MyCompressorOutputStream(new FileOutputStream(fileName));
				

				out.write(maze.length/255);
				out.write(maze.length%255);
				
				out.write(maze);
				out.flush();
				out.close();
				setChanged();
				notifyObservers("display_message The maze '"+name+"' saved successfully to the file "+ fileName);
				//presenter.displayMessage("The maze '"+name +"' saved Successfully to the file "+fileName);
			}
		
			catch (IOException e) {
				setChanged();
				notifyObservers("display_message Error while trying to save the maze '"+name+"' into the file"+ fileName );
				
				//presenter.displayMessage("Error while trying to save the maze '"+name+"' into the file "+fileName);
							}	
	
		}
	/**
	 * <p>loadMaze method
	 * <p> receives the name of the maze to load and the name of the file to load it from
	 * and loads the decompressed maze.
	 * 
	 */
	@Override
	public void loadMaze(String name, String fileName) {
		
		try	
		{
			MyDecompressorInputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
			
			//read the size of the array from the file
			int sizeA = in.read();
			int sizeB = in.read();
			int Arraysize = (sizeA * 255) + sizeB;
			
			//create an array in the correct size then read the maze from the file into it
			byte[] arr =new byte [Arraysize];
			in.read(arr);
			in.close();
			
			//create a maze using the bytes array constructor
			Maze3d maze = new Maze3d(arr);
			
			//if maze is not null add it to mazes then print a message to the user.
			if (maze!=null){
				mazes.put(name, maze);
				setChanged();
				notifyObservers("display_message "+"The maze '"+name+"' loaded Successfully from the file "+fileName+"!");
				//presenter.displayMessage("The maze '"+name+"' loaded Successfully from the file "+fileName+"!");
			}
		}
		
		catch(Exception e){
			setChanged();
			notifyObservers("display_message Error while trying to load the maze from the file "+fileName+"!");
			//presenter.displayMessage("Error while trying to load maze from the file "+fileName+"!");
			
			}
		}
	
/**
 * <p>solveMaze3d method
 * <p> receives the name of the maze to be solved and the desired algorithm(BFS or DFS)
 * to solve it with. then solve the maze in a thread and adds the solution to an hashMap of solutions
 */
	@Override
	public void solveMaze3d(String name, String algorithm) {
		if(solutions.containsKey(name)){
			setChanged();
			notifyObservers("solution_ready"+name);
		}
			
		threadPool.submit(new Callable<Solution<Position>>(){ 
			
			@Override
			public Solution<Position> call() throws Exception {
				if(!mazes.containsKey(name)){
					setChanged();
					notifyObservers("display_message Maze does not exist!");
					//presenter.displayMessage("Maze does not exist!");
					return null;
				}
				
				else{
				Maze3d maze=mazes.get(name);
				
				SearchableMaze3d searchableMaze= new SearchableMaze3d(maze);

				if(algorithm.equalsIgnoreCase("bfs")){
					CommonSearcher<Position> searcher = new Bfs<Position>();
					Solution<Position> solution=searcher.search(searchableMaze);
					solutions.put(name, solution);
					setChanged();
					notifyObservers("solution_ready "+name);	
					
				}
				else if(algorithm.equalsIgnoreCase("dfs")){
					CommonSearcher<Position> searcher = new Dfs<Position>();
					Solution<Position> solution=searcher.search(searchableMaze);
					solutions.put(name, solution);
					
					setChanged();
					notifyObservers("solution_ready "+name);	
					
				}
				else{
					setChanged();
					notifyObservers("display_message "+"Wrong algorithm choice!");
						}
				}
				return null;
			}
		});
		
		
	}
	/**
	 * <p>getMazeSolution method
	 * <p>returns the solution of the maze.
	 */
	@Override
	public Solution<Position> getMazeSolution(String name) {
		
		if(!solutions.containsKey(name)){
			setChanged();
			notifyObservers("display_message No solution for this maze yet!");	
			//presenter.displayMessage("No solution for this maze yet!");
			return null;
				}
		
		else{
			
			Solution<Position> solution=solutions.get(name);
			return solution;	}
		
	}
	/**
	 * <p> exit method
	 *<p> terminate the program.
	 *<p> if a thread is still working it will wait until it will be terminated
	 */
	public void exit(){
		
		threadPool.shutdown();
		boolean terminated=false;

		while(!terminated)
		{
			try {
				terminated=(threadPool.awaitTermination(10, TimeUnit.SECONDS));
				
			} 
			catch (InterruptedException e) {
				setChanged();
				notifyObservers("display_message The program did not terminated properly");
				//presenter.displayMessage("The program did not terminated properly!");
				e.printStackTrace();
			}
		}
		
		if(terminated){
			setChanged();
			notifyObservers("display_message Program terminated!");
			//presenter.displayMessage("Program terminated!");}
			SaveSolutions();
	}

	}
}
	

