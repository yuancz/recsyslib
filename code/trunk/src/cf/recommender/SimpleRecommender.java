package cf.recommender;

import java.util.LinkedList;
import java.util.List;

import cf.predictor.Predictor;
import util.ItemSet;
import util.Rate;

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

	@Override
	public List<Rate> getResultList(int userId, ItemSet items) {
		List<Rate> result = new LinkedList<Rate>();
		for(int itemId : items.getItemIds()){
			Rate rate = predictor.getRate(userId, itemId);
			int index = indexing(rate, result);
			result.add(index, rate);
		}
		return result;
	}
	
	private int indexing(Rate rate, List<Rate> list){
		if(list.size()==0)return 0;
		for(int i = 0;i<list.size();i++){
			if(rate.getRating() > list.get(i).getRating())return i;
		}
		return list.size();
	}

}
