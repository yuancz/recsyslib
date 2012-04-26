package collections;

import java.util.Collection;
import java.util.HashMap;


public class HashBag<E> extends AbstractBag<E> implements Bag<E> {

	public HashBag() {
		super();
	}

	public HashBag(Collection<? extends E> c) {
		super(c);
	}

	@Override
	protected void createInnerMap() {
		map = new HashMap<>();		
	}


}
