package util.impl;

import java.util.Set;

import util.DataSet;
import util.Item;
import util.ItemSet;
import util.Rate;
import util.RateSet;
import util.User;
import util.UserSet;

/**
 * This class implements the <tt>DataSet</tt> interface, backed by a <code>UserSet</code> instance, 
 * an <code>ItemSet</code> instance and a <code>RateSet</code> instance.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class SimpleDataSet implements DataSet {
	
	private UserSet users;
	private ItemSet items;
	private RateSet rates;
	
	/**
	 * Constructs an empty <tt>SimpleDataSet</tt>. 
	 */
	public SimpleDataSet(){
		users = new SimpleUserSet();
		items = new SimpleItemSet();
		rates = new SimpleRateSet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getUserIds() {
		return users.getUserIds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getItemIds() {
		return items.getItemIds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getRelatedUserIds(int itemId) {
		return rates.getRelatedUserIds(itemId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Integer> getRelatedItemIds(int userId) {
		return rates.getRelatedItemIds(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(int userId) {
		return users.getUser(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item getItem(int itemId) {
		return items.getItem(itemId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getUserCount() {
		return users.getUserCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount() {
		return items.getItemCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addRate(Rate rate) {
		if(rate == null)return false;
		int userId = rate.getUserId();
		int itemId = rate.getItemId();
		if(users.containsUser(userId) && items.containsItem(itemId)){
			return rates.addRate(rate);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User removeUser(int userId) {
		for(Rate rate : this.getAllRatesOfUser(userId)){
			removeRate(rate.getUserId(), rate.getItemId());
		}
		return users.removeUser(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addUser(User user) {
		return users.addUser(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addItem(Item item) {
		return items.addItem(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item removeItem(int itemId) {
		for(Rate rate : this.getAllRatesOfItem(itemId)){
			removeRate(rate.getUserId(), rate.getItemId());
		}
		return items.removeItem(itemId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRateCount() {
		return rates.getRateCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		return rates.getRate(userId, itemId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Rate> getAllRatesOfUser(int userId) {
		return rates.getAllRatesOfUser(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Rate> getAllRatesOfItem(int itemId) {
		return rates.getAllRatesOfItem(itemId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate removeRate(int userId, int itemId) {
		return rates.removeRate(userId, itemId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsUser(int userId) {
		return users.containsUser(userId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsItem(int itemId) {
		return items.containsItem(itemId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsRate(Rate rate) {
		return rates.containsRate(rate);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsRate(int userId, int itemId) {
		return rates.containsRate(userId, itemId);
	}

}
