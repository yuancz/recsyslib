package cf.similarity;

import util.DataSet;
import util.RecSysLibException;

/**
 * The class <tt>ItemSimilarity</tt> provides a skeletal implementation of the <tt>Similarity</tt> interface 
 * for computing the similarity of two specified items.
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public abstract class ItemSimilarity implements Similarity {
	
	protected DataSet dataSet;
	
	/**
	 * Constructs a <tt>ItemSimilarity</tt> object based on the given DataSet.
	 */
	public ItemSimilarity(DataSet dataSet){
		this.dataSet = dataSet;
	}
	
	/**
	 * Checks whether or not the specified item ids exists in the data set. 
	 */
	protected void checkItemId(int id){
		if(!dataSet.containsItem(id))
			throw new RecSysLibException("This item id does not exist in the specified data set: "+id);
	}
	
	/**
	 * Returns the similarity of two items with the specified id1 and id2
	 */
	public abstract double getSimilarity(int id1, int id2);
}
