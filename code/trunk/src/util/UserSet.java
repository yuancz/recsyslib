package util;

import java.util.Set;


/**
 * This interface extends the <tt>Set</tt> interface to present a set of <tt>User</tt> objects.
 * It allows to index a <tt>User</tt> object through the <code>uid</code> using the method <code>getUser(int uid)</code>. 
 * 
 * @version 1.0 2012-4-10
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
