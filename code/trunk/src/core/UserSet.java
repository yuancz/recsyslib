package core;

import java.util.Set;

/**
 * The interface <tt>UserSet</tt> presents a set of <tt>User</tt> objects in Recommender Systems.
 * It allows to index a <tt>User</tt> object through the <code>userId</code> using the method <code>getUser(int userId)</code>. 
 * It also does not permit the <tt>null</tt> element or duplicate elements.
 * <p>Noting that <tt>UserSet</tt> does not support iterating each <tt>User</tt> in it for efficiency, 
 * for example, the <tt>User</tt> object points to a database entry. 
 * In most cases, recommendation algorithms just need the <code>userId</code> to define a <tt>User</tt> object, 
 * and the method <code>getUserIds()</code> provides the set of user ids for iterating. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see User
 */
public interface UserSet {
	
	/**
	 * Returns the number of users in this user set.
	 */
	public abstract int getUserCount();
	
	/**
	 * Returns a <tt>Set</tt> view of the user ids contained in this user set.
	 */
	public abstract Set<Integer> getUserIds();
	
	/**
	 * Return the user to which the specified user id is mapped, 
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
	
	/**
	 * Returns true if this <tt>UserSet</tt> contains a <tt>User</tt> for the specified userId.
	 */		
	public abstract boolean containsUser(int userId);
}
