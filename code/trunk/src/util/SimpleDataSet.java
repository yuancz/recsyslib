package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleDataSet implements DataSet {
	
	private HashMap<Integer, User> userMap;
	private HashMap<Integer, Item> itemMap;
	
	public SimpleDataSet(){
		userMap = new HashMap<>();
		itemMap = new HashMap<>();
	}

	@Override
	public Set<User> getUsers() {
		return new HashSet<User>(userMap.values());
	}

	@Override
	public Set<Item> getItems() {
		return new HashSet<Item>(itemMap.values());
	}

	@Override
	public Set<Integer> getUserIds() {
		return userMap.keySet();
	}

	@Override
	public Set<Integer> getItemIds() {
		return itemMap.keySet();
	}

	@Override
	public Set<User> getRelatedUsers(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Item> getRelatedItems(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rate getRate(User user, Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Rate> getAllRates(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Rate> getAllRates(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
