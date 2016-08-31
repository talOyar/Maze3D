package algorithms.mazeGenerators;

public abstract class Generator implements Maze3dGenerator {
		
	public Generator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
		public String measureAlgorithmTime(int levels,int rows,int cols) {
		long startTime=System.currentTimeMillis();
		this.generate( levels,rows,cols);
		long endTime=System.currentTimeMillis();
		return String.valueOf(endTime-startTime);
	}


}
