package cf.similarity;

import util.DataSet;
import util.RecSysLibException;

/**
 * The class <tt>UserSimilarity</tt> provides a skeletal implementation of the <tt>Similarity</tt> interface 
 * for computing the similarity of two specified users.
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public abstract class UserSimilarity implements Similarity {
	
	protected DataSet dataSet;
	
	/**
	 * Constructs a <tt>UserSimilarity</tt> object based on the given DataSet.
	 */
	public UserSimilarity(DataSet dataSet){
		this.dataSet = dataSet;
	}
	
	/**
	 * Checks whether or not the specified user ids exists in the data set. 
	 */
	protected void checkUserId(int id){
		if(!dataSet.containsUser(id))
			throw new RecSysLibException("This user id does not exist in the specified data set: "+id);
	}
	
	/**
	 * Returns the similarity of two users with the specified id1 and id2
	 */
	public abstract double getSimilarity(int id1, int id2);
}
