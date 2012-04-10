package util;

import java.util.AbstractSet;
import java.util.Set;


/**
 * This class implements the <tt>Set</tt> interface for a set of <tt>User</tt> objects, 
 * backed by a hash table(actually a <tt>HashMap</tt> instance).  
 * It makes no guarantees as to the iteration order of the set.
 * This class does not permit the <tt>null</tt> element or duplicate elements.
 * More formally, a <tt>UserSet</tt> object contains no pair of <tt>User</tt> objects <code>u1</code> and <code>u2</code> such that <code>u1.equals(u2)</code>. 
 * It is similar with <tt>HashSet</tt>, but it allows to index a <tt>User</tt> object using the method <code>getUser(int uid)</code>. 
 * 
 * @version 1.0 2012-4-10
 * @author tanchang
 * @since JDK 1.7
 * @see Set, HashSet, HashMap
 */
public abstract class AbstractUserSet extends AbstractSet<User> implements UserSet, Cloneable, java.io.Serializable{

	private static final long serialVersionUID = -394176332765457158L;






}
