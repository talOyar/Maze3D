package algorithms.mazeGenerators;

import java.util.ArrayList;

public class ChoseLastCell implements ChooseMethod {


	@Override
	public Position chose(ArrayList<Position> list) {
		//choose last position in list
		Position position=list.get(list.size()-1);
		return position;
	}

}
