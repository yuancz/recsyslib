package commons.collections.bag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import commons.collections.Bag;

public class HashBag<E> implements Bag<E> {

	private transient HashMap<E, Integer> map;
	
	public HashBag(){
		map = new HashMap<>();
	}
	
	public HashBag(Collection<? extends E> c){
		super();
		addAll(c);
	}
	
	@Override
	public boolean add(E e) {
		return add(e,1);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for(E e : c){
			if(!add(e,1))return false;
		}
		return true;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if(c instanceof Bag){
			return containsAll(c);
		}
		else return containsAll(new HashBag(c));
	}
	
	boolean containsAll(Bag<?> bag) {
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
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> uniqueSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E element, int n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCount(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
