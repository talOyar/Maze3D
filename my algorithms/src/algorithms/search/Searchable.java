package algorithms.search;

import java.util.ArrayList;
/**
 * searchable interface
 * <p>implements by the problem domain.
 * <p> contains four methods to be override 
 * <p> holds by the searcher class
 * 
 * 
 * @author Tal Oyar
 * @version 1.0
 * @since 2016-08-30
 *
 *@param <T> this is the generic parameter received from the specific problem and represent the State.
 * @see Searcher
 * @see Searchable
 */
public interface Searchable<T> {
	 State<T> getStartState();
	 State<T> getGoalState();
	 ArrayList<State<T>> getAllPossibleStates(State<T> currentState);
	 double getMoveCost(State<T> currState, State<T> neighbor);
}

