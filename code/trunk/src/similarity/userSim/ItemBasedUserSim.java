package similarity.userSim;

import java.util.Set;

import similarity.UserSimilarity;

import core.DataSet;

/**
 * The class <tt>ItemBasedUserSim</tt> implements the interface <tt>UserSimilarity</tt>, 
 * The similarity is the set similarity of the related item set of two specified users, 
 * computed by the size of the intersection divided by the union. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public class ItemBasedUserSim extends UserSimilarity {
	
	/**
	 * Constructs a instance based on the given DataSet.
	 */
	public ItemBasedUserSim(DataSet dataSet){
		super(dataSet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSimilarity(int id1, int id2) {
		checkUserId(id1);
		checkUserId(id2);
		Set<Integer> s1 = dataSet.getRelatedItemIds(id1);
		Set<Integer> s2 = dataSet.getRelatedItemIds(id2);
		int and = 0;
		for(int itemId : s1){
			if(s2.contains(itemId))and++;
		}
		return and / (double)(s1.size() + s2.size() - and);
	}

}
