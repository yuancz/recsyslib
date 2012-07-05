package predictor;

import core.Rate;

/**
 * The interface <tt>Predictor</tt> presents the objects 
 * which can predict a rating of a user assigning to an item in Recommender Systems.
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 * @see Rate
 */
public interface Predictor {
	
	/**
	 * Returns the prediction rating of a user assigning to an item
	 */
	public Rate getRate(int userId, int itemId);
}
