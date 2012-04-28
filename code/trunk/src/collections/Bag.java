package collections;

import java.util.Collection;
import java.util.Set;

/**
 * Defines a collection that counts the number of times an object appears in the collection. 
 * It mainly consults org.apache.commons.collections.Bag, but is not identical.
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Bag<E> extends Collection<E>{
	
	/**
	 * Adds n copies of the specified element into this bag. 
	 * Returns false if n<0. 
	 */
	public abstract boolean add(E e, int n);
	
	/**
	 * Removes n copies of the specified object from this bag. 
	 * Returns false if <code>n<0 || !contains(o) || n>getCount(o)</code>.
	 */
	public abstract boolean remove(Object o, int n);
	
	/**
	 * Removes all copies of the specified object from this bag. 
	 * Returns false if <code>!contains(o)</code>
	 */
	public abstract boolean removeAllCopies(Object o);
	
	/**
	 * Returns true if <code>n<=getCount(o)</code>
	 */
	public boolean contains(Object o, int n);
	
	/**
	 * Returns the number of occurrences (cardinality) of the given object currently in the bag. 
	 * Returns 0 if the object was not already in the <code>uniqueSet</code>
	 */
	public abstract int getCount(Object o);
	
	/**
	 * Returns a shallow copy of the Set of unique elements in this Bag. 
	 */
	public abstract Set<E> uniqueSet();
}
