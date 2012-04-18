package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class implements the <tt>UserSet</tt> interface for a set of <tt>User</tt>
 * objects, backed by a hash table(actually a <tt>HashMap</tt> instance). It
 * makes no guarantees as to the iteration order of the set. This class does not
 * permit the <tt>null</tt> element or duplicate elements. More formally, a
 * <tt>UserSet</tt> object contains no pair of <tt>User</tt> objects
 * <code>u1</code> and <code>u2</code> such that <code>u1.equals(u2)</code>. It
 * allows to index a <tt>User</tt> object though the <code>uid</code> using the
 * method <code>getUser(int uid)</code>.<p>
 * It is similar with <tt>HashSet</tt>, but it does not permit the null element and can index User by uid.  
 * 
 * 
 * @version 1.0 2012-4-18
 * @author tanchang
 * @since JDK 1.7
 * @see Set
 * @see HashSet
 * @see HashMap
 */

public class HashUserSet extends AbstractUserSet implements UserSet, Set<User>, Cloneable, java.io.Serializable{

	private static final long serialVersionUID = -2726631074986038792L;
	
	private transient HashMap<Integer, User> map;
	
    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashUserSet() {
        map = new HashMap<>();
    }
    
    /**
     * Constructs a new set containing the elements in the specified
     * collection.  The <tt>HashMap</tt> is created with default load factor
     * (0.75) and an initial capacity sufficient to contain the elements in
     * the specified collection.
     *
     * @param c the collection whose elements are to be placed into this set
     * @throws NullPointerException if the specified collection is null
     */
    public HashUserSet(Collection<? extends User> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    public HashUserSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }
    
    /**
     * Returns an iterator over the elements in this set.  The elements
     * are returned in no particular order.
     *
     * @return an Iterator over the elements in this set
     * @see ConcurrentModificationException
     */
    public Iterator<User> iterator() {
        return map.values().iterator();
    }
    
    /**
     * Returns the number of elements in this set (its cardinality).
     *
     * @return the number of elements in this set (its cardinality)
     */
    public int size() {
        return map.size();
    }
    
    /**
     * Returns <tt>true</tt> if this set contains no elements.
     *
     * @return <tt>true</tt> if this set contains no elements
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this set
     * contains an element <tt>e</tt> such that
     * <tt>(o==null ? e==null : o.equals(e))</tt>.
     *
     * @param o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contains the specified element
     */
    public boolean contains(Object o) {
        return map.containsValue(o);
    }
    
    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element <tt>e</tt> to this set if
     * this set contains no element <tt>e2</tt> such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns <tt>false</tt>.
     * The element should not be null. 
     *
     * @param e element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     * element and the specified element is not null
     */
    public boolean add(User e) {
    	if(e == null)return false;
        return map.put(e.getUid(), e)==null;
    }
    

	@Override
	public User getUser(int uid) {
		return map.get(uid);
	}

}
