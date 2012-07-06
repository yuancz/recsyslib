package evaluation;

import java.util.HashSet;
import java.util.Set;

import core.Rate;
import core.ResultList;
import core.ResultSet;

/**
 * The implementation of the Degree Of Agreement (DOA). More about DOA, see the paper: <br>
 * Fouss, F. and Pirotte, A. and Renders, J.M. and Saerens, M. "Random-walk computation of 
 * similarities between nodes of a graph with application to collaborative recommendation". 
 * TKDE, 2007. 
 * @version 1.0 2012-7-6
 * @author Tan Chang
 * @since JDK 1.7
 */
public class DegreeOfAgreement extends Evaluator {

	public DegreeOfAgreement(ResultSet realSet, ResultSet recSet) {
		super(realSet, recSet);
	}

	public double getDOA(){
		double sum = 0;
		for(int userId : realSet.getUserIds()){
			sum += getDOAForUser(userId);
		}
		return sum/realSet.getUserIds().size();
	}
	
	public double getDOAForUser(int userId){
		//items in test set
		Set<Integer> testSet = new HashSet<Integer>();		
		ResultList realList = realSet.getResultList(userId);
		for(int i = 0;i<realList.size();i++){
			Rate rate = realList.getRate(i);
			testSet.add(rate.getItemId());
		}
		//items not in test set nor train set
		Set<Integer> noRated = new HashSet<Integer>();
		ResultList recList = recSet.getResultList(userId);
		for(int i = 0;i<recList.size();i++){
			Rate rate = recList.getRate(i);
			noRated.add(rate.getItemId());
		}
		noRated.removeAll(testSet);
		if(noRated.size() == 0)return 1;
		//check the rank 
		double cnt = 0;
		for(int iid1 : testSet){
			for(int iid2 : noRated){
				if(isAhead(userId,iid1,iid2))cnt++;
			}
		}
		return cnt/(testSet.size()*noRated.size());
	}
	
	private boolean isAhead(int userId, int iid1, int iid2){
		ResultList recList = recSet.getResultList(userId);
		int index1 = recList.getIndex(iid1);
		int index2 = recList.getIndex(iid2);
		return index1 < index2;
	}
	
}
