package algorithms.search;

import java.util.List;
import java.util.PriorityQueue;

/** 
 *<h2>abstract class commonSearcher</h2>
 *<p> implements searcher interface
 *<p>overriding the method getNumberOfNodesEvaluated
 *<p> methods needs to be override- search.
 *@param <T> this is the generic parameter received from the specific problem and represent the State.
 *
 *
 * @author Tal Oyar
 * @since 2016-08-30
 * @version 1.0
 * 
 * 
 *@see searcher- interface that includes the search method which implemented by the desired solution algorithm
 */

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	PriorityQueue<State<T>> openList;
	protected int evaluatedNodes;
	
	public CommonSearcher() {
		super();
		evaluatedNodes=0;
	}

	public abstract Solution<T> search(Searchable<T> s);
	/**
	 * <p>method getNumberOfNodesEvaluated </p>
	 * <p>returns the parameter evaluatedNodes
	 * @return the number of nodes(iterations) of each search algorithm
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	/**
	 * <p> this method back trace the steps that were made from the current state to the goal state
	 * @param goalState- this is the goal to reach in order to solve the problem- 
	 * @param currentState- uses to back trace the States and detect when we reach goalState
	 * @return the solution of the problem(class solution).
	 * @see solution- this is the solution of the problem.
	 */
	protected Solution<T> backTrace(State<T> goalState){
		Solution<T> solution=new Solution<T>();
		
			State<T> currentState = goalState;
			List<State<T>> states = solution.getStates();
			while (currentState != null) {		
				states.add(0, currentState);
				currentState = currentState.getCameFrom();
			}
			return solution;
	
		
	}
}
