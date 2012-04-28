package collections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class provides a skeletal implementation of the <tt>Bag</tt> interface 
 * to minimize the effort required to implement this interface.
 * It is backed by a <tt>Map<E, Integer></tt> instance. 
 * To implement a bag, the programmer needs only to extend this class and 
 * provide implementations for the createInnerMap methods. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see Bag
 * @see Map
 */
public abstract class AbstractBag<E> implements Bag<E> {

	protected transient Map<E, Integer> map;
	protected transient int size;
	protected transient int modCount;

	public AbstractBag() {
		createInnerMap();
		size = 0;
		modCount = 0;
	}

	public AbstractBag(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * Creates the backing map
	 */
	protected abstract void createInnerMap();

	@Override
	public boolean add(E e) {
		return add(e, 1);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e : c)
			if(!add(e))return false;
		return true;
	}

	@Override
	public boolean add(E e, int n) {
		if(n<0)return false;
		if (!map.containsKey(e))
			map.put(e, 0);
		int count = getCount(e) + n;
		map.put(e, count);
		size += n;
		modCount++;
		return true;
	}

	@Override
	public void clear() {
		size = 0;
		modCount++;
		map.clear();
	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object o, int n) {
		if(n<0 || !contains(o))return false;
		E e = (E)o;
		int count = getCount(e) - n;
		if(count<0)return false;
		if(count == 0)map.remove(e);
		else map.put(e, count);
		size -= n;
		modCount++;
		return true;
	}
	
	@Override
	public boolean remove(Object o) {
		return remove(o, 1);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object o : c){
			if(!remove(o))return false;
		}
		return true;
	}
	
	@Override
	public boolean removeAllCopies(Object o){
		if(!contains(o))return false;
		return remove(o, getCount(o));
	}
	
    /**
     * Remove any elements of this bag that are not in the given
     * collection, respecting cardinality.
     * @return true if this call changed this bag
     */
	@Override
	public boolean retainAll(Collection<?> c) {
        if (c instanceof Bag) {
            return retainAll((Bag<?>) c);
        }
        return retainAll(new HashBag<>(c));
    }

    @SuppressWarnings("unchecked")
	protected boolean retainAll(Bag<?> bag) {
    	HashBag<E> removedBag = new HashBag<>();
    	Set<E> removed = uniqueSet();
    	removed.removeAll(bag.uniqueSet());
        for(E e : removed){
        	removedBag.add(e, getCount(e));
        }// remove not existing in bag from this
    	for(Object o : bag.uniqueSet()){
    		int diff = getCount(o) - bag.getCount(o);
    		if(diff > 0){
    			removedBag.add((E)o, diff);
    		}
    	}// for existing in both bag, remove more than another bag from this
    	//if in another bag is more than in this, do nothing
        return removeAll(removedBag);
    }
	
	public static class BagIterator<E> implements Iterator<E> {
		
		private AbstractBag<E> parent;
		private final int mods;
		private Iterator<E> uniqueSetIterator;
		private E current;
		private int itemCount;
		private boolean canRemove;

		public BagIterator(AbstractBag<E> bag) {
			parent = bag;
			mods = bag.modCount;
			uniqueSetIterator = bag.uniqueSet().iterator();
			current = null;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			return (itemCount>0 || uniqueSetIterator.hasNext());
		}

		@Override
		public E next() {
            if (parent.modCount != mods) {
                throw new ConcurrentModificationException();
            }
            if (itemCount == 0) {
                current = uniqueSetIterator.next();
                itemCount = parent.getCount(current);
            }
            canRemove = true;
            itemCount--;
            return current;
		}

		@Override
		public void remove() {
            if (parent.modCount != mods) {
                throw new ConcurrentModificationException();
            }
            if (canRemove == false) {
                throw new IllegalStateException();
            }
            parent.remove(current, 1);
            canRemove = false;
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new BagIterator<E>(this);
	}

	@Override
	public boolean contains(Object o) {
		return uniqueSet().contains(o);
	}
	
	@Override
	public boolean contains(Object o, int n) {
		return getCount(o)>=n;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if(c instanceof Bag)return containsAll(c);
		return containsAll(new HashBag<>(c));
	}
	
	protected boolean containsAll(Bag<?> bag){
		for(Object o : bag.uniqueSet()){
			if(getCount(o) < bag.getCount(o))return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int getCount(Object o) {
		Integer count = map.get(o);
		if (count == null)
			return 0;
		return count.intValue();
	}

	@Override
	public Set<E> uniqueSet() {
		return new HashSet<>(map.keySet());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Bag))
			return false;
		Bag<?> bag = (Bag<?>) obj;
		if (!uniqueSet().equals(bag.uniqueSet()))
			return false;
		for (E e : uniqueSet()) {
			if (getCount(e) != bag.getCount(e))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public String toString() {
		return map.toString();
	}
	
	@Override
	public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
        	array[i] = it.next();
            i++;
        }
        return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] array) {
        int size = size();
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        }
        int i = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
        	array[i] = (T) it.next();
            i++;
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
	}

}
