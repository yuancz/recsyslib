package evaluation;

import core.RecSysLibException;
import core.ResultSet;

/**
 * The implementation of the Normalized Discounted Cumulative Gain (NDCG). 
 * More about NDCG, see the paper: <br>
 * Xie, M. and Lakshmanan, L.V.S. and Wood, P.T. "Breaking out of the box of recommendations: 
 * from items to packages". RecSys, 2010. 
 * @version 1.0 2012-7-6
 * @author Tan Chang
 * @since JDK 1.7
 */
public class NormalizedDiscountedCumulativeGain extends Evaluator {

	public NormalizedDiscountedCumulativeGain(ResultSet realSet,
			ResultSet recSet) {
		super(realSet, recSet);
	}
	
	public double getNDCG(int k){
		checkK(k);
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getNDCGForUser(userId, k);
		}
		return sum/realSet.getUserIds().size();
	}
	
	public double getNDCGForUser(int userId, int k) {
		checkK(k);	
		//TODO
		return 0;
		
	}
	
	private void checkK(int k){
		if(k<=0)
			throw new RecSysLibException("Illegal parameter value of k");	
	}

}
