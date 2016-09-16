package algorithms.search;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * <h2>BFS- best first search<h2>
 * <p> The BFS algorithm is used to search and find the best(short) solution to the specified problem.
 * <p> implements CommonSearcher abstract class
 * <p> overriding the search method
 * @param <T> this is the generic parameter whice used to implement the searcher.
 * 
 * @author Tal Oyar & tomer cohen 
 * @version 1.0
 * @since 2016-08-30
 * 
 * @see CommonSearcher
 * @see Searcher
 * @see State
 */
public class Bfs<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private HashSet<State<T>> closedList = new HashSet<State<T>>();
	/**
	 * <p> override search method
	 * <p> uses the BFS algorithm to solve the problem
	 * @param s- this is the problem to be solved 
	 * @see State
	 * @see Searchable
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		// getting the start state of the problem and adding it to the open list
		State<T> startState = s.getStartState();
		openList.add(startState);
		
		while(!openList.isEmpty()){ 
			// while the open list is not empty- continue.
			State<T> currentState=openList.poll();
			// each iteration we remove the best state from open list
			evaluatedNodes++; 
			//promoting the nodes number by 1 each iteration 
			closedList.add(currentState); 
			//adding the current state to the close list
			State<T> goalState  = s.getGoalState();
			//if current state equals goal state-return the back trace of states from goal to start
			if(currentState.equals(goalState)) 
				return backTrace(currentState);  
			
				ArrayList<State<T>> neighbors=s.getAllPossibleStates(currentState); 
				
				for (State<T> neighbor : neighbors) {
					
					if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
						neighbor.setCameFrom(currentState);
						neighbor.setCost(currentState.getCost() + s.getMoveCost(currentState, neighbor));
						openList.add(neighbor);
					
				}
					
					else{
					double newCost = currentState.getCost() + s.getMoveCost(currentState, neighbor);
					if (newCost < neighbor.getCost()) {
						neighbor.setCost(newCost);
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
							neighbor.setCameFrom(currentState);

						} 
						else {
							openList.remove(neighbor);
							neighbor.setCameFrom(currentState);
							openList.add(neighbor);

						}
					}
				}	
			}			
		}

		return null;
	}

}
