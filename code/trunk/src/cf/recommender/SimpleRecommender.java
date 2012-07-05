package cf.recommender;

import java.util.Set;

import cf.predictor.Predictor;
import util.Rate;
import util.ResultList;

/**
 * The class <tt>SimpleRecommender</tt> provides a simple implementation of the <tt>Predictor</tt> interface. 
 * It returns result list only based on one specified predictor without any additional process. 
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 */

public class SimpleRecommender implements Recommender {
	
	private Predictor predictor;
	
	/**
	 * Constructs a simple recommender with the given predictor. 
	 */
	public SimpleRecommender(Predictor predictor){
		this.predictor = predictor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResultList getResultList(int userId, Set<Integer> itemIds) {
		ResultList result = new ResultList(userId);
		for(int itemId : itemIds){
			Rate rate = predictor.getRate(userId, itemId);
			result.addRate(rate);
		}
		return result;
	}

}
