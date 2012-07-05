package core;

import java.util.Set;

/**
 * The interface <tt>RateSet</tt> presents a set of <tt>Rate</tt> objects in Recommender Systems.
 * It allows to index <tt>Rate</tt> objects through the related <tt>itemId</tt> or <tt>userId</tt>. 
 * It also does not permit the <tt>null</tt> element or duplicate elements.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface RateSet {
	
	/**
	 * Returns the number of rates in this rate set.
	 */
	public abstract int getRateCount();	
	
	/**
	 * Return the rate to which the specified user id and item id are mapped, 
	 * or null if this rate set contains no such a rate. 
	 */	
	public abstract Rate getRate(int userId, int itemId);
	
	/**
	 * Return all the rate related to the specified user id, 
	 * the set may be empty if this rate set contains no such a user id. 
	 */
	public abstract Set<Rate> getAllRatesOfUser(int userId);
	
	/**
	 * Return all the rate related to the specified item id, 
	 * the set may be empty if this rate set contains no such a item id. 
	 */
	public abstract Set<Rate> getAllRatesOfItem(int itemId);
	
	/**
	 * Return all the user ids related to the specified item id, 
	 * the set may be empty if this rate set contains no such a item id. 
	 */
	public abstract Set<Integer> getRelatedUserIds(int itemId);
	
	/**
	 * Return all the user ids related to the specified item id, 
	 * the set may be empty if this rate set contains no such a user id. 
	 */
	public abstract Set<Integer> getRelatedItemIds(int userId);
	
	/**
	 * Remove the rate associated with the user id and the item id from this rate set. 
	 * @return the removed rate associated with the ids, 
	 * or null if this rate set contains no mapping for the ids
	 */		
	public abstract Rate removeRate(int userId, int itemId);
	
	/**
	 * Add a rate into this rate set. 
	 * If there is a rate r in this rate set meeting 
	 * <code>r.getUserId()==rate.getUserId() && r.getItemId==rate.getItemId()</code>, 
	 * r will be replaced by rate. 
	 * If <code>rate==null</code>, this rate set will not be modified and return false¡£ 
	 */	
	public abstract boolean addRate(Rate rate);
	
	/**
	 * Returns true if this rate set contains a rate r which satisfy <code>r.equals(rate)</code>.
	 */	
	public abstract boolean containsRate(Rate rate);
	
	/**
	 * Returns true if this rate set contains a rate r which satisfy 
	 * <code>r.getUserId()==userId && r.getItemId()==itemId</code>.
	 */	
	public abstract boolean containsRate(int userId, int itemId);
}
