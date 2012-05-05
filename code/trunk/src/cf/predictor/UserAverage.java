package cf.predictor;

import java.util.Collections;
import java.util.Set;

import matrix.SparseVector;
import matrix.Vector;
import util.DataSet;
import util.Rate;

/**
 * The class <tt>UserAverage</tt> always returns the average rating of the specified user as his ratings. 
 * @version 1.0 2012-5-4
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class UserAverage extends AbstractPredictor implements Predictor {
	
	private Vector avg;

	/**
	 * Constructs a <tt>UserAverage</tt> predictor with the given data set. 
	 */
	public UserAverage(DataSet dataSet) {
		super(dataSet);
		buildAverageVector();
	}

	private void buildAverageVector() {
		int length = Collections.max(dataSet.getUserIds()) + 1;
		avg = new SparseVector(length);
		for(int userId : dataSet.getUserIds()){
			Set<Rate> rates = dataSet.getAllRatesOfUser(userId);
			double sum = 0;
			for(Rate rate : rates){
				sum += rate.getRating();
			}
			if(rates.size() != 0){
				avg.setValue(userId, sum/rates.size());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		return new Rate(userId, itemId, avg.getValue(userId));
	}
	
	/**
	 * Returns average rating of the specified user id. 
	 */
	public double getAvgRating(int userId) {
		return avg.getValue(userId);
	}

}
