package cf.predictor;

import util.DataSet;
import util.RecSysLibException;

/**
 * The class <tt>AbstractPredictor</tt> provides a skeletal implementation of the <tt>Predictor</tt> interface 
 * It provides checkItemId and checkUserId methods for checking whether or not these id exist in the given data set. 
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 * @see Predictor
 */
public abstract class AbstractPredictor implements Predictor {

	protected DataSet dataSet;
	
	/**
	 * Constructs a predictor with the given data set. 
	 */
	public AbstractPredictor(DataSet dataSet){
		this.dataSet = dataSet;
	}
	
	/**
	 * Checks whether or not the specified item id exists in the data set. 
	 */
	protected void checkItemId(int itemId){
		if(!dataSet.containsItem(itemId))
			throw new RecSysLibException("This item id does not exist in the specified data set: "+itemId);
	}
	
	/**
	 * Checks whether or not the specified user id exists in the data set. 
	 */
	protected void checkUserId(int userId){
		if(!dataSet.containsUser(userId))
			throw new RecSysLibException("This user id does not exist in the specified data set: "+userId);
	}

}
