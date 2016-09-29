
package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Maze3d implements Serializable{

private static final long serialVersionUID = 1L;
private int rows;
private int cols;
private int levels;
private int[][][] maze;
private Position startPosition;
private Position goalPosition;
public final int PATH=0; 
public final int WALL=1; 



public Maze3d(byte[] mazeAsBytes) {
		int count=0;

		//get the sizes of the maze
		this.levels=(int)mazeAsBytes[count++];
		this.rows=(int)mazeAsBytes[count++];
		this.cols=(int)mazeAsBytes[count++];
		
		// get and set the start position

		setStartPosition(new Position(mazeAsBytes[count++],mazeAsBytes[count++],mazeAsBytes[count++]));
		
		// get and set the goal position
		setGoalPosition(new Position(mazeAsBytes[count++],mazeAsBytes[count++],mazeAsBytes[count++]));
		
		//Initialize maze
		maze = new int[levels][rows][cols];
		
		//save the maze
		for(int i=0;i<levels;i++){
			for(int j=0;j<rows;j++){
				for(int k=0;k<cols;k++){
					maze[i][j][k]=mazeAsBytes[count++];
				}
			}
		}		
}


public Maze3d(int level, int row, int col) {
	this.rows=row*2+1;
	this.cols=col*2+1;
	this.levels=level*2+1;
	maze = new int[this.levels][this.rows][this.cols];
}

public int getCols() {
	return cols;
}

public int getRows() {
	return rows;
}

public int getLevels() {
	return levels;
}

public int[][][] getMaze() {
	return maze;
}

public Position setRandomGoal(){
	Position p= choosRandomPosition();
	while(getCellVal(p.x, p.y, p.z)==WALL || p==startPosition){
		p=choosRandomPosition();
	}
	
	setFree(p.x, p.y, p.z);
	setGoalPosition(p);
	return p;
}
public Position getStartPosition() {

	return startPosition;
}
public void setStartPosition(Position startPos) {
	this.startPosition = startPos;
}
public Position getGoalPosition() {
	return goalPosition;
}
public void setGoalPosition(Position goalPos) {
	this.goalPosition = goalPos;
}
public int getCellVal(int level,int row,int col){
	return maze[level][row][col];
	
	
}
public void setCellVal(int level,int row,int col,int val){
	maze[level][row][col]=val;
}

public void setMazeAsWalls(){
	
	for(int i=0;i<levels;i++)
		for(int j=0;j<rows;j++)
			for(int k=0;k<cols;k++)
				maze[i][j][k]=WALL;
		}

public void setMazeAsPath(){
	
	for(int i=1;i<levels-1;i++)
		for(int j=1;j<rows-1;j++)
			for(int k=1;k<cols-1;k++)
				maze[i][j][k]=PATH;
		}

public Position choosRandomPosition(){
	Random rand=new Random();
	int level=0;
	int row=0;
	int col=0;
	  //only odd position are legal
		  level=rand.nextInt(levels);
		  while(level%2==0)
		  level=rand.nextInt(levels);
		  
		  row=rand.nextInt(rows);
		  while(row%2==0)
			  row=rand.nextInt(rows);
		  
		  col=rand.nextInt(cols);
		  while(col%2==0)
			  col=rand.nextInt(cols);
		  
	  Position randPosition= new Position(level,row,col);
	return randPosition;
}

public void setFree(int level,int row,int col){
	maze[level][row][col]=PATH;
	
}


public ArrayList<Directions> getUnvisitedNeighbors(Position p){
	
	ArrayList<Directions> dir=new ArrayList<Directions>();
	
	if(checkRight(p)==Directions.RIGHT)
		dir.add(Directions.RIGHT);
	
	if(checkLeft(p)==Directions.LEFT)
		dir.add(Directions.LEFT);
	
	if(checkForward(p)==Directions.FORWARD)
		dir.add(Directions.FORWARD);
	
	if(checkBackward(p)==Directions.BACKWARD)
		dir.add(Directions.BACKWARD);
	
	if(checkUp(p)==Directions.UP)
		dir.add(Directions.UP);
	
	if(checkDown(p)==Directions.DOWN)
		dir.add(Directions.DOWN);

	return dir;
	
}
	
public Directions checkRight(Position p){
	
	if((p.z+2)<cols &&  getCellVal(p.x, p.y, p.z+1)==WALL && getCellVal(p.x+1, p.y, p.z+2)==WALL){
	return Directions.RIGHT;}
	else return null;
	
}
public Directions checkLeft(Position p){
	
	if( (p.z-2)>0 && getCellVal(p.x, p.y, p.z-1)==WALL && getCellVal(p.x, p.y, p.z-2)==WALL)
		return Directions.LEFT;
	else
	return null;
	
}
public Directions checkForward(Position p){
	
	if((p.y-2)>0 && getCellVal(p.x, p.y-1, p.z)==WALL && getCellVal(p.x, p.y-2, p.z)==WALL )
		return Directions.FORWARD;
	else
	return null;
	
}	
public Directions checkBackward(Position p){
	
	if((p.y+2)<rows && getCellVal(p.x, p.y+1, p.z)==WALL && getCellVal(p.x, p.y+2, p.z)==WALL)
		return Directions.BACKWARD;
	else
	return null;
	
}
public Directions checkUp(Position p){
	
	if((p.x+2)<levels && getCellVal(p.x+1, p.y, p.z)==WALL && getCellVal(p.x+2, p.y, p.z)==WALL)
		return Directions.UP;
	else
	return null;
	
}	
public Directions checkDown(Position p){
	
	if((p.x-2)>0 && getCellVal(p.x-1, p.y, p.z)==WALL && getCellVal(p.x-2, p.y, p.z)==WALL)
		return Directions.DOWN;
	else
	return null;
	
}	
public int[][] getCrossSectionByX(int x) throws IndexOutOfBoundsException{
	
	int[][] getCrossByX;
	if(x<0 || x> levels-1)
		throw new IndexOutOfBoundsException("The input of x is not valid!");
		getCrossByX = new int[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++){
				getCrossByX[i][j]=maze[x][i][j];
			}
	
	return getCrossByX ;
}

public int[][] getCrossSectionByY(int y) throws IndexOutOfBoundsException{
	
	int[][] getCrossByY=new int[levels][cols];
	if(y<0 || y> rows-1)
		throw new IndexOutOfBoundsException("The input of y is not valid!");	
	for(int i=0;i<levels;i++)
		for(int j=0;j<cols;j++){
			getCrossByY[i][j]=maze[i][y][j];
		}
		
	
	
	return getCrossByY ;
	

}

public int[][] getCrossSectionByZ(int z) throws IndexOutOfBoundsException{	
	int[][] getCrossByZ=new int[levels][rows];
	if(z<0 || z> cols-1)
		throw new IndexOutOfBoundsException("The input of z is not valid!");
	for(int i=0;i<levels;i++)
	for(int j=0;j<rows;j++){
	getCrossByZ[i][j]=maze[i][j][z];
		}
	
	return getCrossByZ ;
}
public ArrayList<Position> getDirectionsReturnPositions(ArrayList<Directions> dir, Position pos){
	ArrayList<Position> positions=new ArrayList<Position>();
	for(Directions direction: dir){
		if (direction==Directions.RIGHT) 
			positions.add(new Position(pos.x,pos.y,pos.z+1));
		if (direction==Directions.LEFT) 
			positions.add(new Position(pos.x,pos.y,pos.z-1));
		if (direction==Directions.FORWARD) 
			positions.add(new Position(pos.x,pos.y-1,pos.z));
		if (direction==Directions.BACKWARD) 
			positions.add(new Position(pos.x,pos.y+1,pos.z));
		if (direction==Directions.UP) 
			positions.add(new Position(pos.x+1,pos.y,pos.z));
		if (direction==Directions.DOWN) 
			positions.add(new Position(pos.x-1,pos.y,pos.z));
	}
	
	return positions;
}

public ArrayList<Directions> getPossibleMoves(Position p){
	
	ArrayList<Directions> dir=new ArrayList<Directions>();
	
	if((p.z+2)<=cols) 
		if(getCellVal(p.x, p.y, p.z+1)==PATH)
		dir.add(Directions.RIGHT);
	if((p.z-2)>=0) 
		if(getCellVal(p.x, p.y, p.z-1)==PATH)
		dir.add(Directions.LEFT);
	if((p.y-2)<=rows)
		if(getCellVal(p.x, p.y-1, p.z)==PATH)
		dir.add(Directions.FORWARD);
	if((p.y+2)>=0)
		if(getCellVal(p.x, p.y+1, p.z)==PATH)
		dir.add(Directions.BACKWARD);
	if((p.x+2)<=levels)
		if(getCellVal(p.x+1, p.y, p.z)==PATH)
		dir.add(Directions.UP);
	if((p.x-2)>=0) 
		if(getCellVal(p.x-1, p.y, p.z)==PATH)
		dir.add(Directions.DOWN);
	

	return dir;
	
}

@Override
public String toString() {
	
	StringBuilder sb = new StringBuilder();
	for (int x = 0 ; x < levels; x++)
	{
		sb.append(" \n");
		for (int y = 0; y < rows; y++) {
			for (int z = 0; z < cols; z++) {
				
				if (y == startPosition.y && x == startPosition.x && z == startPosition.z) {
					sb.append("S"+" ");
				}
				else if (y == goalPosition.y && x == goalPosition.x && z == goalPosition.z) {
					sb.append("G"+" ");
				}
				else {
					sb.append(maze[x][y][z]+" ");
				}
			}
			sb.append("\n");
		}
		sb.append("\n");
	}
	return sb.toString();
}
	
	
	

@Override
public boolean equals(Object arg0) {
Maze3d maze=(Maze3d)arg0;
return this.toString().equals(maze.toString());
}

public byte[] toByteArray(){

	ArrayList<Byte> arr = new ArrayList<Byte>();
	arr.add((byte)this.levels);
	arr.add((byte)this.rows);
	arr.add((byte)this.cols);
	
	arr.add((byte)startPosition.x);
	arr.add((byte)startPosition.y);
	arr.add((byte)startPosition.z);

	arr.add((byte)goalPosition.x);
	arr.add((byte)goalPosition.y);
	arr.add((byte)goalPosition.z);


	for(int i=0;i<levels;i++){
	for(int j=0;j<rows;j++){
		for(int k=0;k<cols;k++){
			arr.add((byte)maze[i][j][k]);
		}
	}
}
	
	byte[] bytesMaze = new byte[arr.size()];
	for (int i = 0; i < arr.size(); i++) {
		bytesMaze[i] = arr.get(i);
	}
	
	return bytesMaze;
	
}	


}