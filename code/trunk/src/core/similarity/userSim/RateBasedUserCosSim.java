package cf.similarity.userSim;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cf.similarity.UserSimilarity;

import matrix.SparseVector;
import matrix.Vector;
import matrix.Vectors;

import util.DataSet;
import util.Rate;

/**
 * The class <tt>RateBasedUserCosSim</tt> implements the interface <tt>UserSimilarity</tt>, 
 * The similarity is computed by the rates of two specified users. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public class RateBasedUserCosSim extends UserSimilarity {
	
	private Map<Integer, Vector> userRates;
	
	/**
	 * Constructs a instance based on the given DataSet.
	 */
	public RateBasedUserCosSim(DataSet dataSet){
		super(dataSet);
		userRates = new HashMap<>();
		int length = Collections.max(dataSet.getItemIds())+1;
		for(int userId : dataSet.getUserIds()){
			Set<Rate> rates = dataSet.getAllRatesOfUser(userId);
			Vector vec = ratesToVector(rates, length);
			userRates.put(userId, vec);
		}
	}

	private Vector ratesToVector(Set<Rate> rates, int length) {
		Vector vec = new SparseVector(length);
		for(Rate rate : rates){
			vec.setValue(rate.getItemId(), rate.getRating());
		}
		return vec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSimilarity(int id1, int id2) {
		checkUserId(id1);
		checkUserId(id2);
		Vector v1 = userRates.get(id1);
		Vector v2 = userRates.get(id2);
		return Vectors.cos(v1,v2);
	}

}
