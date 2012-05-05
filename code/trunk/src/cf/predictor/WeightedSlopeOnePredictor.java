package cf.predictor;

import matrix.Matrix;
import util.DataSet;
import util.Rate;

/**
 * The class <tt>WeightedSlopeOnePredictor</tt> implements the Weighted Slope One prediction method. 
 * More about Slope One method, see the paper:
 * <br>Daniel Lemire, Anna Maclachlan. "Slope One Predictors for Online Rating-Based Collaborative Filtering". 
 * Proceedings of SIAM Data Mining (SDM'05), 2005.  
 * @version 1.0 2012-5-4
 * @author Tan Chang
 * @since JDK 1.7
 */
public class WeightedSlopeOnePredictor extends SlopeOnePredictor {
	
	/**
	 * Constructs a <tt>WeightedSlopeOnePredictor</tt> with the given data set. 
	 */
	public WeightedSlopeOnePredictor(DataSet dataSet) {
		super(dataSet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		double devSum = 0;
		double weightSum = 0;
		for(int related : dataSet.getRelatedItemIds(userId)){
			if(related != itemId){
				double dev = devMat.getValue(itemId, related);
				if(dev != Matrix.ZERO){
					double card = cardMat.getValue(itemId, related);
					double rating = dataSet.getRate(userId, related).getRating();
					devSum += (rating + dev) * card;
					weightSum += card;
				}
			}
		}
		double rating = userAvg.getAvgRating(userId);
		if(weightSum != 0)rating = devSum/weightSum;
		return new Rate(userId, itemId, rating);
	}

}
