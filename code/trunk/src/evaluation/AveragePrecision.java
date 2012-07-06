package evaluation;

import core.Rate;
import core.RecSysLibException;
import core.ResultList;
import core.ResultSet;

/**
 * The implementation of Average Precision (AP) and Mean Average Precision (MAP). 
 * More about the AP and MAP, see the paper: <br>
 * Zhu, M. "Recall, precision and average precision". 
 * Department of Statistics and Actuarial Science, University of Waterloo. 2004. 
 * @version 1.0 2012-6-25
 * @author Tan Chang
 * @since JDK 1.7
 */
public class AveragePrecision extends Evaluator {
	
	public AveragePrecision(ResultSet realSet, ResultSet recSet) {
		super(realSet, recSet);
	}

	public double getAPForUser(int userId, int n){
		checkN(n);
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
	
	public double getMAP(int n){
		checkN(n);
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getAPForUser(userId, n);
		}
		return sum/realSet.getUserIds().size();
	}
	
	private void checkN(int n){
		if(n<=0)
			throw new RecSysLibException("Illegal parameter value of n");	
	}
}
