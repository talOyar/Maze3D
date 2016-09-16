package algorithms.search;
/**
 *<h2>Searcher interface<h2>
 *<p>This interface is used in order to implement different search algorithms</p>
 *<p>Holds two methods to be implemented-search and getNUmberOFNodesEvaluated </p>
 *<p>Implements by CommonSearcher<p>
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 2016-08-30
 * @version 1.0
 * 
 *@see CommonSearcher
 *@see Bfs
 *@see Dfs
 */
public interface Searcher<T> {
/**
 * <p>search method</p>
 * <p>this is the declaration of the search method needs to be implemented
 *  by a specific search algorithm </p>
 *  
 * @param s- this is the specific search domain that implements Searchable.
 * @return the solution to the problem
 * @see Solution
 * @see Searchable
 */
	public Solution<T> search(Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}
