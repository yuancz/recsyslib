package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import evaluation.Evaluator;


/**
 * The <tt>ResultSet</tt> presents recommendation results between a group of user 
 * and a group of item in Recommender Systems. 
 * For each user, the candidate item list is in descending order by the rating value. 
 * @version 1.0 2012-6-25
 * @author Tan Chang
 * @since JDK 1.7
 * @see Evaluator
 * @see ResultList
 */
public final class ResultSet {
	
	private HashMap<Integer, ResultList> map;
	
	/**
	 * Constructs an empty <tt>ResultSet</tt>.
	 */
	public ResultSet(){
		map = new HashMap<Integer, ResultList>();
	}
	
	/**
	 * Add a <tt>ResultList</tt> into this result set. 
	 */
	public void addResultList(ResultList list){
		map.put(list.getUserId(), list);
	}
	
	/**
	 * Return the result list to which the specified user id is mapped, 
	 * or null if this result set contains no mapping for the user id.
	 */
	public ResultList getResultList(int userId){
		return map.get(userId);
	}
	
	/**
	 * Returns a <tt>Set</tt> view of the user ids contained in this result set.
	 */
	public Set<Integer> getUserIds(){
		return new HashSet<Integer>(map.keySet());
	}
}
