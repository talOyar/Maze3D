package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * <h2> Solution class<h2>
 * <p> represent the solution of the specified problem
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 *@since 2016-08-30
 *
 *
 *@param <T> this is the generic parameter received from the specific problem and represent the State.
 *@param states-this it a list holds a set of states from the
 * start state to the goal state
 * 
 */
public class Solution<T> implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private ArrayList<State<T>> states =new ArrayList<State<T>>();

	/**
	 * *getStated method*
	 * <p> return the solution as a set of stated from the start state to the goal state
	 * @return states
	 */
	public ArrayList<State<T>> getStates() {
		return states;
	}
	/**
	 * *setStated method*
	 * <p>sets the states parameter which hold a set of states 
	 *  from the start state to the goal state(solution)
	 * @param states
	 */
	public void setStates(ArrayList<State<T>> states) {
		this.states = states;
	}
	/**
	 * *toString*
	 * <p>this method overrides toString(object class) 
	 * <p>cast from states to string in order to print the solution as a string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
