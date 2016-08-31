package algorithms.mazeGenerators;

public interface Maze3dGenerator {

 Maze3d generate(int levels,int rows,int cols); 

String measureAlgorithmTime(int levels,int rows,int cols);

}