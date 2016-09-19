

import java.io.Serializable;

public class Properties implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int numOfThreads=0;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfTheards) {
		this.numOfThreads = numOfTheards;
	}
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}
	
}
