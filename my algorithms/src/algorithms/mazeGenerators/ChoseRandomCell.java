package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class ChoseRandomCell implements ChooseMethod {

	@Override
	public Position chose(ArrayList<Position> list) {

		Random rand=new Random();

		//choose random position from list
		Position position=list.get(rand.nextInt(list.size()));
		return position;
	}

}
