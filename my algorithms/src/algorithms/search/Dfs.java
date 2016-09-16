package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 *<h2>DFS- Depth first search<h2>
 *<p> The DFS algorithm solve the problem by searching the solution
 * to the depth of each of the nodes in the specified problem
 * <p> extends CommonSearcher
 * <p> overrides the search method
 *@param <T> this is the generic parameter received from the specific problem and represent the State.
 *@param close this is a list used in the BFS algorithm
 *@see CommonSearcher
 *@see State
 *
 *
 * @author Tal Oyar & Tomer Cohen
 * @since 2016-08-30
 * @version 1.0 
*/

public class Dfs<T> extends CommonSearcher<T> {

	ArrayList<State<T>> close= new ArrayList<State<T>>();

	/**
	 * <p> Search method
	 * <p> This method execute the BFS algorithm in order to solve the problem
	 * @return Solution
	 * @see Searchable
	 * @see State
	 * @see Solution
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> goalState= s.getGoalState();		
		State<T> startState=s.getStartState();
		PriorityQueue<State<T>> open=new PriorityQueue<State<T>>();
		open.add(startState);
		
		while (!open.isEmpty())
		{
			
		State<T> current=open.remove();
		evaluatedNodes++;
		if(current.equals(goalState)){
			
		return backTrace(current);
		
		}

		ArrayList<State<T>> neighbors=s.getAllPossibleStates(current);
		
		for (State<T> n : neighbors) {
			if(!close.contains(n) && !open.contains(n) )
			{  
			open.add(n);

			n.setCameFrom(current);
			n.setCost(n.getCost()+s.getMoveCost(current,n));

				}
			}
		close.add(current);
		}
	return null;
	
	}
	
}
	


