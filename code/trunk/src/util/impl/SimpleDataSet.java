package util.impl;

import java.util.Set;

import util.DataSet;
import util.Item;
import util.ItemSet;
import util.Rate;
import util.RateSet;
import util.User;
import util.UserSet;

public class SimpleDataSet implements DataSet {
	
	private UserSet users;
	private ItemSet items;
	private RateSet rates;
	
	public SimpleDataSet(){
		users = new SimpleUserSet();
		items = new SimpleItemSet();
		rates = new SimpleRateSet();
	}

	@Override
	public Set<Integer> getUserIds() {
		return users.getUserIds();
	}

	@Override
	public Set<Integer> getItemIds() {
		return items.getItemIds();
	}

	@Override
	public Set<Integer> getRelatedUserIds(int itemId) {
		return rates.getRelatedUserIds(itemId);
	}

	@Override
	public Set<Integer> getRelatedItemIds(int userId) {
		return rates.getRelatedItemIds(userId);
	}

	@Override
	public User getUser(int userId) {
		return users.getUser(userId);
	}

	@Override
	public Item getItem(int itemId) {
		return items.getItem(itemId);
	}

	@Override
	public int getUserCount() {
		return users.getUserCount();
	}

	@Override
	public int getItemCount() {
		return items.getItemCount();
	}

	@Override
	public boolean addRate(Rate rate) {
		boolean canAdd = rates.addRate(rate);
		if(canAdd){
			User user = rate.getUser();
			Item item = rate.getItem();
			users.addUser(user);
			items.addItem(item);
		}
		return canAdd;
	}

	@Override
	public User removeUser(int userId) {
		return users.removeUser(userId);
	}

	@Override
	public boolean addUser(User user) {
		return users.addUser(user);
	}

	@Override
	public boolean addItem(Item item) {
		return items.addItem(item);
	}

	@Override
	public Item removeItem(int itemId) {
		return items.removeItem(itemId);
	}

	@Override
	public int getRateCount() {
		return rates.getRateCount();
	}

	@Override
	public Rate getRate(int userId, int itemId) {
		return rates.getRate(userId, itemId);
	}

	@Override
	public Set<Rate> getAllRatesOfUser(int userId) {
		return rates.getAllRatesOfUser(userId);
	}

	@Override
	public Set<Rate> getAllRatesOfItem(int itemId) {
		return rates.getAllRatesOfItem(itemId);
	}

	@Override
	public Rate changeRate(Rate rate) {
		return rates.changeRate(rate);
	}

	@Override
	public Rate removeRate(int userId, int itemId) {
		return rates.removeRate(userId, itemId);
	}

}
