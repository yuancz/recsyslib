package cf.recommender;

import java.util.Set;

import util.ResultList;



/**
 * The interface <tt>Recommender</tt> presents the objects 
 * which can recommend a candidate result list to a user in Recommender Systems.
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Recommender {
	
	/**
	 * Returns the candidate result list of the specified user and items.
	 */
	public ResultList getResultList(int userId, Set<Integer> itemIds);
	
}
