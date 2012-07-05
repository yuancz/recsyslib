package core.impl;

import java.util.HashMap;
import java.util.Set;

import core.User;
import core.UserSet;

/**
 * This class implements the <tt>UserSet</tt> interface, 
 * backed by a <code>HashMap<Integer, User></code> instance.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see User
 * @see HashMap
 */
public class SimpleUserSet implements UserSet {
	
	private HashMap<Integer, User> map;
	
	public SimpleUserSet(){
		map = new HashMap<>();
	}
	
	@Override
	public boolean addUser(User user){
		if(user == null)return false;
		int userId = user.getUserId();
		if(!map.containsKey(userId)){
			map.put(userId, user);
			return true;
		}
		return false;
	}
	
	@Override
	public User removeUser(int userId){
		return map.remove(userId);
	}
	
	@Override
	public int getUserCount() {
		return map.size();
	}

	@Override
	public Set<Integer> getUserIds() {
		return map.keySet();
	}

	@Override
	public User getUser(int userId) {
		return map.get(userId);
	}

	@Override
	public boolean containsUser(int userId) {
		return map.containsKey(userId);
	}

}
