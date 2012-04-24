package util;

import java.util.Set;

public interface UserSet {
	public abstract int getUserCount();
	public abstract Set<Integer> getUserIds();
	/**
	 * @return the user to which the specified user id is mapped, 
	 * or null if this user set contains no mapping for the user id
	 */	
	public abstract User getUser(int userId);
	/**
	 * Add a user into this user set. 
	 * If there is a user u in this user set meeting <code>u.equals(user)</code> , 
	 * or <code>user==null</code>, this user set will not be modified and return false¡£ 
	 */	
	public abstract boolean addUser(User user);
	/**
	 * Remove the user associated with the user id from this user set. 
	 * @return the removed user associated with the user id, 
	 * or null if this user set contains no mapping for the user id
	 */	
	public abstract User removeUser(int userId);
	public abstract boolean containsUser(int userId);
}
