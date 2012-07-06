package predictor.impl.average;

import java.util.Collections;
import java.util.Set;

import predictor.AbstractPredictor;

import predictor.Predictor;

import matrix.SparseVector;
import matrix.Vector;
import core.DataSet;
import core.Rate;

/**
 * The class <tt>ItemAverage</tt> always returns the average rating of the specified item as its ratings. 
 * @version 1.0 2012-5-4
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class ItemAverage extends AbstractPredictor implements Predictor {
	
	private Vector avg;

	/**
	 * Constructs a <tt>ItemAverage</tt> predictor with the given data set. 
	 */
	public ItemAverage(DataSet dataSet) {
		super(dataSet);
		buildAverageVector();
	}

	private void buildAverageVector() {
		int length = Collections.max(dataSet.getItemIds()) + 1;
		avg = new SparseVector(length);
		for(int itemId : dataSet.getItemIds()){
			Set<Rate> rates = dataSet.getAllRatesOfItem(itemId);
			double sum = 0;
			for(Rate rate : rates){
				sum += rate.getRating();
			}
			if(rates.size() != 0){
				avg.setValue(itemId, sum/rates.size());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		checkUserId(userId);
		checkItemId(itemId);
		return new Rate(userId, itemId, avg.getValue(userId));
	}
	
	/**
	 * Returns average rating of the specified item id. 
	 */
	public double getAvgRating(int itemId) {
		return avg.getValue(itemId);
	}

}
