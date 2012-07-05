package recommender;

import java.util.Set;

import predictor.Predictor;
import core.DataSet;
import core.Rate;
import core.ResultList;

/**
 * The class <tt>SimpleRecommender</tt> provides a simple implementation of the <tt>Recommender</tt> interface. 
 * It returns result list only based on one specified predictor without any additional process. 
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 */

public class SimpleRecommender extends AbstractRecommender implements Recommender {
	
	protected Predictor predictor;
	
	/**
	 * Constructs a simple recommender with the given data set and predictor. 
	 */
	public SimpleRecommender(DataSet dataSet, Predictor predictor){
		super(dataSet);
		this.predictor = predictor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResultList getResultList(int userId) {
		checkUserId(userId);
		ResultList result = new ResultList(userId);
		Set<Integer> itemIds = dataSet.getItemIds();
		Set<Integer> rated = dataSet.getRelatedItemIds(userId);
		itemIds.removeAll(rated);
		for(int itemId : itemIds){
			Rate rate = predictor.getRate(userId, itemId);
			result.addRate(rate);
		}
		return result;
	}

}
