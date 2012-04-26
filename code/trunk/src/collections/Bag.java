package collections;

import java.util.Collection;
import java.util.Set;

public interface Bag<E> extends Collection<E>{
	/**
	 * Adds n copies of the specified element into this bag. 
	 * @return false if n<0
	 */
	public abstract boolean add(E e, int n);
	/**
	 * Removes n copies of the specified object from this bag. 
	 * @return false if <code>n<0 || !contains(o) || n>getCount(o)</code>
	 */
	public abstract boolean remove(Object o, int n);
	/**
	 * Removes all copies of the specified object from this bag. 
	 * @return false if <code>!contains(o)</code>
	 */
	public abstract boolean removeAllCopies(Object o);
	/**
	 * @return true if <code>n<=getCount(o)</code>
	 */
	public boolean contains(Object o, int n);
	/**
	 * @return 0 if the object was not already in the <code>uniqueSet</code>
	 */
	public abstract int getCount(Object o);
	/**
	 * @return a shallow copy of the <code>uniqueSet</code> of this bag
	 */
	public abstract Set<E> uniqueSet();
}
