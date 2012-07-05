package cf.similarity.itemSim;

import java.util.Set;

import cf.similarity.ItemSimilarity;

import util.DataSet;

/**
 * The class <tt>UserBasedItemSim</tt> implements the interface <tt>ItemSimilarity</tt>, 
 * The similarity is the set similarity of the related user set of two specified items, 
 * computed by the size of the intersection divided by the union. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public class UserBasedItemSim extends ItemSimilarity {
	
	/**
	 * Constructs a instance based on the given DataSet.
	 */
	public UserBasedItemSim(DataSet dataSet){
		super(dataSet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSimilarity(int id1, int id2) {
		checkItemId(id1);
		checkItemId(id2);
		Set<Integer> s1 = dataSet.getRelatedUserIds(id1);
		Set<Integer> s2 = dataSet.getRelatedUserIds(id2);
		int and = 0;
		for(int userId : s1){
			if(s2.contains(userId))and++;
		}
		return and / (double)(s1.size() + s2.size() - and);
	}

}
