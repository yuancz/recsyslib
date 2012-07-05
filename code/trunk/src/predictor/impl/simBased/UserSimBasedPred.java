package predictor.impl.simBased;

import similarity.UserSimilarity;
import core.DataSet;
import core.Rate;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import predictor.AbstractPredictor;
import predictor.SimBasedPredictor;

import collections.Pair;

/**
 * The class <tt>UserSimBasedPred</tt> predicts a rating of a user assigning to an item based on the similarity of related users.
 * More specifically, k users most similar to a user (k nearest neighbors) will be saved. 
 * When predicting a user rating an item, the prediction rating will be computed 
 * based on the ratings which those similar user rating the specified item. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 * @see UserSimilarity
 */
public class UserSimBasedPred extends AbstractPredictor implements SimBasedPredictor {
	
	protected UserSimilarity userSim;
	
	protected int k;
	
	protected Map<Integer, Set<Pair<Integer, Double>>> knn;
	
	/**
	 * Constructs a <tt>UserSimBasedPred</tt> with given parameters. 
	 * Parameter k means k nearest neighbors will be saved for prediction. 
	 */
	public UserSimBasedPred(DataSet dataSet, UserSimilarity userSim, int k){
		super(dataSet);
		this.userSim = userSim;
		this.k = k;
		buildKNN();
	}
	
	private class ValueComparator implements Comparator<Pair<Integer,Double>>{
		@Override
		public int compare(Pair<Integer,Double> p1, Pair<Integer,Double> p2) {
			return Double.compare(p1.getValue(), p2.getValue());
		}
	}
	
	private void buildKNN(){
		knn = new HashMap<Integer, Set<Pair<Integer, Double>>>();
		List<Pair<Integer, Double>> simList = new LinkedList<>();
		for(int userId : dataSet.getUserIds()){
			for(int neighbor : dataSet.getUserIds()){
				if(userId != neighbor){
					simList.add(new Pair<Integer, Double>(neighbor, userSim.getSimilarity(userId, neighbor)));
				}
			}
			Collections.sort(simList, new ValueComparator());
			Set<Pair<Integer, Double>> neighbors = new HashSet<>();
			for(int i = 0;i<k;i++){
				neighbors.add(simList.get(simList.size() - i - 1));
			}
			knn.put(userId, neighbors);
			simList.clear();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		checkUserId(userId);
		checkItemId(itemId);
		double sum = 0;
		double simSum = 0;
		for(Pair<Integer, Double> pair : knn.get(userId)){
			int neighbor = pair.getKey();
			double sim = pair.getValue();
			Rate rate = dataSet.getRate(neighbor, itemId);
			if(rate != null){
				sum += rate.getRating() * sim;
				simSum += sim;
			}
		}
		return new Rate(userId, itemId, sum / simSum);
	}

}
