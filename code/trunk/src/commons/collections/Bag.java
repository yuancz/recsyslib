package commons.collections;

import java.util.Collection;
import java.util.Set;

public interface Bag<E> extends Collection<E>{
	/**
	 * Adds n copies of the specified element into this bag. 
	 * @return false if can not add
	 */
	public abstract boolean add(E element, int n);
	public abstract int getCount(Object o);
	public abstract Set<E> uniqueSet();
}
