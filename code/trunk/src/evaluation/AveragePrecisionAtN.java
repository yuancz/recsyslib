package evaluation;

import core.Rate;
import core.ResultList;
import core.ResultSet;

/**
 * The implementation of the average precision at n. More about the average precision at n, see the paper: <br>
 * Zhu, M. "Recall, precision and average precision". 
 * Department of Statistics and Actuarial Science, University of Waterloo. 2004. 
 * @version 1.0 2012-6-25
 * @author Tan Chang
 * @since JDK 1.7
 */
public class AveragePrecisionAtN extends Evaluator {

	private int n;
	
	public AveragePrecisionAtN(ResultSet realSet, ResultSet recSet, int n) {
		super(realSet, recSet);
		this.n = n;
	}

	public double getAPNForUser(int userId){
		ResultList realList = realSet.getResultList(userId);
		ResultList recList = recSet.getResultList(userId);
		double apSum = 0;
		double cnt = 0;
		for(int i = 0;i<recList.size();i++){
			Rate rate = recList.getRate(i);
			int itemId = rate.getItemId();
			int index = recList.getIndex(itemId);
			if(index != -1 && realList.getRate(index).getRating() > 0){
				cnt++;
				if(i<n)apSum += cnt/(i+1);
			}
		}
		if(apSum == 0)return 0;
		return apSum/cnt;
	}
	
	public double getAPN(){
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getAPNForUser(userId);
		}
		return sum/realSet.getUserIds().size();
	}
}
