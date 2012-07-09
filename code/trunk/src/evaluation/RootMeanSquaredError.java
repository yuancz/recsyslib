package evaluation;

import core.Rate;
import core.ResultList;
import core.ResultSet;

/**
 * The implementation of the Root Mean Squared Error (RMSE). 
 * More about RMSE, see the paper: <br>
 * Koren, Y. "Collaborative filtering with temporal dynamics". 
 * Communications of the ACM, 2010. 
 * @version 1.0 2012-7-9
 * @author Tan Chang
 * @since JDK 1.7
 */
public class RootMeanSquaredError extends Evaluator {

	public RootMeanSquaredError(ResultSet realSet, ResultSet recSet) {
		super(realSet, recSet);
	}
	
	public double getRMSE(){
		double sum = 0;
		int cnt = 0;
		for(int userId : realSet.getUserIds()){
			ResultList realList = realSet.getResultList(userId);
			ResultList recList = recSet.getResultList(userId);
			cnt += realList.size();
			for(int i = 0;i<realList.size();i++){
				Rate realRate = realList.getRate(i);
				int index = recList.getIndex(realRate.getItemId());
				double recRating = recList.getRate(index).getRating();
				double diff = recRating - realRate.getRating();
				sum += diff*diff;
			}
		}
		return Math.sqrt(sum/cnt);
	}

}
