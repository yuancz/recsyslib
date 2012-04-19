package util;

import java.util.Set;

public interface DataSet {
	
	abstract Set<User> getUsers();
	abstract Set<Item> getItems();
	
	abstract Set<Integer> getUserIds();
	abstract Set<Integer> getItemIds();
	
	abstract Set<User> getRelatedUsers(Item item);
	abstract Set<Item> getRelatedItems(User user);
	
	abstract User getUser(int userId);
	abstract Item getItem(int itemId);
	
	abstract Rate getRate(User user, Item item);
	abstract Set<Rate> getAllRates(User user);
	abstract Set<Rate> getAllRates(Item item);

}
