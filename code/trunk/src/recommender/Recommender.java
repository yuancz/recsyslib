package recommender;

import java.util.Set;

import core.ResultList;
import core.ResultSet;



/**
 * The interface <tt>Recommender</tt> presents the objects 
 * which can recommend a candidate result list to a user in Recommender Systems.
 * A recommender can give the results backed by one or more predictors, 
 * and this recommender and related predictors should be built from same data set. 
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Recommender {
	
	/**
	 * Returns the candidate result list of the specified user.
	 * All items, except which have been rated by the specified user in original data set,  
	 * will be in the result list. 
	 */
	public ResultList getResultList(int userId);
	
	/**
	 * Returns the candidate result set of all the specified users.
	 * All items, except which have been rated by the specified user in original data set, 
	 * will be in the result list. 
	 */
	public ResultSet getResultSet(Set<Integer> userIds);
	
}
