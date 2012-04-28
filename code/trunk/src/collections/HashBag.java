package collections;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class implements the <tt>Bag</tt> interface, backed by a <tt>HashMap<E, Integer></tt> instance. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 */
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
