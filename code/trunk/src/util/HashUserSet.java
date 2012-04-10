package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class implements the <tt>Set</tt> interface for a set of <tt>User</tt> objects, 
 * backed by a hash table(actually a <tt>HashMap</tt> instance).  
 * It makes no guarantees as to the iteration order of the set.
 * This class does not permit the <tt>null</tt> element or duplicate elements.
 * More formally, a <tt>UserSet</tt> object contains no pair of <tt>User</tt> objects <code>u1</code> and <code>u2</code> such that <code>u1.equals(u2)</code>. 
 * It allows to index a <tt>User</tt> object though the <code>uid</code> using the method <code>getUser(int uid)</code>. 
 * 
 * @version 1.0 2012-4-10
 * @author tanchang
 * @since JDK 1.7
 * @see Set, HashSet, HashMap
 */

public class HashUserSet extends AbstractUserSet {

	private static final long serialVersionUID = -2726631074986038792L;

	@Override
	public User getUser(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<User> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


}
