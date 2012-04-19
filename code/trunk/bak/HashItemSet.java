package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class implements the <tt>ItemSet</tt> interface for a set of <tt>Item</tt>
 * objects, backed by a hash table(actually a <tt>HashMap</tt> instance). It
 * makes no guarantees as to the iteration order of the set. This class does not
 * permit the <tt>null</tt> element or duplicate elements. More formally, a
 * <tt>ItemSet</tt> object contains no pair of <tt>Item</tt> objects
 * <code>u1</code> and <code>u2</code> such that <code>u1.equals(u2)</code>. It
 * allows to index a <tt>Item</tt> object though the <code>itemId</code> using the
 * method <code>getItem(int itemId)</code>.<p>
 * It is similar with <tt>HashSet</tt>, but it does not permit the null element and can index Item by itemId.  
 * 
 * 
 * @version 1.0 2012-4-18
 * @author tanchang
 * @since JDK 1.7
 * @see Set
 * @see HashSet
 * @see HashMap
 */

public class HashItemSet extends AbstractItemSet implements ItemSet, Set<Item>, Cloneable, java.io.Serializable{

	private static final long serialVersionUID = -2726631074986038792L;
	
	private transient HashMap<Integer, Item> map;
	
    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashItemSet() {
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
    public HashItemSet(Collection<? extends Item> c) {
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
    public HashItemSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }
    
    /**
     * Returns an iterator over the elements in this set.  The elements
     * are returned in no particular order.
     *
     * @return an Iterator over the elements in this set
     * @see ConcurrentModificationException
     */
    public Iterator<Item> iterator() {
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
     * Adds the specified element to this set if it is not null or already present.
     * If this set already contains the element or the element is null, the call leaves the set
     * unchanged and returns <tt>false</tt>.
     *
     * @param e element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     * element and the specified element is not null
     */
    public boolean add(Item e) {
    	if(e == null)return false;
        return map.put(e.getItemId(), e)==null;
    }
    
    /**
     * Removes the specified element from this set if it is present.
	 * Returns <tt>true</tt> if
     * this set contained the element (or equivalently, if this set
     * changed as a result of the call).  (This set will not contain the
     * element once the call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return <tt>true</tt> if the set contained the specified element
     */
    public boolean remove(Object o) {
    	if(o instanceof Item){
    		return map.remove(((Item) o).getItemId()) == o;
    	}
        return false;
    }
    
    /**
     * Removes all of the elements from this set.
     * The set will be empty after this call returns.
     */
    public void clear() {
        map.clear();
    }
    
    /**
     * Returns a shallow copy of this <tt>HashItemSet</tt> instance: the elements
     * themselves are not cloned.
     *
     * @return a shallow copy of this set
     */
    public Object clone() {
        try {
            HashItemSet newSet = (HashItemSet) super.clone();
            newSet.map = (HashMap<Integer, Item>) map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    /**
     * Save the state of this <tt>HashItemSet</tt> instance to a stream (that is,
     * serialize it).
     *
     * @serialData The size of the set (the number of elements it contains)
     *             (int), followed by all of its elements (each an Object) in
     *             no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(map.size());

        // Write out all elements in the proper order.
        for (Item e : map.values())
            s.writeObject(e);
    }

    /**
     * Reconstitute the <tt>HashSet</tt> instance from a stream (that is,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Create backing HashMap with default initial capacity (16) and load factor (0.75)
        map = new HashMap<Integer, Item>();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            Item e = (Item) s.readObject();
            map.put(e.getItemId(), e);
        }
    }

	@Override
	public Item getItem(int itemId) {
		return map.get(itemId);
	}

}
