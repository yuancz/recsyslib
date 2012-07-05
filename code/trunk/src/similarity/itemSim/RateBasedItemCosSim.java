package similarity.itemSim;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import similarity.ItemSimilarity;

import matrix.SparseVector;
import matrix.Vector;
import matrix.Vectors;

import core.DataSet;
import core.Rate;

/**
 * The class <tt>RateBasedItemCosSim</tt> implements the interface <tt>ItemSimilarity</tt>, 
 * The similarity is computed by the rates of two specified items. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public class RateBasedItemCosSim extends ItemSimilarity {
	
	private Map<Integer, Vector> itemRates;
	
	/**
	 * Constructs a instance based on the given DataSet.
	 */
	public RateBasedItemCosSim(DataSet dataSet){
		super(dataSet);
		itemRates = new HashMap<>();
		int length = Collections.max(dataSet.getUserIds())+1;
		for(int itemId : dataSet.getItemIds()){
			Set<Rate> rates = dataSet.getAllRatesOfItem(itemId);
			Vector vec = ratesToVector(rates, length);
			itemRates.put(itemId, vec);
		}
	}

	private Vector ratesToVector(Set<Rate> rates, int length) {
		Vector vec = new SparseVector(length);
		for(Rate rate : rates){
			vec.setValue(rate.getUserId(), rate.getRating());
		}
		return vec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSimilarity(int id1, int id2) {
		checkItemId(id1);
		checkItemId(id2);
		Vector v1 = itemRates.get(id1);
		Vector v2 = itemRates.get(id2);
		return Vectors.cos(v1,v2);
	}

}
