package core.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import core.RateSet;
import core.Rate;

/**
 * This class implements the <tt>RateSet</tt> interface, 
 * backed by a <code>HashMap<Integer, HashMap<Integer, Rate>></code> instance.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Rate
 * @see RateSet
 * @see HashMap
 */
public class SimpleRateSet implements RateSet {
	
	private HashMap<Integer, HashMap<Integer, Rate>> map;
	
	public SimpleRateSet(){
		map = new HashMap<>();
	}
	
	@Override
	public boolean addRate(Rate rate){
		if(rate == null)return false;
		int userId = rate.getUserId();
		int itemId = rate.getItemId();
		if(!map.containsKey(userId))map.put(userId, new HashMap<Integer, Rate>());
		map.get(userId).put(itemId, rate);
		return true;
	}
	
	@Override
	public Rate removeRate(int userId, int itemId){
		if(map.containsKey(userId) && map.get(userId).containsKey(itemId)){
			Rate rate = map.get(userId).remove(itemId);
			if(map.get(userId).size() == 0)map.remove(userId);
			return rate;
		}
		return null;		
	}

	@Override
	public int getRateCount() {
		int sum = 0;
		for(int userId : map.keySet()){
			sum += map.get(userId).size();
		}
		return sum;
	}

	@Override
	public Rate getRate(int userId, int itemId) {
		Rate rate = null;
		if(map.containsKey(userId) && map.get(userId).containsKey(itemId))
			rate = map.get(userId).get(itemId);
		return rate;
	}

	@Override
	public Set<Rate> getAllRatesOfUser(int userId) {
		HashSet<Rate> rates = new HashSet<>();
		if(map.containsKey(userId)){
			rates.addAll(map.get(userId).values());
		}
 		return rates;
	}

	@Override
	public Set<Rate> getAllRatesOfItem(int itemId) {
		HashSet<Rate> rates = new HashSet<>();
		for(int userId : map.keySet()){
			if(map.get(userId).containsKey(itemId))
				rates.add(map.get(userId).get(itemId));
		}
 		return rates;
	}

	@Override
	public Set<Integer> getRelatedUserIds(int itemId) {
		HashSet<Integer> users = new HashSet<>();
		for(int userId : map.keySet()){
			if(map.get(userId).containsKey(itemId))
				users.add(userId);
		}
		return users;
	}

	@Override
	public Set<Integer> getRelatedItemIds(int userId) {
		HashSet<Integer> items = new HashSet<>();
		if(map.containsKey(userId)){
			for(int itemId : map.get(userId).keySet()){
				items.add(itemId);
			}
		}
		return items;
	}

	@Override
	public boolean containsRate(Rate rate) {
		if(rate == null)return false;
		int userId = rate.getUserId();
		int itemId = rate.getItemId();
		return containsRate(userId, itemId)
				&& map.get(userId).get(itemId).equals(rate);
	}

	@Override
	public boolean containsRate(int userId, int itemId) {
		return map.containsKey(userId) 
				&& map.get(userId).containsKey(itemId);
	}

}
