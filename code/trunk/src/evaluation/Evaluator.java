package evaluation;

import java.util.Set;

import core.RecSysLibException;
import core.ResultSet;

/**
 * The class <tt>AbstractEvaluator</tt> provides a skeletal implementation 
 * for different evaluation metrics in Recommender Systems. 
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 * @see ResultSet
 */
public abstract class Evaluator {
	
	protected ResultSet realSet;
	
	protected ResultSet recSet;
	
	/**
	 * Constructs an evaluator with the given result sets. 
	 */
	public Evaluator(ResultSet realSet, ResultSet recSet){
		checkUserIds(realSet.getUserIds(), recSet.getUserIds());
		this.realSet = realSet;
		this.recSet = recSet;
	}
	
	/**
	 * Checks whether or not the result sets contain the same user ids. 
	 */
	protected void checkUserIds(Set<Integer> uids1, Set<Integer> uids2){
		if(!uids1.containsAll(uids2) || !uids2.containsAll(uids1))
			throw new RecSysLibException("These two result sets do not contain the same user ids.");
	}
}
