package cf.predictor;

import java.util.Collections;
import java.util.LinkedList;

import matrix.Matrix;
import matrix.SparseMatrix;
import util.DataSet;
import util.Rate;

/**
 * The class <tt>SlopeOnePredictor</tt> implements the Slope One prediction method. 
 * More about Slope One method, see the paper:
 * <br>Daniel Lemire, Anna Maclachlan. "Slope One Predictors for Online Rating-Based Collaborative Filtering". 
 * Proceedings of SIAM Data Mining (SDM'05), 2005.  
 * @version 1.0 2012-5-4
 * @author Tan Chang
 * @since JDK 1.7
 */
public class SlopeOnePredictor extends AbstractPredictor implements Predictor {

	protected Matrix devMat;// deviation matrix, I*I
	
	protected Matrix cardMat;// card matrix, I*I
	
	protected UserAverage userAvg;// user average predictor
	
	/**
	 * Constructs a <tt>SlopeOnePredictor</tt> with the given data set. 
	 */
	public SlopeOnePredictor(DataSet dataSet) {
		super(dataSet);
		userAvg = new UserAverage(dataSet);
		buildDeviationMatrix();
	}

	private void buildDeviationMatrix() {
		int row = Collections.max(dataSet.getItemIds()) +1;
		devMat = new SparseMatrix(row, row);
		cardMat = new SparseMatrix(row, row);
		LinkedList<Integer> iidList = new LinkedList<>(dataSet.getItemIds());
		for(int i = 0;i<iidList.size();i++){
			int iid1 = iidList.get(i);
			for(int j = i+1;j<iidList.size();j++){				
				int iid2 = iidList.get(j);
				double devSum = 0;
				int card = 0;
				for(int userId : dataSet.getUserIds()){
					Rate r1 = dataSet.getRate(userId, iid1);
					Rate r2 = dataSet.getRate(userId, iid2);
					if(r1 != null && r2 != null){
						devSum += (r1.getRating() - r2.getRating());
						card++;
					}
				}
				if(card > 0){
					devMat.setValue(iid1, iid2, devSum/card);
					devMat.setValue(iid2, iid1, -devSum/card);
					cardMat.setValue(iid1, iid2, card);
					cardMat.setValue(iid2, iid1, card);
				}
			}
		}
	}

	@Override
	public Rate getRate(int userId, int itemId) {
		double sumDev = 0;
		int cnt = 0;
		for(int related : dataSet.getRelatedItemIds(userId)){
			if(related != itemId){
				double dev = devMat.getValue(itemId, related);
				if(dev != Matrix.ZERO){
					sumDev += dev;
					cnt++;
				}
			}
		}
		double rating = userAvg.getAvgRating(userId);
		if(cnt != 0)rating += sumDev/cnt;
		return new Rate(userId, itemId, rating);
	}

}
