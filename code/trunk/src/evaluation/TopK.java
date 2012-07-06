package evaluation;

import core.RecSysLibException;
import core.ResultList;
import core.ResultSet;

/**
 * The implementation of the Top-K. More about Top-K, see the paper: <br>
 * Koren, Y. Factorization meets the neighborhood: a multifaceted collaborative filtering model. 
 * KDD, 2008. 
 * @version 1.0 2012-7-6
 * @author Tan Chang
 * @since JDK 1.7
 */
public class TopK extends Evaluator {

	public TopK(ResultSet realSet, ResultSet recSet) {
		super(realSet, recSet);
	}
	
	private void checkK(int k, boolean isPercent){
		if(isPercent){
			if(k<=0 || k>100)
				throw new RecSysLibException("Illegal parameter value of k");
		}
		else if(k<=0)
			throw new RecSysLibException("Illegal parameter value of k");	
	}

	/**
	 * Returns returns Top-K%. 
	 * @throws RecSysLibException if k<=0 or k>100
	 */
	public double getTopKPercent(int kPercent){
		checkK(kPercent, true);
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getTopKPercentForUser(userId, kPercent);
		}
		return sum/realSet.getUserIds().size();
	}
	
	/**
	 * Returns returns Top-K. 
	 * @throws RecSysLibException if k<=0
	 */
	public double getTopK(int k){
		checkK(k, false);
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getTopKForUser(userId, k);
		}
		return sum/realSet.getUserIds().size();
	}
	
	public double getTopKForUser(int userId, int k) {
		checkK(k, false);
		ResultList realList = realSet.getResultList(userId);
		ResultList recList = recSet.getResultList(userId);
		if(k>recList.size())k=recList.size();
		double hits = 0;
		for(int i = 0;i<k;i++){
			int itemId = recList.getRate(i).getItemId();
			if(realList.getIndex(itemId) != -1)hits++;
		}
		return hits/k;
	}
	
	public double getTopKPercentForUser(int userId, int kPercent) {
		checkK(kPercent, true);
		double total = recSet.getResultList(userId).size();
		int k = (int) (total*kPercent/100);
		return getTopKForUser(userId, k);
	}


	
}
