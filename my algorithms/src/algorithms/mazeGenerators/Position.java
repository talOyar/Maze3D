package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable{

private static final long serialVersionUID = 1L;
public int x;
public int y;
public int z;

public Position(int x, int y,int z) {
	this.x = x;
	this.y = y;
	this.z = z;
}



public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public int getZ() {
	return z;
}

public void setZ(int z) {
	this.z = z;
}

@Override
public String toString() {
	return "{" + x + "," + y + ","+z+"}";
}

@Override
public int hashCode(){
	return (37*x*y*z);
}



@Override
public boolean equals(Object obj) {

	Position pos = (Position)obj;
	return (this.x == pos.x && this.y == pos.y && this.z==pos.z);
	}
}

