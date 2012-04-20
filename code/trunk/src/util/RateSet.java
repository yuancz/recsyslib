package util;

import java.util.Set;

public interface RateSet {
	public abstract int getRateCount();		
	public abstract Rate getRate(int userId, int itemId);
	public abstract Set<Rate> getAllRatesOfUser(int userId);
	public abstract Set<Rate> getAllRatesOfItem(int itemId);
	public abstract Set<Integer> getRelatedUserIds(int itemId);
	public abstract Set<Integer> getRelatedItemIds(int userId);
	public abstract Rate changeRate(Rate rate);
	public abstract Rate removeRate(int userId, int itemId);
	public abstract boolean addRate(Rate rate);
}
