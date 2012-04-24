package util;

import java.util.Set;

import util.impl.Rate;

public interface RateSet {
	public abstract int getRateCount();		
	public abstract Rate getRate(int userId, int itemId);
	public abstract Set<Rate> getAllRatesOfUser(int userId);
	public abstract Set<Rate> getAllRatesOfItem(int itemId);
	public abstract Set<Integer> getRelatedUserIds(int itemId);
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
	public abstract boolean containsRate(Rate rate);
}
