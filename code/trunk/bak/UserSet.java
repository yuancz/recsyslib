package util;

import java.util.Set;


/**
 * This interface extends the <tt>Set</tt> interface to present a set of <tt>User</tt> objects.
 * It allows to index a <tt>User</tt> object through the <code>uid</code> using the method <code>getUser(int uid)</code>. 
 * It also does not permit the <tt>null</tt> element or duplicate elements.
 * More formally, a <tt>UserSet</tt> object contains no pair of <tt>User</tt>
 * objects <code>u1</code> and <code>u2</code> such that
 * <code>u1.equals(u2)</code>. 
 * @version 1.0 2012-4-18
 * @author tanchang
 * @since JDK 1.7
 */
public interface UserSet extends Set<User>{

	/**
	 * Returns the <tt>User</tt> object with the specified <code>uid</code>, 
	 * or <code>null</code> if this <tt>UserSet</tt> contains no mapping for the uid.
	 * @param uid user id
	 * @return the <tt>User</tt> object with the specified <code>uid</code>
	 */
	abstract public User getUser(int uid);

}
