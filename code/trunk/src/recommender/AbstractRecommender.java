package recommender;

import java.util.Set;

import core.DataSet;
import core.RecSysLibException;
import core.ResultList;
import core.ResultSet;

/**
 * The class <tt>AbstractRecommender</tt> provides a skeletal implementation of the <tt>Recommender</tt> interface.
 * It provides checkUserId method for checking whether or not the id exist in the given data set. 
 * It also implements the <tt>getResultSet</tt> method. 
 * To implement a <tt>Recommender</tt> object, the programmer should implement the <tt>getResultList</tt> method. 
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 * @see Recommender
 */
public abstract class AbstractRecommender implements Recommender {

	protected DataSet dataSet;
	
	/**
	 * Constructs a recommender with the given data set. 
	 */
	public AbstractRecommender(DataSet dataSet){
		this.dataSet = dataSet;
	}
	
	/**
	 * Checks whether or not the specified user id exists in the data set. 
	 */
	protected void checkUserId(int userId){
		if(!dataSet.containsUser(userId))
			throw new RecSysLibException("This user id does not exist in the specified data set: "+userId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResultSet getResultSet(Set<Integer> userIds) {
		ResultSet resultSet = new ResultSet();
		for(int userId : userIds){
			ResultList resultList = getResultList(userId);
			resultSet.addResultList(resultList);
		}
		return resultSet;
	}

}
