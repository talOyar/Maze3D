package algorithms.search;

import algorithms.demo.SearchableMaze3d;

/**
 * <h2> State class<h2>
 * <p> This class used to represent a general state in a search problem.
 * Specified by the the Searchable (problem) 
 * <p>implements the comparable interface</p>
 * <p> overrides compareTo method</p>
 * 
 *@author Tal Oyar& Tomer Cohen
 *@since 2016-08-30
 *@version 1.0
 *
 *@param cost- represent the cost of state
 *@param value- holds the specific state of a specific search problem
 * @param cameFrom- holds the previous state which we came from to this state
 *@see Searcher
 *@see Searchable
 *@see SearchableMaze3d
 */

public class State<T> implements Comparable<State<T>> {

	private T value;
	private double cost;
	private State<T> cameFrom;
	private String key;
	
	/**
	 * <p>constructor to class State</p>
	 * <p>gets a a state of a specific search problem 
	 * @param value
	 */
	public State(T value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> currentState) {
		this.cameFrom = currentState;
	}

	public T getValue() {
		return value;
	}
/**
 *<p> overrides object's equals  method</p>
 *<p> uses the implemention of value(the specific state in a specific search problem)
 * equals method 
 *@return true or false
 */
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		State<T> s = (State<T>)obj;
		return s.getValue().equals(this.getValue());
	}
	
	@Override
	public int compareTo(State<T> s) {
		
		return (int) (this.cost-s.cost);
	}
	/**
	 *<p> overrides object's hashCode method</p>
	 *<p> the hashCode is calculated by the  hashCode method of the specific value</p>
	 *@return- int, by the implements of value's hashCode
	 */
		@Override
		
	public int hashCode(){
			return value.hashCode();
		
	} 
		/**
		 * toString method- overrides object's toString method.
		 * @return a string based on value's implemention to toString.
		 */
		@Override
		public String toString() {
			return value.toString();
		}

}
