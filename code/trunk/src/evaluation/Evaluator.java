package evaluation;

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
		this.realSet = realSet;
		this.recSet = recSet;
	}
}
