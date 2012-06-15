package evaluation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import util.Rate;

/**
 * The <tt>ResultSet</tt> presents recommendation results between a group of user 
 * and a group of item in Recommender Systems. 
 * For each user, the candidate item list is in descending order by the rating value. 
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 * @see Evaluator
 * @see Recommender
 */
public final class ResultSet {
	
	private HashMap<Integer, List<Rate>> map;
	
	private HashSet<Integer> itemIds;
	
	public ResultSet(){
		map = new HashMap<Integer, List<Rate>>();
		itemIds = new HashSet<Integer>();
	}
	
	public void addResultList(int userId, List<Rate> list){
		map.put(userId, list);
		for(Rate rate : list){
			itemIds.add(rate.getItemId());
		}
	}
}
